package com.github.java12;

/*
生成器接口的另一个实现:
    接口的多实现衍生的设计模式:策略模式
    相同的行为名,不同的行为性
    同时也可以通过继承来扩展实现适配
 */

import java.util.Iterator;

public class job03 {
    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        System.out.print("Class Fibonacci: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(fibonacci.next() + " ");
        }
        System.out.println();

        System.out.print("Class Fibonacci: ");
        for (Integer integer : new Fibonacci(10)) {
            System.out.print(integer + " ");
        }
        System.out.println();

        IterableFibonacci integers = new IterableFibonacci(10);
        System.out.print("Class IterableFibonacci: ");
        for (Integer integer : integers) {
            System.out.print(integer + " ");
        }
    }
}


class Fibonacci implements Generator<Integer>, Iterable<Integer>{

    private int count = 0;

    private int size = 0;

    public Fibonacci() {
    }

    public Fibonacci(int size) {
        this.size = size;
    }

    @Override
    public Integer next() {
        return fib(count++);
    }

    public static int fib(int count){
        if (count<2){
            return 1;
        }
        return fib(count-2)+fib(count-1);
    }

    class IteratorFibonacci implements Iterator<Integer>{

        @Override
        public boolean hasNext() {
            return size>0;
        }

        @Override
        public Integer next() {
            size--;
            return Fibonacci.this.next();
        }
    }



    @Override
    public Iterator<Integer> iterator() {
        return new IteratorFibonacci();
    }
}



class IterableFibonacci extends Fibonacci implements Iterable<Integer>{

    private int n;

    public IterableFibonacci(int count) {
        this.n = count;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return n>0;
            }

            @Override
            public Integer next() {
                n--;
                return IterableFibonacci.this.next();
            }
        };
    }
}