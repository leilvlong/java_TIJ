package com.github.job09;

public class job02 {
    public static void main(String[] args) {
        FullConstructors fullConstructors = new FullConstructors();
        try {
            fullConstructors.fun();
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
        try {
            fullConstructors.gun();
        }catch (Exception e){
            e.printStackTrace(System.err);
        }
    }
}


class MyException extends Exception{
    public MyException(String message) {
        super(message);
    }

    public MyException() {
    }
}


class FullConstructors{
    public void fun() throws  MyException{
        System.out.println("Throw MyException from Class FullConstructors method fun");
        throw new MyException();
    }

    public void gun() throws MyException{
        System.out.println("Throw MyException from Class FullConstructors method Gun");
        throw new MyException();
    }


}