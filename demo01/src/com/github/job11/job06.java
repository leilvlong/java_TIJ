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
    以下示例将揭示这一特性
*/
public class job06 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<GrandSonClass> grandSonClassClass = GrandSonClass.class;
        GrandSonClass grandSonClass = grandSonClassClass.newInstance();
        System.out.println(grandSonClass);

        Class<? super GrandSonClass> superclassForOne = grandSonClassClass.getSuperclass();
        Object objectForOne = superclassForOne.newInstance();
        SubClass subClass = (SubClass) objectForOne;
        System.out.println(subClass);

        Class<? super GrandSonClass> superclassForTwo = superclassForOne.getSuperclass();
        Object objectForTwo = superclassForTwo.newInstance();
        BaseClass baseClass = (BaseClass) objectForTwo;
        System.out.println(baseClass);

        Class<? super GrandSonClass> superclassForThree = superclassForTwo.getSuperclass();
        Object objectForThree = superclassForThree.newInstance();
        System.out.println(objectForThree);

        /*Class<? super GrandSonClass> superclassForFour = superclassForThree.getSuperclass();
        Object objectForFour = superclassForFour.newInstance();
        System.out.println(superclassForFour);*/
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



