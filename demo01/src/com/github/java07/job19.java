package com.github.java07;

/*
关于继承中的内部类覆盖问题:
    通过job06中对内部类字节码文件的分析就可以知道每个内部类都是单独的实体.不存在覆盖这一说
    但是可以通过继承去覆盖内部类中的所有普通方法与成员,这从某种程度上来讲也是覆盖吧

以下案例执行流程分析(对之前继承知识复习):
    BigEgg bigEgg = new BigEgg();
    1. 导出类构造器被访问,但是不执行(new对象时最晚干活的那个)
    2. 基类构造器被访问,成员开始初始化输出:Out Class Egg inner Class Yolk
    3. 基类构造器开始干活:Class Egg
    4. 一切准备就绪,导出类构造器开始干活,
        new了自身的内部类并传入继承而来的insertYolk方法中
    5. new自身的内部类的时候内部类的构造器被访问,但是不执行
    6. 基类内部类初始化并输出:Out Class Egg inner Class Yolk
    7. 基类内部类的初始化完成,导出类的构造器开始干活
    8. 输出:Out Class BigEgg inner Class Yolk
    9. 执行继承而来的gun方法: Out Class BigEgg inner Class Yolk method


 */

public class job19 {
    public static void main(String[] args) {
        BigEgg bigEgg = new BigEgg();
        bigEgg.gun();
    }
}


class Egg{
    private Yolk yolk = new Yolk();

    public Egg() {
        System.out.println("Class Egg");
    }

    class Yolk{
        public Yolk() {
            System.out.println("Out Class Egg inner Class Yolk");
        }

        public void fun(){
            System.out.println("Out Class Egg inner Class Yolk method");
        }
    }

    public void insertYolk(Yolk yy){
        yolk = yy;
    }

    public void gun(){
        yolk.fun();
    }
}


class BigEgg extends Egg{
    class Yolk extends Egg.Yolk{
        public Yolk() {
            System.out.println("Out Class BigEgg inner Class Yolk");
        }

        public void fun(){
            System.out.println("Out Class BigEgg inner Class Yolk method");
        }
    }

    public BigEgg() {
        insertYolk(new Yolk());
    }
}