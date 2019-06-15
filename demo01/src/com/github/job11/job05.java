package com.github.job11;

import java.util.ArrayList;
import java.util.List;

/*
Class<T>:
    T(指定类型)是什么就是什么,总会得到正确的、确切的类型信息

 */
public class job05 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        fun();

    }

    public static void fun(){
        FilledList<CountedInteger> filledList = new FilledList<>(CountedInteger.class);
        List<CountedInteger> countedIntegerList = filledList.create(10);
        for (CountedInteger countedInteger : countedIntegerList) {
            System.out.println(countedInteger);
        }
    }
}


class CountedInteger{
    private static long counter;
    private final long id = counter++;

    @Override
    public String toString() {
        return "CountedInteger: " +
                "id=" + id ;
    }
}


class FilledList<T>{
    private Class<T> type;


    public FilledList(Class<T> type) {
        this.type = type;
    }

    public List<T> create(int nElements){
        List<T> result = new ArrayList<>();
        try {
            for (int i = 0; i < nElements; i++) {
                result.add(type.newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}