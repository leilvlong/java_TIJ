package com.github.java12;
import java.util.*;
/**
 * 泛型容器的(容器元素)合法向上转型
 */
class Holder{
    public static void main(String[] args) {

        // 对象之间的上下转型
        // 上转一定成功
        // 下转强行成功(一定成功or一定失败)
        Apple apple1 = new Apple();
        Fruit  fruit1 = apple1;

        Fruit fruit2 = new Apple();
        Apple apple2 = (Apple) fruit2;

        // 容器对象,不允许上下转型
        List<Apple> apples = new ArrayList<>();

        /*
        //如果转型为Fruit，则表示可以添加Fruit
        // 当再转型为Apple时，Fruit则不可使用
        List<Fruit> fruits = apples;

        // 如果转型为Jonathan，则容器中原本持有的Apple不可使用
        List<Jonathan> jonathans = apples; */

        // 而要做到转型则可以用到上边界通配符
        // 因为它代表的是边界，既然有了边界就不会有问题了
        List<? extends Fruit> fruits = apples;

        // 理解这种泛型的关键字:同构
        // 在限定的边界的泛型容器中: 行为模式必须相同
        // 在单一类型的泛型容器中: 类型必须保持一致

    }
}



class MyTest{
    public static void main(String[] args) {
        // 当做参数使用时
        // 上边界则只允许添加当前边界类及其子类类型的引用

        // 下边界则只允许添加当前边界类及其基类类型的引用

        // 原因不再赘述

    }


    public static List<? extends Fruit> upExtends(List<? extends Fruit> list){
        for (Fruit fruit : list) {
            System.out.println(fruit);
        }
        return list;
    }

    public static List<? super Fruit> downSuper(List<? super Fruit> list){
        for (Object o : list) {
            System.out.println(o);
        }
        return list;
    }
}
