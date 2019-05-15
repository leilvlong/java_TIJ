package com.jedis.redis.reidspool;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.*;
/*
所谓的json就是将java中的map数据结构给toString了
真正难的是在java中解析json
 */
public class JedisUtilTest {
    public static Jedis jcpu = JedisContentPoolUtil.getPoolJedisResoures();
    public static Gson gs = new Gson();

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        //fun1();
        fun2();
        //fun3();
    }

/*    //将javaBean对象转为Json字符串对象存入redis 再取出转为javaBean对象
    public static void fun1() throws IllegalAccessException {
        StudenBean stu = new StudenBean(1, "tom", "man");
        *//*
        String jsonStu = gs.toJson(stu);
        jcpu.set("studen",jsonStu);
        String studen = jcpu.get("studen");
        StudenBean studenBean = gs.fromJson(studen, StudenBean.class);
        System.out.println(studen);
        System.out.println(studenBean);
        *//*

        Map<String,Object> stuMap = new LinkedHashMap<>();
        Field[] fields = stu.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Object o = field.get(stu);
            stuMap.put(name,o);
        }
        //String jsonStu = stuMap.toString();
        String jsonStu = String.valueOf(stuMap);
        jcpu.set("studen",jsonStu);
        String studen = jcpu.get("studen");
        StudenBean studenBean = gs.fromJson(studen, StudenBean.class);
        System.out.println(studen);
        System.out.println(studenBean);

        jcpu.close();

    }*/

    // 将多个对象存入到redis里面
    public static void fun2() throws IllegalAccessException {
        StudenBean stu1 = new StudenBean(1, "tom1", "man");
        StudenBean stu2 = new StudenBean(2, "tom2", "man");
        StudenBean stu3 = new StudenBean(3, "tom3", "man");
        List<StudenBean> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        stus.add(stu3);


        /*String jsonStus = gs.toJson(stus);
        jcpu.set("stus",jsonStus);
        String stusStr = jcpu.get("stus");
        Type stu = new TypeToken<List<StudenBean>>(){}.getType();
        List<StudenBean> o = gs.fromJson(stusStr, stu);
        System.out.println(o);*/


        List<String> linkStus = new LinkedList<>();
        for (StudenBean stu : stus) {
            Map<String,Object> stuMap = new LinkedHashMap<>();
            Field[] fields = stu.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fileName = field.getName();
                Object o = field.get(stu);
                stuMap.put("\""+fileName+"\"","\""+o+"\"");
            }
            String s = stuMap.toString();
            String replace = s.replace("=", ":");
            linkStus.add(replace);
        }

        String s = linkStus.toString();
        System.out.println(s);
        jcpu.set("stus",s);
        String stusStr = jcpu.get("stus");
        Type stu = new TypeToken<List<StudenBean>>(){}.getType();
        List<StudenBean> o = gs.fromJson(stusStr, stu);
        System.out.println(o);

        jcpu.close();
    }

    //尝试解析json
    public static void fun3() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        StudenBean stu = new StudenBean();
        stu.setId(1);
        stu.setGender("man");
        stu.setName("lucy");
        // java对象转json
        Map<String,Object> stuMap = new LinkedHashMap<>();
        Field[] fields = stu.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Object o = field.get(stu);
            stuMap.put(name,o);
        }
        String jsonStu = String.valueOf(stuMap);
        jcpu.set("studen",jsonStu);
        String studen = jcpu.get("studen");

        //解析json
        String substring = studen.substring(1, studen.length() - 1);
        String[] filed = substring.split(", ");
        Map<String,Object> map = new HashMap<>();
        for (String s : filed) {
            String[] file = s.split("=");
            map.put(file[0],file[1]);
        }

        // 将json转换为对象
        // 获取所有属性
        Field[] fields1 = StudenBean.class.getDeclaredFields();

        // 获取所有属性类型
        Class[] classes = new Class[fields1.length];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = fields1[i].getType();
        }

        // 获取构造器
        Constructor<StudenBean> studenBeanConstructor = null;
        try{
            studenBeanConstructor = StudenBean.class.getDeclaredConstructor();
        }catch (NoSuchMethodException e){
            studenBeanConstructor = StudenBean.class.getDeclaredConstructor(classes);
        }catch (Exception e){
            throw new RuntimeException("构造器异常");
        }

        // 获取所有值
        Object[] values = new Object[fields1.length];
        int i = 0;
        for (Field field : fields1) {
            field.setAccessible(true);
            String name = field.getName();
            Class<?> type = field.getType();
            Object value = map.get(name);
            Object o = type.getDeclaredConstructor(value.getClass()).newInstance(value);
            values[i++] = o;
        }

        // 判断是空参构造还是有参构造
        StudenBean studenBean = null;
        try{
            studenBean = studenBeanConstructor.newInstance(values);
        }catch (IllegalArgumentException e){
            studenBean = studenBeanConstructor.newInstance();
            int y = 0;
            for (Field field : fields1) {
                field.setAccessible(true);
                field.set(studenBean,values[y++]);
            }
        }
        System.out.println(studenBean.toString());

        //System.out.println(studen);
        //System.out.println(studenBean);

        jcpu.close();
    }



}
