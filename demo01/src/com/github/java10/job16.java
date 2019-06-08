package com.github.java10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
以下案例展示了重载的find方法:
    \\w+正则将传入的字符串分为了每一个单词放在了组中(group)
    而通过无参find可以得到迭代器那样的效果,返回每个单词
    传入整数时返回的则是索引在该字符串中索引位置的字符
 */

public class job16 {
    public static void main(String[] args) {
        Matcher m = Pattern.compile("\\w+").matcher("Evening is full of the linnet's wings");

        /*while(m.find()){
            System.out.println(m.group()+ "");
        }*/

        int i = 0;
        while (m.find(i)){
            System.out.println(m.group()+ " i: "+ i);
            i++;
        }
        System.out.println("Evening is full of the linnet's wings".length()-1);

    }
}
