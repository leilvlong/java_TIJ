package com.github.java01;

import java.util.ArrayList;

/*
二分算法查找计算公式
    (end -start) /2 + start

运行了一下 查看了mid
[4999999, 2499999, 1249999, 624999, 312499, 156249, 78124, 39062, 19531,
 9765, 4882, 2441, 1220, 610, 305, 152, 76, 114, 95, 85, 80, 78]

 */
public class job10 {
    public static void main(String[] args) {
        int count  = 0;
        for (int i = 5; i <= 100; i++) {
            count +=i;
        }
        System.out.println(count);
        fun2(5,100);
    }

    public static void fun1 (int[] ints, int num){
        int start = 0;
        int end = ints.length-1;
        int mid;
        int count = 0;
        ArrayList<Integer> mids = new ArrayList<>();
        while(true){
            mid = (end - start)/2 +start;
            mids.add(mid);
            if(num<ints[mid]){
                end = mid-1;
            }else if(num>ints[mid]){
                start = mid+1;
            }else{
                System.out.println(mid);
                break;
            }
            count++;
        }
        System.out.println("查找次数: " + count);
        System.out.println(mids);
    }


    public static void fun2(int a, int b){
        // 求任意两个正整数之间的所有数之和
        if( (b+1-a) % 2 ==0){
            System.out.println( (a+b) * (b+1 -a) / 2);
        }else{
            System.out.println( (a+b) * ((b - a) / 2) + (a + (b - a) / 2));
        }
    }
}
