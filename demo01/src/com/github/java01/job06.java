package com.github.java01;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/*
(递归高级使用多用于算法....)
递归简单使用:
    案例在下

递归次数过多会导致内存溢出:
    栈有先进后出的原理:
        当递归调用自己过多 方法出不了栈,栈内存使用完了得不到释放而导致内存溢出
        (可以理解: 一直开进程运算导致电脑cpu运算爆炸
        而栈就是这个cpu)
 */
public class job06 {
    static ArrayList<File> fileArrayList = new ArrayList<>();
    public static void main(String[] args) {
        //System.out.println(fun(5));
        /*File file = new File("E:\\");
        fun2(file);
        System.out.println(fileArrayList);*/
        System.out.println(fun3(5));
    }

    public static int fun(int num){
        if(num == 1){
            return num;
        }
        return num*fun((num-1));
    }

    public static int fun3(int num){
            if(num==1){
                return num;
            }
        return num+ fun3(num-1);
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
