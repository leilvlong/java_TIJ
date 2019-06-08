package com.github.java10;

public class job12 {

    static String str = "Then, when you have found the shrubbery, you must cut down the mightiest tree in the " +
            "forest... with...a herring!";

    public static void main(String[] args) {
        System.out.println(str.replaceFirst("w\\w+", "null"));
        System.out.println(str.replaceAll("w\\w+", "null"));
    }
}
