package com.javaJdbc.pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/*
使用动态代理实现自定义连接池
 */
public class NewMyPool implements InvocationHandler {
    private Connection conn;
    private LinkedList<Connection> conections;

    private boolean status = true;

    public NewMyPool(Connection conn, LinkedList<Connection> conections) {
        this.conn = conn;
        this.conections = conections;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String methodName = method.getName();
        if("createStatement".equals(methodName)){
            Statement obj = createStatement();
            return obj;
        }else if("prepareStatement".equals(methodName)){
            PreparedStatement obj = prepareStatement((String) args[0]);
            return obj;
        }else if("close".equals(methodName)){
            close();
            return null;
        }else{
           return method.invoke(conn, args);

        }
    }

    public Statement createStatement() throws SQLException {
        if(status == false){
            throw new SQLException( " You can't operate on a closed Connection!!!");
        }
        return conn.createStatement();
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        if(status == false){
            throw new SQLException( " You can't operate on a closed Connection!!!");
        }
        return conn.prepareStatement(sql);
    }

    public void close() throws SQLException {
        if(conections.contains(this.conn)){
            throw new SQLException();
        }else{
            conections.add(this.conn);
            status = false;
        }
    }

}
