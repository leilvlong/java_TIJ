package com.github.java02;
/*
单例模式;
    确保该类只有一个实例对象
 */
public class job11 {
    public static void main(String[] args) {
        //new Mc();
        System.out.println(Mc.returnMc());
        System.out.println(Mc.returnMc());
    }
}

class Mc{
    private static Mc mc = new Mc();

    private Mc() {
    }

    public static Mc returnMc(){
        return mc;
    }

}
