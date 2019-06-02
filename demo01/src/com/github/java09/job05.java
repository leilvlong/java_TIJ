package com.github.java09;

public class job05 {
    public static void main(String[] args) {
        try{
            fun();
        }catch (Exception e){

            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                System.out.println(stackTraceElement);
            }
        }
    }

    public static void fun() throws MyException2, MyException{
        System.out.println("hahah");
    }

}
