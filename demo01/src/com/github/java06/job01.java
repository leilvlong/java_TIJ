package com.github.java06;

import java.util.Arrays;
/*
策略设计模式:
    1. 能根据所传参数对象不同而具有不同行为的方法
    2. 这类方法包含要执行的程序中固定不变的部分
    3. 而策略就是传递进去的参数对象,它自身已包含要执行的代码  策略是该方法变化的部分

以下案例Processor就是一个策略
 */
public class job01 {
    public static String s = "Disagreement with beliefs is by definition incorrect";

    public static void main(String[] args) {
        processor(new Upcase(),s);
        processor(new Downcase(),s);
        processor(new Splitter(),s);
    }

    public static void processor(Processor p, String s){
        System.out.println("SimpleName: "+p.name());
        System.out.println(p.process(s));
    }
}


interface Processor{
    public String name();



    Object process(Object input);

}


class Upcase implements Processor {

    @Override
    public String name() {
        return getClass().getSimpleName();
    }

    public String process(Object input) {
        return((String)input).toUpperCase();
    }
}


class Downcase implements Processor {
    @Override
    public String name() {
        return getClass().getSimpleName();
    }

    public String process(Object input) {
        return ((String)input).toLowerCase();
    }
}


class Splitter implements Processor {
    @Override
    public String name() {
        return getClass().getSimpleName();
    }

    @Override
    public String process(Object input) {
        return Arrays.toString(((String)input).split(" "));
    }
}