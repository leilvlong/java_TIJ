package com.github.java07;
/*
内部类向上转型:
    实现某个接口,用该接口获得实现类的引用与某个实现对象向上转型为这个接口效果是一样的

内部类访问权限设置与隐藏:
    在以下案例中的内部类:
        PContents 被设置为私有的
        PDestination 被设置为包访问权限
        这意味着你无法正常的获取他们,只能通过get方法
        而get方法返回的又是一个接口引用,内部类的实现被彻底的隐藏了起来
使用者使用这些内部类对象时,除了接口提供的方法外,那些可能存在的内部类方法无法被访问,扩展也就变得没有意义



 */
public class job05 {
    public static void main(String[] args) {
        Parcel4 parcel4 = new Parcel4();
        Contents c = parcel4.getContents();
        Destination d = parcel4.getDestination("test");
        System.out.println(c.value());
        System.out.println(d.readLable());


    }
}

interface Destination{
    String readLable();
}


interface Contents{
    int value();
}


class Parcel4{
    private class PContents implements Contents{
        private int i = 11;

        @Override
        public int value() {
            return i;
        }

        public void out(){
            System.out.println(getClass().getSimpleName());
        }
    }



    protected class PDestination implements Destination{
        private String label;

        public PDestination(String label) {
            this.label = label;
        }

        @Override
        public String readLable() {
            return label;
        }

        public void out(){
            System.out.println(getClass().getSimpleName());
        }
    }


    public Contents getContents(){
        return this.new PContents();
    }

    public Destination getDestination(String label){
        return this.new PDestination(label);
    }
}