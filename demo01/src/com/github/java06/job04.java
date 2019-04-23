package com.github.java06;
/*
java 类只允许单继承 接口允许多继承
经管如此,却可能有些意外情况在不细心时发生
动态绑定的机制使得总是使对象拥有正确的行为
以下案例中两个接口与一个抽象类具有同名方法
因为继承中允许协变返回类型(同一继承体系之间向上转型)
方法的不同更多是以参数来区分此时便涉及到:
    方法重写的同时方法也重载了
    job05将证明这种问题
 */
public class job04 {
    public static void main(String[] args) {
        Count count = new Count();
        count.fun("hello");
        count.fun(125);
        count.fun();
    }
}


interface One{
    String fun(String str);
}


interface Two{
    Integer fun(Integer num);
}


abstract class Three{
    abstract void fun();
}


class Count extends Three implements One, Two{

    @Override
    public String fun(String str) {
        System.out.println("参数类型String: " + str);
        return null;
    }

    @Override
    public Integer fun(Integer num){
        System.out.println("参数类型Integer: "+ num);
        return null;
    }

    @Override
    public void fun(){
        System.out.println("没有参数");
    }

}