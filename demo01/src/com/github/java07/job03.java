package com.github.java07;

import java.util.Optional;
import java.util.Random;

/*
内部类可以访问外部类的所有成员:
    1.不同于job02中必须以 外部类.内部类的方式获取对象引用
        使用接口可以很好的规避这个问题(基类引用指向导出类)
        但本质没变 依然是通过外部类对象创建的对象

    2.这里接口的导出类实现的三个方法很巧妙
     end 判断是否可以获取
     current 获取
     next 负责获取游标移动(确保不会越界)

外部类提供容器:
    1.在该案例中,外部类的作用就是提供一个构造时指定size的数组容器
    2.外部内的add方法每被调用一次则将数据存放在该容器中
private 内部类提供行为:
    内部类则继承了接口让内部类自身拥有了行为以及行为规范:
        next()游标计数(产出计数,如果提供一个方法获取游标值,则可以得知下一个数据(对象)在该容器中的索引)
        end() 验证是否能继续获取对象
        current() 产出实质的数据(对象)/访问容器当前对象

该设计模式被称为迭代器设计模式(在java6 job12的仿next 与hasnext中使用过 尽管实现细节不同(该案例中游标更为隐晦))
这种内部类迭代器模式的设计源于:
    内部类拥有外部类所有成员访问权的机制

抽象类Get的目的:
    不出意外 既然可以实现接口,就可以继承

内部类为外部类对象提供了更大的灵活性与组织性:
    私有内部类可以用来组织某一类行为规范,但不使用内部类也是可以做到的
    假设都移到外部类中:
        外部类的next变量是 getNext
        内部类的i变量是 setNext
        end 与 current 与 next 方法不变

 通过外部类创建对象时;
    内部类会捕获指向外部类对象的引用,访问外部类成员实际就是使用指向外部类的引用,至于如何做到的可能就要去问编译器了
    内部类对象只能在与外部类对象相关联时创建(非static时),而构建内部类时,需要一个指向外部类的引用
    但这都是由编译器完成的,如果编译器无法访问这个指向外部类的引用就会抛出异常
    而且这个指向引用比正常获取的对象引用权限高的多得多

事实上无法解释的不能理解的都归咎于解释器来完成的是最心安理得的~!!
 */
public class job03 {

    public static void main(String[] args) {
        /*Sequence sequence = new Sequence(10);
        for (int i = 0; i < 10; i++) {
            sequence.add(i);
        }

        Selector selector = sequence.selector();
        while (! selector.end()){
            System.out.println(selector.current());
            selector.next();
        }*/

/*        Random r = new Random();

        Sequence sequence = new Sequence(10);
        for (int i = 0; i < 10; i++) {
            char[] chars = {
                    (char) (r.nextInt(26)+97),
                    (char) (r.nextInt(26)+97),
                    (char) (r.nextInt(26)+97),
                    (char) (r.nextInt(26)+97),
                    (char) (r.nextInt(26)+97),
            };
            sequence.add(new ToString(new String(chars,0,chars.length)));
        }

        Selector selector = sequence.selector();
        while (! selector.end()){
            System.out.println(selector.current());
            selector.next();
        }*/

        Outer outer = new Outer("you are my ?");
        GetObject object = outer.getObject();
        System.out.println(object.get());

    }
}


interface Selector{
    boolean end();
    Object current();
    void next();
}

abstract class Get{
}

class Sequence{
    private Object[] items;
    private int next = 0;

    public Sequence(int size){
        items = new Object[size];
    }

    public void add(Object x){
        if(next < items.length){
            items[next++] = x;
        }
    }

    private class SequenceSelector extends Get implements Selector{
        private int i = 0;

        @Override
        public boolean end(){
            return i == items.length;
        }

        @Override
        public Object current() {
            return items[i];
        }

        @Override
        public void next() {
            if(i<items.length){
                i++;
            }
        }

    }

    public Selector selector(){
        return new SequenceSelector();
    }
}

class ToString{
    private String str;

    public ToString(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "ToString{" +
                "str='" + str + '\'' +
                '}';
    }
}


interface GetObject{
    Object get();
}

class Outer{
    private String str;

    public Outer(String str) {
        this.str = str;
    }

    class Inner implements GetObject{
        public Object get(){
            return str;
        }
    }

    public GetObject getObject(){
        return new Inner();
    }
}

