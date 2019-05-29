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
                this
            参数2: Method method,
                代理方法:
                method.invoke(代理对象, Object[] args)
            参数3: Object[] args
                代理参数 数组形式存取(作为method.invoke的参数之一)
            返回值
    3.Proxy动态创建一个代理实例
        静态方法: Proxy.newProxyInstance
            参数1: InvocationHandler.getClass().getClassLoader()
            参数2: Object.getClass().getInterfaces()
            参数3: InvocationHandler
 */
public class job02 {
    public static void main(String[] args) {
        fun1();
        fun2();


    }

    /**
     * 动态代理的第一种实现方式
     * 通过实现类
     */
    public static void fun1(){
        // 1. 委托者
        Subject real = new RealSubject();
        //2. 代理对象
        InvocationHandler handler = new DynamicProxy(real);
        //3.代理实现
        Subject subject =(Subject)Proxy.newProxyInstance(handler.getClass().getClassLoader(),real.getClass().getInterfaces(),handler);
        //调用方法 对原本方法的返回值做出修改
        System.out.println(subject.print("java", 5));
        System.out.println(subject.out("word", 10));
    }

    /**
     * 动态代理的第二种实现方式
     * 通过匿名内部类
     */
    public static void fun2(){
        Subject subject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[]{Subject.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("匿名内部类处理接口委托不需要实现类");
                System.out.println("匿名内部类处理委托者委托方法: " + method.getName());
                String str;
                if("print".equals(method.getName())){
                    str =(String) args[0]+ " " +  args[1];
                }else{
                    str = (String) args[0] +" "+  args[1];
                }
                System.out.print("代处理后返回结果: ");
                return str;
            }
        });
        System.out.println(subject.print("python", 19));
        System.out.println(subject.out("c++", 20));

    }
}


// 动态代理接口
interface Subject{
    String print(String str,int num);
    String out(String str, int num);
}


//动态代理委托者
class RealSubject implements Subject{

    @Override
    public String print(String str, int num) {
        System.out.println("委托者: RealSubject"+"\n"+"委托方法: print");
        return str;
    }

    @Override
    public String out(String str, int num) {
        System.out.println("委托者: RealSubject"+"\n"+"委托方法: print");
        return str;
    }
}


//动态代理 代理者
class DynamicProxy implements InvocationHandler{

    private Subject subject;

    public DynamicProxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String str = null;
        if("print".equals(method.getName())){
            str = subject.print((String) args[0], (int) args[1]);
        }else{
            str = subject.out((String) args[0], (int) args[1]);
        }
        System.out.print("代处理后结果: ");
        return str+" " + args[1];
    }

}


