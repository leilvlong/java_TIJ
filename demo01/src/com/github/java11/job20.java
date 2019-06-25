package com.github.java11;


/*
interface:
    interface的一个重要目标是隔离构件,降低耦合性,但是通过类型信息,这种耦合性还是会传递出去的
        吐槽:
            这种解耦思想的发展趋势越演越烈,有利于分工协作,共同开发,并隐藏实现
            但过多的间接性与隔离性导致服务启动时出现bug的定位也越发的不友好-

以下案例中:
    类B实现了接口A,并创建了类型A的对象,
    但是通过instanceof,可以轻易的将其转型为类型B的对象,并使用B的所有可调用的属性与行为
    假如不希望客户端程序员任意的上下转型滥用实现类的方法,可以限制访问权限
    即:
        将实现类设置为包权限,通过一个public的类去返回它的接口类型的引用
        这样在包外就不会有接口类型的实现类,也就不存在转型问题了
        注意;接口必须是public修饰的(因为我图省事所以写在一个文件中 但是有单独测试过这种访问权限的控制方式)

 通过访问权限限制转型(对以上的梳理):
    1)假使有一个 公共接口A   一个 实现类B
    2)B是public的
    3)那么客户端程序员通过查看A的实现类可以任意转型
    4)转型后可以任意访问实现类的属性与方法
    5)作为服务端程序员要杜绝这种行为
    6)将实现类B设置为包访问权限
    7)通过public的工厂类返回公共接口A的引用
    8)即便客户端程序员依然可以通过查看A的实现类
      此时也无法转型,在包外使用A的引用并没有可供转型的实现类
 */

public class job20 {
    public static void main(String[] args) {
        A a = new B();
        if (a instanceof B){
            B b = (B)a;
            b.hello();
        }

    }

    /**
     * 本文件中唯一的公共类提供的创建对象的方法
     * 这样就可以避免包外的转型问题
     * 避免main方法中出现的那种情形
     * @return
     */
    public static A makeA(){
        return new B();
    }
}


interface A{
    void fun();
}


class B implements A{

    @Override
    public void fun() {
        System.out.println("Class B method Fun");
    }

    public void hello(){
        System.out.println("Say hello");
    }

}