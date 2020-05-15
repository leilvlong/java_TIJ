package com.github.java12;


import java.util.Iterator;
import java.util.LinkedList;

public class SingleTrackLinkedListReversal {

    public static void main(String[] args) {
        new LinkedList<>();
        SingleTrackLinkedList<Integer> list = new SingleTrackLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        list.reversalByRecursion();
        for (Object o : list) {
            System.out.println(o);
        }
        System.out.println(list.last.data);

    }
}

class SingleTrackLinkedList<T> implements Iterable{
     private static  int count = 0;
     private Node<T> first;

     Node<T> last;

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

    // 写递归最重要的是要明白,指向的对象是同一对象
    public Node reversalRecursion(Node node){
        count++;
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
        Node cur = pre.getNext();
        Node tmp;
        while (cur != null) {
            tmp = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = tmp;
        }
        first.setNext(null);
        last = first;
        first = pre;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


    class Node<T>{
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


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
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
        };
    }
}
