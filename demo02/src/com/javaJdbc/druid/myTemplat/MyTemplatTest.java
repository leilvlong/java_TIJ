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

        SingleColumnRowMapper<String> stringSingleColumnRowMapper = new SingleColumnRowMapper<>(String.class);

        /*MyTemplate jdbcTemplate = new MyTemplate(JdbcUtil.getDataSource());
        String sql = "select gender from studen where id=10";

        String query = jdbcTemplate.query(sql, String.class);
        System.out.println(query);*/



    }

    public static void fun(String str){
        boolean bool;
        try{
        Integer.parseInt(str);
        bool = true;
        }catch (Exception e){
            bool = false;
        }
        System.out.println(bool);

    }
}
