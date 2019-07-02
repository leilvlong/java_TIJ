package com.github.java12;

/*
除非使用边界泛型,不然泛型在编译期本质就只是一个向上转型的obj
如果希望泛型不仅仅是“Object”则需要边界管理
 */


public class job10 {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Derived2 d2 = new Derived2();
        Object obj = d2.getElement();
        d2.setElement(obj);
    }
}


class Foo<T>{
    T ova;

    public Foo(T ova) {
        this.ova = ova;
    }
}


class GenericBase<T>{
    private T element;

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }
}


class Derived1<T> extends GenericBase<T>{
}


class Derived2 extends GenericBase{
}
