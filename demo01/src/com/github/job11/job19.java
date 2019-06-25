package com.github.job11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class job19 {
    public static void main(String[] args) {
        MyArrar<String> stringMyArray = new MyArrar<>(Arrays.asList("1","2","3","4","5","6","7"));

        System.out.println(stringMyArray);

        for (String str : stringMyArray) {
            System.out.println(str);
        }

        for (String str : stringMyArray) {
            System.out.println(str);
        }
    }
}



class MyArrar <T> implements Iterable<T>{
    Object[] elementData;

    private int size;

    private int count;

    public MyArrar(Collection<T> collection) {
        this.elementData = collection.toArray();
        this.size = elementData.length;
        this.count = size;
    }

    @Override
    public String toString() {
        Iterator<T> iterator = iterator();


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        while (iterator.hasNext()){
            T next = iterator.next();
            stringBuilder.append(next).append(", ");
        }
        stringBuilder.append("]");
        stringBuilder.replace(stringBuilder.length()-3,stringBuilder.length()-1,"");
        return stringBuilder.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private boolean status = true;

            @Override
            public boolean hasNext() {
                if (count<=0){
                    count = size;
                    status = false;
                }
                return status;
            }

            @Override
            public T next() {
                return (T) elementData[--count];
            }
        };
    }
}