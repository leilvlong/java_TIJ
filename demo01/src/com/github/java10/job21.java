package com.github.java10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class job21 {

    static String input =
            "/*! Here's a block of text to use as inpu to\n" +
            "    the regular expression matcher. Note that we'll\n"+
            "    first extract the block of text by looking for\n" +
            "    the special delimiters, then process the\n"+
            "    extracted block. !*/";

    public static void main(String[] args) {
        Matcher m = Pattern.compile("/\\*!(.*)!\\*/", Pattern.DOTALL).matcher(input);
        String str = "";
        if (m.find()){
            str = m.group(1);
            System.out.println(str);
        }
        System.out.println("---------------new Str---------------");

        str = str.replaceAll("  {2}", " ");
        System.out.println(str);
        System.out.println("---------------new Str---------------");

        str = str.replaceAll("(?m)^ +","");
        System.out.println(str);
        System.out.println("---------------new Str---------------");

        str = str.replaceFirst("[aeiou]","(VOEWK1)");
        System.out.println(str);
        System.out.println("---------------new Str---------------");

        StringBuffer sb = new StringBuffer();
        Matcher matcher = Pattern.compile("[aeiou]").matcher(str);
        while (matcher.find()){
            matcher.appendReplacement(sb,matcher.group().toUpperCase());
        }
        matcher.appendTail(sb);
        System.out.println(sb);

    }
}
