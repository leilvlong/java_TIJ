package com.github.java12;

/*
泛型接口:
    生成器
 */


import java.util.Iterator;
import java.util.Random;

public class job02 {
    public static void main(String[] args) {
        CoffeeGenerator coffees = new CoffeeGenerator();
        for (int i = 0; i < 5; i++) {
            Coffee next = coffees.next();
            System.out.println(next);
        }

        for (Coffee coffee : new CoffeeGenerator(5)) {
            System.out.println(coffee);
        }

    }
}


interface Generator<T> {
    T next();
}


class Coffee {
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName()+": " + id;
    }
}


class Latte extends Coffee {
}


class Mocha extends Coffee {
}


class Cappuccino extends Coffee {
}


class Americano extends Coffee {
}


class Breve extends Coffee {
}


class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {

    private Class[] types = {
            Latte.class,
            Mocha.class,
            Cappuccino.class,
            Americano.class,
            Breve.class
    };

    private static Random rand = new Random(47);

    public CoffeeGenerator() {
    }

    private int size = 0;

    public CoffeeGenerator(int size) {
        this.size = size;
    }

    @Override
    public Coffee next() {
        try {
            return (Coffee) types[rand.nextInt(types.length)].newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    class Coffeeiterator implements Iterator<Coffee> {

        int count = size;

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public Coffee next() {
            count--;
            return CoffeeGenerator.this.next();
        }
    }


    @Override
    public Iterator<Coffee> iterator() {
        return new Coffeeiterator();
    }
}


