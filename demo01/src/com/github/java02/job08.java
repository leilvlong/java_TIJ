package com.github.java02;

import java.util.Arrays;
/*
使用可变长度参数列表(结构为数组)时:
    若涉及到方法的重载(也是可变长度参数列表)
    不传入参数会抛出错误(若只有单个方法时不会抛出错误):
        对方法的引用不明确(都匹配)
 */
public class job08 {
    public static void main(String[] args) {
        String[] str = new String[]{"1","2","3"};
        fun(1);
    }

    public static  void fun(String... str){
        System.out.println("str");
    }

    public static  void fun(Integer... str){
        System.out.println("int");
    }

    public static  void fun(char... str){
        System.out.println("char");
    }
}
