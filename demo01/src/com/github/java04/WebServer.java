package com.github.java04;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/*
bos.write("HTTP/1.1 200 OK\r\n".getBytes());
bos.write("Content-Type:text/html\r\n".getBytes());
// 必须要写入空行,否则浏览器不解析
bos.write("\r\n".getBytes());
 */
/*
网络编程之模拟服务器
 */
public class WebServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(9898);
        while(true){
            Socket socket = serverSocket.accept();
            new Thread(()->{
                try {
                    //获取和浏览器连接的输入流
                    BufferedReader bil = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    // 获取http请求路径
                    String httpPath = bil.readLine();
                    String[] splitHttp = httpPath.split(" ");
                    String path = splitHttp[1].substring(1);
                    System.out.println("path: "+ path);

                    // 返回浏览器请求的资源
                    BufferedInputStream bfil = new BufferedInputStream(new FileInputStream(path));
                    BufferedOutputStream bol = new BufferedOutputStream(socket.getOutputStream());

                    // 返回http响应格式
                    bol.write("HTTP/1.1 200 OK\r\n".getBytes());
                    bol.write("Content-Type:text/html\r\n".getBytes());
                    bol.write("\r\n".getBytes());

                    // 响应对应路径资源
                    int len;
                    for( byte[] bytes = new byte[1024 * 8];(len=bfil.read(bytes)) != -1;){
                        bol.write(bytes,0,len);
                    }
                    bfil.close();
                    bol.close();
                    bil.close();
                    socket.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();

        }
    }
}

/*
//获取和浏览器连接的输入流
                    BufferedReader bil = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    // 获取http请求路径
                    String httpPath = bil.readLine();
                    String[] splitHttp = httpPath.split(" ");
                    String path = splitHttp[1].substring(1);
                    System.out.println("path: "+ path);

                    // 返回浏览器请求的资源
                    BufferedInputStream bfil = new BufferedInputStream(new FileInputStream(path));
                    BufferedOutputStream bol = new BufferedOutputStream(socket.getOutputStream());

                    // 返回http响应格式
                    bol.write("HTTP/1.1 200 OK\r\n".getBytes());
                    bol.write("Content-Type:text/html\r\n".getBytes());
                    bol.write("\r\n".getBytes());

                    // 响应对应路径资源
                    int len;
                    for( byte[] bytes = new byte[1024 * 8];(len=bfil.read()) != -1;){
                        bol.write(bytes,0,len);
                    }
                    bfil.close();
                    bol.close();
                    bil.close();
                    socket.close();
 */