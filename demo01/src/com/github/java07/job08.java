package com.github.java07;
/*
匿名类中定义字段的初始化:
    通过方法参数传递

匿名类的参数初始化达到类似构造器的效果:
    实际上与job07的案例相似
    需要纠正一点:
        匿名类使用无参构造器时是默认的构造器,
        有参构造器时,实际使用的构造器是 new Base(i) 这个Base类的构造器

真正意义上的(然而还是假的)匿名类构造器:
    {
    该域会随着对象的加载而加载,利用这种特性
    但是无法做到重载
    }
 */
public class job08 {
    public static void main(String[] args) {
        Parcel9 parcel9 = new Parcel9();

        Destination game = parcel9.destination("Game");
        System.out.println(game.readLable());

        Destination movie = parcel9.getDestination("Movie");
        System.out.println(movie.readLable());

        Base base = parcel9.getBase(47);
        base.fun();

    }
}


class Parcel9{

    public Destination destination(String alabel){
        return new Destination() {
            private String label = alabel;
            @Override
            public String readLable() {
                return label;
            }
        };
    }

    public Destination getDestination(String alabel){
        return new Destination() {
            private String label;
            {
                label=alabel;
            }
            @Override
            public String readLable() {
                return label;
            }
        };
    }


    public Base getBase(int i){
        return new Base(i) {
            @Override
            void fun() {
                System.out.println("in anonymous fun");
            }
        };
    }

}


abstract class Base{
    public Base(int i) {
        System.out.println("Base Constructor: " + i );
    }

    abstract void fun();
}