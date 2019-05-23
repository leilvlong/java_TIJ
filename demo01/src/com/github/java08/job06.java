package com.github.java08;

import java.util.Arrays;
import java.util.Random;

public class job06 {
    public static void main(String[] args) {
        fun2(78);
    }

    /**
     * 冒泡排序
     */
    public static void fun1(){
        Integer[] nums = nums();
        for (int i = 0; i < nums.length; i++) {
            for (int y = 0; y < i; y++) {
                if(nums[i]<nums[y]){
                    Integer temp = nums[i];
                    nums[i] = nums[y];
                    nums[y] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
    }


    /**
     * 二分算法
     */
    public static void fun2(Integer get){
        Integer[] nums = nums(6);
        Integer start = 0;
        Integer end = nums.length - 1;
        Integer midd;
        System.out.println(Arrays.toString(nums));
        while (true){
            midd = (end-start)/2 +start;
            System.out.println("midd: "+ midd);
            if (nums[midd]>get){
                end = midd-1;
            }else if(nums[midd]<get){
                start = midd+1;
            }else {
                System.out.println(midd);
                break;
            }
        }



    }

    public static Integer[] nums(Integer skip){
        Integer[] nums = new Integer[100];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = 0 == i ? skip:nums[i-1]+skip;
        }
        return nums;
    }


    /**
     *
     * @return 100内随机数的不重复的数组
     */
    public static Integer[] nums(){
        Integer[] nums = new Integer[100];
        Random random = new Random(47);
        for (int i = 0; i < nums.length; i++) {
            Integer y = random.nextInt(101);
            if (util(nums,y)) {
                i--;
                continue;
            }
            nums[i] = y;
        }
        return nums;
    }

    /**
     *
     * @param integers
     * @param element
     * @return  数组是否包含某个元素
     */
    public static boolean util(Integer[] integers,Integer element ){
        try{
            for (Integer integer : integers) {
                if (integer.equals(element)){
                    return true;
                }
            }
        }catch (Exception e){
        }

        return false;
    }

}
