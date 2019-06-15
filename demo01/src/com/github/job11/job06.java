package com.github.job11;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/*
Class<? super GrandSonClass>:
    下边界(指当前class对象),此处的意思是只要是GrandSonClass或GrandSonClass的父类引用都可以,
    使用getSuperclass()时会一级一级的往上找基类
    但是因为一个类可能多重继承,也可能只继承自Objetc,
    所以使用newInstance()时统一指向Object类型引用的策略是合理的
    而且当getSuperclass()最终指向了Object.class后如果继续网上寻找或获得一个null
    同样,因为是直接创建的对象,所以不会维护向下转型的关系(不可向下强转为当前Class对象更低级的导出类)
    这里并不冲突,Class对象依然处于泛型引用的下边界中,但通过Class对象创建的是类对象并非是Class对象
    以下示例将揭示这一特性
*/
public class job06 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<GrandSonClass> grandSonClassClass = GrandSonClass.class;
        GrandSonClass grandSonClass = grandSonClassClass.newInstance();
        System.out.println("创建一个 GrandSonClass 对象:  " + grandSonClass);

        //尽管当前的下边界依然是GrandSonClass 但实际的Class对象则是SubClass 强转边界为SubClass
        Class<? super GrandSonClass> superclassForOne = grandSonClassClass.getSuperclass();
        Object objectForOne = superclassForOne.newInstance();
        SubClass subClass = (SubClass) objectForOne;
        System.out.println("创建一个 SubClass 对象:  " +subClass);

        //尽管当前的下边界依然是GrandSonClass 但实际的Class对象则是BaseClass 强转边界为BaseClass
        Class<? super GrandSonClass> superclassForTwo = superclassForOne.getSuperclass();
        Object objectForTwo = superclassForTwo.newInstance();
        BaseClass baseClass = (BaseClass) objectForTwo;
        System.out.println("创建一个 BaseClass 对象:  " +baseClass);

        //尽管当前的下边界依然是GrandSonClass 但实际的Class对象则是Object 强转边界为 没有可以强转的余地了
        Class<? super GrandSonClass> superclassForThree = superclassForTwo.getSuperclass();
        Object objectForThree = superclassForThree.newInstance();
        System.out.println("创建一个 Object 对象:  " +objectForThree);

        /*Class<? super GrandSonClass> superclassForFour = superclassForThree.getSuperclass();
        Object objectForFour = superclassForFour.newInstance();
        System.out.println(superclassForFour);*/

        System.out.println();
        System.out.println("下边界中的获取的Class对象可以自由转换引用: Class<? super GrandSonClass>");
        superclassForThree = superclassForTwo;
        superclassForTwo = superclassForOne;
        Object object = superclassForThree.newInstance();
        System.out.println("superclassForThree = superclassForTwo; 创建对象: " +object);
        Object object1 = superclassForTwo.newInstance();
        System.out.println("superclassForTwo = superclassForOne; 创建对象: "+object1);

    }
}


class BaseClass{
    @Override
    public String toString() {
        return "BaseClass";
    }
}


class SubClass extends BaseClass{
    @Override
    public String toString() {
        return "SubClass";
    }
}


class GrandSonClass extends SubClass{
    @Override
    public String toString() {
        return "GrandSonClass";
    }
}



