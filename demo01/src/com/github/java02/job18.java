package com.github.java02;

/*

final修饰变量的一些细节:
     final允许指定空白常量 但是在使用前需要为其赋值（通过构造方法）
    而被静态修饰的 final常量则不允许指定空白常量（除非在静态代码块中为该常量赋值）

构造器也是static方法,尽管并没有显示的static修饰:
        java程序只有当类的静态成员或者构造器被访问时才会
    被加载。
        创建对象，实际是new来完成的，构造方法更多的作用是
    是为该类的加载提供一个加载入口（在该类的任何静态成员
    未被使用时）以及为该类的成员变量传递创建对象时指定的初
    始化值这两个作用。
        从job04的案例中已经得知,在new对象后,构造器尚未起作
    用时成员的变量初始化便已经完成了
        从对类的加载来看,static域与构造器有着一样的功能（
     所以我斗胆猜测TIJ作者才会说构造器是静态方法，他指的是在
     类加载功能上），但是构造器更为强大，可以给该类的所有非
     静态成员变量赋值,而静态只能在本类的静态上下文中使用,而
     构造器却可以在本类的任何地方使用
         静态成员变量只会有一次初始化，初始化完成后会有自己的
     静态内存存储区域，会随着程序运行时对它的修改而修改，但不
     会再被初始化。既然构造器也能为类的加载提供入口，那么是否
     是因为构造器也是在初始化后（第一次被访问）生存与静态内存
     区域而非随着对象生存与堆上呢？我认为是生存在静态内存区域
     ，不然每次创建新对象都要初始化该类的构造器未免太蠢。
        除非java还专为类的构造器提供了一片内存空间
        以上是对构造器的一点分析。
        仔细观察静态方法的调用与构造器的调用：
            String.valueOf()
            new String()   //假如去掉关键字new呢(我当然知道会报错)

从论坛上找的关于对象创建流程的看法:
    检测类是否被加载没有加载的先加载→为新生对象分配内存→将分配到的内存空间都初始化为零值→对对象进行必要的设置→执行<init>方法把对象进行初始化

通过静态代码块观察静态的初始化顺序:
    根据静态代码块得知是根据书写顺序的上下文初始化的

在构造器中 new 自己:
    不可逆递归 哈哈

子父类的构造关系:
    子类构造器被访问时，通过extends去访问他的父类。
    如果该父类依然有extends，则继续加载该类的父类。
    以此类推，直到该类的所有继承体系类的构造器都能得到初始化（该类的所有继承体系类都能得到初始化）
创建对象成员变量的初始化：
    所有基本类型成员变量被设置为默认值，所有引用变量被设置为null--通过将对象内存设置为二进制零值一举生成的
 */

import java.util.Random;

public class  job18 {
    public static void main(String[] args) {


        /*/
        /第一次访问该类以及静态成员
        System.out.println(FinalClass.num);
        // 第二次访问
        System.out.println(FinalClass.num);
        // 静态代码块的输出只有一次
        */

        //通过构造器访问
       /*
        new FinalClass();
        System.out.println(FinalClass.num);
        System.out.println(FinalClass.num);
        // 静态代码块的依然输出只有一次
        num的值为发生任何改变(通过随机样本赋值的)
        */


    }
}

final class FinalClass{
    static final int count = 15;
    static final int num ;
    final int index;

    static{
        num = new Random().nextInt(100)+1;
    }
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
        //new FinalClass();
    }


    public static int fun(){
        return 10;
    }

    public void fun2(){
        System.out.println("fun2");
    }

}

