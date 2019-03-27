package com.github.java04;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

/*
java 网络编程:
    收消息是输入
    返回消息是输出
    从客户端接收文件是输入到服务器再输出到硬盘
    从服务器返回文件给客户端是先输入到服务器再输出到客户端
 */
public class Server {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        //fun1();
        fun2();
    }

    /*
    客户端服务端通话
     */
    public static void fun1() throws Exception{
        // 初始化工作
        ServerSocket serverSocket = new ServerSocket(20020);
        Socket socker = serverSocket.accept();
        BufferedInputStream bil = new BufferedInputStream(socker.getInputStream());
        BufferedOutputStream bol = new BufferedOutputStream(socker.getOutputStream());
        System.out.println("处理客户端请求中.....");
        int len;
        byte[] bytes = new byte[1024 * 8];
        while ((len = bil.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, len));
        }
        String s = sc.nextLine();
        bol.write(s.getBytes());

        bol.close();
        bil.close();
        socker.close();
        serverSocket.close();
    }

    public static void fun2() throws Exception{
        ServerSocket serverSocket = new ServerSocket(20020);
        while(true){
            Socket socker = serverSocket.accept();
            new Thread(()->{
                try {
                    BufferedInputStream bil = new BufferedInputStream(socker.getInputStream());
                    BufferedOutputStream bol = new BufferedOutputStream(socker.getOutputStream());
                    long jpg = System.currentTimeMillis();
                    String path = "demo01\\funs\\"+jpg+".jpg";

                    BufferedOutputStream bfol = new BufferedOutputStream(
                            new FileOutputStream(path));
                    int len;
                    byte[] bytes = new byte[1024 * 8];
                    while ((len = bil.read(bytes)) != -1) {
                        bfol.write(bytes,0,len);
                    }
                    bol.write("上传成功".getBytes());
                    bfol.close();
                    bol.close();
                    bil.close();
                    socker.close();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();
        }
    }


}
