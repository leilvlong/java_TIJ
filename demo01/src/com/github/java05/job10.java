package com.github.java05;
/*
java SE 5 之后允许这种协变返回类型
 */
public class job10 {
    public static void main(String[] args) {
        Mill m = new Mill();
        Grain process = m.process();
        System.out.println(process);

        m = new WheatMill();
        Grain process1 = m.process();
        System.out.println(process1);
    }
}

class Grain{
    @Override
    public String toString() {
        return "Grain";
    }
}

class Wheat extends Grain{
    @Override
    public String toString() {
        return "Wheat";
    }
}

class Mill{
    Grain process(){
        return new Grain();
    }
}

class WheatMill extends Mill{
    @Override
    Wheat process(){
        return new Wheat();
    }
}
