package com.javaJdbc.druid.myTemplat;


import javax.sql.DataSource;
import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.sql.*;
import java.util.*;

/*
模拟JDBC TempLate
    Update(实现增删改操作)
    query实现查询单数据返回 (sql,需求数据类型,sql条件)
        当需求数据类型与返回查询数据类型都非Sting时的转换未实现
 */
public class MyTemplate {
    private static Map<String,String> dataTypes = new HashMap<>();

    static{
        // 数据类型与对应的转换方法
        dataTypes.put(String.class.getName(),"valueOf");
        dataTypes.put(Long.class.getName(),"parseLong");
        dataTypes.put(Integer.class.getName(),"parseInt");
        dataTypes.put(Double.class.getName(),"parseDouble");
        dataTypes.put(Float.class.getName(),"parseFloat");
        dataTypes.put(Boolean.class.getName(),"parseBoolean");
    }


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

    public <T> T query(String sql, Class<T> type, Object ...args) throws Exception {
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
        ResultSet rs = ps.executeQuery();

        T data = getData(rs, type);

        return data;
    }


    private <T> T getData(ResultSet rs, Class<T> type) throws Exception {
        T data = null;
        if (rs.next()){

            data = (T) rs.getObject(1);

            //查询集数据类型与需求数据类型

            if(dataTypes.containsKey(type.getName())){
                while (true){
                    //如果参数与需求是同一类型 直接返回
                    if(data.getClass().getName().equals(type.getName())){
                        break;
                    }

                    //如果需求是String类型
                    if(type.getName().equals("java.lang.String")) {
                        try {
                            data = (T) String.valueOf(data);
                            break;
                        } catch (Exception e) {
                            throw new RuntimeException("GET TYPE ERROR");
                        }
                    }

                    //如果查询集为String,转为需求非String数据类型
                    if(data.getClass().getName().equals("java.lang.String")){
                        // 获取对应的包装类静态方法名与设置参数类型
                        String md = dataTypes.get(type.getName());
                        Method method = type.getMethod(md, data.getClass());
                        try {
                            data= (T) method.invoke(null,data);
                            break;
                        }catch (Exception e){
                            throw new RuntimeException("GET TYPE ERROE");
                        }
                    }

                    //如果查询集不是String 需求类型不是String
                    if( ! data.getClass().getName().equals("java.lang.String")){
                        // 由于查询集对应的java数据类型都是包装类 包装类互转不允许
                        String dataType = dataTypes.get(data.getClass().getName());
                    }

                    throw new RuntimeException("TYPE GET NULL ERROR");
                }
            }

        }
        return data;
    }




}

