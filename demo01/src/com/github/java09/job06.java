package com.github.java09;
/*
获取异常栈:
    第一个为抛出异常的方法名
 */
public class job06 {
    public static void main(String[] args) {
        fun();
        System.out.println("-------------------------------");
        gun();
        System.out.println("-------------------------------");
        dun();

    }

    public static void fun(){
        try{
            throw new Exception();
        }catch (Exception e){
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                System.out.println(stackTraceElement.getMethodName());
            }
        }
    }

    public static void gun(){
        fun();
    }

    public static void dun(){
        gun();
    }
}
