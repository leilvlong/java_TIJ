package com.github.java07;

import sun.misc.ProxyGenerator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
import java.io.FileOutputStream;

/*
JAVA动态代理机制:
	对象主要分为两个部分，属性与行为，而JAVA中有一种方式专门去定义对象的行为规范:接口。
	JAVA的动态代理机制需要三个角色来完成：委托者、代理者、代理对象。
	委托者实现接口，具备规范的行为
	代理者实现JAVA提供的动态代理接口，实现该接口的invoke方法,并且代理者的构造器接受委托者并组成组合关系
	代理对象则由Proxy类提供的api构建（由JVM声明并创建），需要委托者的类加载器、委托者自身的所有接口、代理者实例
	JVM声明并创建的代理对象因为接受了代理者实例作为构造参数，因此与代理者构成组合关系。并且表示实现了委托者所有的接口方法 
	实际上这些接口方法都做了同一件事情：在所有的方法中都调用代理者实例的invoke方法，把this对象、当前需要执行的method对象、当前的执行参数作为参数传递
	因此动态代理机制中最终的执行点在代理者的invoke方法中，我们可以在该方法中中对委托者做一些方法增强以及统一处理
	method对象的执行与获取方式无关,与执行对象有关,这也是代理者构造器需要委托者的原因
*/

public class job22 {
    public static void main(String[] args) throws Throwable {
        //委托者
        MyInter myInter = new MyInter();

        Prxo prxo = new Prxo(myInter);
        System.out.println("承接委托类名: " + prxo.getClass().getName());



        /*//动态代理对象使用
        ThisInter handler= (ThisInter) Proxy.newProxyInstance(Prxo.class.getClassLoader(), new Class[]{ThisInter.class}, prxo);
        System.out.println("动态代理对象名: "+ handler.getClass().getName());
        handler.fun();*/

        /*//获取动态代理对象的自字节码文件的方法
        testPoxy();  */

        //自定义伪动态代理对象
        MyPoxy myPoxy = new MyPoxy(prxo);
        System.out.println("自定义伪动态代理对象名: " + myPoxy.getClass().getName());
        myPoxy.fun();
    }

    /**
     * 该方法可用于反编译动态代理对象
     */
    public static void testPoxy() {
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy", new Class[]{ThisInter.class});
        try (FileOutputStream fos = new FileOutputStream("demo01\\funs\\$Proxy.class");) {
            fos.write(bytes);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/**
 * 第一部分:
 *      接口
 *      实现类（委托者）
 *      承接委托的类
 */
interface ThisInter {
    void fun();
}

class MyInter implements ThisInter {
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
        System.out.println("invoke方法参数对象名：" + proxy.getClass().getName());

        //若使用proxy的任何方法都会无限递归 因为这个对象就是动态代理对象,原因已解释过
        //System.out.println(proxy.toString());
        /*
        以上是详细的异常信息,完全一致 只不过java的设计者把这个问题给抛出来了而我水平不够，
        只能等JVM虚拟机给我抛出来:
            *** java.lang.instrument ASSERTION FAILED ***: "!errorOutstanding" with message
            transform method call failed at JPLISAgent.c line: 844
        */

        method.invoke(obj, args);
        return null;
    }
}


/**
 * 第二部分:
 *      自己动手实现动态代理对象
 * 因为是编译期实现,所以不需要参数:
 *      类加载器
 *      接口数组
 * 只需要承接委托类即可
 */
class MyPoxy implements ThisInter {
    private InvocationHandler handler;

    public MyPoxy(InvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void fun() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        try {
            Method method = null;
            Class<?>[] interfaces = this.getClass().getInterfaces();
            for (Class<?> anInterface : interfaces) {
                try {
                    method = anInterface.getMethod(methodName);
                } catch (NoSuchMethodException e) {
                }
            }
            handler.invoke(this, method, new Object[]{});
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * Object 根类只重写这一个方法以做示例
     *
     * @return
     */
    @Override
    public String toString() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        try {
            Method method = Object.class.getMethod(methodName);
            return (String) handler.invoke(this, method, new Object[]{});
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}


/**
 * 以下内容为JVM动态实现的代理对象:
 * 通过API得到该动态代理对象的类的具体实现编写方式
 */
final class $Proxy extends Proxy implements ThisInter {
    private static Method m1;
    private static Method m3;
    private static Method m2;
    private static Method m0;

    public $Proxy(InvocationHandler var1) {
        super(var1);
    }

    public final boolean equals(Object var1) {
        try {
            return (Boolean) super.h.invoke(this, m1, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final void fun() {
        try {
            super.h.invoke(this, m3, (Object[]) null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final String toString() {
        try {
            return (String) super.h.invoke(this, m2, (Object[]) null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final int hashCode() {
        try {
            return (Integer) super.h.invoke(this, m0, (Object[]) null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            m3 = Class.forName("com.github.java07.ThisInter").getMethod("fun");
            m2 = Class.forName("java.lang.Object").getMethod("toString");
            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }
}
