package com.github.java04;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/*
java 网络编程:
    发消息是输出
    接收服务器消息是输入
    从客户端发送文件给服务端是输入再输出
    从服务器接收文件是输入再输出

这种网络编程有较大的局限性:
    居然要靠关流来阻塞等待服务端刷新消息
 */
public class Client {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception{
        //fun1();
        fun2();
    }

    /*
    客户端服务端通话
     */
    public static void fun1() throws Exception{
        Socket socker = new Socket("127.0.0.1",20020);
        BufferedInputStream bil = new BufferedInputStream(socker.getInputStream());
        BufferedOutputStream bol = new BufferedOutputStream(socker.getOutputStream());

        System.out.println("成功连上服务端");
        String s = sc.nextLine();
        bol.write(s.getBytes());
        bol.flush();
        socker.shutdownOutput();
        int len;
        byte[] bytes = new byte[1024 * 8];
        while ((len = bil.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, len));
        }

        //bol.close();
        bil.close();
        socker.close();
    }

    public static void fun2() throws Exception{
        Socket socker = new Socket("127.0.0.1",20020);
        BufferedInputStream bil = new BufferedInputStream(socker.getInputStream());
        BufferedOutputStream bol = new BufferedOutputStream(socker.getOutputStream());
        System.out.println("成功连上服务端");

        BufferedInputStream bfil = new BufferedInputStream(new FileInputStream("demo01\\funs\\wonver.jpg"));
        int len1;
        for(byte[] bytes=new byte[1024*8];(len1=bfil.read(bytes)) != -1;){
            bol.write(bytes,0,len1);
        }
        bol.flush();
        socker.shutdownOutput();

        int len2;
        for(byte[] bytes=new byte[1024*8];(len2=bil.read(bytes)) != -1;){
            System.out.println(new String(bytes,0,len2));
        }

        bfil.close();
        //bol.close();
        bil.close();
        socker.close();
    }


}
