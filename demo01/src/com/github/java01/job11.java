package com.github.java01;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/*
函数式接口练习
 */
public class job11 {
    public static void main(String[] args) {
        //   复习一下方法引用 (可读性极差)
        //fun1("hah",job11::fun2);
        //new MyClass().fun("haah", MyClass::fun2);

        // 函数式接口使用
        /*
        使用lambda表达式创建Predicate对象p1,p1能判断整数是否是自然数(大于等于0)
        使用lambda表达式创建Predicate对象p2,p2能判断整数的绝对值是否大于100
        使用lambda表达式创建Predicate对象p3,p3能判断整数是否是偶数
        System.out.println(fun3(15, (num) -> num >= 0));
        System.out.println(fun3(15, (num) -> Math.abs(num) >= 0));
        System.out.println(fun3(15, (num) -> num % 2 == 0));
        fun4();
        */

        /*
        使用Function 求ArrayList<Integer> 的平均值
         */
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(59);
        ints.add(82);
        ints.add(98);
        ints.add(65);
        ints.add(70);
        fun5((list)->{
            int sum = 0;
            for (Integer inte :list){
                sum+=inte;
            }
          return sum/list.size();
        },ints);


    }

    /*
    fun2作为fun中函数式接口的方法引用
     */
    public static  void fun1(String str,Predicate<String> PreStr){
        boolean test = PreStr.test(str);
        System.out.println(test);
    }

    public static boolean fun2(String str){
        return str.contains("H") && str.length()>3;
    }

    public static boolean fun3(Integer ints, Predicate<Integer> pre ){
        return pre.test(ints);
    }

    public static void fun4(){
        /*
        遍历arr，仅利用已创建的Predicate对象(不使用任何逻辑运算符)，完成以下需求
        打印自然数的个数
        打印负整数的个数
        打印绝对值大于100的偶数的个数
        打印是负整数或偶数的数的个数
        */
        Integer[] arr = {-12345, 9999, 520, 0,-38,-7758520,941213};
        int count1 = 0, count2 = 0, count3 = 0, count4 = 0;
        for (Integer ints : arr) {
            // 正整数
            if(fun3(ints,(num) -> num >= 0)){
                count1++;
            }
            // 负整数
            if(fun3(ints,(num) -> num < 0)){
                count2++;
            }
            // 绝对值大于100的偶数
            if(fun3(ints,(num) -> Math.abs(num) >= 100 && num%2==0)){
                count3++;
            }
            if(fun3(ints,(num) -> (num !=0) && (num < 0 || num % 2==0))){
                count4++;
            }
        }
        System.out.println(" "+ count1+" "+count2+" "+count3+" "+count4);
    }

    public static void fun5(Function<ArrayList<Integer>,Integer> fun, ArrayList<Integer> array){
        System.out.println(fun.apply(array));
    }
}

class MyClass implements Consumer{

    @Override
    public void accept(Object o) {
        String s = String.valueOf(o);
        System.out.println(s.toUpperCase());

    }

    public void fun(String str,Consumer<String> con){
        Consumer<String> consumer = andThen(con);
        consumer.accept(str);
        //return consumer;
    }

    /*
    fun2 作为fun中的函数式接口的方法引用
     */
    public static void fun2(String  t){
        System.out.println(t.toLowerCase());
    }
}

