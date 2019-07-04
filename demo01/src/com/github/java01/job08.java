package com.github.java01;

import java.util.ArrayList;
import java.util.Random;

/*
之前遇到的解决不了的问题先丢在一边
多线程案例:
    一个生产者生产8条数据在ArrayList中 4条线程交替删除元素
记住一个原则:
    线程在哪里"睡的","醒了"就从哪里开始干活

 */
public class job08 {
    static ArrayList<Integer> integers = new ArrayList<>();
    static Random r = new Random();
    static int count = 1;

    public static void main(String[] args) {
        // 线程1  作为生产者
/*        new Thread(()->{
            while (true) {
                synchronized (job08.class) {
                    while(!integers.isEmpty()) {
                        try {
                            // 如果非空 则无需添加
                            job08.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // 生产数据
                    for (int x = 0; x < 8; x++) {
                        integers.add(r.nextInt(100) + 1);
                    }
                    // 加工数据结束后唤醒消费者线程执行
                    job08.class.notifyAll();
                }
            }
        }).start();

        // 线程2删除数据
        new Thread(()->{
            while(true){
                synchronized (job08.class) {
                    while( integers.isEmpty() || count !=1){
                        try {
                            job08.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName()+"当前容器为: "+integers);
                    System.out.println("删除元素为: " + integers.get(0));
                    integers.remove(0);

                    job08.class.notifyAll();

                    count = 2;
                }
            }
        }).start();

        // 线程3删除数据
        new Thread(()->{
            while(true){
                synchronized (job08.class) {
                    while( integers.isEmpty() || count != 2 ){
                        try {
                            job08.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(Thread.currentThread().getName()+"当前容器为: "+integers);
                    System.out.println("删除元素为: " + integers.get(0));
                    integers.remove(0);

                    job08.class.notifyAll();

                    count = 3;
                }
            }
        }).start();

        // 线程4删除数据
        new Thread(()->{
            while(true){
                synchronized (job08.class) {
                    while( integers.isEmpty() || count != 3){
                        try {
                            job08.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(Thread.currentThread().getName()+"当前容器为: "+integers);
                    System.out.println("删除元素为: " + integers.get(0));
                    integers.remove(0);

                    job08.class.notifyAll();

                    count = 4;
                }
            }
        }).start();

        // 线程5删除数据
        new Thread(()->{
            while(true){
                synchronized (job08.class) {
                    while( integers.isEmpty() || count != 4){
                        try {
                            job08.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName()+"当前容器为: "+integers);
                    System.out.println("删除元素为: " + integers.get(0));
                    integers.remove(0);

                    job08.class.notifyAll();

                    count = 1;
                }
            }
        }).start();*/

        new Run1().start();
        new Run2().start();
        new Run3().start();
        new Run4().start();
        new Run5().start();
    }
}

// 没问题
class Run1 extends Thread{
    @Override
    public void run() {
        while (true) {
            synchronized (job08.class) {
                while(!job08.integers.isEmpty()) {
                    try {
                        // 如果非空 则无需添加
                        job08.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 生产数据
                for (int x = 0; x < 8; x++) {
                    job08.integers.add(job08.r.nextInt(100) + 1);
                }
                // 加工数据结束后唤醒消费者线程执行
                job08.class.notifyAll();
            }
        }
    }
}

// 执行了
class Run2 extends Thread{
    @Override
    public void run() {
        while(true){
            synchronized (job08.class) {
                while( job08.integers.isEmpty() || job08.count !=1){
                    try {
                        job08.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()+"当前容器为: "+job08.integers);
                System.out.println("删除元素为: " + job08.integers.get(0));
                job08.integers.remove(0);

                job08.class.notifyAll();

                job08.count = 2;
            }
        }
    }
}

class Run3 extends Thread{
    @Override
    public void run() {
        while(true){
            synchronized (job08.class) {
                while( job08.integers.isEmpty() || job08.count != 2 ){
                    try {
                        job08.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread().getName()+"当前容器为: "+job08.integers);
                System.out.println("删除元素为: " + job08.integers.get(0));
                job08.integers.remove(0);

                job08.class.notifyAll();

                job08.count = 3;
            }
        }
    }
}

class Run4 extends Thread{
    @Override
    public void run() {
        while(true){
            synchronized (job08.class) {
                while( job08.integers.isEmpty() || job08.count != 3 ){
                    try {
                        job08.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread().getName()+"当前容器为: "+job08.integers);
                System.out.println("删除元素为: " + job08.integers.get(0));
                job08.integers.remove(0);

                job08.class.notifyAll();

                job08.count = 4;
            }
        }
    }
}

class Run5 extends Thread{
    @Override
    public void run() {
        while(true){
            synchronized (job08.class) {
                while( job08.integers.isEmpty() || job08.count != 4){
                    try {
                        job08.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread().getName()+"当前容器为: "+job08.integers);
                System.out.println("删除元素为: " + job08.integers.get(0));
                job08.integers.remove(0);

                job08.class.notifyAll();

                job08.count = 1;
            }
        }
    }
}