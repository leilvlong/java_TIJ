package com.github.java05;

import java.util.Random;

/*
在一下示例中 tune() 与tuneAll():
        tune方法与tuneall方法可以很大程度上忽略周围代码的变化，
    这正是多态应具有的特性（深有同感）。多态是一项将改变的事物
    与不需要改变的事物分离开来的重要技术实现。
 */
public class job04 {
    public static void main(String[] args) {
        tuneAll(multiplyInstrument(),"MIDDLE_C");

    }

    public static Instrument[] multiplyInstrument(){
        RandomInstrumentGenerator rig = new RandomInstrumentGenerator();
        Instrument[] instruments = new Instrument[18];
        for (int i = 0; i < instruments.length; i++) {
            instruments[i] = rig.next();
        }
        return instruments;
    }




    public static void tune(Instrument instrument, String str){
        instrument.play(str);
    }

    public static void tuneAll(Instrument[] instruments, String str){
        for (Instrument instrument : instruments) {
            tune(instrument,str);
        }
    }

}


class RandomInstrumentGenerator{
    private Random rand = new Random(74);

    public Instrument next(){
        switch (rand.nextInt(5)){
            default:
            case 0:
                return new Wind();
            case 1:
                return new Percussion();
            case 2:
                return new Stringed();
            case 3:
                return new Brass();
            case 4:
                return new Woodwind();
        }
    }
}


abstract class Instrument{
    abstract void play(String str);
    abstract String what();
    abstract void adjust();
}


class Wind extends Instrument{

    @Override
    void play(String str) {
        System.out.println("Wind Play:" +" "+ str);
    }

    @Override
    String what() {
        return "Wind";
    }

    @Override
    void adjust() {
        System.out.println("Adjusting Wind");
    }
}


class Percussion extends Instrument{

    @Override
    void play(String str) {
        System.out.println("Percussion Play: " + str);
    }

    @Override
    String what() {
        return "Percussion";
    }

    @Override
    void adjust() {
        System.out.println("Adjusring percussion");
    }
}


class Stringed extends Instrument{

    @Override
    void play(String str) {
        System.out.println("Stringed Play: "+ str);
    }

    @Override
    String what() {
        return "Stringed";
    }

    @Override
    void adjust() {
        System.out.println("Adjusting Stringed");
    }
}


class Brass extends Wind{

    @Override
    void play(String str) {
        System.out.println("Brass Play: "+ str);
    }

    @Override
    void adjust() {
        System.out.println("Adjusting Brass");
    }
}


class Woodwind extends Wind{

    @Override
    void play(String str) {
        System.out.println("Woodwind Play: " + str);
    }

    @Override
    String what() {
        return "Woodwind";
    }
}