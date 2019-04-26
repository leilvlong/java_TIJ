package com.github.java06;

/*
适配器模式:
    通过继承的方式适配
    以下案例中可以在任意类上添加接口
    任何方法或构造器都可以接收参数,其参数都可以是接口类型
    这意味着任何类都都可以对该方法(构造器)的参数进行适配
    这就是使用接口的强大之处

尽管将这个Readable 定义为抽象类的抽象方法也是可以做到的:
    但是类绝不仅仅只是行为,他是除开java提供的数据类型由我们自定义的数据类型
    类是携带数据的,接口是携带行为的
    在编写方法或设计类时更应当思考好你所要接收的是一个行为还是一种数据类型
    并为其设计适配模式

在job12中我将定义一个类似Readable接口的抽象类以证明确实是可行的 尽管这种思想是错误的
 */


import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

public class job11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new AdaptedRandomDoubles(10));
        while (sc.hasNext()) {
            System.out.println(sc.next());
        }

        Scanner sa = new Scanner(new NumberInts(10));
        while (sa.hasNext()) {
            System.out.println(sa.next());
        }
    }
}


class RandomDoubles {
    private Random rand = new Random(47);
    public double next(){
        return rand.nextDouble();
    }
}


class AdaptedRandomDoubles extends RandomDoubles implements Readable{
    private int count;
    public AdaptedRandomDoubles(int count) {
        this.count = count;
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        if (count-- == 0){
            return -1;
        }
        String s = Double.toString(next()) + " ";
        cb.append(s);
        return s.length();
    }
}


class RandomIntS{
    private Random rand = new Random(47);
    private int start = 10;
    private int end = 100;


    public int netx(){
        return rand.nextInt(end-start)+start;
    }
}


class NumberInts extends RandomIntS implements Readable{
    private int count;

    public NumberInts(int count) {
        this.count = count;
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        if(count --  == 0){
            return -1;
        }
        String s = "";
        for (int i = 0; i < 5; i++) {
             s += Integer.toString(netx());
        }
        s = s+ " ";
        cb.append(s);
        return s.length();
    }
}