package com.github.java10;

import java.util.regex.Pattern;

/*
正则匹配符可参考  java.util.regex.Pattern;
Character classes
[abc]	a, b, or c (simple class)
[^abc]	Any character except a, b, or c (negation)
[a-zA-Z]	a through z or A through Z, inclusive (range)
[a-d[m-p]]	a through d, or m through p: [a-dm-p] (union)
[a-z&&[def]]	d, e, or f (intersection)
[a-z&&[^bc]]	a through z, except for b and c: [ad-z] (subtraction)
[a-z&&[^m-p]]	a through z, and not m through p: [a-lq-z](subtraction)

在正则匹配规则中一些特殊符号则代表将正则分割(不正确的表述)匹配:
    例:
    (abc)+def 它所代表的意义则是尽可能匹配多的abc

 */
public class job13 {
    public static void main(String[] args) {
        String str = "abcccccdef";
        System.out.println(str.matches("abc+def"));


    }
}
