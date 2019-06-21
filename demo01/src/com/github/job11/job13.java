package com.github.job11;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.regex.Pattern;

/*
类方法提取器
 */
public class job13 {
    public static void main(String[] args) {
        String[] strs = {"com.github.job11.ShowMethods"};
        ShowMethods.main(strs);
    }
}



class ShowMethods{

    private static Pattern p = Pattern.compile("\\w+\\.| native| final");

    public ShowMethods() {
    }

    public static void main(String[] args) {
        if (args.length<1){
            System.out.println("you need ClassForName");
            System.exit(0);
        }

        int lines = 0;
        try {
            Class<?> c = Class.forName(args[0]);
            Method[] methods =c.getMethods();
            Constructor<?>[] constructors = c.getConstructors();
            for (Method method : methods) {
                System.out.println(p.matcher(method.toString()).replaceAll(""));
            }
            for (Constructor<?> constructor : constructors) {
                System.out.println(p.matcher(constructor.toString()).replaceAll(""));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}