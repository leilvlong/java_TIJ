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

构造器也是static方法,尽管并没有显示的static修饰(通过静态方法返回本类对象即可得知,而静态方法中只能只用本类的静态方法或成员)
每个类的编译代码都存于它自己的独立文件中,该文件
只在需要使用程序代码时才会被加载(所以说java的加载原则是不分静态或一般成员,只在使用时才会加载)
可以说,类的加载是在其任何静态成员被访问时加载(因为只能直接访问类的静态成员
非静态成员只能通过new 对象,而现在已经得知构造方法也是静态的)
通过静态代码块观察静态的初始化顺序:
    根据静态代码块得知是根据书写顺序的上下文初始化的
 */

public class  job18 {
    public static void main(String[] args) {
        new FinalClass();
    }
}

final class FinalClass{
    static final int count = 15;
    final int index;

    static {
        System.out.println("111");
    }
    static {
        System.out.println("222");
    }
    static {
        System.out.println("333");
    }


    public FinalClass() {
        this.index = fun();
    }

    public static int fun(){
        return 10;
    }
}

