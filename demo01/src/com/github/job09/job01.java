package com.github.job09;

public class job01 {
    public static void main(String[] args) throws SimpleException {
        InheritingExceptions inheritingExceptions = new InheritingExceptions();
        inheritingExceptions.fun();
    }
}


class SimpleException extends Exception{

}

class InheritingExceptions{
    public void fun() throws SimpleException{
        System.out.println("Throw SimpleException from Class InheritingExceptions method fun");
        throw new SimpleException();
    }
}