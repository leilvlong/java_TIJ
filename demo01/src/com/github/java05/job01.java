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
向下转型(编译不报错运行各种麻烦)：
        第一种：将原本是“父类引用指向导出类对象”的引用向
    下转型该对象的原本类型，这种转型基于双方都初始化后的转
    型，因而向下转型是可行且危险性较低的（儿子与爸爸面对面）
        第二种： new 对象时转型。
        危险的行为，导出类一定会具备基类的所有非私有属性，
    且导出类被初始化时会将基类初始化。
        基类却不一定具有导出类的属性(扩展),且基类被初始化
    时导出类不会被初始化,  向下转型时不会加载导出类的属性。
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
                    未维持向下的关系),除非对该继承树都初始化
                        模拟继承图: (123代表基类 每一个数字代表一个体系)
                                      123
            1.1          |            2.1            |           3.1
        1.21| 1.22       |         2.21| 2.22        |         3.21|3.22
1.211 1.212 |1.221 1.222 | 2.211 2.212 |2.221 2.222  | 3.211 3.212 |3.221 3.222
    继承体系会越来越膨胀,维护向下的关系过于消耗资源
    向下呈现扇形 向上线型
    继承接龙: A extends B extends C  extends Z
              D extends E extends F  extends Z
    A B C D E F通过关键字往上找轻而易举 Z通过关键字往下找几乎不可实现，除非每个关键字不同
    试图以这种方式向下转型编译时没错(编译器知道他们是一类的) 运行时异常 are in unnamed module of loader 'app'
    证明我的猜想是有一定道理的

继承带来属性加强：
    将原本是“父类引用指向导出类对象”的引用向下转型为该对象的原本类型这种则是建立在该对象的初始化已经完成
总结:
    转型的前提是双方都初始化且互有关联才可以做到的(需要面对面的那种关联 ('='号相连))
    Sub subs = (Sub) new BaseClass();虽然也互有关联,但是Sub是没有被初始化的

导出类初始化时为什么一定会初始化基类：
    导出类初始化时一定会初始化基类这点已经知道，造成这种机制的重要原因是因为导出类会
    依赖基类的属性与行为（若没有重写导出类会获得所有基类的公开属性，但并非在自身的代码(字节码)中），
可以假设:
    有两个继承的类,导出类完全依赖基类（即导出类没有任何内容），此时这两个类被编译成字节码
    丢到JVM虚拟机中，我们通过导出类创建一个对象，却依然可以使用关于基类的任何公共方法与属
    性，此时要明白，导出类字节码的内容只有  extends 与默认构造器这两个存在,默认构造器提供
    了该类的访问入口,extends维护了和基类之间的关系,若基类不被初始化,不让导出类依赖，导出类
    是什么也做不了的
    继承一定会让导出类依赖基类，导出类初始化时其内部一定有个super 指向基类(我猜测这是extends所带来的)
    打个不恰当的比方:
        一个好的父母总会监督孩子的成长,而java的基类是一种另类的父母,以它合理的方式帮助它的孩子

 多态:
    继承
    父类引用指向导出类对象
    重写基类方法
    运行时只能运行基类具有的方法与属性 若导出类没有则出异常
    因为导出类初始时基类会被初始,所以会使用基类的
    打个比方,引用是遥控器,对象是空调,尽管空调有 A B C D E 这些模式
    但遥控器只有 A B C 这些选项
  */


import javax.sql.DataSource;
import java.util.concurrent.Flow;

public class job01{
    public static void main(String[] args) {
        /*
        父类初始 子类不会初始
        BaseClass baseClass = new BaseClass();
        */

        /*
        多态
        BaseClass baseClass = new Sub();
        baseClass.fun();
        baseClass.print();
        baseClass.getIndex();
         */

        /*
        向下转型（面对面）
        BaseClass baseClass = new Sub();
        baseClass.fun();
        baseClass.print();
        baseClass.getIndex();
        //会出异常 遥控器没有这个功能
        baseClass.out

        // 相当于换了个能真正解放所有功能的遥控器
        Sub sub = (Sub) baseClass;
        sub.fun();
        sub.print();
        sub.getIndex();
        sub.out();
         */


        /*
        而这样编译没问题 运行会出问题
        Sub subs = (Sub) new BaseClass();
        */

    }
}



class BaseClass{
    private int index=10;

    public BaseClass() {
        //System.out.println("baseClass 初始");
    }

    public BaseClass(int index) {
        this.index = index;
    }

    public void fun(){
        System.out.println("baseClass fun");
    }

    public void print() {
        System.out.println("baseClass print");
    }

    public void getIndex() {
        System.out.println(index);
    }
}

class Sub extends BaseClass{
    public Sub() {
        //System.out.println("sub 初始");
    }

    public Sub(int index) {
        super(index);
    }

    @Override
    public void fun() {
        System.out.println("sub fun");
    }

    @Override
    public void print() {
        System.out.println("sub print");
    }

    public void out(){
        System.out.println("sub out");
    }
}

class sub2 extends BaseClass{
    private boolean status = false;

    public sub2(boolean status) {
        this.status=status;
        if (this.status != true){
            throw new ExceptionInInitializerError("No initialization");
        }
        System.out.println("sub2 初始");
    }
}



