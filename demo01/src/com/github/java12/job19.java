package com.github.java12;
import java.util.HashMap;


class FixedSizeStack<T>{
    private int index;
    private Object[] storage;

    public FixedSizeStack(int size) {
        storage = new Object[size];
    }

    public void push (Object item){
        storage[index++] = item;
    }

    public T pop(){
        return (T) storage[--index];
    }
}


/**
 * 转型验证ClassCastException只会发生在赋予给不符合实体对象引用时才会出现的异常
 * 即: 没有引用就不会有这类异常
 * 编译期检查能解决百分之95以上这类问题
 * 但还是有办法能骗过编译器的编译期检查
 */
class GenericCast{
    public static void main(String[] args) {
        FixedSizeStack<String> stack = new FixedSizeStack<>(10);

        for (int i : new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}) {
            stack.push(i);
        }

        /*for (int i = 0; i < 10; i++) {
            Object obj = stack.pop();
            System.out.println(obj);
        }*/

        for (int i = 0; i < 10; i++) {
            String pop = stack.pop();
            System.out.println(pop);
        }
    }
}




