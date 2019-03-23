package com.github.java02;

class Number {
    //int c = fun3(a);  //发出向前引用警告 程序的正确性取决于初始化的顺序 , 与编译无关

    int a = fun();

    int b = fun2(a);

    int fun(){
        return 11;
    }

    int fun2(int num){
        return num*10;
    }

    int fun3(int num) {return num*10;};
}

public class job03{
    public static void main(String[] args) {
        Number number = new Number();
        System.out.println(number.b);
    }
}
