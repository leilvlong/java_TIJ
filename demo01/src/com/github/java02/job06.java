package com.github.java02;
/*
静态初始化补充:
    原则就是原则 不会轻易发生改变

 */
public class job06 {
    public static void main(String[] args) {
         Cups.cup1.cupFun();
    }

    //static Cups cups = new Cups();
}


class Cup{
    public Cup(){
        System.out.println("cup初始化");
    }

    public void cupFun(){
        System.out.println("cupFun");
    }
}

class Cups{
    static Cup cup1;
    static Cup cup2;
    static {
        cup1 = new Cup();
        cup2 = new Cup();
    }



    public Cups(){
        System.out.println("cups");
    }
}
