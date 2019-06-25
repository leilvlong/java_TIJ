package com.github.java11;

/*
运行时类型信息:
    程序运行时才会识别对象和类的信息
    它使开发者从只能在编译期执行面向类型的操作的禁锢中解放开来
    (强类型语言的弊端,灵活性不够,但提供了一系列策略来解决这种麻烦
    这些策略一般用于编写框架)

RTTI:
        在运行时,识别一个对象的类型!
        在类层次结构中，基类位于顶端，向下扩展。
        面向对象编程的基本目的是：让代码只操作对基类的引用，这样
    如果要添加一个新类来扩展程序，就不会影响到原来的程序代码。这
    种思想不仅应用于继承中，现在流行的分模块面向接口编程也有异曲
    同工之妙，不同模块之间的耦合问题由接口引用来解决，需要扩展时
    向该模块的接口添加方法并实现，其它的模块只有调用该接口的新方
    法时才会察觉到这种变化，而这种思想得益于动态绑定机制
        以下案例中揭示了RTTI这一特性，尽管被向上转型为Shape,但在
    使用instanceof时依然能准确的执行对应的语句
        容器使用了泛型存放Shapes,但实际上存入时都是object,在取出
    时将他们转型为Shape,以get方法为例下相关源码可以提供这种证明：
        transient Object[] elementData;  //容器对象

        public E get(int index) {
            rangeCheck(index);          //验证索引是否合法
            return elementData(index);  //取出元素
        }

        E elementData(int index) {          // 取出元素的具体执行逻辑
            return (E) elementData[index]; //从容器中取出元素并且转型
        }
       E: 指泛型,定义时是什么该泛型就是什么  List<Shape>
    在编译期期间，由容器以及泛型系统解决元素存放以及类型问题，而所
    有类型的转换都是在运行时才会进行正确检查。至于程序如何执行，这
    些都可以交给多态 。
        泛型和反射的目的就是为了解决类型限制
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class job01 {
    public static void main(String[] args) {
        List<Shape> list = new ArrayList<>(Arrays.asList(
                new Circle(),
                new Square(),
                new Triangle()
        ));

        for (Shape shape : list) {
            shape.draw();
        }

        for (Shape shape : list) {
            if(shape instanceof Shape){
                System.out.print("Base Shape:  ");
            }
            if (shape instanceof Circle){
                System.out.print("instanceof Circle:  ");
                shape.draw();
            }
            if (shape instanceof Square){
                System.out.print("instanceof Square:  ");
                shape.draw();
            }
            if (shape instanceof Triangle){
                System.out.print("instanceof Triangle:  ");
                shape.draw();
            }

        }


    }
}


abstract class Shape{
    void draw (){
        System.out.println(this + ": Draw");
    }

    @Override
    public abstract String toString();
}


class Circle extends Shape{
    @Override
    public String toString() {
        return "Circle";
    }
}


class Square extends Shape{
    @Override
    public String toString() {
        return "Square";
    }
}


class Triangle extends Shape{
    @Override
    public String toString() {
        return "Triangle";
    }
}