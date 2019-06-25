package com.github.java11;

/*
空对象:
    通过属性返回一个空的单例对象,需要实例时则可直接创建
 */

public class job16 {
    public static void main(String[] args) {
        System.out.println(Person.Null);
    }
}


interface Null{}


class Person{
    public final String first;
    public final String last;
    public final String address;

    public Person(String first, String last, String address) {
        this.first = first;
        this.last = last;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static class NullPerson extends Person implements Null{

        public NullPerson() {
            super("None", "None", "None");
        }
    }

    public static final Person Null = new NullPerson();
}


