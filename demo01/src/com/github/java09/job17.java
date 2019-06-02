package com.github.java09;

/*
将异常检查转为不检查异常:
    WrapCheckedException.throwRuntiemException的代码可以生成不同类型的异常
    但是这些异常最终使用异常链的技术被包装成了RuntimeException,所以他们成了运行时异常
    job17的fun1:
        try catch只是为了观看输出语句,方法本身不需要声明异常 ,前两句代码代用了方法
        也完全不需要在方法上异常声明或捕获
    fun2:
        与fun1的前两句代码唯一的区别就是调用的方法不同,很明显需要声明异常
 */

import java.io.FileNotFoundException;
import java.io.IOException;

public class job17 {
    public static void main(String[] args) {
        fun1();
    }

    public static void fun1(){
        WrapCheckedException wce = new WrapCheckedException();
        wce.throwRuntiemException(3);

        for (int i = 0; i < 4; i++) {
            try{
                if(3>i){
                    wce.throwRuntiemException(i);
                }else {
                    throw new SomeOtherException();
                }
            }
            catch (SomeOtherException e){
                System.out.println("SomeOtherException: " + e);
            }
            catch (RuntimeException e){
                try{
                    throw e.getCause();
                }
                catch (FileNotFoundException e2){
                    System.out.println("FileNotFoundException: " + e2);
                }
                catch (IOException e2){
                    System.out.println("IOException: " + e2);
                }
                catch (Throwable e2){
                    System.out.println("Throwable: " + e2);

                }
            }
        }
    }

    public static void fun2() throws Exception {
        WrapCheckedException wce = new WrapCheckedException();
        wce.throwRuntiemException2(3);
    }
}


class SomeOtherException extends Exception{
}


class WrapCheckedException{
    void throwRuntiemException(int type){
        try{
            switch (type){
                case 0: throw new FileNotFoundException();
                case 1: throw new IOException();
                case 2: throw new RuntimeException("where an I");
                default: break;
            }
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }

    }

    void throwRuntiemException2(int type) throws Exception {

        try{
            switch (type){
                case 0: throw new FileNotFoundException();
                case 1: throw new IOException();
                case 2: throw new RuntimeException("where an I");
                default: break;
            }
        }
        catch(Exception e){
            throw new Exception(e);
        }


    }
}



