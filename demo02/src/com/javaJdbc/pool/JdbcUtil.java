package com.javaJdbc.pool;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcUtil {
    private static DataSource dataSource;
    static {
        try {
            dataSource = ConnectionPool.getConectionPool();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 获取连接对象
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static int getPoolSize(){
        return ConnectionPool.getPoolsize();
    }

    // 关闭连接池对象
    public static void close(Connection conn, Statement st) throws SQLException {
        close(conn,st,null);
    }

    public static void close(Connection conn, Statement st, ResultSet rs) throws SQLException {
        if(rs != null){
            rs.close();
        }
        if(st != null){
            st.close();
        }
        if(conn != null){
            conn.close();

        }
    }
}
