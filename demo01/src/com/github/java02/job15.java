package com.github.java02;

import jdk.swing.interop.SwingInterOpUtils;

/*
结合使用组合与继承:
    虽然编译器在你初始化子类时必定会初始化基类(这就确保你必须合理的处理父子间的关系)
    但是作为成员变量在另一个类中做组合关系时,只要你不去使用它,不初始化也没关系
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