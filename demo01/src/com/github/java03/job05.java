package com.github.java03;

import java.io.*;
import java.util.Arrays;

/*

 */
public class job05 {
    public static void main(String[] args) throws IOException {
       fun();
       fun2();
    }

    public static void fun() throws IOException{
        // 输入缓冲流
        FileInputStream fil = new FileInputStream("demo01\\funs\\ddd.txt");
        BufferedInputStream bfil = new BufferedInputStream(fil);

        // 输出缓冲流
        FileOutputStream fol = new FileOutputStream("demo01\\funs\\mmm.txt");
        BufferedOutputStream bfol = new BufferedOutputStream(fol);

        long start = System.currentTimeMillis();
        for(int len;(len=bfil.read()) != -1;){
            bfol.write(len);
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        bfol.close();
        bfil.close();
    }

    public static void fun2() throws IOException{
        // 输入缓冲流
        FileInputStream fil = new FileInputStream("demo01\\funs\\ddd.txt");

        // 输出缓冲流
        FileOutputStream fol = new FileOutputStream("demo01\\funs\\mmm.txt",true);

        long start = System.currentTimeMillis();
        int len;
        for(byte[] bytes = new byte[1024*8];(len=fil.read(bytes)) != -1;){
            fol.write(bytes,0,len);
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        fol.close();
        fil.close();
    }

}

