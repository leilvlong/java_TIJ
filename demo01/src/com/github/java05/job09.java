package com.github.java05;

import java.util.Random;

/*
以下案例涉及到的机制:
    1.在父类的构造器中使用多态(从输出结果来看确确实实是多态的)
    2.却得到一个意想不到的结果, radius的值既非1 亦非通过构造器赋值的5 而是对象初始化时的0
    3.通过多态的结果，在导出类创建对象的时候，该方法已被加载，无论是从书写上下文又或者是机制上来说 radius都不该为0
从结果导向来看:
    1.在其它任何事物发生之前，将分配给对象的内存空间初始化为二进制的零
    2.在继承体系中，基类的初始化早于导出类（在job07中已经明确知道）
    3.在前文中已经知道构造器是最后才开始工作的,又由于1的原因 此时raduis的值为0
    4.此时的draw获取了该值 结果自然为0
    5.通过以上流程 我更加确认 构造器在创建对象的过程中除开系统服务它只提供了类访问入口以及为成员变量赋值
    6.实际的初始化是由 new 关键字来完成的(包括对成员变量与方法) 因为原因1 这比任何形式的为成员变量赋值来的更早
    7.此时才会按照书写上下文来初始化
打个不恰当的比方:
    在你的眼前有一张白纸,尽管你准备用来画画,但此时是什么都没有的,你用你的眼睛去获取了这个什么都没有
    但这张纸有返回被画的事物的功能(你可以用眼睛看 基类构造器中的draw方法相当于我的眼睛),此时你去获取它,自然什么都没有
这种机制至少有一个优点:
    在非继承体系中可以确保得到初始化后的对象,而且运行时也很容易发现问题来自哪里
    而在继承体系中则要尽量避免这样做（当导出类过多时很难定位问题所在）
 */

public class job09 {
    public static void main(String[] args) {
        new RoundGlyph(5);
    }
}


class Glyph{

    void draw(){
        System.out.println("Glyph draw");
    }

    public Glyph() {
        System.out.println("Glyph before draw");
        draw();
        System.out.println("Glyph after drwa");
    }
}


class RoundGlyph extends Glyph{
    private int radius = 1;

    public RoundGlyph(int r) {
        radius = r;
        System.out.println("RoundGlyph radius: " + radius);
    }

    @Override
    void draw() {
        System.out.println("RoundGlyph draw radius: "+ radius);
    }
}