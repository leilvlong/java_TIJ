package com.github.java12;

import java.util.ArrayList;
import java.util.List;


public class StringBytesOperate {

    private static final int sqlIndex = 0;

    private static final int sqlParameterLen = 1;

    private static final byte parameterByte = 63;



    private static int[][] indexMessage;

    private static String executeSql;


    public static void main(String[] args) {
        setSql("set id = ? and pwd = ? and age = ?");


        System.out.println(replace(1, "123"));
        System.out.println(replace(2, "123"));
        System.out.println(replace(3, "17"));
        System.out.println(replace(2, "12dada3456"));
        System.out.println(replace(1, "12dada3456"));

    }

    public static void setSql(String sql){

        indexMessage = null;

        executeSql = sql;

        byte[] sqlBytes = sql.getBytes();

        List<Integer> index = new ArrayList<>();

        for (int i = 0; i < sqlBytes.length; i++) {
            if (sqlBytes[i] == parameterByte){
                index.add(i);
            }
        }

        indexMessage = new int[index.size()][2];

        for (int i = 0; i < index.size(); i++) {
            indexMessage[i][0] = index.get(i);
        }
    }



    public static String replace(int index, String parameter){

        long start = System.currentTimeMillis();

        int parameterIndex = indexMessage[index-1][sqlIndex];

        int indexParameterLength = indexMessage[index-1][sqlParameterLen];

        byte[] parameterBytes = parameter.getBytes();

        if (indexParameterLength == 0){
            indexParameterLength = 1;
        }

        int parameterLength = parameterBytes.length;

        indexMessage[index-1][sqlParameterLen] = parameterLength;

        byte[] executeSqlBytes = executeSql.getBytes();

        byte[] newSqlBytes = new byte[executeSqlBytes.length + (parameterLength - indexParameterLength)];


        System.arraycopy(executeSqlBytes, 0, newSqlBytes, 0, parameterIndex);

        System.arraycopy(parameterBytes, 0, newSqlBytes, parameterIndex, parameterBytes.length);

        System.arraycopy(executeSqlBytes, parameterIndex + indexParameterLength, newSqlBytes, parameterBytes.length+parameterIndex, executeSqlBytes.length-(parameterIndex+indexParameterLength));

        for (int i = index; i < indexMessage.length; i++) {
            indexMessage[i][sqlIndex] = indexMessage[i][sqlIndex] + (newSqlBytes.length - executeSqlBytes.length );
        }

        executeSql = new String(newSqlBytes);

        System.out.println("执行时间: " + (System.currentTimeMillis() - start));

        return executeSql;
    }

}
