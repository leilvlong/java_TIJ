package com.javaJdbc.druid.templatejar;

import com.javaJdbc.druid.JdbcUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
/*
值与预编译sql语句中的?对应
upadte(sql,值)  返回有效修改数据条数

queryForObjct(sql,数据类型字节码) 返回单列单个查询数据

queryForMap(sql)  返回该条数据的Map集合Map<String,Object>
    列名为String key 数据为 Object value

queryForList 该方法有多个重载 但是返回值是将查询的多条数据放入List<>中返回
     List<T> 与 Lits<Map<String,Object>> 最为常用

query(sql,RowMapper)  该方法可将数据表对应的实体类设置好并获取对象返回到List<T>中

jdbc template 只需获取连接池对象并传入构造中即可使用

 */


public class JarTemplateTest {
    static JdbcTemplate jt = new JdbcTemplate(JdbcUtil.getDataSource());
    public static void main(String[] args) throws Exception {
        //fun1();
        //fun2();
        //fun3();
        //fun4();
        //fun5();
        fun6();

    }

    static void fun1(){
        String sql1 = "select gender from studen where id=1";
        System.out.println(jt.queryForObject(sql1, Double.class));

        /*String sql2 = "select name from studen where id=? and age=?";
        System.out.println(jt.queryForObject(sql2, String.class, 1, 19));*/

    }
    static void fun2(){
        String sql = "select * from studen where id=?";
        Map<String, Object> dataMap = jt.queryForMap(sql, 1);
        for (String s : dataMap.keySet()) {
            if(dataMap.get(s) instanceof Boolean){
                System.out.println(dataMap.get(s));
            }
        }

    }
    static void fun3(){
        // List<Map<String,Object>>
        String sql1 = "select * from studen";
        System.out.println(jt.queryForList(sql1));

        String sql2 = "select age from studen where id>?";
        List<Map<String, Object>> maps = jt.queryForList(sql2, 1);
    }

    static void fun4(){
        String sql1 = "select * from studen";
        List<Studen> studens = jt.query(sql1, new BeanPropertyRowMapper<>(Studen.class));
        for (Studen studen : studens) {
            System.out.println(studen);
        }

        String sql2 = "select * from studen where id=1";
        Studen studen = (Studen) jt.queryForObject(sql2,new BeanPropertyRowMapper<>(Studen.class));
        System.out.println(studen);
    }

    public static void fun5() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String sql = "select * from studen where id=?";
        //通过反射机制自己为为Stunde bean创建对象
        Map<String, Object> studen = jt.queryForMap(sql,1);
        Set<Map.Entry<String, Object>> entries = studen.entrySet();
        Object[] objects = new Object[entries.size()];
        int i = 0;
        for (Map.Entry<String, Object> entry : entries) {
            objects[i++] = entry.getValue();
        }
        Class<Studen> studenClass = Studen.class;
        Field[] fields = studenClass.getDeclaredFields();
        Class<?>[] parameterType = new Class<?>[fields.length];
        int z = 0;
        for (Field field : fields) {
            field.setAccessible(true);
            parameterType[z++] = field.getType();
        }
        Studen studen1 = studenClass.getDeclaredConstructor(parameterType).newInstance(objects);
        System.out.println(studen1);
    }

    public static void fun6(){
        // 该方法验证猜想 当我构造器里少一个构造参数时果然抛出异常
        // 在拥有指定构造器时, 使用反射机制创建实例 通过fiels 获取filed type数组
        // 将其放入Class构造器的参数列表中,将查询集放入数组中放入实例化方法中 若缺少参数必然抛出实例化异常
        String sql="select * from studen WHERE id=1";
        Studen query = jt.queryForObject(sql, new BeanPropertyRowMapper<>(Studen.class));
        System.out.println(query);
    }
}
