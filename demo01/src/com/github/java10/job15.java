package com.github.java10;
/*
编译后的Pattern对象提供了对字符串split操作,从匹配regex的地方
通过调用matcher方法返回一个Match对象
match的find方法则返回其所在字符串的索引
 */
public class job15 {
    public static void main(String[] args) {

        String[] strs = {
                "Arline ate eight apples and one orange while Anita hadn't any",
                "(?i)((^[aeiouwh])|(\\s+[aeiouwh]))\\w+?[aeioutsdy]\\b"
        };

        job14.fun(strs);
        System.out.println(strs[0].length());
    }
}
