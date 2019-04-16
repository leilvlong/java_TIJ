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
    利用反射构造该对象(Object.class.newInstance()),
    获取该对象的成员变量名与成员对象属性,并将成员属性暴力反射 (Object.class,getFileds())
    ResultSet.next() 获取一行数据(一个对应的实体类对象)
    循环遍历获取的类成员属性数组 通过ResultSet.getObject(String name) 获取数据
    实体类与数据表是对应的 因此成员变量名也应与数据库一致
    设置值
    当循环结束 一个对象即诞生了 将设置好的对象返回或将对象装入容器(List)返回
    如果无法通过成员属性获取值 则抛出异常告诉template使用者实体类或数据表属性不对应

只是模拟简单的实现原理,没有考虑额外的更多的意外情况
 */
public class MyTemplate {
    private  String[] dataTypes = {
            String.class.getName(),
            Long.class.getName(),
            Integer.class.getName(),
            Double.class.getName(),
            Float.class.getName(),
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
        if(data == null){
            throw new SQLException("Get value is null");
        }

        return data;
    }


    private <T> Object getData(ResultSet rs, Class<T> type) throws Exception {
        Object data = null;
        if (rs.next()){

            //查询数据
            Object selectData =  rs.getObject(1);
            System.out.println(selectData.getClass().getSimpleName());
            List<String> dataType = Arrays.asList(dataTypes);
            if(dataType.contains(type.getName()) && dataType.contains(selectData.getClass().getName())){
                while (true){
                    //如果需求与查询集类型相同
                    if(type.getSimpleName().equals(selectData.getClass().getSimpleName())){
                        data=selectData;
                        break;
                    }

                    // 如果需求为Boolean
                    if("Boolean".equals(type.getSimpleName())){
                        if(selectData.getClass().getSimpleName().equals("Integer") && ((int)data==1 || (int)data==0)){
                            data = ((int)selectData) == 1 ? true:false;
                            break;
                        }
                        throw new SQLException("Value Can't to Boolean");
                    }

                    // 当查询集为Boolean时 需求为非Boolean
                    if("Boolean".equals(selectData.getClass().getSimpleName())){
                        if("String".equals(type.getSimpleName())){
                            data = String.valueOf(selectData);
                            break;
                        }
                        Integer boolData = (boolean) selectData ? 1 : 0;
                        data = getDataValue(type,boolData);
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

