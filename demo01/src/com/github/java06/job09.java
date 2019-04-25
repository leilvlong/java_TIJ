package com.github.java06;
/*
通过继承可以扩展接口:
    java拒绝class的菱形继承使得动态绑定总能的到正确的行为
    java允许接口的菱形继承,因为接口不具备方法体,由导出类实现,依然能得到正确的行为
    c++与python允许class多继承带来的广度优先或深度优先执行方法脑阔疼
 */
public class job09 {
    public static void main(String[] args) {
        Dragon dragon = new Dragon();
        fun1(dragon);
        fun2(dragon);

        Horror horror = new Horror();
        fun1(horror);
        fun2(horror);
        fun3(horror);
    }

    public static void fun1(Monster monster){
        System.out.println("向上转型为Monster_________________");

        monster.monster();
    }

    public static void fun2(Danger danger){
        System.out.println("向上转型为Danger_________________");
        danger.monster();
        danger.danger();
    }

    public static void fun3(VeryBad veryBad){
        System.out.println("向上转型为VeryBad_________________");
        veryBad.monster();
        veryBad.danger();
        veryBad.lethal();
        veryBad.veryBad();
    }

}


interface Monster{
    void monster();
}


interface Danger extends Monster{
    void danger();
}


interface Lethal{
    void lethal();
}


class Dragon implements Danger{

    @Override
    public void monster() {
        System.out.println("Dragon: Interface Monster");
    }

    @Override
    public void danger() {
        System.out.println("Dragon: Interface Danger");
    }
}


interface VeryBad extends Danger, Lethal{
    void veryBad();
}


class Horror implements VeryBad{

    @Override
    public void monster() {
        System.out.println("Horror: Interface Monster");
    }

    @Override
    public void danger() {
        System.out.println("Horror: Interface Danger");
    }

    @Override
    public void lethal() {
        System.out.println("Horror: Interface Lethal");
    }

    @Override
    public void veryBad() {
        System.out.println("Horror: Interface VeryBad");
    }
}