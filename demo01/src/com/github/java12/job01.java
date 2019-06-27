package com.github.java12;


/*
反射与泛型都是用来简化编程难度的(指逻辑上)
其根本目的是为了解决强类型语言带来的诸多限制
有了反射,每个类都相当于被开了一个后门
有了泛型,则可以让最大限度适配,它的含义跟接近于"某种不具体的类型"
泛型的核心概念,编译期告诉编译器想使用什么类型,运行时就是什么类型
以下案例是一个栈链使用内部类作为链表节点,其中使用了泛型
 */

public class job01 {
    public static void main(String[] args) {
        LinkedStack<String> ls = new LinkedStack<>();
        for (String s : "Phasers on stun".split(" ")) {
            ls.push(s);
        }
        String s;
        while((s = ls.pop()) != null){
            System.out.println(s);
        }
    }
}


class LinkedStack<T>{
    private static class Node<U>{
        U item;
        Node<U> next;
        Node<U> last;

        Node(){
            this.item = null;
            this.next = null;
        }

        public Node(U item, Node<U> next) {
            this.item = item;
            this.next = next;
        }

        public boolean end(){
            return item == null && next == null;
        }


        @Override
        public String toString() {
            return "Node{" +
                    "item:" + item +
                    ", next:" + next +
                    ", last:" + last +
                    '}';
        }
    }

    private Node<T> top = new Node<>();

    public void push(T item){
        top = new Node<T>(item,top);
    }

    public T pop(){
        T result = top.item;
        if (! top.end()){
            top = top.next;
        }
        return result;
    }

    @Override
    public String toString() {
        return "LinkedStack{" +
                "top:" + top +
                '}';
    }
}
