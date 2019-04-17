package com.github.java05;

public class job11 {
    public static void main(String[] args) {
        Mt mt = new Mt();
        Ut[] uts = new Ut[15];
        Et[] ets = new Et[15];
        // 给Ut 数组每个元素分配对象
        for (int i = 0; i < uts.length; i++) {
            uts[i] = new Ut(mt);
        }
        // 给Et 数组每个元素分配对象
        for (int i = 0; i < uts.length; i++) {
            ets[i] = new Et(mt);
        }
        // 清除Ut  不会导致Mt被清除 因为计数还在
        for (Ut ut : uts) {
            ut.dispose();
        }
        // 清除Et  计数失去 Mt被清除
        for (Et et : ets) {
            et.dispose();
        }
    }
}


class Mt{
    private long relect = 0;
    private static long count = 0;
    private final long id = count++;

    public Mt() {
        System.out.println("Creating Mt: " + this);
    }

    public void addrelect(){
        relect++;
    }

    public void dispose(){
        if(--relect==0){
            System.out.println("Mt dispose: " + this);
        }
    }

    @Override
    public String toString() {
        return "Mt id" + id;
    }
}


class Ut{
    private static long count=0;
    private final long id=count++;
    private Mt mt;

    public Ut(Mt mt) {
        System.out.println("Creating Ut: " + this);
        this.mt = mt;
        mt.addrelect();
    }

    public void dispose(){
        System.out.println("UT dispose: " + this);
        mt.dispose();
    }

    @Override
    public String toString() {
        return "Ut id" + id;
    }
}

class Et{
    private static long count=0;
    private final long id=count++;
    private Mt mt;

    public Et(Mt mt) {
        System.out.println("Creating Et: " + this);
        this.mt = mt;
        mt.addrelect();
    }

    public void dispose(){
        System.out.println("ET dispose: " + this);
        mt.dispose();
    }

    @Override
    public String toString() {
        return "Et id" + id;
    }
}