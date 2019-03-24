package com.github.java02;
/*
 确保成员引用变量的初始化

 */
public class job07 {
    public static void main(String[] args) {
        Mugs mugs = new Mugs();

    }
}

class Mug{
    Mug(){
        System.out.println("Mug 初始化");
    }
}

class Mugs{

    Mug mug1;
    Mug mug2;
    {
        mug1 = new Mug();
        mug2 = new Mug();
    }

    Mugs(){
        System.out.println("Mugs 初始化");
    }
}