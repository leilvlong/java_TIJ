package com.github.java01;

import java.util.Comparator;

/*
lambad表达式与方法引用
 */
public class job09 {
    public static void main(String[] args) {
        //函数式编程  lambda作为参数
        /*String a= "1";
        String b= "2";
        String c= "3";
        fun1(1,()-> a+b+c);*/

        /*//函数式编程 lambda作为返回值
        Integer[] ints = {1,2,3,4,5,6,7,8,9};
        Arrays.sort(ints,fun2());
        //  方法引用 实现降序
        // Arrays.sort(ints,this::fun3);
        System.out.println(Arrays.toString(ints));*/

        /*
        方法引用:
            与参数无关 与返回值无关
            使用原则:
                1.函数式接口作为方法的参数
                2.函数式接口在被调用的方法中使用,且其抽象方法的参数可推导(参数确认传入即可省写)
                3. A::B 代表A对象引用自己的B方法
                    :: 则代表对函数式接口的引用
                    相当于B充当了函数式接口的抽象方法的实现方法体
                    所以才叫方法引用
                4.A对象的B方法必须是已经实现好的
                5.方法引用不适用于复杂的运算场景(我会拿显示器爆你的头)
         */
        fun4("我是你爸爸",System.out::println);
        System.out.println(fun5(-5, Math::abs));
        fun6("雷吕龙", Zi::new);
    }

    public static void fun1(int lerver, Cups cups){
        if(1==lerver){
            System.out.println(cups.printLog());
        }
    }

    public static Comparator<Integer> fun2(){
        return (o1,o2)->o2-o1;
    }

    public int fun3(int o1, int o2){
        return o2-o1;
    }

    public static void fun4(String str, Tups tups){
        tups.print(str);
    }

    public static int fun5(int num, Dups dups){
        return dups.print(num);

    }

    public static void fun6(String name, Fu fu){
        Zi fun = fu.fun(name);
        System.out.println(fun);
    }
}

@FunctionalInterface
interface Cups{
    String printLog();
}

@FunctionalInterface
interface Tups<E>{
    void print(E e);
}

@FunctionalInterface
interface Dups{
    int print(int e);
}

interface Fu{
    Zi fun(String name);
}

class Zi{
    String name;

    public Zi(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Zi{" +
                "name='" + name + '\'' +
                '}';
    }
}
