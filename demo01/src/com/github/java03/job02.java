package com.github.java03;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/*
字符流
 */
public class job02 {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        FileReader reader = new FileReader("demo01\\funs\\config.properties");
        properties.load(reader);
        reader.close();
        Set<Object> strings = properties.keySet();
        for (Object string : strings) {
            System.out.println(properties.getProperty((String) string));
        }
    }
}
