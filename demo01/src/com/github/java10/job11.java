package com.github.java10;


/*
str.split
正则表达式匹配会匹配整个相符的合法字符串
例:
    with...a
    split("h\\W+");
    则: wit,a
 */

import java.lang.reflect.Array;
import java.util.Arrays;

public class job11 {

    static String str =  "Then, when you have found the shrubbery, you must cut down the mightiest tree in the " +
            "forest... with...a herring!";

    static void split(String reg){
        System.out.println(Arrays.toString(str.split(reg)));
    }

    public static void main(String[] args) {
        split(" ");
        split("\\W+");
        split("t\\W+");
    }
}
