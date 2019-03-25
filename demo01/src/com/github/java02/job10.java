package com.github.java02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Set;

/*
属性集与配置文件的一些方法
 */
public class job10 {
    public static void main(String[] args) throws Exception{
        Properties ppt = new Properties();
        FileInputStream fil = new FileInputStream("demo01\\funs\\config.properties");
        ppt.load(fil);
        fil.close();
        // 查看配置文件信息
        ppt.list(new PrintStream(System.out));
        // 获取所有key与值
        Set<String> keys = ppt.stringPropertyNames();
        for (String key : keys) {
            System.out.println(ppt.getProperty(key));
        }


        // 修改age的值 并同步到配置文件中
        ppt.setProperty("age","33");
        FileOutputStream fol = new FileOutputStream("demo01\\funs\\config.properties");
        ppt.store(fol,"修改年龄");
        fol.close();

    }
}
