package com.github.java01;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/*
解决线程数据安全问题的方法:
    1.ReentrantLock对象 的lock与unlock (上锁需要释放锁 忘记可能导致各种问题)
        抢到执行权的线程一直在运行即便代码运行完毕(我的电脑在哭泣)
    2.synchronized(锁对象:一般使用 类名.class) 修饰
        1. 修饰代码块
            this (多个对象时不好用,会有数据安全问题)
            类名.class (相对好用)
        2. 修饰方法(不太灵活)
            1.普通方法 this对象锁
            2.静态方法  类名.class(字节码对象锁)
        3.由于字节码对象的锁是唯一的 可以确保数据安全
            而this锁对象会随着对象创建而更新 所以应当视场景使用
开启线程和释放线程资源势必会消耗系统性能,但是线程的开启再大量数据计算时则可以达到提高程序运行效果

 */
public class job02 {
    static int counts = 1000;
    public static void main(String[] args) {
        // 开启四条线程交替执行 并确保数据安全
        // lambda线程开启
/*        new Thread(()->{
            while (true){
                synchronized (job02.class){
                    if(counts<=0){
                        break;
                    }
                    System.out.println(Thread.currentThread().getName()+"counts"+counts);
                    counts--;
                }
            }
        },"线程1:").start();

        new Thread(()->{
            while (true){
                synchronized (job02.class){
                    if(counts<=0){
                        break;
                    }
                    System.out.println(Thread.currentThread().getName()+"counts"+counts);
                    counts--;
                }
            }
        },"线程2:").start();

        new Thread(()->{
            while (true){
                synchronized (job02.class){
                    if(counts<=0){
                        break;
                    }
                    System.out.println(Thread.currentThread().getName()+"counts"+counts);
                    counts--;
                }
            }
        },"线程3:").start();

        new Thread(()->{
            while (true){
                synchronized (job02.class){
                    if(counts<=0){
                        break;
                    }
                    System.out.println(Thread.currentThread().getName()+"counts"+counts);
                    counts--;
                }
            }
        },"线程4:").start();*/

    // 继承 Runnable接口 用synchronized修饰 代码块与普通方法和静态方法
        /*MyRunnable my = new MyRunnable();
        new Thread(my).start();
        new Thread(my).start();
        new Thread(my).start();
        new Thread(my).start();*/

    //  继承Thread用synchronized修饰 代码块与普通方法和静态方法
        // synchronized使用this修饰是否会出现数据安全问题需待验证(控制台打印的信息不好看)
       /*new MyThread().start();
       new MyThread().start();
       new MyThread().start();
       new MyThread().start();*/

    // 写一个类 继承Thread 使用对象锁 开启四条线程访问共享资源 每条线程将访问数据加入容器
    // 如果容器长度不对则数据安全有问题
/*      new MyThread2().start();
        new MyThread2().start();
        new MyThread2().start();
        new MyThread2().start();
        // 主线程不去争抢执行权 确保四条验证线程先执行完毕
        try {
            Thread.sleep(10000);
            System.out.println(MyThread2.intList.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    // 创建一个对象 给多个线程运行
        /*MyThread2 my = new MyThread2();
        new Thread(my).start();
        new Thread(my).start();
        new Thread(my).start();
        new Thread(my).start();
        try {
            Thread.sleep(10000);
            System.out.println(MyThread2.intList.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        // 使用ReentrantLock.lock上锁而不释放锁
        MyThread2 my = new MyThread2();
        new Thread(my).start();
        new Thread(my).start();
        new Thread(my).start();
        new Thread(my).start();


    }

}


// 继承接口实现多线程
class MyRunnable implements Runnable {
    private static int counts = 1000;

    @Override
    public void run() {
        while (true){
            synchronized (MyRunnable.class){
                if(counts<=0){
                    break;
                }
                System.out.println(Thread.currentThread().getName()+"counts"+counts);
                counts--;
            }
        }
    }

    /*public void run(){
        fun2();
    }*/

    public synchronized void fun(){
        while (true){
            if(counts<=0){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"counts"+counts);
            counts--;
        }
    }

    public static synchronized void fun2(){
        while (true){
            if(counts<=0){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"counts"+counts);
            counts--;
        }
    }
}

// 继承Threda父类
class MyThread extends Thread {
    private static int counts = 1000;

    /*@Override
    public void run() {
        while (true){
            synchronized (MyThread.class){
                if(counts<=0){
                    break;
                }
                System.out.println(Thread.currentThread().getName()+"counts"+counts);
                counts--;
            }
        }
    }*/

    public void run(){
        fun2();
    }

    public synchronized void fun(){
        while (true){
            if(counts<=0){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"counts"+counts);
            counts--;
        }
    }

    public static synchronized void fun2(){
        while (true){
            if(counts<=0){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"counts"+counts);
            counts--;
        }
    }
}

// 线程对象锁安全验证
class MyThread2 extends Thread{
    private static int num = 10000;
    static ArrayList<Integer> intList = new ArrayList<>();
    ReentrantLock lock = new ReentrantLock();

    /*@Override
    public void run() {
        while(true){
            synchronized (MyThread2.class){
                if(num<=0){
                    break;
                }
                intList.add(num--);
                if(num==0){
                    System.out.println(Thread.currentThread().getName()+"num"+num);
                    System.out.println("线程执行完毕");
                }
            }
        }
    }*/

/*    @Override
    public void run() {
        while(true){
            synchronized (this){
                if(num<=0){
                    break;
                }
                intList.add(num--);
                if(num==0){
                    System.out.println(Thread.currentThread().getName()+"num"+num);
                    System.out.println("线程执行完毕");
                }
            }
        }
    }*/

    @Override
    public void run() {
        while(true){
            lock.lock();
            if (num <= 0) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "num" + num);
            intList.add(num--);
        }
    }
}
