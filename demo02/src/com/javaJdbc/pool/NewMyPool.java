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

    private  boolean status = true;

    public NewMyPool(Connection conn, LinkedList<Connection> conections) {
        this.conn = conn;
        this.conections = conections;
    }

    public void setStatus(){
        status=true;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        /*
        String methodName = method.getName();
        if("createStatement".equals(methodName)){
            return createStatement();
        }else if("prepareStatement".equals(methodName)){
            return prepareStatement((String) args[0]);
        }else if("close".equals(methodName)){
            close();
            return null;
        }else{
           return method.invoke(conn, args);
        }*/
        /*String methodName = method.getName();
        Method[] sqlMethods = getClass().getDeclaredMethods();
        for (Method sqlMethod : sqlMethods) {
            if(sqlMethod.getName().equals(methodName)){

                return sqlMethod.invoke(this, args);
            }
        }
        System.out.println("没有执行");
        return method.invoke(conn,args);*/

        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        try{

            Object invoke = this.getClass().getDeclaredMethod(methodName, parameterTypes).invoke(this, args);
            return invoke;
        }catch (NoSuchMethodException e){
            System.out.println("非代理方法");
            return method.invoke(conn,args);
        }catch (Exception e){
            throw new SQLException("代理异常");
        }


    }

    private Statement createStatement() throws SQLException {
        if(!status){
            throw new SQLException( " You can't operate on a closed Connection!!!");
        }
        return conn.createStatement();
    }

    private PreparedStatement prepareStatement(String sql) throws SQLException {
        if(!status){
            throw new SQLException( " You can't operate on a closed Connection!!!");
        }
        return conn.prepareStatement(sql);
    }

    private void close() throws SQLException {
        if(conections.contains(this.conn)){
            throw new SQLException();
        }else{
            conections.add(this.conn);
            status = false;
        }
    }

}


