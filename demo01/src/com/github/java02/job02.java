package com.github.java02;

/*\
垃圾清理之finalize(已过时):
    当对象引用指向null时,生存与堆上的对象内存空间没有了引用 等待的将是被销毁
    在销毁时,会首先调用对象的finalize(因为并非所有的数据都和对象一样生存与堆上 比如静态)
    通过下面案例 可以明确的到 在将对象引用为null时,发生了什么

 */

public class job02 {
    public static void main(String[] args) throws Exception {
        A a = new A(new B("allen", 20));
        a = null;

        System.gc();
        Thread.sleep(5000);
        System.out.println(C.a.b);
    }
}


class A {
    B b;

    public A(B b) {
        this.b = b;
    }

    @Override
    public void finalize() {
        System.out.println("A finalize");
        C.a = this;
    }
}

class B {
    String name;
    int age;

    public B(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void finalize() {
        System.out.println("B finalize");
    }

    @Override
    public String toString() {
        return name + " is " + age;
    }
}

class C {
    static A a;
}


