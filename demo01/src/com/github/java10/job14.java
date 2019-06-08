package com.github.java10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Pattern 与 Matcher构造功能更强大的正则表达式对象
stc.matches源码:
    public boolean matches(String regex) {
            return Pattern.matches(regex, this);
    }
 */
public class job14 {
    public static void main(String[] args) {
        String[] str = {
            "abcabcabcderabc",
            "abc+",
            "(abc)+",
            "(abc){2,}"
        };
        fun(str);
    }

    public static void fun(String[] args){
        if (args.length<2){
            System.out.println("Usage: \njava TestRegularExpression CharacterSequence regularExpression+");
            System.exit(0);
        }

        System.out.println("Input:\""+args[0]+"\"");

        for (String arg : args) {
            System.out.println("Regular expression :\""+arg+"\"");
            Pattern compile = Pattern.compile(arg);
            Matcher matcher = compile.matcher(args[0]);
            while (matcher.find()){
                System.out.println("Match \"" + matcher.group() + "\" at positions" + matcher.start()+"-"+(matcher.end()-1));
            }
        }


    }

}
