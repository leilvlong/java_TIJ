package com.github.java01;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/*
(递归高级使用多用于算法....)
递归简单使用:

 */
public class job06 {
    static ArrayList<File> fileArrayList = new ArrayList<>();
    public static void main(String[] args) {
        //System.out.println(fun(5));
        File file = new File("E:\\");
        fun2(file);
        System.out.println(fileArrayList);
    }

    public static int fun(int num){
        if(num == 1){
            return num;
        }
        return num*fun((num-1));
    }

    public static void fun2(File file){
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1.isDirectory() && file1.exists()) {
                    fun2(file1);
                } else {
                    fileArrayList.add(file1);
                    System.out.println(fileArrayList.size());
                }
            }
        }
    }
}
