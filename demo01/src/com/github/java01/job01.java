package com.github.java01;
/*
线程:
    线程处理不妥当会带来各种问题,以下示例中
        //实际的num的值为 -70 但是因为线程等待的原因
        // main线程执行结束后 剩余的三个线程才开始执行
        // 即main线程则将自己执行的结果打印后才开始执行剩余的线程
        加入sleep  使main线程等待剩余的线程执行即可得到准确的数据结果
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


 */
public class job01 {
    public static void main(String[] args) {
        Data data = new Data(10);
        data.setNum(data.getNum()+10);

        // 线程1
        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.setNum(data.getNum()-100);
            System.out.println("线程1: " + data.getNum());
        }).start();
        // 线程2
        new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.setNum(data.getNum()+100);
            System.out.println("线程2: " + data.getNum());
        }).start();
        // 线程3
        new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.setNum(data.getNum()-100);
            System.out.println("线程3: " + data.getNum());
        }).start();

/*        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        System.out.println(data.getNum());
        data.setNum(data.getNum()+10);
        System.out.println(data.getNum());

    }
}



class Data{
    int num;

    public Data(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}