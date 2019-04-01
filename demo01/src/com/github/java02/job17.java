package com.github.java02;

/*
子类对基类的方法名称屏蔽:
    如果基类的某个方法被多次重载,在子类中重新定义同名的方法
    并不会屏蔽其在基类中的任何版本(指基类中被重载多次的那个方法)
    但是在子类中要区分重载与重写的区别:
        重载; 与返回值无关 方法同名 参数列表不同
        重写: 返回值 方法命名 参数列表一致
 */
public class job17 {
    public static void main(String[] args) {
        ExtendClass ext = new ExtendClass();
        ext.fun();
        ext.fun(1);
        ext.fun('a');
        ext.fun("哈哈哈");
    }
}

class BaseClass{
    void fun(int inter){
        System.out.println("This is BaseClass Int: " + inter);
    }

    void fun (char cha){
        System.out.println("This is BaseClass Char: " + cha);
    }

    void fun(String str){
        System.out.println("This is BaseClass String: " + str);
    }
}

class ExtendClass extends BaseClass{


    void fun(String str){
        System.out.println("This is ExtendClass String" + str);
        super.fun("BaseClass 的重载方法fun的这个被本类重写 " +
                "子类对象使用fun时只会找到子类的fun方法" +
                "但是可以子类内部可以通过super使用我");
    }

    double fun(double dou){
        System.out.println("This is ExtendClass Double" + dou);
        return dou;
    }

    void fun(){
        System.out.println("This is ExtendClass");
    }
}

