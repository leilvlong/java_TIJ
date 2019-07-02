package com.github.java12;

/*
不要试图认为容器持有不同的泛型返回的Class对象不同
容器持有的对象与容器本身的Class对象没有任何关系
当你定义了容器泛型是什么,改变的并不是这个容器对象,而是容器持有的对象类型
因此,想要通过泛型容器获取泛型信息是不可能的,获取类型占位符信息倒是可以

泛型擦除:
    通过定义泛型的边界,保存必要的部分,不必要的则擦除.泛型调用的行为安全得到了保证
    这看起来很蠢,因为继承也可以做到,但是要明白一点,泛型是指定什么就是什么,而继承只是指定了引用类型
    这意味着可以使 根类一的边界，也可以是根类二、根类三的
    而这种泛型的类型检查只会发生在编译期间,(通过ArrayList可以窥探一点,当指定泛型后,返回时做类型转换)
    在运行期间,容器持有的泛型数据类型会被擦除,替换为他们的上边界,如果未指定,则是Object

    这里有一点需要区分 行为返回的数据类型与数据类型的区别 以下是验证信息:
//反编译的jvm指令  编译的是最后一个类
class com.github.java12.Get<T> {
  public com.github.java12.Get(T);
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: aload_0
       5: aload_1
       6: putfield      #2                  // Field obj:Ljava/lang/Object;
       9: return

  public T get();
    Code:
       0: aload_0
       1: getfield      #2                  // Field obj:Ljava/lang/Object;
       4: areturn
}


 */

import java.util.*;

public class job09 {
    public static void main(String[] args) {
        /*HasF hasF1 = new HasF();
        HasTwoF hasF2 = new HasTwoF();
        Get<HasF> get1 = new Get<>(hasF1);
        get1.get().fun();

        Get<HasF> get2 = new Get<>(hasF2);
        get2.get().fun();*/

        fun3();
    }

    public static void fun1(){
        Class aClass1 = new ArrayList<Integer>().getClass();
        Class aClass2 = new ArrayList<String>().getClass();
        System.out.println(aClass1 == aClass2);
    }

    public static void fun2(String arg){
        Map<String,Integer> maps = new HashMap<>();
        System.out.println(Arrays.toString(maps.getClass().getTypeParameters()));
    }

    public static void fun3(){
        HasF hasF1 = new HasF();
        HasF hasF2 = new HasTwoF();

        Manipulator<HasF> m1 = new Manipulator<>(hasF1);
        m1.manipulator();
        m1.manipulator(hasF2);

        System.out.println("-----------------------");
        Manipulator<HasF> m2 = new Manipulator<>(hasF2);
        m2.manipulator();
        m2.manipulator(hasF2);
    }

}



class Manipulator <T extends HasF>{
    T obj;

    public Manipulator(T obj) {
        this.obj = obj;
    }

    void manipulator(){
        System.out.print("T extends HasF:  ");
        obj.fun();
    }

    void manipulator(HasF hasF){
        hasF.fun();
    }
}


class HasF{
    public void fun(){
        System.out.println("Class HasF method fun");
    }

    @Override
    public String toString() {
        return "HasF{}";
    }
}


class HasTwoF extends HasF{
    public void fun(){
        System.out.println("Class HasTwoF method fun");
    }

    @Override
    public String toString() {
        return "HasTwoF{}";
    }
}


class Get<T>{
    private T obj;

    public Get(T obj) {
        this.obj = obj;

    }

    public T get(){
        return obj;
    }

}