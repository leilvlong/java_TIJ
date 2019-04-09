package com.javaJdbc.druid.myTemplat;


import javax.sql.DataSource;
import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.TypeVariable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
模拟JDBC TempLate
    Update(实现增删改操作)
    query(实现查询单数据(单条某列))
        真正的template的查询实现要比这复杂的多，
        会根据所传的 数据类型.class 来判断该查询
        数据是否可以转为需求的数据类型或该查询数
        据是否本身就是这种数据类型以及很多我尚未
        想到的点
            而我实现的是若查询的数据类型与传入的
        类型不符合就抛出异常
            如果需求类型是String应该是最好做的。
            Class.class.getName.equls(java.lang.String)
        String.valueOf就完事了
            如果要转为非String类型,可以尝试使用正则匹配
        matches,或者以抛出异常的方式来判断
还是实现一下试试看
我突然明白写框架的人为什么那么牛逼哄哄了:
    不满足我的要求与规则就抛异常给你看
 */
public class MyTemplate {
    private DataSource dataSource;

    public MyTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int update(String sql, Object... args) throws SQLException {
        //获取连接
        Connection conn = dataSource.getConnection();
        // 预编译sql语句
        PreparedStatement ps = conn.prepareStatement(sql);
        // 获取元选项并设置sql语句的参数
        ParameterMetaData psmd = ps.getParameterMetaData();
        int valueCount = psmd.getParameterCount();
        if (valueCount != args.length) {
            throw new SQLException("Value incoming error");
        }
        for (int i = 1; i <= valueCount; i++) {
            ps.setObject(i, args[i - 1]);
        }
        // 执行增删改等更新数据库操作
        int i = ps.executeUpdate();
        // 关闭资源
        ps.close();
        conn.close();

        // 返回受影响行数
        return i;
    }

/*    public <T> T query(String sql, Class<T> type, Object ...args) throws SQLException,IllegalAccessException, InstantiationException {
        //获取连接
        Connection conn = dataSource.getConnection();
        // 预编译sql语句
        PreparedStatement ps = conn.prepareStatement(sql);

        // 获取元选项并设置sql语句的参数
        ParameterMetaData psmd = ps.getParameterMetaData();
        int valueCount = psmd.getParameterCount();
        if (valueCount != args.length) {
            throw new SQLException("Value incoming error");
        }
        if (args.length != 0) {
            for (int i = 1; i <= valueCount; i++) {
                ps.setObject(i, args[i - 1]);
            }
        }

        // 对查询集结果处理
        T t = type.newInstance();
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            Object object = rs.getObject(1);
            if(object.getClass().getName().equals(type.getName())){
                t= (T) object;
            }else{
                throw new RuntimeException("return type error");
            }
        }
        return t;
    }*/

    public <T> T query(String sql, Class<T> type, Object ...args) throws SQLException,IllegalAccessException, InstantiationException {
        //获取连接
        Connection conn = dataSource.getConnection();
        // 预编译sql语句
        PreparedStatement ps = conn.prepareStatement(sql);

        // 获取元选项并设置sql语句的参数
        ParameterMetaData psmd = ps.getParameterMetaData();
        int valueCount = psmd.getParameterCount();
        if (valueCount != args.length) {
            throw new SQLException("Value incoming error");
        }
        if (args.length != 0) {
            for (int i = 1; i <= valueCount; i++) {
                ps.setObject(i, args[i - 1]);
            }
        }


        // 对查询集结果处理
        T t = null;
        ResultSet rs = ps.executeQuery();
        if (rs.next()){

            Object object = rs.getObject(1);

            if(object.getClass().getName().equals(type.getName())){
                // 如果需求与查询集同一类型 可以直接返回了
                t= (T) object;
            }else if(type.getName().equals("java.lang.String")){
                // 非字符串查询集转字符串
                t = (T) String.valueOf(object);
            }else if( object.getClass().equals("java.lang.String")){
                //字符串查询集转非字符串 若匹配不上可选项 抛出异常
                Object conversion = isConversion((String) object);
                t= (T) conversion;
            }else{
                //非字符串之间的转换 能转则转 不能转抛异常
                try{
                    t= (T) object;
                }catch (Exception e){
                    throw new RuntimeException("Get type error");
                }
            }
        }
        return t;
    }

    private Object isConversion(String str){
        //将传入的String 与他们做转换 成功则停止循环 返回true
        // 若所有的都转换失败 则抛出异常
        Object object;
        while(true) {
            try {
                object = Integer.parseInt(str);
                break;
            } catch (Exception e) {
            }

            try {
                object = Long.parseLong(str);
                break;
            } catch (Exception e) {
            }

            try {
                object = Float.parseFloat(str);
                break;
            } catch (Exception e) {
            }

            try {
                object = Double.parseDouble(str);
                break;
            } catch (Exception e) {
            }
            throw new RuntimeException("Get type error");
        }
        return object;
    }

}

