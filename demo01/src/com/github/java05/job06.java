package com.github.java05;
/*
java多重继承对成员变量的获取
 */
public class job06 {
    public static void main(String[] args) {
        Sub1 sub2 = new Sub2();
        System.out.println(sub2.num);
        // 该示例实际是  sub2 的super 指向 sub1的堆上内存寻求num
        //sub1并不具有 通过super指向 Base的堆上内存成功获取
        //若Base 亦获取不到  若Base亦有super  则继续指向基类的堆上内存 直到获取到该值或不存在super的基类(此时会抛出异常)

        // 案例2
        Sub1 subs2 = new Sub2();
        System.out.println(subs2.count);
    }
}

class BA {
    int count=15;
}


class Base extends BA{
    public int num = 0;
}


class Sub1 extends Base{
    public int num1 = 1;
}


class Sub2 extends Sub1{
    public int num2 = 2;
}


