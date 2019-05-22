package com.github.java07;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Objects;

public class job22 {
    public static void main(String[] args) throws Throwable {
        // 承接委托对象使用
        MyInter myInter = new MyInter();

        Prxo prxo = new Prxo(myInter);
        /*System.out.println("动态代理对象名: "+ prxo.getClass().getName());
        prxo.invoke(prxo,myInter.getClass().getMethod("fun"),null);*/

        //动态代理对象使用
        /*ThisInter handler= (ThisInter) Proxy.newProxyInstance(Prxo.class.getClassLoader(), new Class[]{ThisInter.class}, prxo);
        System.out.println("动态代理对象名: "+ handler.getClass().getName());
        handler.fun();*/

        //自定义伪动态代理对象
        MyPoxy myPoxy = new MyPoxy( prxo);
        System.out.println("MyPoxy 对象名: "+ myPoxy.getClass().getName());
        myPoxy.fun();


    }
}

interface ThisInter{
    void fun();
}

class MyInter implements ThisInter{

    @Override
    public void fun() {
        System.out.println("Class MtInter method fun...");
    }
}


class Prxo implements InvocationHandler {
    private MyInter obj;


    public Prxo(MyInter obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //获取对象类名
        System.out.println("invoke方法参数对象名："+ proxy.getClass().getName());

        //若使用proxy的任何方法都会无限递归 因为这个对象就是动态代理对象,原因已解释过
        System.out.println(proxy.toString());
        /*
        *** java.lang.instrument ASSERTION FAILED ***: "!errorOutstanding" with message
        transform method call failed at JPLISAgent.c line: 844

        以上是详细的异常信息,完全一致 只不过java的设计者把这个问题给抛出来了
        而我水平不够，只能等JVM虚拟机给我抛出来
        */

        method.invoke(obj,args);
        return null;

    }
}

class MyPoxy implements ThisInter{

    InvocationHandler handler;
    String fieldName ;

    public MyPoxy(InvocationHandler handler)  {
        this.handler = handler;

        Class<?>[] interfaces = this.getClass().getInterfaces();
        Field[] handlerFields = handler.getClass().getDeclaredFields();

        for (Field handlerField : handlerFields) {
            handlerField.setAccessible(true);
            try{
                Class<?>[] fieldInterface = handlerField.get(handler).getClass().getInterfaces();
                if(Arrays.equals(interfaces,fieldInterface)){
                    fieldName = handlerField.getName();
                    break;
                }
            }catch (Exception e){
            }
        }
    }

    @Override
    public void fun() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        try {
            Field obj = handler.getClass().getDeclaredField(fieldName);
            obj.setAccessible(true);
            Object o = obj.get(handler);
            Method method = o.getClass().getMethod(methodName);
            handler.invoke(this,method,null);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        try {
            Field obj = handler.getClass().getDeclaredField(fieldName);
            obj.setAccessible(true);
            Object o = obj.get(handler);
            Method method = o.getClass().getMethod(methodName);
            Object invoke = handler.invoke(this, method, null);
            return (String) invoke;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
