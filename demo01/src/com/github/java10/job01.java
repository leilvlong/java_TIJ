package com.github.java10;

/*
不可变String:
    String对象是不可变的,每一个修改String的方法实际都会返回一个全新的String对象
    以下案例中对字符串的操作并未影响原本的那个字符串,通过fun方法传递str1最终操作
    后并未改变str1
    这种不可变的判断方式也适用于其它对象

 参数传递机制:
    向方法传递参数,若传递的是常量,则实际是该常量的引用,若传递的是变量
    则传递的实际是该变量的拷贝,而指向的对象实际上一直呆在单一的物理地址上

字符串与常量池:
    以下字符串的存在实际是在常量池中创建的对象
    1）str1 引用常量池中的字符串"abc" ，常量池中若存在则返回引用，若不存在则新建"abc"
    2）str2 引用常量池中的字符串"abc",此时因为str1的关系,该字符串一定存在,返回引用
    3) str3 声明将创建对象,为字符串"abc"，但是依然做的是1）的事,只不过多了一道中间过程
    创建堆上对象，堆上对象引用字符串常量池中的字符串，存在则返回引用，不存在则创建。
    打个比方就是：str1与str2这两遥控器直接操作对象，str3遥控器操作的对象也是个遥控器，
    该对象遥控器才能操作具体的实际对象
    4） == 比较的是地址值，str1与str2返回的都是常量池中同一字符串的地址，而str3返回的
    则是堆上遥控器的内存地址

既然串可以直接声明却依然保留了new的这种方式:
    String的官方文档提供了多种方式构建一个字符串对象,这是需要用到的
    比如一个字符数组将其构造为一个字符串
    因此new一个字符串的情况很可能是创建了两个对象,但也有可能只是一个
    字符串的存在在开发中无处不在,这种机制可以很好的优化内存,避免重复的东西占用内存
    不仅仅是字符串,其它常量机制也必定是如此
 */


public class job01 {
    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "abc";
        String str3 = new String("abc");

        System.out.println(str1 == str2);
        System.out.println(str1 == str3);

        String str11 = fun(str1);
        System.out.println(str11);
        System.out.println(str1);
    }

    public static String fun(String s){
        return s.toUpperCase();
    }
}
