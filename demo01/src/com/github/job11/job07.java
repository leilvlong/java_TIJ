package com.github.job11;
/*
可以使用Class对象的cast为对象引用转型
 */
public class job07 {
    public static void main(String[] args) {
        Building b = new House();
        Class<House> houseClass = House.class;
        House h = houseClass.cast(b);
        System.out.println("h: " + h);
        h = (House) b;
        System.out.println("b; "+ b);
    }
}


class Building{
    @Override
    public String toString() {
        return "Building";
    }
}


class House extends Building{
    @Override
    public String toString() {
        return "House";
    }
}
