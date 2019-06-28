package com.github.java12;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/*
泛型参数的方法
 */

public class job04 {
    public static void main(String[] args) {
        Collection<Coffee> coffees = fill(new ArrayList<Coffee>(), new CoffeeGenerator(), 4);
        System.out.println(coffees);


        Collection<Integer> fibonacci = fill(new ArrayList<>(), new Fibonacci(),10);
        System.out.println(fibonacci);

    }

    public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int n){
        for (int i = 0; i < n; i++) {
            coll.add(gen.next());
        }
        return coll;
    }

    public static <T> List<T> fill(List<T> coll, Generator<T> gen, int n){
        for (int i = 0; i < n; i++) {
            coll.add(gen.next());
        }
        return coll;
    }
}

