package com.github.java02;

import java.lang.reflect.Array;

/*
类中的成员变量具有初始值:
    基本数据类型:
        整形 : 0
        浮点型: 0.0
        boolean: false
        char(输出时什么也看不到 但有案例):
            static char a;
            public static void main(String[] args) {
                if(a =='\0'){
                    System.out.println("hahah");
                }
            }
   引用数据类型:
        默认为null:
            static String str;
            static C c;
            public static void main(String[] args) {
                if(str == null){
                    System.out.println("ahah");
                }
                if(c == null){
                    System.out.println("ahah");
                }
           }
    这些在之前阅读 Head first java 就已知道(而且知道局部变量不会初始化 某种程度上可以防止疏忽
                                            就如同while循环是否设置出口)
    现在 在关于使用构造器初始化时会如何?

在使用构造器初始化之前:
    无法阻止自动初始化的进行:
        案例 class Initalize
        可以清楚的看到成员变量时无论如何都会获得初始值的
    如果成员变量有初始值, 构造方法所做的也只是覆盖他原先的数据
    就如同:
        int a = 1   a = 2
        String str = null   str = "哈哈哈";

    基本数据类型的变量(即便未经构造也能得到初始值而不会抛出空指针异常)
    引用类型变量(会抛出空指针异常)

 */
public class job04 {

    public static void main(String[] args) {
        // 在案例中 使用构造方法对成员变量赋值
        // 但是我会先输出这些尚未经构造方法赋值的成员变量
        Initalize initalize = new Initalize('A',(byte)127,(short)32767,100000,
                5000,0.3f,0.5,true, "一二三");
        // 现在我对这成员变量进行了构造初始化 对其输出
        System.out.println(initalize.cha);
        System.out.println(initalize.byt);
        System.out.println(initalize.sho);
        System.out.println(initalize.aInt);
        System.out.println(initalize.aLong);
        System.out.println(initalize.flo);
        System.out.println(initalize.dou);
        System.out.println(initalize.aBool);
        System.out.println(initalize.str);

        // 成员变量不管被定义的顺序如何 都会被确保得到初始化
        House house = new House();

    }
}

class Initalize{
    char cha;
    byte byt;
    short sho;
    int aInt;
    long aLong;
    float flo;
    double dou;
    boolean aBool;
    String str;

    public Initalize(char cha, byte byt, short sho, int aInt, long aLong, float flo, double dou, boolean aBool, String str) {
        System.out.println(this.cha);
        System.out.println(this.byt);
        System.out.println(this.sho);
        System.out.println(this.aInt);
        System.out.println(this.aLong);
        System.out.println(this.flo);
        System.out.println(this.dou);
        System.out.println(this.aBool);
        System.out.println(this.str);

        this.cha = cha;
        this.byt = byt;
        this.sho = sho;
        this.aInt = aInt;
        this.aLong = aLong;
        this.flo = flo;
        this.dou = dou;
        this.aBool = aBool;
        this.str = str;
    }
}

// 成员变量不管被定义的顺序如何 都会被确保得到初始化
class Window{
    int num;
    Window(int num){
        System.out.println("window: " + num);
        this.num = num;
    };
}

class House{
    Window win1 = new Window(1);
    Window win2 = new Window(2);

    House(){
        win3 = new Window(4);
    }

    Window win3 = new Window(3);

}