package com.github.java06;
/*
该案例中 Hero对象可以给每一个 job07的静态方法执行并获得正确的行为:
    多继承接口的同时也被允许向上转型为多基类型
以下案例展示使用接口的核心原因:
    1. 为了能够向上转型为多个基类型(获取灵活性)
    2. 防止对该抽象接口创建对象(与抽象基类原则相同)
前面提到过:
    class 更像是一套数据类型规范(自定义数据类型)  is-a
    而接口则更像是一套行为模式规范(多个对象都具有类似的行为)  is-like-a
使用时:
    当不携带任何成员变量(组合关系)时都应当使用接口
    java8允许接口中存在带有方法体的方法了
    在java8以后对接口的这种支持特性更为明显(几乎没有太多区别了)
    补充: 接口的普通方法是没有方法体的, 而抽象基类可能会有
 */
public class job07 {
    public static void main(String[] args) {
        Hero hero = new Hero();
        fun1(hero);
        fun2(hero);
        fun3(hero);

    }

    public static void fun1(CanFight canFight){
        canFight.fight();
    }

    public static void fun2(CanSwim canSwim){
        canSwim.swim();
    }

    public static void fun3(Canfiy canfiy){
        canfiy.fiy();
    }
}

// 抽象类拥有有方法体的 public 修饰的普通方法
abstract class CanClass{
    public  void fun(){
        System.out.println("hahah");
    }

    abstract void fun(String string);
}

/*  一下代码在java9以后完全行得通
interface Port1{
    // 接口的常量 默认有final修饰
    String name="zhang";

    // 接口的抽象方法 默认有 public abstract修饰
    void abstractFun();

    //接口的无参默认方法 默认有 public修饰
    default void defaultFun(){
        System.out.println("与接口23同名");
    }
    // 接口的有参默认方法 默认有 public 修饰
    default int defaultFun1(String num){
        System.out.println("接口1的字符串参数 默认方法: "+ num  + "  ");
        return Integer.parseInt(num);
    }

    //接口的无参静态方法 默认有 public修饰
    static void staticFun(){
        System.out.println("接口1的无参静态方法");
    }
    //接口的有参静态方法 默认有 public修饰
    static void staticFun1(String num){
        System.out.println("接口1的有参静态方法: " +num );
    }
    // 接口的私有静态方法
    private static void privateStaticFun(){
        System.out.println("接口1的私有静态方法");
    }

    // 接口的普通私有方法
    private void privateDefaultFun1(){
        System.out.println("接口1的普通私有方法");
    }
}*/



// Hero 多接口继承多基类向上转型
class Hero implements CanFight, Canfiy, CanSwim{

    @Override
    public void fight() {
        System.out.println("Fight");
    }

    @Override
    public void swim() {
        System.out.println("Swim");
    }

    @Override
    public void fiy() {
        System.out.println("Fiy");
    }
}



interface CanFight{
    void fight();
}


interface CanSwim{
    void swim();
}


interface Canfiy{
    void  fiy();
}