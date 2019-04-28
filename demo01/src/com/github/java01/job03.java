package com.github.java01;



/*

 */


import java.util.ArrayList;
import java.util.Random;




public class job03 {
    public static void main(String[] args) {
        Array array = new Array();
        new AddArray(array).start();
        new RemoveArray(array).start();


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


