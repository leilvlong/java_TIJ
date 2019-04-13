package com.javaJdbc.druid.myTemplat;


import javax.sql.DataSource;
import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

/*
模拟JDBC TempLate
    Update(实现增删改操作)
    query实现查询单数据返回 (sql,需求数据类型,sql条件)
        只简单实现了常见的几种数据类型(int , double , String , boolean)

代码虽然组织不好看 但jar包肯定也是做了数据类型的判断再进行转换的,而且代码组织比我肯定好的多
反射真的是java的很重要的一部分
 */
public class MyTemplate {
    private  Map<String,String> dataTypes = new HashMap<>();

    {
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

        T data = (T) getData(rs, type);

        return data;
    }


    private <T> Object getData(ResultSet rs, Class<T> type) throws Exception {
        Object data = null;
        if (rs.next()){

            //查询数据
            data =  rs.getObject(1);

            if(dataTypes.containsKey(type.getName())){
                while (true){
                    //如果参数与需求是同一类型 直接返回
                    if(data.getClass().getName().equals(type.getName())){
                        break;
                    }

                    //如果需求是String类型
                    if("java.lang.String".equals(type.getName())) {

                        try {
                            data =  String.valueOf(data);
                            break;
                        } catch (Exception e) {
                            throw new RuntimeException("GET TYPE ERROR");
                        }
                    }

                    //如果查询集为String,转为需求非String数据类型
                    if("java.lang.String".equals(data.getClass().getName())){
                        // 获取对应的包装类静态方法名与设置参数类型
                        String mdName = dataTypes.get(type.getName());

                        // 获取该对象的什么方法执行, 传入的参数类型字节码
                        Method method = type.getMethod(mdName, data.getClass());
                        try {
                            // 对象 参数 因为是静态方法已经加载过字节码不需要对象
                            data=  method.invoke(null,data);
                            break;
                        }catch (Exception e){
                            throw new RuntimeException("Get Type Error");
                        }
                    }

                    //如果查询集不是String 且不是布尔值 需求类型不是String 即互为整数或浮点数
                    if( ! "java.lang.String".equals(data.getClass().getName()) && ! data.getClass().getName().contains("Boolean")){
                        //查询集对应的java数据类型都是包装类  需要做基本类型转换后在转换
                        Number dataNum = (Number)data;
                        String typeName = dataTypes.get(type.getName());

                        if("parseLong".equals(typeName)){
                            data = (T) getDataValue(type,int.class,dataNum);
                            break;
                        }else if("parseInt".equals(typeName)){
                            data = (T) getDataValue(type,int.class,dataNum);
                            break;
                        }else if("parseDouble".equals(typeName)){
                            double newDataNum = dataNum.doubleValue();
                            data = (T) getDataValue(type,double.class,newDataNum);
                            break;
                        }else if("parseFloat".equals(typeName)){
                            float newDataNum = dataNum.floatValue();
                            data = (T) getDataValue(type,float.class,newDataNum);
                            break;
                        }else if("parseBoolean".equals(typeName)){
                            if(data.getClass().getName().contains("Integer") && ((int)data==0 ||(int)data ==1)){
                                data = (int) data == 0 ? false : true;
                                break;
                            }else{
                                throw new RuntimeException("Get Type Error");
                            }
                        }
                    }

                    // 当查询集为Boolean时
                    if(data.getClass().getName().contains("Boolean")){
                        Number boolData = (boolean) data ? 1 : 0;
                        String typeName = dataTypes.get(type.getName());

                        if("parseLong".equals(typeName)){
                            data = (T) getDataValue(type,int.class,boolData);
                            break;
                        }else if("parseInt".equals(typeName)){
                            data = (T) getDataValue(type,int.class,boolData);
                            break;
                        }else if("parseDouble".equals(typeName)){
                            data = (T) getDataValue(type,double.class,boolData);
                            break;
                        }else if("parseFloat".equals(typeName)){
                            data = (T) getDataValue(type,float.class,boolData);
                            break;
                        }
                    }

                    throw new RuntimeException("Type Get Null Error");
                }
            }else{
                throw new RuntimeException("Type Does not support");
            }

        }
        return data;
    }

    private <T> T getDataValue(Class<T> type,Object cla, Number dataNum)throws Exception{
        //System.out.println(cla.getName());
        Method valueOf = type.getMethod("valueOf", (Class<?>) cla);
        return (T) valueOf.invoke(null, dataNum.intValue());
    }

    private <T> T getDataValue(Class<T> type,Object cla, double dataNum)throws Exception{
        //System.out.println(cla.getName());
        Method valueOf = type.getMethod("valueOf", (Class<?>) cla);
        return (T) valueOf.invoke(null, dataNum);
    }

    private <T> T getDataValue(Class<T> type,Object cla, float dataNum)throws Exception{
        Method valueOf = type.getMethod("valueOf", (Class<?>) cla);
        return (T) valueOf.invoke(null, dataNum);
    }

}

