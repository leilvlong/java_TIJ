package com.github.java06;
/*
接口也允许继承接口
当被类实现时
实现类应当重写所有的方法(接口继承的基类接口的抽象方法)
以下案例又将是方法重写与重载出现在同一个类中,但这次将不用担心带来问题
因为接口继承基类接口的方法,当导出接口重载基类接口的方法时应当被视为一个新方法由实现类实现
 */
public class job06 {
}


interface Inte{
    Object fun();
    String fun2();
}


interface Inter extends Inte{
    Object fun(String string);

    @Override
    String fun2();
}


class Shi implements Inter{

    @Override
    public Object fun() {
        return null;
    }

    @Override
    public Object fun(String string) {
        return null;
    }

    @Override
    public String fun2() {
        return null;
    }
}