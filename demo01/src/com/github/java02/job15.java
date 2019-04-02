package com.github.java02;

import jdk.swing.interop.SwingInterOpUtils;

/*
结合使用组合与继承:
    虽然编译器在你初始化子类时必定会初始化基类(这就确保你必须合理的处理父子间的关系)
    但是作为成员变量在另一个类中做组合关系时,只要你不去使用它,不初始化也 没关系

在组合与继承之间的选择:
    在组合与继承之间都允许放置子对象,组合是显示的这样做(成员引用实例对象变量),
    而继承则是隐式的这样做(继承)
    而组合通常用于想在新类中直接使用某个类的功能达到自己的目的:
        例如员工信息类必然会有name属性,最合适的方式便是以字符串来表示,
        而字符串也是个String对象,那么这个name属性与这个类本身就可以说
        是个组合关系,
        甚至于每个属性都和这个类本身是组合关系(组成一类新的事物 缝合怪?)
        由此可以确定组合关系建立在当确实需要某一类数据类型(某一对象方法)
        但该数据类型是已被实现的,可以拿来就用的而非接口.
        接口这种的更适合去另外实现或扩展(继承)
is-a 与 has-a:
    是一个: 继承
    有一个: 组合


再论组合与继承:
        面向对象编程中,生成和使用程序代码最有可能采用的方法就是直接将数据和方法包装进一个类中
    并使用该类对象。也可以使用组合的方式使用现有类来开发新的类。面向对象尽管具有继承的特性
   但并不意味要尽量使用继承来扩展程序，反而应该慎用继承
       到底是使用继承还是组合，最清晰的判断方式就是新类是否需要向上转型，如果必须向上转型，则继
   承是必要的（暗示使用继承时 确保本质确确实实是一样的，而非看起来一样），否则应该考虑是否使用组
   合更为合适
 */


class Custom{
    public Custom() {
        System.out.println(" This is Custom");
    }
}

public class job15 extends Custom {
    private Spoon sp;
    private Fork fk;
    private Knife kn;
    private DinnerPlate dp;


    public job15() {
        sp = new Spoon();
        fk = new Fork();
        kn = new Knife();
        dp = new DinnerPlate();
        System.out.println(" This is Job15");
    }

    public static void main(String[] args) {
        job15 j15 = new job15();
    }
}



class Plate{
    public Plate() {
        System.out.println(" This is Plate");
    }
}

class DinnerPlate extends Plate{
    public DinnerPlate() {
        System.out.println(" This is DinnerPlate");
    }
}

class Utensil{
    public Utensil() {
        System.out.println(" This is Utensil");
    }
}

class Spoon extends Utensil{
    public Spoon() {
        System.out.println(" This is Spoon");
    }
}

class Fork extends Utensil{
    public Fork() {
        System.out.println(" This is Fork");
    }
}

class Knife extends Utensil{
    public Knife() {
        System.out.println( " This is Knife");
    }
}