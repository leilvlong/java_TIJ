package com.github.java10;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class job10 {

    public static List<Integer> list = new ArrayList<>();
    public static Integer count = 0;
    public static int forCount = 0;

    public static void main(String[] args)  {
        new job10().fun();
    }

    public void fun(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count<=100000){
                    synchronized (job10.class){
                        if(!list.isEmpty()){
                            try {
                                job10.class.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        for (int i = 0; i < 10; i++) {
                            list.add(count++);
                        }
                        job10.class.notify();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count<=100000){
                    synchronized (job10.class){
                        if(list.isEmpty()){
                            try {
                                job10.class.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        Integer remove = list.remove(0);
                        System.out.println("forCount:" + forCount);
                        System.out.println("remove:" + remove);
                        System.out.println(forCount==remove);
                        forCount++;

                        if (list.isEmpty()){
                            job10.class.notify();
                        }
                    }
                }
            }
        }).start();


    }



}
