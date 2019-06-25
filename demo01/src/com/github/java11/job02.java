package com.github.java11;

/*
Class对象:
        Api中提供了对Class对象的各种操作(对象在它面前没有秘密)。
        类是程序的一部分,每个类都有一个Class对象（保存在以 .class结尾的文件中）。
    为了生成这个类的对象，JVM虚拟机将使用被称为类加载器的子系统。
        类加载器只有一个原生类加载器（可以有多个类加载器），它是JVM实现的一部分。
    原生类加载器加载的是可信类，这些可信类包含了java Api与从本地盘加载的类。
        前文已经提过（com. github. java. job18），当类第一次被访问时才会加载，这
    也说明所有类并非java程序开始运行都会完全加载，而是在第一次被使用时才被加载；
        类加载器首先检查这个类的Class对象是否已经加载,如果尚未加载，默认的类加载
    器就会根据类名查找 .class 文件（甚至可以通过额外的附加类加载器在数据库中查找
    字节码加载该类对象，我是没见过这种操作）；在这个类的字节码被加载时，他们会接
    受验证，以确保字节码文件的内容没有被破坏并且不包含不良java代码（这是java中用
    于安全防范目的措施之一）；一旦某个类的Class对象被加载进内存，它就会被用来创建
    该类的所有对象。
        以下案例将对事实补充说明

注：
    TIJ作者坚持认为构造器是没有静态修饰的静态成员，它以构造器提供了类访问入口为理由证明这点

 */

public class job02 {
    public static void main(String[] args) {
        // 1.构造器访问
        Candy candy = new Candy();

        // 2. 静态成员变量访问
        Gum.str = "";

        // 3. Class Api 访问
        try {
            Class.forName("com.github.java11.Cookie");
        } catch (ClassNotFoundException e) {
        }

    }
}


class Candy{
    static {
        System.out.println("Loading Candy");
    }
}


class Gum{
    public static String str;
    static {
        System.out.println("Loading Gum");
    }
}


class Cookie{
    static {
        System.out.println("Loading Cookie");

    }
}
