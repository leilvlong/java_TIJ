package com.github.java03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
/*
字节流
 */
public class job01 {
    public static void main(String[] args) throws IOException {
        FileInputStream fil = new FileInputStream("demo01\\aaa.txt");
        FileOutputStream out = new FileOutputStream("demo01\\bbb.txt");

        //byte[] byt = new byte[5];
        int count;
        while((count=fil.read()) != -1){
            out.write(count);
        }

        fil.close();
        out.close();

    }
}
