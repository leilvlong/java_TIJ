package com.github.java12;


import com.github.typeinfo.pets.Dog;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T>
 *     由于擦除(编译期泛型在类的内部不具备任何属性与行为,只是占位符)
 *     泛型类无法直接或间接继承Throwable
 *     catch也不能捕获泛型类的异常,
 *     因为编译期和运行时都必须知道异常的类型
 */
/*
class MyClass <T> extends Exception{
    public MyClass(){}
}   */



/*  //无法捕获这种被擦除后的异常
class MyClass <T>{
    public MyClass(){
        try{
            new T();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}*/




/**
 * 一种可以达成的做法是限定泛型一样边界并将其抛出在方法上
 */
interface Processor<T ,E extends Exception>{
    void process(List<T> result) throws E;
}


class ProcessRunner<T, E extends Exception> extends ArrayList<Processor<T,E>> {
    List<T> processAll() throws E{
        List<T> result = new ArrayList<>();
        for (Processor<T, E> processor : this) {
            processor.process(result);
        }
        return result;
    }
}


class Failure1 extends Exception{}


class Processor1 implements Processor<String,Failure1>{
    static int count = 3;

    @Override
    public void process(List<String> result) throws Failure1 {
        if (count-- >1){
            result.add("hep");
        }else{
            result.add("ho");
        }
        if (count<0){
            throw new Failure1();
        }
    }
}


class Failure2 extends Exception{
}


class Processor2 implements Processor<Integer,Failure2>{
    static int count = 2;

    @Override
    public void process(List<Integer> result) throws Failure2 {
        if (count -- == 0){
            result.add(47);
        }else{
            result.add(11);
        }
        if (count<0){
            throw new Failure2();
        }
    }
}


class ThrowGenericException{
    public static void main(String[] args) {
        ProcessRunner<String, Failure1> runner = new ProcessRunner<>();
        for (int i = 0; i < 3; i++) {
            runner.add(new Processor1());
        }
        try{
            System.out.println(runner.processAll());
        }catch (Exception e){
            e.printStackTrace();
        }

        ProcessRunner<Integer, Failure2> runner2 = new ProcessRunner<>();
        for (int i = 0; i < 2; i++) {
            runner2.add(new Processor2());
        }
        try{
            System.out.println(runner2.processAll());
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
