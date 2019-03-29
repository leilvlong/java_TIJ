package com.github.java03;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
/*
流的简单练习
 */
public class job03 {
    public static void main(String[] args) throws IOException {
        // fun1();
        //fun2();
        //fun3();
        //fun4();
        //fun5();
        //fun6();
        //fun7();
        //fun8();
        //fun9('a');
        //fun10();
        //fun();
        fun11();
    }

    public static void fun() throws  IOException{
        FileInputStream fis = new FileInputStream("demo01\\files\\aaa.txt");
        FileOutputStream fos = new FileOutputStream("demo01\\files\\bbb.txt");
        for(int x; (x =fis.read()) != -1;){
            fos.write(x);
        }

        fos.close();
        fis.close();

    }

    public static void fun1 () throws IOException {
        FileOutputStream fil = new FileOutputStream("demo01\\files\\ccc.txt");
        fil.write(97);
        fil.close();
    }

    public static void fun2() throws IOException{
        FileOutputStream fil = new FileOutputStream("demo01\\files\\ddd.txt");
        String str = "i love java";
        for (int i = 0; i < str.length(); i++) {
            fil.write(str.charAt(i));
        }
        fil.close();
    }

    public static void fun3() throws IOException{
        FileOutputStream fil = new FileOutputStream("demo01\\files\\ccc.txt",true);
        for (int i = 0; i < 5; i++) {
            fil.write("\r\ni lovae java".getBytes());
        }
        fil.close();
    }

    public static void fun4() throws IOException{
        FileInputStream fil = new FileInputStream("demo01\\files\\ddd.txt");
        int count;
        while((count = fil.read()) != -1){
            System.out.println(count);
        }
        fil.close();
    }

    public static void fun5() throws IOException{
        FileInputStream fil = new FileInputStream("demo01\\files\\ddd.txt");
        byte[] byt = new byte[1024];
        int len;
        while((len = fil.read(byt)) != -1){
            System.out.println(new String(byt,0,len));
        }
        fil.close();
    }

    public static void fun6() throws IOException{
        FileInputStream fil = new FileInputStream("C:\\Users\\L1455013965\\Pictures\\Saved Pictures\\1520986107703.jpg");
        FileOutputStream out = new FileOutputStream("demo01\\files\\wonver.jpg");
        byte[] byt = new byte[1024];
        int len;
        while((len=fil.read(byt)) != -1){
            out.write(byt,0,len);
        }
        out.close();
        fil.close();
    }

    public static void fun7() throws IOException{
        FileWriter fil = new FileWriter("demo01\\files\\eee.txt");
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("请输入: ");
            String input = sc.nextLine();
            if(input.equals("886")){
                break;
            }
            fil.write(input);
            fil.write("\r\n");

        }
        fil.close();
    }

    public static void fun8() throws IOException{
        Properties pro = new Properties();
        FileReader fil = new FileReader("demo01\\files\\config.properties");
        FileWriter fwl= new FileWriter("demo01\\files\\config.properties",true);
        pro.load(fil);
        for (String str : pro.stringPropertyNames()) {
            if(str.equals("lisi")){
                pro.setProperty(str,"100");
                pro.store(fwl,"??");
            }
        }
        fwl.close();
        fil.close();
    }

    public static void fun9(char cha) throws IOException {
        FileInputStream fil = new FileInputStream("demo01\\files\\fff.txt");
        int chas;
        int count=0;
        while((chas = fil.read()) != -1){
            if((char)chas == cha){
                count++;
            }
        }
        System.out.println(count);
        fil.close();
    }

    public static void fun10() throws IOException{
        FileOutputStream fil = new FileOutputStream("demo01\\files\\ggg.txt",true);
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("输入end退出系统: ");
            System.out.print("请输入学号: ");
            String str1 = sc.nextLine();
            if(str1.equals("end")){
                break;
            }
            fil.write((str1+": ").getBytes());
            System.out.print("请输入名字: ");
            String str2 = sc.nextLine();
            fil.write((str2+"\r\n").getBytes());
        }
        fil.close();
    }

    public static void fun11() throws IOException{
        FileInputStream fil = new FileInputStream("demo01\\hhh.docx");
        FileOutputStream fol = new FileOutputStream("demo01\\iii.txt");
        int len;
        while((len=fil.read())!=-1){
            fol.write(len);
        }
        fol.close();
        fil.close();
    }
}


