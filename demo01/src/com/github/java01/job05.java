package com.github.java01;

import java.util.ArrayList;

/*
开启三条线程交替执行:
    使用标识标记状态来进行执行
 */


public class job05 {
    static int tag=1;
    public static void main(String[] args) {
        new Thread(()->{
            while(true){
                synchronized (job05.class){
                    if(tag != 1){
                        try {
                            job05.class.wait();
                            continue;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName()+": "+tag);
                    tag = 2;
                    job05.class.notifyAll();
                }
            }
        }).start();

        new Thread(()->{
            while(true){
                synchronized (job05.class){
                    if(tag != 2){
                        try {
                            job05.class.wait();
                            continue;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName()+": "+tag);
                    tag = 3;
                    job05.class.notifyAll();
                }
            }
        }).start();

        new Thread(()->{
            while(true){
                synchronized (job05.class){
                    if(tag != 3){
                        try {
                            job05.class.wait();
                            continue;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName()+": "+tag);
                    tag = 1;
                    job05.class.notifyAll();
                }
            }
        }).start();
    }
}


