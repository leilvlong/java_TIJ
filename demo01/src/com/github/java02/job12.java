package com.github.java02;

import java.lang.ref.Cleaner;

class Caeanser{
    private String s = "Cleanser";
    public void append(String a){
        s+=a;
    }

    public void dilute(){
        append("  dilute()");
    }

    public void apply(){
        append("  apply()");
    }

    public void scrub(){
        append("  scrub()");
    }

    public String toString(){
        return s;
    }

    public static void main(String[] args){
        Caeanser x = new Caeanser();
        x.dilute();
        x.apply();
        x.scrub();
        System.out.println(x);
    }
}


public class job12 extends Caeanser{

    public void scrub(){
        append("  Detergent.sscrub()");
        super.scrub();

    }

    public void foam(){
        append("  foam()");
    }

    public static void main(String[] args) {
        job12 x =new job12();
        x.dilute();
        x.apply();
        x.scrub();
        x.foam();
        System.out.println(x);
        Caeanser.main(args);
    }
}
