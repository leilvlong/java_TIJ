package com.github.java02;

/*

final修饰变量的一些细节:
     final允许指定空白常量 但是在使用前需要为其赋值
     而且只允许在构造方法中为其赋值一旦被赋值则不可
     修改(机会只有一次,我们上吧!, 不 可以再new一个)
    而被静态修饰的 final常量则不允许指定空白常量
说到静态我之前的理解有些误区:
    静态有自己独立的存储空间,静态数据的初始化在第一次被调用时加载到内存时数据的初始化完
    成后该数据一直存于静态的内存空间,随时调用,相对的也会更消耗内存资源,而一般成员则在被
    调用时初始化数据后生存于堆上,失去依赖后会被清理,更加动态的使用系统内存资源
    正是这个原因 不允许static final 修饰的空白常量的存在

构造器也是static方法,尽管并没有显示的static修饰:
    创建对象,实际是new来完成的,构造方法完成的只是他的构造
    甚至于初始化,都不是构造方法来完成的,在job04的案例中,已
    经得知每个成员变量都会在被 new 的时候以我目前不知道的
    方式来完成初始化,构造方法内有隐式的this,代表本类,它所做
    的事实际是将传入的值传给本类成员变量。通过构造方法创建一
    个新对象,这种认知我认为是错误的。但构造方也绝不是静态方法,
     它可能具有某些静态的特性
     (未经证实的对构造方法的一点分析)

每个类的编译代码都存于它自己的独立文件中,
该文件只在需要使用程序代码时才会被加载,
类的加载是在其任何可以被访问的成员第一次被访问时加载
但第一次访问只能访问该类的静态成员或者是该类的构造方法
需要注意:
    静态成员第一次被使用后才会被初始化然后进静态内存存储空间
那么构造器第一次被使用时及之后是否与静态方法一样呢?

通过静态代码块观察静态的初始化顺序:
    根据静态代码块得知是根据书写顺序的上下文初始化的
 */

public class  job18 {
    public static void main(String[] args) {
        new FinalClass();
    }
}

final class FinalClass{
    static final int count = 15;
    final int index;

    static {
        System.out.println("111");
    }
    static {
        System.out.println("222");
    }
    static {
        System.out.println("333");
    }


    public FinalClass() {
        this.index = fun();
        fun2();
    }

    public static int fun(){
        return 10;
    }

    public void fun2(){
        System.out.println("fun2");
    }

}

