package com.github.java12;


import java.util.Iterator;
import java.util.LinkedList;

public class SingleTrackLinkedListReversal {

    public static void main(String[] args) {

        SingleTrackLinkedList<Integer> list = new SingleTrackLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);


        for (Integer integer : list) {
            System.out.println(integer);
        }

        list.reversalByLogic();

        System.out.println(list.get(0));
    }
}

class SingleTrackLinkedList<T> implements Iterable<T>{

    private Node<T> first;
    private Node<T> last;
    private int size;

    public void add(T data){
        Node<T> l = last;
        Node<T> newNode = new Node<T>(data,null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;

    }

    public void reversalByRecursion(){

        Node node = reversalRecursion(first);
        last = first;
        first = node;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

    // 写递归最重要的是要明白,指向的对象和出栈的对象
    private Node reversalRecursion(Node node){
        if (node == null || node.getNext() == null){
            return node;
        }
        Node prev = reversalRecursion(node.getNext());
        node.getNext().setNext(node);
        node.setNext(null);
        return prev;
    }



    public void reversalByLogic(){
        Node pre = first;
        Node next = first.next;
        Node tmp;

        while (next != null){
            tmp = next.next;
            next.setNext(pre);
            pre = next;
            next = tmp;
        }
        last = first;
        first = pre;

        Iterator<T> iterator = iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorClass();
    }


    private class Node<T>{
        T data;
        Node next;

        Node(T data, Node next){
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }


    private class IteratorClass implements Iterator<T>{

        private int nextIndex;

        private Node next;

        @Override
        public boolean hasNext() {

            return nextIndex++ < size;
        }

        @Override
        public T next() {
            if (nextIndex == 1){
                next = first.next;
                return (T)first.data;
            }
            Node oldNext = next;
            next = next.next;
            return (T) oldNext.data;
        }
    }

    public T getFirst(){
        return first.data;
    }

    public T getLast(){
        return last.data;
    }

    public T get(int index){
        Node pre = first;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        return (T)pre.data;
    }
}
