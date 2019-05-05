package com.github.java07;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/*
在外部类Parcel1中:
   1.在ship方法中可以为两个内部类创建对象
    并且与正常的类创建对象没什么区别

   2.无法通过静态方法获取(加上静态修饰以后就就可了)
    跟修饰符无关,内部类对象生存与堆上(通过比较得知 若生存于静态区则会随运行变化而变化,但对象本身不会发生改变)
    单例又是另外一回事了
    而且普通内部类属于对象,对象又是由外部类的构造器被访问才诞生的,只有当外部类被构造后内部类才提供被访问的入口
    为防止难以排查的空指针异常,这种编译期报错很有必要
    当然静态内部类又是另外一个说法了,毕竟static修饰的会随着类的被访问而加载(准备好了可使用而非立即产出)

   3.普通方法可以获取静态修饰的内部类

   4.从获取对象引用格式上看必须指明该内部类的归属类
    外部内.内部类

   5.一种奇怪的现象
   内部类被设定为私有的成员可以直接在本类中被 对象引用.私有成员 的方式获取值
   而在非本类中只能通过类似getter 与setter的方法获取
   推测:
    内部类属于外部类对象的一部分
    内部类与外部类有直接的联系关系
 */
public class job02 {
    public static void main(String[] args) {
        Parcel2 parcel2 = new Parcel2();
        parcel2.ship("Parcel2");

        Parcel2.Contens p2Contens = parcel2.toContens();
        Parcel2.Destination p2Destination = parcel2.toDestination("Destination");

        /*
        通过静态方法获取比较
        Parcel2.Contens contens1 = Parcel2.getContens();
        Parcel2.Contens contens11 = Parcel2.getContens();
        System.out.println(contens1==contens11);

        Parcel2.Destination destination1 = Parcel2.getDestination("Parcel2");
        Parcel2.Destination destination11 = Parcel2.getDestination("Parcel2");
        System.out.println(destination1==destination11);

        //通过对象方法获取比较
        Parcel2.Contens contens2 = parcel2.toContens();
        Parcel2.Destination destination2 = parcel2.toDestination("Parcel2");
        System.out.println(contens1==contens2);
        System.out.println(destination1==destination2);*/
    }
}


class Parcel2{
    private String describe = "parcel2";

    class Contens{
        private int i = 12;

        public int value(){
            return i;
        }
    }

    class Destination{
        private String label;

        public Destination(String label) {
            this.label = label;
        }

        String readLabel(){
            return label;
        }
    }

    public Contens toContens(){
        return new Contens();
    }

    public Destination toDestination(String lable){
        return new Destination(lable);
    }


/*    static class Contens{
        private int i = 12;

        public int value(){
            return i;
        }
    }

    static class Destination{
        private String label;

        public Destination(String label) {
            this.label = label;
        }

        String readLabel(){
            return label;
        }
    }*/

/*    public static Contens getContens(){
        return new Contens();
    }

    public static Destination getDestination(String lable){
        return new Destination(lable);
    }*/

    public void ship(String label){
        Contens c = new Contens();
        Destination d = new Destination(label);
        System.out.println("innerClass Content member variable i: " + c.i);
        System.out.println("innerClass Destination method readLable: " +d.readLabel());
    }
}