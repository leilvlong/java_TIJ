package com.github.java01;

import java.util.ArrayList;
import java.util.Random;


/*
线程间资源共享与第三方容器:
    多线程实现资源共享需要第三方媒介

 */
public class job04 {
    public static void main(String[] args) {
        Array04 array04 = new Array04();
        new AddArray04(array04).start();
        new RemoveArray04(array04).start();

    }
}

// 设计到生产与消费的第三方媒介
class Array04 {
    ArrayList<Integer> intList = new ArrayList<>();
}

class AddArray04 extends Thread{
    Array04 array04;
    Random r = new Random();

    public AddArray04(Array04 array04) {
        this.array04 = array04;
    }

    @Override
    public void run() {
        // 生产者将添加随机数
        while(true) {
            synchronized (array04) {
                if (!array04.intList.isEmpty()) {
                    try {
                        array04.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 10; i++) {
                    int num = r.nextInt(100) + 1;
                    array04.intList.add(num);
                }
                System.out.println(Thread.currentThread().getName()+ "元素列表为: "+ array04.intList);
                array04.notify();
            }
        }
    }
}


class RemoveArray04 extends Thread{
    Array04 array04;
    Random r = new Random();
    public RemoveArray04(Array04 array04) {
        this.array04 = array04;
    }

    @Override
    public void run() {
        // 消费者删除这些随机数
        while(true){
            synchronized (array04) {
                if( array04.intList.isEmpty()){
                    try {
                        System.out.println("等待添加元素..");
                        array04.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //ArrayList<Integer> intList = array04.intList;
                //Integer remove = intList.remove(r.nextInt(intList.size()));
                Integer remove = array04.intList.remove(0);
                System.out.println(Thread.currentThread().getName()+"删除了元素: "+remove);

                if(array04.intList.isEmpty()){
                    array04.notify();
                }

            }
        }
    }
}


