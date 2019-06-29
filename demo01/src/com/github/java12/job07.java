package com.github.java12;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class job07 {
    public static void main(String[] args) {
        TupleList.fun();
    }
}


class TupleList<A,B,C> extends ArrayList<Tuple<A,B,C>>{
    public static void fun(){
        TupleList<String,Integer,Double> tl = new TupleList<>();
        tl.add(new Tuple<>("hello",123,456.789));
        System.out.println(tl);
    }
}


class Tuple<A, B, C> {
    private A a;
    private B b;
    private C c;

    public Tuple(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return "Tuple(" + a + ", " + b + ", " + c + ')';
    }
}



