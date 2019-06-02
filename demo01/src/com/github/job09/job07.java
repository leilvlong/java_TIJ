package com.github.job09;

/*
当调用 (Exception) e.fillInStackTrace()后
异常的信息与发生地随着发生了改变,通过日志可以观察
 */
public class job07 {
    public static void main(String[] args) {
        /*try{
            Rethrowing.fun();
        }catch (Exception e){
            System.out.println("main: printStackTrace");
            ErrorUtil.pringErrorMassage(e);
        }
        System.out.println("---------------------------------------");
        try{
            Rethrowing.gun();
        }catch (Exception e){
            System.out.println("main: printStackTrace");
            ErrorUtil.pringErrorMassage(e);
        }*/
        System.out.println("---------------------------------------");
        try{
            Rethrowing.hun();
        }catch (Exception e){
            System.out.println("main: printStackTrace");
            ErrorUtil.pringErrorMassage(e);
        }


    }
}


class Rethrowing{
    public static void fun() throws Exception{
        System.out.println("error in class Rethrowing method fun");
        throw new NullPointerException("Throw from fun");
    }

    public static void gun() throws Exception {
        try{
            fun();
        }catch (Exception e){
            System.out.println("error in class Rethrowing method gun");
            ErrorUtil.pringErrorMassage(e);
            throw (Exception) e.fillInStackTrace();
        }
    }

    public static void hun() throws Exception {
        try{
            gun();
        }catch (Exception e){
            System.out.println("error in class Rethrowing method hun");
            ErrorUtil.pringErrorMassage(e);
            throw (Exception) e.fillInStackTrace();
        }
    }

}