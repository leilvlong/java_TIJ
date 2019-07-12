package com.github.java12;


interface Comparable<T>{
    int compareTo(T arg);
}


/**
 * 基类劫持接口
 */
class ComparablePet implements Comparable<ComparablePet>{
    @Override
    public int compareTo(ComparablePet arg) {
        return 0;
    }

}


/**
 * 编译期异常:Comparable cannot be inherited with different type arguments: ComparablePet and Cat
 */
/*
class Cat extends ComparablePet implements Comparable<Cat>{
    @Override
    public int compareTo(ComparablePet arg) {
        return 0;
    }
}*/


/**
 * 但这只能称得上重写基类的方法,基类接口的泛型无法改变
 */
class Hamster extends ComparablePet implements Comparable<ComparablePet>{
    @Override
    public int compareTo(ComparablePet arg) {
        return 1;
    }
}