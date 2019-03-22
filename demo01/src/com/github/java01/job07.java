package com.github.java01;

import java.util.ArrayList;

/*
多线程计数
    四条线程计数到十万
    虽然成功做到了 但是却是失败的
    遇到了与之前相同的情况 信息不对称,添加了额外的判断才解决问题:
    new Thread(()->{
            while (count <= 100000) {
                synchronized (job07.class) {
                    if (count >= 100000){
                        break
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + " count: " + count);
                }
            }
        }).start();
    不然最终值会到100004
    是我的代码组织有问题?
    经过分析得出: 多线程对数据的操作是原子性的 如果我没有将while包括在循环中
                  当计数以100000进入循环语句中时 因为执行程序语句synchronized (job07.class)
                  会消耗一定的时间 而这时还未进入同步代码块中 此时另外三条线程争抢执行权进入到这里
                  换言之 4条线程的计数都为十万 都进入了while语句中 当执行具体的同步代码块中的内容时
                  会返回这样的结果也就不奇怪了
    更改了代码以后:
        new Thread(()->{
            synchronized (job07.class) {
                while (count <= 100000) {
                    count++;
                    System.out.println(Thread.currentThread().getName() + " count: " + count);
                }
            }
        }).start();
   最终值为100001:
        其实是没错的 因为是100000进去的 然后自增
        但是这会导致一个线程执行到结束 (头好大)
 */
public class job07 {
    static int count = 0;
    public static void main(String[] args) throws InterruptedException {

        // 线程1
        new Thread(()->{
            while (count <= 100000) {
                synchronized (job07.class) {
                    if (count == 100000){
                        break;
                    }
                        count++;
                    System.out.println(Thread.currentThread().getName() + " count: " + count);
                }
            }
        }).start();

        // 线程2
        new Thread(()->{
            while (count <= 100000) {
                synchronized (job07.class) {
                    if (count >= 100000){
                        break;
                    }
                        count++;
                    System.out.println(Thread.currentThread().getName() + " count: " + count);
                }
            }
        }).start();

        // 线程3
        new Thread(()->{
            while (count <= 100000) {
                synchronized (job07.class) {
                    if (count >= 100000){
                        break;
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + " count: " + count);
                }
            }
        }).start();

        // 线程4
        new Thread(()->{
            while (count <= 100000) {
                synchronized (job07.class) {
                    if (count >= 100000){
                        break;
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + " count: " + count);
                }
            }
        }).start();

    }

    public static long fun(){
        long l = System.currentTimeMillis();
        int con = 0;
        while(con<=1000000){
            con++;
            System.out.println(Thread.currentThread().getName() + " con: " + con);
            if(con==1000001){
                job07.class.notify();
            }
        }

        long l1 = System.currentTimeMillis();
        return l1-l;
    }
}

