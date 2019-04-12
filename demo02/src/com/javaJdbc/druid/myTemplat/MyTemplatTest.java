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

        Double query =  jdbcTemplate.query(sql, Double.class);
        System.out.println(query);




    }





}










