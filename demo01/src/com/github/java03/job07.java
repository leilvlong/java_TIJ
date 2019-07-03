package com.github.java03;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;

/*

 */
public class job07 {
    public static void main(String[] args) {
        // 求两个数之间的所有自然数之和
        int count = 0;
        for(int x=2; x<=231 ; x++){
            count+=x;
        }
        System.out.println(count);
        fun2(2,231);
    }

    public static void fun1(){
        String str = "ABCABC";
        // String.replace(replaceAll replaceFirst)
        StringBuilder strs = new StringBuilder(str);
        str =String.valueOf(strs.replace(str.lastIndexOf("A"),str.lastIndexOf("A")+1,"D"));
        System.out.println(str);
    }

    public static void fun2(int a, int b){
        // 求任意两个正整数之间的所有数之和
        if( (b+1-a) % 2 ==0){
            System.out.println( (a+b) * (b+1 -a) / 2);
        }else{
            System.out.println( (a+b) * ((b - a) / 2) + (a + (b - a) / 2));
        }
    }
}
