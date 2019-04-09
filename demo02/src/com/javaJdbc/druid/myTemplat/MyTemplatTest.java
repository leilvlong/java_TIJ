package com.javaJdbc.druid.myTemplat;

import com.javaJdbc.druid.JdbcUtil;

import javax.swing.tree.RowMapper;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/*
测试 JdbcTemplate Test
 */
public class MyTemplatTest {
    public static void main(String[] args) throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {
        /*MyTemplate jdbcTemplate = new MyTemplate(JdbcUtil.getDataSource());
        String sql = "insert into studen value(0,?,?,?)";
        jdbcTemplate.update(sql,"周八",22,1);*/

        MyTemplate jdbcTemplate = new MyTemplate(JdbcUtil.getDataSource());
        String sql = "select age from studen where id=1";

        System.out.println(jdbcTemplate.query(sql, String.class));


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
