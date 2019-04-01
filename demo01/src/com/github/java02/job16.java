package com.github.java02;

/*
确保正确清理:
    以下十个清理的示例
    需要注意的是 先清理子类 后清理基类,确保没有依赖关系可以清理成功
    虽然java提供的垃圾清理已经足够强大(在堆上的对象失去引用(依赖)后就会被清理)
    当涉及到手动清理时(清理的原则: 至于内存有关) 需要根据实际场景做清理准备

    try{

    }finally{
        里面的代码一定会被执行 哪怕方法ruturn  只要不是System.exit
        该域内多用于各种清理与资源回收
        例如io流
    }



 */

class Shape{

    public Shape() {
        System.out.println(" This is Shape, Shape of the base class");
    }
    void dispose(){
        System.out.println(" Shape dispose");
    }

}


public class job16 extends Shape {
    private Circle c;
    private Triangle t;
    private Line[] lines = new Line[3];

    public job16() {
        super();
        c = new Circle();
        t = new Triangle();
        for (int i = 0; i < 3; i++) {
            lines[i] = new Line(i,i*i);
        }
        System.out.println( " Job16 构造方法对所有成员初始化");
    }

    @Override
    void dispose() {
        System.out.println(" job16.disPose()");
        c.dispose();
        t.dispose();
        for (Line line : lines) {
            line.dispose();
        }
        super.dispose();
    }

    public static void main(String[] args) {
        job16 j16 = new job16();
        try{

        }finally {
            j16.dispose();
        }
    }
}


class Circle extends Shape{

    public Circle() {
        super();
        System.out.println(" This is Circle");
    }

    @Override
    void dispose() {
        System.out.println(" Erasing Ciecle");
        super.dispose();
    }
}

class Triangle extends Shape{
    public Triangle() {
        super();
        System.out.println( " This is Triangle");
    }

    @Override
    void dispose() {
        System.out.println(" Ereasing Triangle");
        super.dispose();
    }
}

class Line extends Shape{
    int strat, end;
    public Line(int strat, int end) {
        super();
        this.strat = strat;
        this.end = end;
        System.out.println( " This is Line: " + strat +",  " + end);
    }

    @Override
    void dispose() {
        System.out.println(" Ereasing Line");
        super.dispose();
    }
}
