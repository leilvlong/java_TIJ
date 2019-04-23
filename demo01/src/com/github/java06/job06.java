package com.github.java06;
/*
接口也允许继承接口:
    当被类实现时实现类应当重写所有的方法(接口继承的基类接口的抽象方法与自身的方法)
    以下案例又将是方法重写与重载出现在同一个类中，
但这次将不用担心带来问题。
    因为接口继承基类接口的方法，当导出接口重载基类
接口的方法时应当被视为一个导出接口自身的方法由实现
类实现
    而接口是不能实现方法的,因此不存在重写方法然后被
实现类继承带来冲突这种问题

接口继承接口只为扩展更多态多样的的接口行为规范

 */
public class job06 {
}


interface Inte{
    Object fun();
    String fun(Integer str);
}


interface Inter extends Inte{
    Object fun(String string);


}


class Shi implements Inter{

    @Override
    public Object fun() {
        return null;
    }

    @Override
    public String fun(Integer str) {
        return null;
    }

    @Override
    public Object fun(String string) {
        return null;
    }


}