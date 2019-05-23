package com.github.java08;

import java.util.*;

public class job06 {
    public static void main(String[] args) {
        MyList3<String> list = new MyList3<>(Arrays.asList("1 2 3 4 5 6".split(" ")));
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}

class MyList3<T>{
    private T[] objs;
    private Integer size;

    public MyList3(Collection<T> c) {

        this.size = c.size();
        objs = (T[]) new Object[size];
        int i = 0;
        for (T t : c) {
            objs[i++] = t;
        }
    }


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
