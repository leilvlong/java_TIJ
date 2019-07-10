package com.github.java12;
import java.util.*;


/**
 * 看来JDK8对于泛型有了很大的优化
 * 在TIJ中会出现编译异常的fun方法此处是合法的
 * 这本来就应当是合法的: 向基类泛型的容器中添加导出类元素,获取时做向上转型有何不可
 * 导出类与基类既可以是 is-a 也可以是 is-like-a,要做到这些只需导出类上下转型即可
 */
class GenericWriting{

    static <T> void writeExact(List<T> list ,T item){
        list.add(item);
    }

    static List<Apple> apples = new ArrayList<>();
    static List<Fruit> fruits = new ArrayList<>();

    static void fun(){
        writeExact(apples,new Apple());
        writeExact(fruits,new Apple());
    }

    static <T> void writeWithWildcard(List<? super T> list, T item){
        list.add(item);
    }

    static void hun(){
        writeWithWildcard(apples,new Apple());
        writeWithWildcard(fruits,new Apple());
    }

    public static void main(String[] args) {
        fun();
        hun();
    }
}


/**
 * 静态内部类CovariantReader中的readCovariant方法:
 *      其实际行为允许传入一个上边界泛型List
 *      即:参数List可以是该泛型任意导出
 *      因为不确定任意导出的类型,但一定都可以向上转型基类泛型
 */
class GenericReading{
    static <T> T readExact(List<T> list){
        return list.get(0);
    }

    static List<Apple> apples = Arrays.asList(new Apple());
    static List<Fruit> fruits = Arrays.asList(new Fruit());

    static void fun(){
        Apple apple = readExact(apples);
        Fruit fruit = readExact(fruits);
        fruit = readExact(apples);
    }

    static class Reader<T>{
        T readExact(List<T> list){
            return list.get(0);
        }
    }

    static void gun(){
        Reader<Fruit> fruitReader = new Reader<>();
        Fruit fruit = fruitReader.readExact(fruits);
    }

    static class CovariantReader<T>{
        T readCovariant(List<? extends T> list){
            return list.get(0);
        }
    }

    static void hun(){
        CovariantReader<Fruit> reader = new CovariantReader<>();
        Fruit fruit = reader.readCovariant(fruits);
        Fruit fruit1 = reader.readCovariant(apples);
    }

    public static void main(String[] args) {
        fun();
        gun();
        hun();
    }

}
