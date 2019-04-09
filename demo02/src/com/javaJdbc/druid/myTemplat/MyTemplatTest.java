package com.javaJdbc.druid.myTemplat;

import com.javaJdbc.druid.JdbcUtil;

import java.sql.SQLException;

/*
测试 JdbcTemplate Test
 */
public class MyTemplatTest {
    public static void main(String[] args) throws SQLException {
        MyTemplate jdbcTemplate = new MyTemplate(JdbcUtil.getDataSource());
        String sql = "insert into studen value(0,?,?,?)";
        jdbcTemplate.update(sql,"周八",22,1);
    }
}
