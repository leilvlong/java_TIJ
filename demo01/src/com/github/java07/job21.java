package com.github.java07;
/*
局部类与匿名内部类:
    定义在各种作用域中,本质不会发生变化
    局部类并不能称之为外部类的一部分了,因此不能有访问说明符
    但是可以访问当前代码块的常量以及外围类的所有成员

他们的使用功能是一样的:
    1.局部内部类可以有真正意义上的构造器,而匿名内部类没有
    2.局部内部类可以反复使用(指创建多个对象)
 这种本质python的函数闭包很相似,外部可以为内部提供环境
 但不同的是java以类为单位:
    每个类都是单独的实体,都可以有自己的行为与数据
    一个外部类可以为多个内部类以及局部内提供环境,
    可以通过多个内部类实现多继承,而不用担心菱形继承






 */
public class job21 {
    public static void main(String[] args) {
        LocalInnerClass local = new LocalInnerClass();

        Counter
                c1 = local.getCounter("Local inner: "),
                c2 = local.getCounter2("Anonymous inner:");
        for (int i = 0; i < 5; i++) {
            System.out.println(c1.next());
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(c2.next());
        }

    }
}


interface Counter{
    int next();
}


class LocalInnerClass{
    private int count = 0;

    Counter getCounter(String name){
        class LocalCounter implements Counter{

            public LocalCounter() {
                System.out.println("Class LocalCounter");
            }

            @Override
            public int next() {
                System.out.print(name);
                return count++;
            }
        }


        return new LocalCounter();
    }

    Counter getCounter2(String name){
        return new Counter() {
            {
                System.out.println("Class Anonymous");
            }

            @Override
            public int next() {
                System.out.print(name);
                return count++;
            }
        };
    }
}


