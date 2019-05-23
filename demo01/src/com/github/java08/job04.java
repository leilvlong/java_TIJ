package com.github.java08;


import java.util.*;

public class job04 {
    public static void main(String[] args) {
        MyList1<String> strings = new MyList1<>(Arrays.asList("To be or not to be".split(" ")));
        for (String string : strings) {
            System.out.println(string);
        }
    }
}


class MyList1<T> implements Iterable<T>{
    private T[] objs;
    private Integer size;

    public MyList1(Collection<T> c) {

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
            private Integer index = 0;
            @Override
            public boolean hasNext() {
                return index<size;
            }

            @Override
            public T next() {
                return objs[index++];
            }
        };
    }

}
