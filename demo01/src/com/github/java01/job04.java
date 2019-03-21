package com.github.java01;

import java.util.ArrayList;
import java.util.Random;


/*
        Array04 array04 = new Array04();
        AddArray04 addArray04 = new AddArray04(array04);
        RemoveArray04 remove1 = new RemoveArray04(array04);
        addArray04.start();
        new Thread(remove1).start();
        new Thread(remove1).start();
        new Thread(remove1).start();
        new Thread(remove1).start();
使用以上方式去开启多线程协作时会出现一个巨大的问题:
    信息不对称:
        当容器装满数据给四条线程时,四条线程在执行时获取的容器长度是一致的
        (因为同步代码块的原因 执行里面的代码需要一定的时间虽然肉眼不可察觉
        在执行同步代码块时另外三条线程已经获取了容器的信息)
        当其中一条线程执行并修改了容器后,另外三条线程中的某条进入同步代码块执行
        但是并未收到容器已被修改的消息 对容器的操作就会出现问题而导致抛出异常
        就好像收到一条诡异的来自2017年8月的短信 告诉你2017年双色球号码是多少
        买了必中,然后你信了 去买了 天知道会发生什么
单生产多消费看来我还是想的太简单了 呵! 呵! 呵!
 */
public class job04 {
    public static void main(String[] args) {


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
                System.out.println(array04.intList);
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

                ArrayList<Integer> intList = array04.intList;
                Integer remove = intList.remove(r.nextInt(intList.size()));
                System.out.println(Thread.currentThread().getName()+"删除了元素: "+remove);

                if(intList.isEmpty()){
                    array04.notify();
                }

            }
        }
    }
}


