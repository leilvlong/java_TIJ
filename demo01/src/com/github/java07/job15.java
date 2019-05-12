package com.github.java07;

/*
job14已经提到过java拒绝菱形继承该如何通过内部类实现多继承,尤其是继承对象为抽象类时
1.内部类是依赖于外部类对象创建的对象,但是内部类却可以拥有多个实例对象
2.在单个外部类下可以拥有多个内部类,多个内部类各自实现不同的接口,继承别的类以实现多继承
3.在单个外部类下可以拥有多个内部类,多个内部类以不同的方式实现同一个接口或者继承扩展一个类
4.内部类就是一个独立的实体,通过拥有单独的字节码文件就可足以做出这种判断
5.创建内部类对象的时刻并不依赖于外围内对象的创建:
     构建对象需要提供构造器进行访问
     遗憾的是只有当外部类对象创建后,内部类的构造器才会加载好
     换言之,内部类的构造器在外部类的堆内存空间中
     内部类创建所依赖的关键条件在外部类的内存空间中
     而非创建内部类是由外部类完成的
 */


public class job15 {
    static void takes(IsD d){
        d.fun();
    }

    static void takes(IsE e){
        System.out.print("Producer acquisition execution: ");
        e.getIsD().fun();
    }


    public static void main(String[] args) {
        IsDE isDE = new IsDE();

        IsDE.EEIsE1 eeIsE1 = isDE.new EEIsE1();
        IsD isD1  = eeIsE1.getIsD();

        IsDE.EEisE2 eeIsE2 = isDE.new EEisE2();
        IsD isD2 = eeIsE2.getIsD();

        takes(isD1);
        takes(isD2);

        takes(eeIsE1);
        takes(eeIsE2);
    }
}


abstract class IsD{
    abstract void fun();
}

abstract class IsE{
    abstract IsD getIsD();
}


class IsDE {

    private class EEIsD extends IsD{
        @Override
        void fun() {
            System.out.println("Class IsDE method fun");
        }
    }



    class EEIsE1 extends IsE{
        @Override
        IsD getIsD() {
            return new IsD() {
                @Override
                void fun() {
                    System.out.println("Lambda Class method fun");
                }
            };
        }
    }

    class EEisE2 extends IsE{
        @Override
        IsD getIsD() {
            return new EEIsD();
        }
    }
}

