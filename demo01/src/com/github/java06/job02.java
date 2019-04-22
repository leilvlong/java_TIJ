package com.github.java06;
/*
策略设计模式之二:
    完全解耦
    job01 与job02并无任何差别
    但是此processor完全无法作用于 Filter
    现在 这些类中都具有process方法
    将Proceesor 设计为一个接口

class 更像是一套数据类型规范
而接口则更像是一套行为模式规范
 */
public class job02 {
    public static String s = "Disagreement with beliefs is by definition incorrect";

    public static void main(String[] args) {
        processor(new Upcase(),s);
        processor(new Downcase(),s);
        processor(new Splitter(),s);
        processor(new Filter(),new Waveform());
    }

    public static void processor(Processor p, Object s){
        System.out.println("SimpleName: "+p.name());
        System.out.println(p.process(s));
    }
}

class Waveform{
    private static long counter = 0;
    private long id = counter++;

    @Override
    public String toString() {
        return "Wavefor ID: " + id;
    }
}

class Filter implements Processor{
    public String name(){
        return getClass().getSimpleName();
    }

    @Override
    public Object process(Object input) {
        return input;
    }
}

