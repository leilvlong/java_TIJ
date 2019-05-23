package com.github.java08;

/*
正常队列：
    1，出来的元素应是等待时长最长的那个元素
算法队列：
    根据需求出来的队列，常见的以字典排序的字符串与Integer

 */

import java.util.*;

public class job01 {

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Random rand = new Random(47);
        for (int i = 0; i < 10; i++) {
            pq.offer(rand.nextInt(i+10));
        }
        printQ(pq);
        /*Queue: 8
        Queue: 1
        Queue: 1
        Queue: 1
        Queue: 5
        Queue: 14
        Queue: 3
        Queue: 1
        Queue: 0
        Queue: 1*/



    }


    public static void QueueTest(){
        Queue<Integer> queue = new LinkedList<>();
        Random rand = new Random(47);

        for (int i = 0; i < 10; i++) {
            queue.offer(rand.nextInt(i+10));
        }
        printQ(queue);
    }

    public static void printQ(Queue queue){
        while (queue.peek()!= null){
            System.out.println("Queue: "+ queue.remove());
        }
    }



}

