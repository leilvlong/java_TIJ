package com.github.java10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
使用matcher.reset方法重新设置扫描的字符串,可以减少对象的创建,优化性能
正则表达式也可以反复使用
 */
public class job22 {
    public static void main(String[] args) {
        Matcher m = Pattern.compile("[frb][aiu][gx]").matcher("fix the rug with rags");
        while (m.find()){
            System.out.print(m.group()+"  ");
        }
        System.out.println();
        m.reset("fix the rig with rags");
        while (m.find()) {
            System.out.print(m.group()+"  ");
        }

    }
}


