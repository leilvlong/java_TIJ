package com.github.java02;

/*

final修饰变量的一些细节:
     final允许指定空白常量 但是在使用前需要为其赋值
     而且只允许在构造方法中为其赋值一旦被赋值则不可
     修改(机会只有一次,我们上吧!, 不 可以再new一个)
    而被静态修饰的 final常量则不允许指定空白常量
说到静态我之前的理解有些误区:
    静态有自己独立的存储空间,静态数据的初始化在第一次被调用时加载到内存时数据的初始化完
    成后该数据一直存于静态的内存空间,随时调用,相对的也会更消耗内存资源,而一般成员则在被
    调用时初始化数据后生存于堆上,失去依赖后会被清理,更加动态的使用系统内存资源
    正是这个原因 不允许static final 修饰的空白常量的存在
 */

public class job18 {
    public static void main(String[] args) {

    }
}

final class FinalClass{
    final int index;
    static final int count = 10;
    public FinalClass(int index) {
        this.index = index;
    }
}

