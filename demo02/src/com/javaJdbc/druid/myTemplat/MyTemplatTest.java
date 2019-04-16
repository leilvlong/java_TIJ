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
        String sql = "select gender from studen where id=10";

/*       Long query1 =  jdbcTemplate.query(sql, Long.class);
        Integer query2 =  jdbcTemplate.query(sql, Integer.class);
        Float query3 =  jdbcTemplate.query(sql, Float.class);
        Double query4 =  jdbcTemplate.query(sql, Double.class);
        String query5 =  jdbcTemplate.query(sql, String.class);
       System.out.println("LONG :"+query1);
       System.out.println("INTEGER :"+query2);
       System.out.println("FLOAT :"+query3);
       System.out.println("DOUBLE :"+query4);
       System.out.println("STRING :"+query5);*/

        Long query1 =  jdbcTemplate.query(sql, Long.class);
        Integer query2 =  jdbcTemplate.query(sql, Integer.class);
        Float query3 =  jdbcTemplate.query(sql, Float.class);
        Double query4 =  jdbcTemplate.query(sql, Double.class);
        String query5 =  jdbcTemplate.query(sql, String.class);
        System.out.println("LONG :"+query1);
        System.out.println("INTEGER :"+query2);
        System.out.println("FLOAT :"+query3);
        System.out.println("DOUBLE :"+query4);
        System.out.println("STRING :"+query5);
    }





}










