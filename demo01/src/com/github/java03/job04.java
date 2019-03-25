package com.github.java03;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/*
I/O的使用虽然简便  但是对应的类很多 使用起来各具特点:
    字节流:
        FileInputStream      输入字节流(read())
        FileOutputStream     输出字节流(write())
        BufferedInputStream   输入缓冲字符流
        BufferedOutputStream  输出缓冲字符流
        缓冲的底层是用数组实现的(将字节数据存入数组中
        若底层的数组装满后继续添加缓冲内容则之前的缓冲
        会从内存中出来(写入文件/从文件读出),若未装满
        则需 .close 或 .flush 才会从内存中出来(写入文件/
        从文件读出))
        此外 缓冲对原先的流进行了一些包装变得更为好用
    字符流:
        FilerReader          输入字符流
        FilerWriter          输出字符流
        BufferedReader        输入字符缓冲流
        BufferedWriter        输出缓冲字符流
    序列化流(序列化的该类需实现):
        ObjectInputStream    输入对象流
        ObjectOutputStream   输出对象流
        // 需要注意 当输入输出后 就不再是同一引用了(无法)
    转换流(gbk -> utf-8(可指定)):
        InputStreamReader    输入转换流
        OutputStreamWriter   输出转换流
输出流中:
    构造中有可选项 append(true为续写 false为覆盖(默认项))
 */
public class job04 {
    public static void main(String[] args) throws Exception {
        //fun1();
        //fun2();
        //fun3();
        //fun4();
        //fun5();
        fun6();
    }

    /*
        FileInputStream      输入字节流(read())
        FileOutputStream     输出字节流(write())
     */
    public static void fun1() throws IOException {
        FileInputStream fil = new FileInputStream("demo01\\funs\\aaa.txt");
        FileOutputStream fos = new FileOutputStream("demo01\\funs\\bbb.txt");

        for(int byt; (byt = fil.read()) != -1;){
            fos.write(byt);
        }

        fos.close();
        fil.close();
    }

    /*
        BufferedInputStream   输入缓冲字符流
        BufferedOutputStream  输出缓冲字符流
     */
    public static void fun2() throws IOException{
        BufferedInputStream bfil = new BufferedInputStream(new FileInputStream("demo01\\funs\\ccc.txt"));
        BufferedOutputStream bfol = new BufferedOutputStream(new FileOutputStream("demo01\\funs\\ddd.txt"));

        int len;
        for(byte[] bytes = new byte[1024*8];(len=bfil.read(bytes)) != -1;){
            bfol.write(bytes,0,len);
        }

        bfol.close();
        bfil.close();
    }

    /*
        FilerReader          输入字符流
        FilerWriter          输出字符流
     */
    public static void fun3() throws IOException{
        FileReader fil = new FileReader("demo01\\funs\\eee.txt");
        FileWriter fol = new FileWriter("demo01\\funs\\fff.txt");

        int len;
        for(char[] chars = new char[1024*8];(len = fil.read(chars)) != -1;){
            fol.write(chars,0,len);
        }

        fil.close();
        fol.close();
    }

    /*
        BufferedReader        输入字符缓冲流
        BufferedWriter        输出缓冲字符流
     */
    public static void fun4() throws IOException{
        BufferedReader fil = new BufferedReader(new FileReader("demo01\\funs\\ggg.txt"));
        BufferedWriter fol = new BufferedWriter(new FileWriter("demo01\\funs\\hhh.txt",true));

        int len;
        for(;(len = fil.read()) != -1; ){
            fol.write(len);
        }
        fol.close();
        fil.close();
    }

    /*
        ObjectInputStream    输入对象流
        ObjectOutputStream   输出对象流
     */
    public static void fun5() throws Exception{
        ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("demo01\\funs\\iii.txt"));
        /*ArrayList<Cat> catList= new ArrayList<>();
        Cat cat1 = new Cat("白猫",1);
        Cat cat2 = new Cat("白猫",1);
        Cat cat3 = new Cat("白猫",1);
        catList.add(cat1);
        catList.add(cat2);
        catList.add(cat3);
        objOut.writeObject(catList);*/
        Cat cat1 = new Cat("黑猫", 10);
        objOut.writeObject(cat1);

        ObjectInputStream objIn  = new ObjectInputStream(new FileInputStream("demo01\\funs\\iii.txt"));
        /*ArrayList<Cat> catlist =(ArrayList<Cat>)objIn.readObject();
        // 当输入再输出后 就不再是同一对象
        System.out.println(catlist.remove(cat1));*/
        // 重写equlas后进行比较 同一属性 同一值
        // 不重写equlas进行比较 内存地址不同  经过序列化与反序列化后 引用发生了改变(内存发生了改变)

        // 尝试对该对象续写(属性修改) 改为 age 15
        Cat cat2 =(Cat) objIn.readObject();
        System.out.println(cat2);
        cat2.age = 15;
        objOut.writeObject(cat2);
        Cat cat3 = (Cat)objIn.readObject();
        System.out.println(cat3);
    }

    /*
        InputStreamReader    输入转换流
        OutputStreamWriter   输出转换流
     */
    public static void fun6() throws Exception{
        InputStreamReader fil = new InputStreamReader(new FileInputStream("demo01\\funs\\jjj.txt"),"gbk");
        OutputStreamWriter fol = new OutputStreamWriter(new FileOutputStream("demo01\\funs\\kkk.txt"),"utf-8");
        int len;
        for(char[] chars=new char[1024*8];(len=fil.read(chars)) != -1; ){
            fol.write(chars,0,len);
        }
        fol.close();
        fil.close();
    }

}

class Cat implements Serializable{
    String color;
    int age;

    public Cat() {

    }

    public Cat(String color, int age) {
        this.color = color;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "color='" + color + '\'' +
                ", age=" + age +
                '}';
    }


}


