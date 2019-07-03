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

缓冲流读写文件为何会比单个字符读写快很多(实现原理):
    文件读写操作硬盘:
        单个字节的读与写操作会增加大量的硬盘开销
        而缓冲流则将多个字节读入内存中的数组上再写出到硬盘
        通过操作内存减少了硬盘读写的开销
        而操作内存是比硬盘快很多的
如何通过数组一次性读取写出多个字节:
    1. 一次性写入多个字节到数组(实现方式不知 最关键的两步)
    2. 遍历数组写入字节值(数组查询快)) 操作内存
    3. 遍历数组写取出存入的字节值
    4  将数组中的所有字节值一次写入到硬盘(实现方式不知 最关键的两步)
    因为数组特性的原因(所以会选择数组做缓冲流) 所以缓冲流会有flush方法:
        1. 有一个数组写入  [1,2,3,4,5]
        2. 写出
        3. 在此写入 [6,7,8,9,10]
        4.再次写出.....
        这个过程只需遍历就可以操作,不需要其他多余的操作
        当进行文件读写操作时:
            逻辑判断当数组写满后如果依然有后续字节写入
            就写出到文件 (为防止覆盖数组原先字节值所导致文件内容丢失)
            再重头(索引0)继续写入字节
数组特点:
    连续的内存空间查询只需遍历
        所以查询快
    因为是连续的内存空间,所以要进行增删时需对整个连续的内存块进行操作
    插入删除的时候，就要把修改的那个节点之后的所有数据都向后移动，或者向前移动。所以就慢了
链表特点:
    查询慢 增删快
    链表中的数据在内存中是松散的，每一个节点都有一个指针指向下一个节点，
    这样查找起来就比较慢了
    而插入删除的时候就是断开一个节点，然后插入删除之后再接起来
 */
public class job04 {
    public static void main(String[] args) throws Exception {
        //fun1();
        //fun2();
        //fun3();
        //fun4();
        //fun5();
        fun6();
        //fun7();
    }

    /*
        FileInputStream      输入字节流(read())
        FileOutputStream     输出字节流(write())
     */
    public static void fun1() throws IOException {
        FileInputStream fil = new FileInputStream("demo01/funs/aaa.txt");
        FileOutputStream fos = new FileOutputStream("demo01/funs/bbb.txt");

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
        InputStreamReader fil = new InputStreamReader(new FileInputStream("demo01\\funs\\曹尼玛.txt"),"gbk");
        OutputStreamWriter fol = new OutputStreamWriter(new FileOutputStream("demo01\\funs\\去尼玛.txt"),"gbk");
        int len;
        for(char[] chars=new char[1024*8];(len=fil.read(chars)) != -1; ){
            fol.write(chars,0,len);
        }
        fol.close();
        fil.close();
    }

    public static void fun7() throws IOException{
        // 验证不做写 单做读的速度
        FileInputStream fil = new FileInputStream("demo01\\funs\\mmm.txt");

        long statr = System.currentTimeMillis();
        for(byte[] bytes=new byte[1024*8];fil.read(bytes) != -1;){
        }
        long end = System.currentTimeMillis();
        System.out.println(end-statr);
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


