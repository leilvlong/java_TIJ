package com.github.java02;

/*
静态的初始化(光靠看试图看懂真是太蠢了):
    静态方法会随着类的初始化而初始化(程序生命运行周期中只有一次初始):
        在job05(包含main(程序入口中)的类中以静态成员变量的方式初始化 Table
        Cpuboard 而这两个类中`又分别对Bow类进行了各种不同的初始化
        我只是在包含main的类中添加这两个静态成员 java编译器在编译后交给jvm虚拟机时都会找到他们
        然后将他们输出(初始化))
        而且静态只在必要时(使用时)进行初始化,我若不在包含main方法的类中添加Table与Cupboard的
        静态引用变量,那么什么都不会发生
        而且,我们也得知: 静态的初始化比普通成员的初始化来的跟早
            (之前的分析已经得出 类中的成员变量都会被初始化(为指定则用默认值,指定则使用指定值))
(通过分别注释可以直观的看到效果:
    1.
    2.
    3.
        )
 */
public class job05 {
    public static void main(String[] args) {
        // 2.第二次执行时及以后都不注释
        new Cupboard();
        new Cupboard();

        //3. 第三次执行才不注释
        table.tableFun(1);
        cupboard.cupboardFun(2);
    }

    // 1.第一次执行不注释及以后都不注释
    static Table table = new Table();
    static Cupboard cupboard = new Cupboard();
}


class Bow{
    Bow(int num){
        System.out.println("Bow; " + num);
    }

    void bowFun(int num){
        System.out.println("bowFun: " + num);
    }
}

class Table{
    static  Bow bow1 = new Bow(1);

    Table(){
        System.out.println("table 构造方法");
        bow2.bowFun(1);
    }

    void tableFun(int num){
        System.out.println("tableFun" + num);
    }

    static Bow bow2 = new Bow(2);

}

class Cupboard{
    Bow bow3 = new Bow(3);
    static Bow bow4 = new Bow(4);

    Cupboard(){
        System.out.println("cupboard 构造方法");
        bow4.bowFun(2);
    }

    void cupboardFun(int num){
        System.out.println("cupboardFun: " + num);
    }

    static Bow bow5 = new Bow(5);
}

