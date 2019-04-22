package com.github.java06;

import java.util.Arrays;
/*
策略设计模式:
    1. 能根据所传参数对象不同而具有不同行为的方法
    2. 这类方法包含要执行的程序中固定不变的部分与变化的部分
    3. 而策略就是传递进去的参数对象,它自身已包含要执行的代码  策略是该方法变化的部分

以下案例Processor就是一个策略:
    Apply.process 接收任意类型的Porcessor对象
 */
public class job01 {
    public static void main(String[] args) {
        Apply.process(new Upcase(),Apply.s);
        Apply.process(new Downcase(),Apply.s);
    }
}


class Apply{
    public static String s = "Disagreement with beliefs is by definition incorrect";

    public static void process(Processor p, Object obj){
        System.out.println("SimpleName: " + p.name());
        System.out.println(p.process(obj));

    }
}


/*class Processor{
    public String name(){
        return getClass().getSimpleName();
    }

    Object process(Object input){
        return input;
    }
}*/
interface Processor{
    String name();
    Object process(Object input);
}


/*class Upcase extends Processor{
    String process(Object obj){
        return ((String)obj).toUpperCase();
    }
}*/
class Upcase implements Processor{
    @Override
    public String name() {
        return getClass().getSimpleName();
    }

    public String process(Object obj){
        return ((String)obj).toUpperCase();
    }
}


/*class Downcase extends Processor{
    String process(Object obj){
        return ((String)obj).toLowerCase();
    }
}*/
class Downcase extends Upcase{

    public String process(Object obj){
        return ((String)obj).toLowerCase();
    }
}

