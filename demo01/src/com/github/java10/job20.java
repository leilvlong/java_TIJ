package com.github.java10;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*

结合文档与String源码来看:
    String的大多数方法的实现都是由Pattern提供协助实现的
 */
public class job20 {
    public static String input = "This!!unusual use!!of exclamation!!points";

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {





    }

    public static void fun1(){
        System.out.println(Arrays.toString(Pattern.compile("!!").split(input)));
        System.out.println(Arrays.toString(Pattern.compile("!!").split(input,3)));
    }

    public static void fun2(){
        System.out.println(input.replaceAll("!!","AA"));
        System.out.println(input.replace("!!","AA"));
        /*  源码
        replaceAll:
        return Pattern.compile(regex).matcher(this).replaceAll(replacement);
        replace:
        return Pattern.compile(target.toString(), Pattern.LITERAL).matcher(
                this).replaceAll(Matcher.quoteReplacement(replacement.toString())); */
    }
}
