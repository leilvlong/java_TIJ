package com.github.java10;

/*
格式化输出字符串
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.Format;
import java.util.Date;
import java.util.Formatter;

public class job05 {
    public static void main(String[] args) throws FileNotFoundException {
        Turtle tom = new Turtle("tom", new Formatter(new PrintStream("demo01\\funs\\error.txt")));

        tom.move(1,3);


        // 原生的日期格式化
        Formatter formatter = new Formatter();
        Date date = new Date();
        Formatter format = formatter.format("Local time:%tY-%tm-%td/%tT", date,date,date,date);
        String s = format.toString();
        System.out.println(s);
    }

    public static void fun1(){
        int x = 5;
        double y = 5.332452;
        System.out.format("row : [%d %f]\n",x,y);
    }
}


class Turtle{
    private String name;
    private Formatter f;

    public Turtle(String name, Formatter f) {
        this.name = name;
        this.f = f;
    }

    public void move(int x, int y){
        f.format("%s the Turtle is at (%d,%d)\n",name,x,y);
    }

}