package com.javaJdbc.c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTest {
    public static void main(String[] args) throws SQLException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            String sql = "select * from studen where id=?";
            Connection conn1 = JdbcUtil.getConnection();
            PreparedStatement ps = conn1.prepareStatement(sql);
            ps.setInt(1,1);
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println("学生名字为: " + rs.getString("name"));

            Connection conn2 = JdbcUtil.getConnection();
            PreparedStatement ps2 = conn2.prepareStatement(sql);
            ps2.setInt(1,2);
            ResultSet rs2 = ps2.executeQuery();
            rs2.next();
            System.out.println("学生名字为: " + rs2.getString("name"));

            Connection conn3 = JdbcUtil.getConnection();
            PreparedStatement ps3 = conn3.prepareStatement(sql);
            ps3.setInt(1,3);
            ResultSet rs3 = ps3.executeQuery();
            rs3.next();
            System.out.println("学生名字为: " + rs3.getString("name"));

            Connection conn4 = JdbcUtil.getConnection();
            PreparedStatement ps4 = conn4.prepareStatement(sql);
            ps4.setInt(1,4);
            ResultSet rs4 = ps4.executeQuery();
            rs4.next();
            System.out.println("学生名字为: " + rs4.getString("name"));

            Connection conn5 = JdbcUtil.getConnection();
            PreparedStatement ps5 = conn5.prepareStatement(sql);
            ps5.setInt(1,4);
            ResultSet rs5 = ps5.executeQuery();
            rs5.next();
            System.out.println("学生名字为: " + rs5.getString("name"));

            JdbcUtil.close(conn1,ps,rs);
            JdbcUtil.close(conn2,ps2,rs2);
            JdbcUtil.close(conn3,ps3,rs3);
            JdbcUtil.close(conn4,ps4,rs4);
            JdbcUtil.close(conn5,ps5,rs5);

            PreparedStatement ps6 = conn5.prepareStatement(sql);
            ps6.setInt(1,4);
            ResultSet rs6 = ps6.executeQuery();
            rs6.next();
            System.out.println("学生名字为: " + rs6.getString("name"));
        }
        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000);


    }
}
