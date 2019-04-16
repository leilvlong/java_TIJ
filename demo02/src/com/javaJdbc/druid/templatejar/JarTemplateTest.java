package com.javaJdbc.druid.templatejar;

import com.javaJdbc.druid.JdbcUtil;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
    public static void main(String[] args) {
        //fun1();
        //fun2();
        //fun3();
        fun4();
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
        System.out.println(jt.queryForList(sql2,1));
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
}
