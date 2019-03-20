package com.github.java02;
/*
涉及到基本数据类型的方法重载
 */

public class job01 {
    public static void main(String[] args) {
        byte b = 1;
        short s = 2;
        int i = 3;
        long l = 4;
        float f= 5.0f;
        double d = 6.0;
        char c = 'a';

        /*Base base = new Base();
        base.fun(b);
        base.fun(s);
        base.fun(i);
        base.fun(l);
        base.fun(f);
        base.fun(d);
        base.fun(c);
        没有问题
        */
        /*
        Base base = new Base();
        base.fun(1);
        base.fun(2);
        base.fun(3);
        base.fun(4);
        base.fun(5.0);
        base.fun(6.0);
        base.fun('a');
        整形常量默认为int
        浮点型默认为 double
        如果没有找到接受char参数的方法 会默认为转为 int类型参数
         */

        Base base = new Base();
        base.fun((byte)1.0);
        base.fun((short)2.0);
        base.fun((int)3.0);
        base.fun((long)4.0);
        base.fun((float) 5.0);
        base.fun((double) 6.0);
        base.fun((char)7.0);
        /*
        会进行转换 找到对应的重载方法  除了char 会被转为int(因为没找到char方法 被我注释了)
        需要注意的是注意基本数据类型的取值范围,超过取值范围会抛出异常
         */
    }
}

class Base{
    // byte
    void fun(byte funbyte){
        System.out.println("funbyte: " + funbyte);
    }

    // short
    void fun(short funshort){
        System.out.println("funshort: " + funshort);
    }

    // int
    void fun(int funint){
        System.out.println("funint: " + funint);
    }

    // long
    void fun(long funlong){
        System.out.println("funlong: " + funlong);
    }

    //float
    void fun(float funfloat){
        System.out.println("funfloat: " + funfloat);
    }

    //double
    void fun(double fundouble){
        System.out.println("fundouble: " + fundouble);
    }

/*    void fun(char funchar){
        System.out.println("funbyte: " + funchar);
    }*/
}
