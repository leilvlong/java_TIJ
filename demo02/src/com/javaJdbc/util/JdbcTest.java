package com.javaJdbc.util;

import java.sql.*;

/*
相较于Statement,PrepareStatement因为是将sql语句预编译会更加安全
可以防止sql注入
sql注入:
    在查询时,因为会对传入的查询属性与sql语句拼接所带来的问题
    以下案例将演示
    表数据结构与内容为:
CREATE TABLE `gameplays`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pasword` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- Records of gameplays

INSERT INTO `gameplays` VALUES (1, 'leione', 'leione');
INSERT INTO `gameplays` VALUES (2, 'leitwo', 'leitwo');
INSERT INTO `gameplays` VALUES (3, 'leithree', 'leithree');

SET FOREIGN_KEY_CHECKS = 1;

但是不得不说jdbc不怎么好用:
    大多操作只能对单表查询集操作
数据库连接因为是静态引用变量,所以不能随意关闭连接,
静态变量在运行时被共享一旦被关闭则其余地方无法使用,
同理一次只能有一个连接,若想获取多个则可以将连接非静态变量引用即可

 */


public class JdbcTest {
    public static void main(String[] args) throws SQLException {
        /*
        fun1在对传入的参数拼接后得到sql语句其为:
            select * from gameplays where name='leifsfe'and pasword='leiosdfe' or 1='1'
         */
        //fun1("leifsfe","leiosdfe' or 1='1");
        //fun2("leifsfe", "leiosdfe' or 1='1");
        //fun2("leione", "leione");
        fun3();

    }

    /**
     * @param username 查询条件1
     * @param password 查询条件2
     *                 通过 username password 拼接查询语句展示sql注入
     */
    public static void fun1(String username, String password) throws SQLException {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        conn = JdbcUtil.getConn();
        st = conn.createStatement();
        String sql = "select * from gameplays where name='" + username + "'and pasword='" + password + "'";
        System.out.println(sql);
        rs = st.executeQuery(sql);
        if (rs.next()) {
            System.out.println("userName: " + rs.getString("name"));
            System.out.println("userPassword: " + rs.getString("pasword"));
            System.out.println("userID: " + rs.getInt("id"));
        } else {
            System.out.println("空查询集");
        }

        rs.close();
        st.close();

    }

    /**
     * PrepareStatement解决sql注入问题
     */
    public static void fun2(String username, String password) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection conn = JdbcUtil.getConn();
        String sql = "select * from gameplays where name=? and pasword=? ";
        ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);

        rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("userName: " + rs.getString("name"));
            System.out.println("userPassword: " + rs.getString("pasword"));
            System.out.println("userID: " + rs.getInt("id"));
        } else {
            System.out.println("空查询集 注入失败");
        }
        //JdbcUtil.close(conn, ps, rs);
        rs.close();
        ps.close();

    }

    /**
     * 尝试解决关闭连接后对象依然可用的问题
     * 在关闭资源返回容器后将其引用设置为null
     */
    public static void fun3() throws SQLException {
        String sql = "select * from studen where id=?";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,1);
        ResultSet rs = ps.executeQuery();
        rs.next();
        System.out.println("学生名字为: "+ rs.getString("name"));

        rs.close();
        ps.close();
        conn.close();



    }
}
