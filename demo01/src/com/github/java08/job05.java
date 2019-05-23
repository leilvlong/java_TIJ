package com.github.java08;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class job05 {
    public static void main(String[] args) {
        MyList2<String> strings = new MyList2<>(Arrays.asList("To be or not to be".split(" ")));
        for (String string : strings) {
            System.out.println(string);
        }
    }
}


class MyList2<T> implements Iterable<T>{
    private T[] objs;
    private Integer size;

    public MyList2(Collection<T> c) {

        this.size = c.size();
        objs = (T[]) new Object[size];
        int i = 0;
        for (T t : c) {
            objs[i++] = t;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Integer index = size-1;
            @Override
            public boolean hasNext() {
                return index>-1;
            }

            @Override
            public T next() {
                return objs[index--];
            }
        };
    }

}