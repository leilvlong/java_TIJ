package com.javaJdbc.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.debug.CloseLoggingComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
    private static DataSource dataSource;
    static {
        dataSource = new ComboPooledDataSource();
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void  close(Connection conn, Statement st, ResultSet rs) throws SQLException {
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

    public static void close(Connection conn, Statement st) throws SQLException {
        close(conn,st,null);
    }
}
