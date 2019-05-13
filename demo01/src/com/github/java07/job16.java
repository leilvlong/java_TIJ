package com.github.java07;

import com.sun.org.apache.bcel.internal.classfile.InnerClass;

/*
静态内部类单例设计:
    利用嵌套类的特性设计的单例模式,
    该单例模式下具有线程安全(静态只加载一次)
    以及懒加载(只有当类被访问时静态才会加载,而内部类是单独的实体,具有自己的字节码文件)
 */
public class job16 {
    public static void main(String[] args) {
        OutClass instance1 = OutClass.getInstance();
        OutClass instance2 = OutClass.getInstance();
        System.out.println(instance1==instance2);
    }
}


class OutClass{

    private OutClass(){
    }

    private static class InnerClass{
        static OutClass oc = new OutClass();
    }

    public static OutClass getInstance(){
        return InnerClass.oc;
    }
}