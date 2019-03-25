package com.github.java02;

import com.sun.jdi.Value;

import java.io.*;
import java.util.ArrayList;
/*
简单使用一下枚举与Enum提供的方法
将多个对象序列化到一个文件再反序列化时的问题  EOFException:
    1. 多个序列化对象放入 ArrayList<E>(或者别的容器) 将ArrayList对象序列化(灵活性高 使用体验差)
    2. 末尾写入null( .writerObject(null) )或别的标识符数据,读取时根据标识符数据判断 (灵活性低 使用体验好)
    3. try catch 捕获异常不做处理
 */
public class job09 {
    public static void main(String[] args) throws Exception{
        // 应用
        //new EnumOrder(Enumeration.valueOf("NOT")).descrebe();


        // 枚举实现了接口Serializable  可以直接序列化
        ObjectOutputStream fol = new ObjectOutputStream(new FileOutputStream("demo01\\funs\\lll.txt"));
        for (Enumeration value : Enumeration.values()) {
            System.out.println(value.name());
            System.out.println(value.ordinal());
            System.out.println(value.hashCode());
            if(value.equals(new EnumOrder(value).et)){
                System.out.println(value);
            }

            fol.writeObject(value);
        }
        fol.close();

        System.out.println("反序列化");
        ObjectInputStream fil = new ObjectInputStream(new FileInputStream("demo01\\funs\\lll.txt"));
        Enumeration et;
        while((et=(Enumeration) fil.readObject()) != null){
            System.out.println(et);
        }
        fil.close();




    }
}

// 枚举常量
enum Enumeration {
    NOT, MILD, MEDIUM, HOT, FLAMING;
}

class EnumOrder{
    Enumeration et;

    public EnumOrder(Enumeration et) {
        this.et = et;
    }

    public void descrebe(){
        switch (et){
            case NOT:
                System.out.println(" IS NOT");
                break;
            case MILD:
                System.out.println("IS MILD");
                break;
            case MEDIUM:
                System.out.println("IS MEDIUM");
                break;
            case HOT:
                System.out.println("IS HOT");
                break;
            case FLAMING:
                System.out.println("IS FLAMING");
                break;
            default:
                System.out.println("IS NULL");
        }
    }
}

class Colors{
    String color;

    public Colors(String color) {
        this.color = color;
    }
}