package com.github.java05;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/*
动态代理:
    1.相关类与接口
         InvocationHandler(Interface) Proxy(Class)
    2.每一个动态代理类都必须要实现InvocationHandler这个接口
        方法: invoke
            参数1: Object proxy:
                代理对象
            参数2: Method method,
                代理方法
            参数3: Object[] args
                代理参数 数组形式存取
    3.Proxy动态创建一个代理实例
        静态方法: Proxy.newProxyInstance
            参数1: InvocationHandler.getClass().getClassLoader()
            参数2: Object.getClass().getInterfaces()
            参数3: InvocationHandler
 */
public class job02 {
    public static void main(String[] args) {
        // 1. 被代理对象
        Subject real = new RealSubject();

        //2. 代理对象
        InvocationHandler handler = new DynamicProxy(real);

        //3.代理实现
        Subject subject =(Subject)Proxy.newProxyInstance(handler.getClass().getClassLoader(),real.getClass().getInterfaces(),handler);
        subject.print("java",5);
        subject.out("word",10);
    }
}

interface Subject{
    void print(String str,int num);
    void out(String str, int num);
}


class RealSubject implements Subject{

    @Override
    public void print(String str, int num) {
        System.out.println("print hello " + str + " " + num);
    }

    @Override
    public void out(String str, int num) {
        System.out.println("out hello "+ str + " " + num );
    }
}


class DynamicProxy implements InvocationHandler{

    private Object subject;

    public DynamicProxy(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        for (Object arg : args) {
            System.out.println("遍历arg[]: " + arg);
        }
        System.out.println("method: "+ method);
        method.invoke(subject,args);
        System.out.println("args[]: "+ Arrays.toString(args));
        return null;
    }

    public void fun(){
        System.out.println("DynamicProxy fun");
    }
}


