package com.github.java12;
import java.awt.*;
import java.awt.color.*;


interface HasColor{
    Color getColor();
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


}