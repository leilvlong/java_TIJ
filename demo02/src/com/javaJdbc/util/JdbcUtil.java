package com.javaJdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {

    private static String DRIVERCLASS, URL, USERNAME, PASSWORD;
    private static Connection conn;

    static {
        // 获取属性集(数据库连接配置信息)
        Properties dataConfig = new Properties();
        InputStream ril = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbcinfo.properties");
        try {
            dataConfig.load(ril);
            DRIVERCLASS = dataConfig.getProperty("driverClass");
            URL = dataConfig.getProperty("url");
            USERNAME = dataConfig.getProperty("username");
            PASSWORD = dataConfig.getProperty("password");
            Class.forName(DRIVERCLASS);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ril.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private JdbcUtil(){
        System.out.println("该jdbc工具类不允许实例存在");
    }
    /**
     * @return 获取jdbc连接
     */
    public static Connection getConn() {
        return conn;
    }

}