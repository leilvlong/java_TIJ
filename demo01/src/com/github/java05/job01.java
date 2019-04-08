package com.github.java05;

/*
组合: 将现有类的实现功能拿来复用作为底层实现的一部分（宝马车在大马路上奔驰 奔驰车在大马路上奔驰）
继承: 遵循基类的接口规范而扩展功能与属性 (机械式键盘 塑料薄膜式键盘。导电橡胶式键盘。电容式键盘。无线键盘。)
向上转型： 因为继承树中基类在上边，向下叫继承，向上就自然叫向上转型
多态实现手段: 动态绑定(后期绑定):
            又叫运行时绑定，在运行时根据对象间的关系绑定，
        编译器在编译时并不关注对象的类型，但会关注对象之间
        的关系（extends与 implements),通过这种联系,虽然编译
        器依然不知道对象的类型（也不关注）,但至少知道他们是
        一伙的（得出继承树）
            假如有Base类与SubClass:
            Base sub = new SubClass()
            依然是访问SubClass的构造器，通过该类extends关键字确
            认他们的关系,允许向上转型，运行时使用SubClass的方法，
            当导出类没有覆写方法时,则使用基类的
             （对动态绑定机制的的分析尝试）
向下转型：
        危险的行为，导出类一定会具备基类的所有非私有属性，
    且导出类被初始化时会将基类初始化。
       基类却不一定具有导出类的属性(扩展),且基类被初始化时
    被初始化时导出类不会被初始化,   向下转型时不会加载导出
    类的属性,因而若使用导出类的属性会抛出异常。
        以下案例足以说明问题, 尝试对这种机制的分析:
                导出类初始化时基类一定会被初始化,这个多次案例以说明
            并且也可以为动态绑定提供先天条件。
                基类初始化时，导出类并不会初始化，可能java作者设计时
            觉得并不需要向下的动态绑定又或者是底层实现向上的动态绑定
            消耗较少,向下的动态绑定过于消耗资源导致的
                如果是后者,可以设想:
                    一个基类会有多个继承体系,但每个继承体系互不干涉
                    此时一个继承体系的某个节点需要被使用,只需向上初
                    始化即可，而若想完成向下转型,因为对向下转型的未
                    知(extends与 implements虽然维持了向上的关系,却并
                    未维持向下的关系),则需要对该继承树初始化
                        模拟继承图: (123代表基类 每一个数字代表一个体系)
                                      123
            1.1          |            2.1            |           3.1
        1.21| 1.22       |         2.21| 2.22        |       3.21| 3.22
1.211 1.212 |1.221 1.222 | 2.211 2.212 |2.221 2.222  | 3.211 3.212 |3.221 3.222
    继承体系会越来越膨胀,维护向下的关系过于消耗资源
    向下呈现扇形 向上线型
    继承接龙: A extends B extends C  extends Z
              D extends E extends F  extends Z
    A B C D E F通过关键字往上找轻而易举 Z通过关键字往下找几乎不可实现(除非每个关键字不同)
 */


import javax.sql.DataSource;

public class job01{
    public static void main(String[] args) {
        Fu fu = new Zi();


    }
}

class Fu{
    public Fu() {
        System.out.println("fu 初始");
    }

    void fun1(){
        System.out.println("fun1");
    }

    void fun2(){
        System.out.println("fun2");
    }
}

class Zi extends Fu{
    public Zi() {
        System.out.println("zi 初始");
    }

    void fun1(){
        System.out.println("ZI fun1");
    }

    void fun2(){
        System.out.println("ZI fun2");
    }

    void fun3(){
        System.out.println("fun3");
    }
}