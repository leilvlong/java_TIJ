package com.github.java01;



/*
进程间共享数据可以轻易实现,使用同步代码块可以确保数据的安全
但是如何实现多条线程间操作数据,并满足要求
这涉及到线程通信:
    由线程锁对象实现(线程锁对象可以确保数据安全的根本目的是为了操作数据,
                       所以线程通信理所应当应由线程锁对象实现)
    tip: 由于线程锁对象可以是任意对象,所以很自然而然的在基类Object中存在对线程通信的操作方法
         public final void wait()  让线程等待
         public final native void notify()  让线程执行
线程通信带来的最直观的体现:
    生产者与消费者:
        生产者 生成数据
        消费者 使用数据
        例: 对一个根文件夹进行操作 根文件夹包含多个子文件夹(假设10000个) 对文件名字包含name的文件进行插入一条文本说明
            则可以一条线程遍历这个根文件夹 找到包含name的文件子文件夹与文件组成的路径 加入容器
              另外一条(或多条,根据实际场景进行)线程去容器取路径,取完路径后删除该路径,然后对文件操作
        (我对java的文件流还不是太会写所以代码会换个例子 但是思路应该没问题)
可以很容易想到:
    多线程应用场景在 需要协同但是大量重复的情况



设计程序:
    1. 不应将简单的问题复杂化
            即逻辑应当条理分明,
            而实现时则允许使用更多的代码减少计算次数而达到提高性能的效果(但逻辑依然简单明了)
                例如: 1. 计算1到n的和 可以使用for累加 也可以使用计算公式 (1+n)*n/2
                      2. 使用更多代码减少计算次数的代码案例一时想不到
    2. 着手开始写程序时 程序是已知的属性与行为的集合

 */


import java.util.ArrayList;
import java.util.Random;

/*
单生产单消费
一条线程往ArrayList 添加元素
一条线程将ArrayList 删除元素:
    或者开多条线程删除元素(其实单生产多消费原理没什么不同)
        (呵呵 我还是太天真!!!)
        最保守稳定的做法还是单生产单消费

 */
public class job03 {
    public static void main(String[] args) {
        Array array = new Array();
        AddArray addArray = new AddArray(array);
        RemoveArray removeArray = new RemoveArray(array);
        addArray.start();
        removeArray.start();

    }
}

// 设计到生产与消费的第三方媒介
class Array {
    ArrayList<Integer> intList = new ArrayList<>();
}

// 生产者操作媒介
class AddArray extends Thread{
    Array array;
    Random r = new Random();

    public AddArray(Array array) {
        this.array = array;
    }

    @Override
    public void run() {
        // 生产者将添加随机数
        while(true) {
            synchronized (array) {
                if (!array.intList.isEmpty()) {
                    try {
                        array.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int num = r.nextInt(100) + 1;
                array.intList.add(num);
                System.out.println(array.intList);
                array.notify();
            }
        }
    }
}

// 消费者操作媒介
class RemoveArray extends Thread{
    Array array;

    public RemoveArray(Array array) {
        this.array = array;
    }

    @Override
    public void run() {
        // 消费者删除这些随机数
        while(true){
            synchronized (array) {
                if( array.intList.isEmpty()){
                    try {
                        System.out.println("等待添加元素");
                        array.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int x = 0; x < array.intList.size(); x++) {
                    array.intList.remove(x);
                    x--;
                    if(array.intList.isEmpty()){
                        System.out.println("删除完毕: "+ array.intList);
                    }
                }
                array.notify();
            }
        }
    }
}


