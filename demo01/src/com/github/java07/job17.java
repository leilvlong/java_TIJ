package com.github.java07;
/*
前文job01中已经简单阐述过python垃圾回收机制以及为保存运行数据而使用闭包
尽管java与python垃圾回收机制不同,且java以class与对象为单位,但这种内部类机制的本质像极了python中的闭包
    同样是嵌套
    同样是内部拥有外部的所有成员访问权
    同样是外部为内部提供运行环境
    垃圾回收机制的本质是清理程序运行中的垃圾
    不管是采用引用计数动态清理还是将失去引用的对象标记待程序需要更多内存时一并清理
    最终都将清理这些垃圾以释放内存空间为程序运行提供足够多的内存
    使用闭包内部域就必须依赖外部的内存空间,这确保了有引用存在而不会被标记为垃圾

java的内部类是一种面向对象的闭包

在以下案例中:
    就实现接口IncrementAble而言 Callee1是最简单的方式
    而Callee2继承自MyIncrement 该类已经有了一个完全不同的increment方法
    并且该方法与IncrementAble接口的increment方法完全不相关
    同名的却完全不相关的两个方法会导致覆写
    使用内部类独立的实现IncrementAble接口就不会对外部类有任何影响
    而且在Callee2中所有成员都是私有的,外部的公共访问接口只有getIncrementAble()方法
    Callee2的内部类Closure实现接口IncrementAble后通过这个方法返回一个内部类的引用,
    而该内部类拥有外部类的所有数据,并且方法的返回对象是IncrementAble类型的
    这意味着不管是谁拿到该引用都只能执行正确的increment方法
    在类Caller中构造器需要一个IncrementAble的引用作为参数
    然后在某个时刻可以使用该引用回调Callee类

 */


public class job17 {
    public static void main(String[] args) {
        Callee1 callee1 = new Callee1();
        Callee2 callee2 = new Callee2();

        MyIncrement.fun(callee2);

        Caller caller1 = new Caller(callee1);
        Caller caller2 = new Caller(callee2.getIncrementAble());

        caller1.go();
        caller1.go();
        caller2.go();
        caller2.go();

    }
}


interface IncrementAble{
    void increment();
}


class Callee1 implements IncrementAble{
    private int i = 0;

    @Override
    public void increment() {
        i++;
        System.out.println("Callee1 i: " + i);
    }
}


class MyIncrement {
    public void increment (){
        System.out.println("Other operation");
    }

    static void fun(MyIncrement mi){
        mi.increment();
    }
}


class Callee2 extends MyIncrement{
    private int i = 0;

    public void increment(){
        super.increment();
        i++;
        System.out.println("Callee2 i: " + i);
    }

    private class Closure implements IncrementAble{

        @Override
        public void increment() {
            Callee2.this.increment();
        }
    }

    IncrementAble getIncrementAble(){
        return new Closure();
    }
}


class Caller{
    private IncrementAble incrementAble;

    public Caller(IncrementAble incrementAble) {
        this.incrementAble = incrementAble;
    }

    void go(){
        incrementAble.increment();
    }
}