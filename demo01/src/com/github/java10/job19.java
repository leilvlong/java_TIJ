package com.github.java10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Pattern.CASE_INSENSITIVE(?i):
    忽略大小写
Pattern.COMMENTS(?x):
    忽略空格符(注意空格符与空格字符是由区别的)
Pattern.DOTALL(?s):
    表达式 . 匹配所有字符包括行终结符(默认不匹配)
Pattern.MULTILINE(?m):
    多行模式,^表达式$表示匹配一行,开启后可以匹配多行
    ^还默认匹配字符串的开头 $默认匹配字符串的结尾
    默认情况下匹配字符串输入的开头和结尾
Pattern.UNICODE_CASE(?u)
    当指定后大小写不敏感的匹配将于unicode的标准一样
    大小写敏感默认只在US-ASCLL
Pattern.UNIX_LINES(?d)
    .^$行为中只识别行终结符\n
 */
public class job19 {
    public static void main(String[] args) {
        String input = job18.input;

        /*Matcher matcher = Pattern.compile("(?i)(?m)^t.*$").matcher(input);

        while (matcher.find()){
            System.out.println(matcher.group());
        }*/

        /*System.out.println("asd/".matches("^\\w{3}\\W"));
        System.out.println("asd/".matches("\\w{3}\\W$"));*/
        System.out.println("n\nss".matches("(?d)(?s)^....$"));
    }
}
