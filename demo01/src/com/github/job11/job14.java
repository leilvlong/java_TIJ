package com.github.job11;

/*
静态代理:
    由于需要为原先的对象添加额外的状态,但又不想改动原先的代码
    基于此出现的代理思想:
        以面向接口编程的方式,运行时总是关注行为对数据的处理
        在每一个需要添加额外状态的方法中编写添加状态的代码
        基于这种间接性实现的称之为静态代理
 */

public class job14 {
    public static void main(String[] args) {
        SimpleProxyDemo.consumer(new RealObject());
        SimpleProxyDemo.consumer(new SimpleProxy(new RealObject()));
    }
}


interface Interface{
    void doSomething();
    void somethingElse(String arg);
}


class RealObject implements Interface{

    @Override
    public void doSomething() {
        System.out.println("doSomething in RealObject");
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("somethingElse in RealObject: " + arg);
    }
}


class SimpleProxy implements Interface{

    private Interface proxied;

    public SimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

    @Override
    public void doSomething() {
        proxied.doSomething();
    }

    @Override
    public void somethingElse(String arg) {
        System.out.print("The arg: ");
        proxied.somethingElse(arg);
    }
}


class SimpleProxyDemo{
    public static void consumer(Interface i){
        i.doSomething();
        i.somethingElse("bobobo");
    }
}