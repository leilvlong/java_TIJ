package com.github.java07;

/*
泛定义内部类:
    不是指泛型,而是;
        定义在方法中
        实现了接口的匿名类
        方法作用于内
        等等等等...........
 */

/*
    1.类作为方法的一部分,并被方法返回,这与被设置了权限的外部类的内部类通过方法返回对象很像
    2.但是需要注意的是能直接访问这个类的只能是在这个方法中,现在使用的实际是通过这个方法返回的引用
        注意: 定义在外部类之下的内部类可以通过一下方式获取引用
        Parcel5.PDestination innerClass = new Parcel5().new PDestination("InnerClass");
        定义在方法中使得这种操作不可能
    3.对象的生存空间在堆上,这意味着方法执行完毕后该对象在有引用依然存在的情况下依然是可用的
        从以下的一系列案例是可以看出来的
    4.从这里也能看出不管是被定义在哪里的类,都会生存在堆上 以下是该对象字节码文件;
        1.定义在外部类下
            out\production\demo01\com\github\java07\Parcel5$PDestination.class
        2.定义在方法中
            out\production\demo01\com\github\java07\Parcel5$1PDestination.class
        3.定义在方法中
            out\production\demo01\com\github\java07\Parcel5$2PDestination.class
        4.定义在静态方法中
            out\production\demo01\com\github\java07\Parcel5$3PDestination.class
        5.定义在if语句中
            out\production\demo01\com\github\java07\Parcel5$4PDestination.class
            定义在另一个方法中的if语句中 为调用依然产生字节码文件
            out\production\demo01\com\github\java07\Parcel5$5PDestination.class
        6.定义在构造时只会加载一次的域内
            out\production\demo01\com\github\java07\Parcel5$6PDestination.class
从中应该也能(必须能)看出内部类的的字节码文件的命名规则:
    定义在外部类下的内部类:
        外部内名$内部类名
        与修饰符无关 不允许同名
    定义在方法中或语句作用域内
        外部类名$升序编号(id) + 内部类名
        与修饰符无关
值得注意的匿名内部类的字节码文件:
    定义在job06中的两个线程的匿名内部类的文件字节码:
        只以序号标识
    out\production\demo01\com\github\java07\job06$1.class
    out\production\demo01\com\github\java07\job06$2.class
*/

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class job06 {
    public static void main(String[] args) {
        Parcel5.PDestination innerClass = new Parcel5().new PDestination("InnerClass");
        System.out.println(innerClass.readLable());


        Parcel5 parcel5 = new Parcel5();

        //作用在方法域内
        Destination movie = parcel5.getdestination("Movie");
        System.out.println(movie.readLable());

        // 作用在方法域内
        Destination game = parcel5.destination("Game");
        System.out.println(game.readLable());

        Destination aStatic = Parcel5.staticDestination("Static");
        System.out.println(aStatic.readLable());

        // 作用在语句域内
        Destination getDestination1 = parcel5.isGetDestination(true);
        System.out.println(getDestination1.readLable());


        // 在Parcel5构造对象时会被加载一次的域内的对象引用 为该对象的引用另外获取一个对象引用
        System.out.println(parcel5.instancesVariable.readLable());
        parcel5.instancesVariable = getDestination1;
        System.out.println(parcel5.instancesVariable.readLable());

        //静态的外部类下的内部类
        Parcel5.PContents pContents = new Parcel5.PContents();
        System.out.println(pContents.value());


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hahahaha");
            }
        }
        ).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hehehehe");
            }
        }
        ).start();
    }
}


class Parcel5{
    /**
     * 外部类的普通内部类
     */
    class PDestination implements  Destination{
        private String lable;

        public PDestination(String lable) {
            this.lable = lable;
        }

        @Override
        public String readLable() {
            return lable;
        }
    }


    /**
     *  局部内部类(方法中的内部类)
     * @param s 构造局部内部类的参数
     * @return 局部内部类的接口引用
     */
    public Destination destination(String s){
        class PDestination implements  Destination{
            private String lable;

            public PDestination(String lable) {
                this.lable = lable;
            }

            @Override
            public String readLable() {
                return lable;
            }
        }
        return new PDestination(s);
    }

    /**
     * 类同名方法不同名的局部内部类
     * @param s 构造局部内部类的参数
     * @return 局部内部类的接口引用
     */
    public Destination getdestination(String s){
        class PDestination implements  Destination{
            private String lable;

            public PDestination(String lable) {
                this.lable = lable;
            }

            @Override
            public String readLable() {
                return lable;
            }
        }
        return new PDestination(s);
    }

    /**
     * 语句作用域内的内部类
     * @param b
     * @return
     */
    public Destination isGetDestination(boolean b){
        if (b){
            class PDestination implements  Destination{
                private String lable;

                public PDestination(String lable) {
                    this.lable = lable;
                }

                @Override
                public String readLable() {
                    return lable;
                }
            }
            return new PDestination("isGetDestination? " + String.valueOf(b));
        }
        return null;
    }

    public static Destination staticDestination(String s){
        class PDestination implements  Destination{
            private String lable;

            public PDestination(String lable) {
                this.lable = lable;
            }

            @Override
            public String readLable() {
                return lable;
            }
        }
        return new PDestination(s);
    }

    /**
     *  该方法验证一个猜想,与域(是否被执行)没关系 类在编译时不管在哪都会产生字节码文件
     * @param b
     * @param lable
     * @return
     */
    public Destination isGetDestination(boolean b, String lable){
        if (! b){
            class PDestination implements  Destination{
                private String lable;

                public PDestination(String lable) {
                    this.lable = lable;
                }

                @Override
                public String readLable() {
                    return lable;
                }
            }
            return new PDestination(lable);
        }
        return null;
    }

    /**
     * 对象初始化后会跟着初始化一次的域内
     */
    public Destination instancesVariable;

    {
        class PDestination implements  Destination{
            private String lable;

            public PDestination(String lable) {
                this.lable = lable;
            }

            @Override
            public String readLable() {
                return lable;
            }
        }
        instancesVariable = new PDestination("无人生还");
    }

    static class PContents implements Contents{
        private int i = 11;
        @Override
        public int value() {
            return i;
        }
    }

}