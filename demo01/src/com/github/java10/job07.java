package com.github.java10;

import sun.plugin2.message.Conversation;

import java.math.BigInteger;
import java.util.Formatter;

public class job07 {

    public static void main(String[] args) {
        Formatter f = new Formatter(System.out);
/*  最常用:
        d 整数型
        c Unicode字符
        b boolean值 注意只要值不为null结果就是true
        s String
        f 浮点数(十进制)
        e 科学计数
        x 整数
        h 散列码                           */
        /* 适用于char的转换
        char u = 'a';
        //f.format("d: %d\n",u);
        f.format("c: %c\n",u);
        f.format("b: %b\n",u);
        f.format("s: %s\n",u);
        //f.format("f: %f\n",u);
        //f.format("e: %e\n",u);
        //f.format("x: %x\n",u);
        f.format("h: %h\n",u);              */

        /* 适用于int的转换
        int u = 121;
        f.format("d: %d\n",u);
        f.format("c: %c\n",u);
        f.format("b: %b\n",u);
        f.format("s: %s\n",u);
        //f.format("f: %f\n",u);
        //f.format("e: %e\n",u);
        f.format("x: %x\n",u);
        f.format("h: %h\n",u);               */

        /* 适用于BigInteger的转换
        BigInteger u = new BigInteger("50000000000000");
        f.format("d: %d\n",u);
        //f.format("c: %c\n",u);
        f.format("b: %b\n",u);
        f.format("s: %s\n",u);
        //f.format("f: %f\n",u);
        //f.format("e: %e\n",u);
        f.format("x: %x\n",u);
        f.format("h: %h\n",u);                */

        /* 适用于 Conversation的转换
        Conversation u = new Conversation();
        //f.format("d: %d\n",u);
        //f.format("c: %c\n",u);
        f.format("b: %b\n",u);
        f.format("s: %s\n",u);
        //f.format("f: %f\n",u);
        //f.format("e: %e\n",u);
        //f.format("x: %x\n",u);
        f.format("h: %h\n",u);                    */

        boolean u = false;
        //f.format("d: %d\n",u);
        //f.format("c: %c\n",u);
        f.format("b: %b\n",u);
        f.format("s: %s\n",u);
        //f.format("f: %f\n",u);
        //f.format("e: %e\n",u);
        //f.format("x: %x\n",u);
        f.format("h: %h\n",u);
    }
}
