package com.github.java10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class job16 {
    public static void main(String[] args) {
        Matcher m = Pattern.compile("\\w+").matcher("Evening is full of the linnet's wings");

        /*while(m.find()){
            System.out.println(m.group()+ "");
        }*/

        /*int i = 0;
        while (m.find(i)){
            System.out.println(m.group()+ " i: "+ i);
            i++;
        }*/
        System.out.println("Evening is full of the linnet's wings".length()-1);

    }
}
