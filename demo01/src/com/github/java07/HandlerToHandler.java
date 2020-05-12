package com.github.java07;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 基于代理的再代理
 */

public class HandlerToHandler {
    public static void main(String[] args) throws Throwable {
        TestInterfaceClass testInterfaceClass = new TestInterfaceClass();
        TestInterfaceClassHandler<TestInterfaceClass> testInterfaceClassHandler = new TestInterfaceClassHandler<>(testInterfaceClass);

        TestInterFace testInterFace = (TestInterFace)Proxy.newProxyInstance(testInterfaceClassHandler.getClass().getClassLoader(), testInterfaceClass.getClass().getInterfaces(), testInterfaceClassHandler);
        testInterFace.printHello();
        System.out.println("\n____________________________分割线____________________________\n");
        TestInterfaceClassHandlerToHandler<TestInterfaceClassHandler> handlerToHandler = new TestInterfaceClassHandlerToHandler<>(testInterfaceClassHandler);
        TestInterFace handlerToHandlerInstance = (TestInterFace)Proxy.newProxyInstance(handlerToHandler.getClass().getClassLoader(), testInterFace.getClass().getInterfaces(), handlerToHandler);
        handlerToHandlerInstance.printHello();
    }
}



interface TestInterFace{
    void printHello();
}

class TestInterfaceClass implements TestInterFace{

    @Override
    public void printHello() {
        System.out.println("hello world");
    }
}


class TestInterfaceClassHandler <T> implements InvocationHandler{

    private T obj;

    public TestInterfaceClassHandler(T obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName());
        Object invoke = method.invoke(obj, args);
        return invoke;
    }
}


class TestInterfaceClassHandlerToHandler<T> implements InvocationHandler{

    private InvocationHandler invocationHandler;

    public TestInterfaceClassHandlerToHandler(InvocationHandler invocationHandler){
        this.invocationHandler = invocationHandler;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = invocationHandler.invoke(proxy, method, args);
        System.out.println(method.getName());
        return invoke;
    }
}


