package com.github.java02;

import java.util.ArrayList;
import java.util.HashSet;

/*
由于现在涉及基类和导出类两个类,而不是只有一个类,所以要试着想象代导出类所产生
的结果对象会有点困惑. 从外部来看,他就像是一个与基类具有相同接口的新类,或许还
会有额外的方法和域.但继承并不只是复制基类的接口.当创建了一个导出类的对象时,该
对象包含了一个基类的子对象,.这个子对象与你用基类直接创建的对象时一样的.二者区
别在于,或者来自于外部,而基类的子类对象被包装在导出类对象内部.

这段话换个方式理解就是:
    子类对象被创建时基类会被初始化,而子类中引用基类对象的方式是super(指非 new 基类 这种情况 ),
    但super只会存于子类,所以该对象只能被子类引用(基类的子类对象被包装在导出类对象内部)
 */
public class job13 {
    public static void main(String[] args) {
        Exd exd = new Exd("Exc");
        exd.fun();
    }
}


class Exb{
    String message = "不管我有多少子类 只要不被覆盖子类就可以通过super得到我";
    public Exb() {
        System.out.println("不管我有多少子类 我都会被初始化");
    }
}

class Exc extends Exb{
    String className;

    public Exc(String className) {
        System.out.println("子类创建对象时 我一定会被初始化");
        this.className = className;
    }

}

class Exd extends Exc{

    public Exd(String className) {
        super(className);
        System.out.println("必须保证基类能被正确初始化 ,super必须在我之上");
    }

    public void fun(){
        System.out.println(super.className);
        System.out.println(super.message);
    }
}

