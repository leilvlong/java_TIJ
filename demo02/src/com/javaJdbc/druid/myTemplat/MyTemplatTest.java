package com.javaJdbc.druid.myTemplat;

import com.javaJdbc.druid.JdbcUtil;
import org.springframework.jdbc.core.SingleColumnRowMapper;

import javax.swing.tree.RowMapper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.sql.SQLException;
import java.util.Arrays;

/*
测试 JdbcTemplate Test
 */
public class MyTemplatTest {
    public static void main(String[] args) throws Exception {
        /*MyTemplate jdbcTemplate = new MyTemplate(JdbcUtil.getDataSource());
        String sql = "insert into studen value(0,?,?,?)";
        jdbcTemplate.update(sql,"周八",22,1);*/


        MyTemplate jdbcTemplate = new MyTemplate(JdbcUtil.getDataSource());

        /*String sql1 = "select age from studen where id=?";
        Long query1 = jdbcTemplate.query(sql1, Long.class);
        Integer query2 = jdbcTemplate.query(sql1, Integer.class);
        Float query3 = jdbcTemplate.query(sql1, Float.class);
        Double query4 = jdbcTemplate.query(sql1, Double.class);
        String query5 = jdbcTemplate.query(sql1, String.class);
        System.out.println("LONG :" + query1);
        System.out.println("INTEGER :" + query2);
        System.out.println("FLOAT :" + query3);
        System.out.println("DOUBLE :" + query4);
        System.out.println("STRING :" + query5);*/

        /*String sql2 = "select age from studen where id=?";
        Long query1 =  jdbcTemplate.query(sql2, Long.class,1);
        Integer query2 =  jdbcTemplate.query(sql2, Integer.class,1);
        Float query3 =  jdbcTemplate.query(sql2, Float.class,1);
        Double query4 =  jdbcTemplate.query(sql2, Double.class,1);
        String query5 =  jdbcTemplate.query(sql2, String.class,1);
        System.out.println("LONG :"+query1);
        System.out.println("INTEGER :"+query2);
        System.out.println("FLOAT :"+query3);
        System.out.println("DOUBLE :"+query4);
        System.out.println("STRING :"+query5);*/

        String sql3 = "select * from studen where id=1 ";
        System.out.println(jdbcTemplate.queryForObject(sql3, Studen.class));

        String sql4 = "select * from studen  ";
        System.out.println(jdbcTemplate.queryForObjects(sql4, Studen.class));

    }





}










