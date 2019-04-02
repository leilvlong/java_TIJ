package com.github.java02;

/*
静态的初始化(光靠看试图看懂真是太蠢了):
        静态成员的数据初始化程序生命运行周期中只有一次初始:
            从下面的例子中也可以看到静态优先于类构造器的初始化而优先初始化
            而且静态数据只在被调用时初始化,而静态代码块则会在加载进JVM虚拟
            机内存时会随着类的加载而加载,自动执行,且只会执行一次
(通过分别注释可以直观的看到效果:
    1.
    2.
    3.
        )
 */
public class job05 {
    public static void main(String[] args) {
        // 2.
        //new Cupboard();
        //new Cupboard();

        //3.
        //table.tableFun();
        //cupboard.cupboardFun(2);
    }

    // 1.
    static Table table = new Table();
    static Cupboard cupboard = new Cupboard();
}


class Bow{
    Bow(){
        System.out.println("This is Bow");
    }

    void bowFun(){
        System.out.println("bowFun" );
    }

}

class Table{
    static  Bow bow1 = new Bow();

    Table(){
        System.out.println("table 构造方法");
        bow2.bowFun();
    }

    void tableFun(){
        System.out.println("tableFun" );
    }

    static Bow bow2 = new Bow();

}

class Cupboard{
    Bow bow3 = new Bow();
    static Bow bow4 = new Bow();

    Cupboard(){
        System.out.println("cupboard 构造方法");
        bow4.bowFun();
    }

    void cupboardFun(int num){
        System.out.println("cupboardFun: " + num);
    }

    static Bow bow5 = new Bow();
}

