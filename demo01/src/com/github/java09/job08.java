package com.github.java09;

/*
out error仅知道自己来自于main方法在第十三行发生的异常
每个异常都是如此,仅知道自己发生异常的信息
异常对象都是new出来的,垃圾回收器会自动回收
 */

public class job08 {
    public static void main(String[] args) {
        try{
            try{
                RethrowNew.fun();
            }catch (OneException e){
                System.out.println("inner error");
                ErrorUtil.pringErrorMassage(e);
                throw new TwoException("throw from class job08 method main");
            }
        }catch (TwoException e){
            System.out.println("out error");
            ErrorUtil.pringErrorMassage(e);
        }
    }
}

class OneException extends Exception{
    public OneException(String message) {
        super(message);
    }
}


class TwoException extends Exception{
    public TwoException(String message) {
        super(message);
    }
}


class RethrowNew{
    public static void fun() throws OneException {
        throw new OneException("throw from class RethrowNew method fun");
    }
}