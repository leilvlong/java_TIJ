package com.github.java12;

import java.util.*;

class TestPage{

    static int size = 6; //相当于总页数

    static int[] nums = new int[size];  //相当于持有翻页的数据容器
    static {
        for (int i = 1; i <= size; i++) {
            nums[i-1] = i;
        }
    }

    static int thisPage = 5; //相当于每页显示的页码

    public static void fun(int pageNum){
        int forPage = pageNum-3; //计算左浮动页
        int toPage = pageNum+2;  //计算右浮动页

        if (size<=thisPage){    //如果总页数小于总页码
            forPage = 0;
            toPage = size;
        }else{
            if (pageNum -3<0){      //此时总页数大于总页码
                forPage = 0;        //前三页不浮动
                toPage = thisPage;  //此时可以显示每页总页码值的页数
            }
            if (pageNum+2>size){
                toPage = size;
                forPage = toPage-thisPage;
            }
        }

        //根据计算的浮动索引取数据
        int[] ints = Arrays.copyOfRange(nums, forPage , toPage );
        System.out.println(Arrays.toString(ints));
    }

    public static void main(String[] args) {
        fun(4);
    }
}