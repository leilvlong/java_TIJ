package com.github.java12;
import java.awt.*;



interface HasColor{
    Color getColor();
}

interface Weight{
    int weight();
}


class Colored <T extends HasColor>{
    T item;

    public Colored(T item) {
        this.item = item;
    }

    public T getItem(){
        return item;
    }

    public Color color(){
        return item.getColor();
    }
}


class Dimension{
    public int x, y, z;
}


class ColoredDimension<T extends Dimension & HasColor>{
    T item;

    public ColoredDimension(T item) {
        this.item = item;
    }

    public T getItem(){
        return item;
    }

    public Color color(){
        return item.getColor();
    }

    public int getY(){
        return item.y;
    }

    public int getZ(){
        return item.z;
    }

    public int getX(){
        return item.x;
    }
}


class Solid <T extends Dimension & HasColor & Weight>{
    T item;

    public Solid(T item) {
        this.item = item;
    }

    public T getItem(){
        return item;
    }

    public Color color(){
        return item.getColor();
    }

    public int getX(){
        return item.x;
    }

    public int getY(){
        return item.y;
    }

    public int getZ(){
        return item.z;
    }

    public int weight(){
        return item.weight();
    }
}


class SolidExtend <T extends Dimension & HasColor & Weight> extends ColoredDimension<T>{

    public SolidExtend(T item) {
        super(item);
    }

    public void fun(){
        super.getItem();
        super.getX();
        super.getY();
        super.getZ();
        super.color();
        this.weight();
    }

    public int weight(){
        return item.weight();
    }
}

/**
 *    泛型边界的重要作用：泛型在类的内部可以不用再受Object的约束
 *    在泛型边界中有个很重要的关键字 extends 该关键字被赋予了特殊意义
 *    即: 类型子集，可以是一个，也可以是多个，类中的泛型可以使用类型
 *    子集的所有可用方法，而不再是Object
 *    对于持有边界泛型也可以使用继承的方式:
 *      在每个继承类中添加边界泛型 并把这种泛型传递到基类中
 *      这样,可以只在必要的时候重写方法,而不至于每个都要编写
 *      该方式允许添加额外的泛型边界子集,但是已有的边界类型子集必须满足基类泛型边界子集
 */
class Bounded extends Dimension implements HasColor, Weight{

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public int weight() {
        return 0;
    }

    public static void main(String[] args) {
        Solid<Bounded> solid1 = new Solid<>(new Bounded());
        solid1.getItem();
        solid1.getX();
        solid1.getY();
        solid1.getZ();
        solid1.color();
        solid1.weight();

        SolidExtend<Bounded> solidExtend = new SolidExtend<>(new Bounded());
        solidExtend.getItem();
        solidExtend.getX();
        solidExtend.getY();
        solidExtend.getZ();
        solidExtend.color();
        solidExtend.weight();
    }
}

