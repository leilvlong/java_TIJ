package com.github.java08;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Random;
/*
数组与迭代器
 */


public class job02 {
    public static Random r = new Random(47);

    public static void main(String[] args) {
        CollectionSequence integers = new CollectionSequence(8);
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}


class CollectionSequence extends AbstractCollection<Integer>{
    private Integer[] nums;

    private Integer size;
    public CollectionSequence(Integer size ) {
        this.size = size;
        this.nums = new Integer[size];
        Random random = new Random(47);
        for (Integer i = 0; i < size; i++) {
            nums[i] = random.nextInt(21)+80;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private Integer index = 0;

            @Override
            public boolean hasNext() {
                return index<size;
            }

            @Override
            public Integer next() {
                return nums[index++];
            }
        };
    }

    @Override
    public int size() {
        return size;
    }
}