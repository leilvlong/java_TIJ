package com.github.job11;

/*
泛化的Class引用:
    Class引用总是指向某个Class对象,因此,Class引用表示的就是它所指向的对象的确切类型
    而该对象便是Class类的一个对象
    Class<?> 与Class是等价的,
    但Class<?>优于Class,
    Class<?>表示你明确知道它的好处
 */
public class job04 {
    public static void main(String[] args) {
        Class integerClass = int.class;
        Class<Integer> genericintClass = int.class;
        genericintClass = Integer.class;
        integerClass = Double.class;
        //genericintClass = Double.class;

        Class<?> intClass = int.class;
        intClass = double.class;
    }
}
