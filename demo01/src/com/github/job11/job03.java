package com.github.job11;

/*
一个有趣的现象当使用 .class 来创建Class对象的引用时,不会自动的初始化该Class对象:
为了使用类而做的准备工作实际包含三个步骤:
    1）加载：这是由类加载器执行的。该步骤将查找字节码，并从这些字节码中创建一个Class对象
    2）链接：在该阶段将验证类中的字节码，为静态成员分配内存空间，并且如果必须的话，将解析
            这个类创建的对其他类的所有引用。
    3）初始化： 如果该类具有超类（基类），则对其初始化并构造

初始化被延迟到了对静态方法或者非常量静态域被首次引用时才执行:
    以下代码揭示了这一特性,这种行为是惰性的,它会尽可能的实现“按需加载”
    如果一个static final的值是编译期常量（在四类八种的取值范围内），
    那么不需要被初始化就可以被读取,该类的静态域自然也不会被初始化

 */


import java.util.Random;

public class job03 {
    public static Random rand = new Random();

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        //值是常量,编译器以确定,读取即可,不会初始化
        System.out.println(Initable1.staticFinal);
        //值在运行时由别的类的静态成员产生,非编译器常量,该类静态区域会被初始化
        System.out.println(Initable1.staticFinal2);

        //值是常量,编译器以确定,读取即可,不会初始化
        System.out.println(Initable2.staticFinal);

        //值会随着程序运行变化而变化,非编译器常量,该类静态区域会被初始化
        System.out.println(Initable3.staticFinal);

    }
}


class Initable1{
    static final int staticFinal = 47;
    static final int staticFinal2 = job03.rand.nextInt(1000);
    static {
        System.out.println("Initializing Initable1");
    }
}


class Initable2{
    static final boolean staticFinal = true;
    static {
        System.out.println("Initializing Initable2");
    }
}


class Initable3{
    static int staticFinal = 74;
    static {
        System.out.println("Initializing Initable3");
    }
}