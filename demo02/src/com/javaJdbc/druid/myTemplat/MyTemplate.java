package com.javaJdbc.druid.myTemplat;

// 利用反射 对各包装数据类型进行优化
import javax.sql.DataSource;

import java.sql.*;
import java.util.*;

/*
模拟JDBC TempLate
    Update(实现增删改操作)
    query实现查询单数据返回 (sql,需求数据类型,sql条件)
        只简单实现了常见的几种数据类型(int , double , String , boolean)
案例分析:
    根据反射获取类型信息,这些java数据的构造方法的共同点 都接收String
    如果查询集与需求数据类型都为java中的包装数据类型 则将查询集转为String
    通过反射获取构造器返回该需求类型

涉及到需求为一个实体类或多个实体类时:
    利用反射构造该对象,获取该对象的成员变量名与成员对象属性,并将成员属性暴力反射
    ResultSet.next() 获取一行数据(一个对应的实体类对象)
    利用该对象的成员变量名与属性类型在查询集获取对应的数据信息并设置
    将设置好的对象返回
    或将对象装入容器(List)返回
 */
public class MyTemplate {
    private  String[] dataTypes = {
            String.class.getName(),
            Long.class.getName(),
            Integer.class.getName(),
            Double.class.getName(),
            Boolean.class.getName(),
    };

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

        T data = (T) getData(rs, type);

        return data;
    }


    private <T> Object getData(ResultSet rs, Class<T> type) throws Exception {
        Object data = null;
        if (rs.next()){

            //查询数据
            Object selectData =  rs.getObject(1);
            List<String> dataType = Arrays.asList(dataTypes);
            if(dataType.contains(type.getName()) && dataType.contains(selectData.getClass().getSimpleName())){
                while (true){
                    //如果需求与查询集类型相同
                    if(type.getSimpleName().equals(selectData.getClass().getSimpleName())){
                        break;
                    }

                    // 如果需求为Boolean
                    if(type.getSimpleName().equals("Boolean")){
                        if(data.getClass().getSimpleName().equals("Integer") && ((int)data==1 || (int)data==0)){
                            data = (int)data==1? true:false;
                            break;
                        }
                        throw new SQLException("Value Can't to Boolean");
                    }

                    // 当查询集为Boolean时 需求为非Boolean
                    if(selectData.getClass().getSimpleName().equals("Boolean")){
                        Integer boolData = (boolean) data ? 1 : 0;
                        data = (T)getDataValue(type,data);
                        break;
                    }

                    //需求为非Boolean 需求与查询类型不同
                    if(! type.getSimpleName().equals(selectData.getClass().getSimpleName())){
                        data = (T)getDataValue(type,selectData);
                        break;
                    }

                    throw new SQLException("Value get Error");
                }
            }
        }

        return data;
    }

    private  <T> T getDataValue(Class<T> type, Object object) throws Exception {
        T t=null;
        try{
             t = type.getConstructor(String.class).newInstance(String.valueOf(object));
        }catch (Exception e){
            throw new SQLException("Value get Error");
        }
        return t;
    }



}

