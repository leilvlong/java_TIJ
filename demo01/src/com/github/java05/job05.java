package com.github.java05;
/*
奇怪的行为:
        以下案例中，将对象向上转型后,通过getField方法与直接使用
    该变量所得到的值完全不同(指内存);
        在将对象向上转型时,任何域访问操作都将由编译器解析，因此
    不是多态的（指尚未进行到运行期间，在编译期为其分配内存空间）。
        在下面案例中为两个对象的field分配了不同的存储空间(继承
    会将基类也初始化并且依赖导出类的存在而存在,依赖导出类的基类
    不能以任何方式在导出类外部分配引用(至少我目前尚未知道有这样
    的方式));
        当对象引用使用成员变量时必须显示的指出使用谁的成员变量,
    否则默认使用该对象引用指定Class的成员变量

普通成员 成员变量与成员方法虽然都可以被继承,但因机制的不同,使用时亦不同
 */
public class job05 {
    public static void main(String[] args) {
        Super aSuper = new Subr();
        //此处未指明使用谁的field  默认使用当前对象引用的成员变量 即 Super的
        System.out.print("aSuper field: "+ aSuper.field+"~~~");
        // 方法是多态的(动态绑定) 因此获取的是当前对象引用指向的堆上对象 该return field实际上有隐式的this
        System.out.println("aSuper getField(): "+ aSuper.getField());


        Subr subr = new Subr();
        //引用类型与对象都指向本身 自然使用自己的
        System.out.print("subr field: "+ subr.field+"~~~");
        //引用类型与对象都指向本身 自然使用自己的
        System.out.print("subr getField(): "+ subr.getField()+"~~~");
        // 基类随着子类的初始化亦初始化 依赖于导出类 通过super关键字可使用基类对象的公有成员
        System.out.println("subr getSuperField(): "+ subr.getSuperField());




    }
}

class Super{
    public int field = 0;

    public int getField(){
        return field;
    }

    private void out(){
        System.out.println("Print Super");
    }
}


class Subr extends Super{
    public int field = 1;

    public int getField(){
        return field;
    }

    public int getSuperField(){
        return super.field;
    }

    void out(){
        System.out.println("Print Subr");
    }

}