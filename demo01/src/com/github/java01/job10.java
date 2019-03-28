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
        int[] ints = new int[10000000];
        for (int x = 0; x < 10000000; x++) {
            ints[x]=x;
        }
        int num = 78;
        fun1(ints,num);
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
                end = mid;
            }else if(num>ints[mid]){
                start = mid;
            }else{
                System.out.println(mid);
                break;
            }
            count++;
        }
        System.out.println("查找次数: " + count);
        System.out.println(mids);
    }

}
