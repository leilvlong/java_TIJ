package com.github.java09;

public class job04 {
    public static void main(String[] args) {
        try {
            fun1();
        }catch (MyException2 e){
            e.printStackTrace();
        }
        try {
            fun2();
        }catch (MyException2 e){
            e.printStackTrace();
        }
        try {
            fun3();
        }catch (MyException2 e){
            e.printStackTrace();
            System.out.println("e.val: " +e.val());
        }


    }

    public static void fun1() throws MyException2 {
        System.out.println("Throw MyException2 form fun1");
        throw new MyException2();
    }

    public static void fun2() throws MyException2 {
        System.out.println("Throw MyException2 form fun2");
        throw new MyException2("Originated in fun2");
    }

    public static void fun3() throws MyException2 {
        System.out.println("Throw MyException2 form fun3");
        throw new MyException2("Originated in fun3",47);
    }

}


class MyException2 extends Exception{
    private int x;

    public MyException2() {
    }

    public MyException2(String message) {
        super(message);
    }

    public MyException2(String message, int x) {
        super(message);
        this.x = x;
    }

    public int val(){
        return x;
    }

    public String getMessage(){
        return "Detail Message: "+x+" " + super.getMessage();
    }
}
