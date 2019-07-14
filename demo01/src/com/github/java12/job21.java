package com.github.java12;

import java.util.*;
import java.util.function.Consumer;

/**
 * 自限定类型
 */
class SelfBounded <T extends SelfBounded<T>>{ }


class GenericType<T>{}


class CuriouslyRecurringGeneric extends GenericType<CuriouslyRecurringGeneric>{}


class BasicHolder<T>{
    T element;

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public void fun(){
        System.out.println(element.getClass().getSimpleName());
    }
}


class Subtype extends BasicHolder<Subtype>{
}


/**
 * java的泛型关乎参数和返回值:
 *      从这儿理解就很容易了,具有封装特性的类
 *      其正常工作流程是创建对象,使用方法
 *      提供参数,获取返回值
 * CRG的本质:
 *      基类用导出类代替其参数
 *      基类将泛型限定为自身,劫持泛型类型
 *      使其导出类被限定参数与返回值的类型
 *      在泛型的灵活性上提供了限制
 *      泛型基类成为了一种所有导出类的公共功能的模板
 */
class CRGWithBasicHolder{
    public static void main(String[] args) {
        Subtype
                st1 = new Subtype(),
                st2 = new Subtype();
        st1.setElement(st2);
        Subtype st3 = st1.getElement();
        if (st2 == st3){
            st1.fun();
        }
    }
}


