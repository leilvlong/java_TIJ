package com.github.job11;

/*
动态代理:
    关于动态代理的运行机制与详细案例前文已做过探究
    在这里还有一些需要补充的地方:
        前文已经提到过静态代理,它以一种间接性的方式为原本的对象的行为提供了额外的状态
        动态代理的实现原理与静态代理本质并无任何不同,同样是以一种间接性的方式为原本的
        对象提供了额外的状态,但不同于静态代理的模板化以及甚至对每一个相同的方法都需要
        添加同一种状态时需要每个方法都编写同样的代码,动态代理更加灵活,便捷

JDK为动态代理提供了强有力的支持:
    InvocationHandler接口只有一个方法: invoke(Object proxy, Method method, Object[] args )
    invoke: 翻译成中文具有"调用"的意思
    同样具有invoke方法的还有Method:
        在思考InvocationHandler.invoke方法之前先梳理Method对象的invoke方法,
        Method对象本身是一个方法对象,它是直接从字节码对象中获取的
        Method对象的invoke方法具有两个参数:
            其一: 执行对象,该执行对象必须是Method对象的同一字节码文件创建的
            其二: 执行方法所需的参数
            满足以上两点后使用Method对象的invoke方法则该方法具有执行对象的行为
    再次声明:动态代理的运行机制与详细案例前文已做过探究
    所以此处不再分析InvocationHandler.invoke的参数的意义
    无论如何,动态代理机制中最后的执行点都在InvocationHandler.invoke方法中
    而原本对象执行的方法由其Method对象去执行
    这就提供了巨大的灵活性,无论是统一添加状态,又或是单独添加,这都很容易实现了

JDK提供了Api由JVM来生成动态代理对象:
    InvocationHandler.invoke终究只是动态代理对象的执行点
    归根结底,需要一个动态代理对象,该对象由JDK的Api提供:
        Proxy.newProxyInstance(ClassLoader loader,
                               Class<?>[] interfaces,
                               InvocationHandler h)
    这里依然不分析这三个参数的意义(动态代理的运行机制与详细案例前文已做过探究)
    满足这三个参数后,JVM就将为了创建一个动态代理对象,其行为由接口决定

前文案例URL: com/github/java07/job22.java

以下为Tij中的案例:
    我对该案例稍微做了一些修改:
        任何对proxy的调用都会导致递归,
        因为动态代理对象的每个方法最终是调用了InvocationHandler.invoke方法,并将this传递过来
        但是只要给个递归出口,这种情况就可以避免
*/

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class job15 {
    public static void main(String[] args) {
        RealObject real = new RealObject();

        Interface proxy = (Interface)Proxy.newProxyInstance(real.getClass().getClassLoader(),
                real.getClass().getInterfaces(),
                new DynamicProxyHandler(real));

        SimpleProxyDemo.consumer(proxy);
    }
}


class DynamicProxyHandler implements InvocationHandler{
    private static int count;

    private Interface proxied;

    public DynamicProxyHandler(Interface proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        count++;
        if (count++<=200){
            System.out.println(proxy.toString());
        }


        if(args != null){
            System.out.print("The arg: ");
            return method.invoke(proxied,args);
        }else{
            return method.invoke(proxied,args);
        }
    }
}
