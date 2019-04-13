package com.github.java05;

import java.lang.reflect.Type;
import java.util.Random;
/*
随机种子相同 结果相同
Random rand1 = new Random(48);
Random rand2 = new Random(48);
System.out.println(rand1.nextInt());
System.out.println(rand2.nextInt());

2019/04/13:
随机数种子相同 与案例输出相同
(Thinking in java)
 */
public class job03 {
    private static RandomShapeGenerator gen = new RandomShapeGenerator();
    public static void main(String[] args) {
        Shape[] shapes = new Shape[9];
        for (int i = 0; i < 9; i++) {
            shapes[i]= gen.next();
        }

        for (Shape shape : shapes) {
            shape.draw();
        }
    }
}


class RandomShapeGenerator{
    private Random rand = new Random(47);

    public Shape next(){
        switch (rand.nextInt(3)){
            default:
            case 0:
                return new Circle();
            case 1:
                return new Square();
            case 2:
                return new Triangle();
        }

    }
}




abstract class Shape{
    abstract public void draw();
    abstract public void erase();
}


class Circle extends Shape{

    @Override
    public void draw() {
        System.out.println("Circle draw");
    }

    @Override
    public void erase() {
        System.out.println("Circle erase");
    }
}


class Square extends Shape {

    @Override
    public void draw() {
        System.out.println("Square draw");
    }

    @Override
    public void erase() {
        System.out.println("Square erase");
    }
}


class Triangle extends Shape{

    @Override
    public void draw() {
        System.out.println("Triangle draw");
    }

    @Override
    public void erase() {
        System.out.println("Triangle erase");
    }
}