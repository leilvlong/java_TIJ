package com.github.java08;

import java.lang.reflect.Array;
import java.util.*;

public class job03 {
    public static void main(String[] args) {
        ReversibleArrayList<String> ra = new ReversibleArrayList<>(Arrays.asList("To be or not to be".split(" ")));
        for (String o : ra) {
            System.out.println(o);
        }

    }
}


class ReversibleArrayList<T> extends ArrayList<T>{
    public ReversibleArrayList(Collection<T> c) {
        super(c);
    }

    public Iterable<T> reversed(){
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    int current = size()-1;

                    @Override
                    public boolean hasNext() {
                        return current>-1;
                    }

                    @Override
                    public T next() {
                        return get(current--);
                    }
                };
            }
        };
    }
}