package com.javaJdbc.pool;


import java.sql.*;
/*
自定义数据库连接池最难点:
    close
    我目前所使用的都是最为简单粗暴的方法实现并解决问题
 */
public class JdbcTest {
    public static void main(String[] args) throws SQLException, InterruptedException {
        // 测试连接池 以及等待超时功能能否正常发挥作用
        //fun1();

        // 解决一个对象可以反复回到连接池
        //fun2();

        // 解决连接池关闭后依然可用
        //fun3();

        // 基本性能测试
        fun4();

    }

    /**
     * 测试连接池是否可用
     * 以及测试最大连接等待时间
     * 未使用线程
     * @throws SQLException
     */
    public static void fun1() throws SQLException {
        /*Connection conn1 = JdbcUtil.getConnection();
        Connection conn2 = JdbcUtil.getConnection();
        Connection conn3 = JdbcUtil.getConnection();
        Connection conn4 = JdbcUtil.getConnection();
        Connection conn5 = JdbcUtil.getConnection();
        System.out.println(JdbcUtil.getPoolSize());
        conn5.close();
        System.out.println(JdbcUtil.getPoolSize());*/
        Connection conn1 = JdbcUtil.getConnection();
        Connection conn2 = JdbcUtil.getConnection();
        Connection conn3 = JdbcUtil.getConnection();
        Connection conn4 = JdbcUtil.getConnection();
        Connection conn5 = JdbcUtil.getConnection();
        long start = System.currentTimeMillis();
        try{
            Connection conn6 = JdbcUtil.getConnection();
        }finally {
            System.out.println((System.currentTimeMillis() - start)/1000);
            conn1.close();
            conn2.close();
            conn3.close();
            conn4.close();
            conn5.close();
        }


    }

    /**
     * 解决连接池一个连接对象可以反复close回到连接池容器的问题
     * 该问题会导致连接池容器膨胀
     * 根本问题连接对象关闭后连接对象依然可用未解决
     * @throws SQLException
     */
    public static void fun2() throws SQLException{
        String sql = "select * from studen where id=?";
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,1);
        ResultSet rs = ps.executeQuery();
        rs.next();
        System.out.println("学生名字为: "+ rs.getString("name"));
        JdbcUtil.close(conn,ps,rs);
        JdbcUtil.close(conn,ps,rs);

        PreparedStatement ps2 = conn.prepareStatement(sql);
        ps2.setInt(1,2);
        ResultSet rs2 = ps2.executeQuery();
        rs2.next();
        System.out.println("学生名字为: "+ rs2.getString("name"));
        JdbcUtil.close(conn,ps2,rs2);


        Connection conn2 = JdbcUtil.getConnection();
        PreparedStatement ps3 = conn2.prepareStatement(sql);
        ps3.setInt(1,3);
        ResultSet rs3 = ps3.executeQuery();
        rs3.next();
        System.out.println("学生名字为: "+ rs3.getString("name"));
        JdbcUtil.close(conn2,ps3,rs3);
    }


    /**
     * 尝试解决关闭连接后对象依然可用的问题
     * 在关闭资源返回容器后将其连接池状态设置false 若试图继续使用抛出异常
     */
    public static void fun3() throws SQLException {
        String sql = "select * from studen where id=?";
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,1);
        ResultSet rs = ps.executeQuery();
        rs.next();
        System.out.println("学生名字为: "+ rs.getString("name"));

        JdbcUtil.close(conn,ps,rs);

        // 连接对象虽然close后回到连接池容器中,但连接对象依然存在,如何处理连接对象成为一个问题
        PreparedStatement ps2 = conn.prepareStatement(sql);
        ps2.setInt(1,2);
        ResultSet rs2 = ps2.executeQuery();
        rs2.next();
        System.out.println("学生名字为: "+ rs2.getString("name"));

    }

    public static void fun4() throws SQLException {
        String sql = "select * from studen where id=?";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            Connection conn1 = JdbcUtil.getConnection();
            Connection conn2 = JdbcUtil.getConnection();
            Connection conn3 = JdbcUtil.getConnection();
            Connection conn4 = JdbcUtil.getConnection();
            Connection conn5 = JdbcUtil.getConnection();

            conn1.createBlob();

            PreparedStatement ps1 = conn1.prepareStatement(sql);
            PreparedStatement ps2 = conn2.prepareStatement(sql);
            PreparedStatement ps3 = conn3.prepareStatement(sql);
            PreparedStatement ps4 = conn4.prepareStatement(sql);
            PreparedStatement ps5 = conn5.prepareStatement(sql);

            ps1.setInt(1,1);
            ps2.setInt(1,2);
            ps3.setInt(1,3);
            ps4.setInt(1,4);
            ps5.setInt(1,5);

            ResultSet rs1 = ps1.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
            ResultSet rs3 = ps3.executeQuery();
            ResultSet rs4 = ps4.executeQuery();
            ResultSet rs5 = ps5.executeQuery();

            rs1.next();
            rs2.next();
            rs3.next();
            rs4.next();
            rs5.next();

            System.out.println("学生名字为: "+ rs1.getString("name"));
            System.out.println("学生名字为: "+ rs2.getString("name"));
            System.out.println("学生名字为: "+ rs3.getString("name"));
            System.out.println("学生名字为: "+ rs4.getString("name"));
            System.out.println("学生名字为: "+ rs5.getString("name"));

            JdbcUtil.close(conn1,ps1,rs1);
            JdbcUtil.close(conn2,ps2,rs2);
            JdbcUtil.close(conn3,ps3,rs3);
            JdbcUtil.close(conn4,ps4,rs4);
            JdbcUtil.close(conn5,ps5,rs5);
        }
        System.out.println(System.currentTimeMillis()-start);
    }
}
