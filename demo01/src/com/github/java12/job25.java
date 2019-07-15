package com.github.java12;


import java.util.Date;


/**
 * java 并不允许多重继承,要实现类型混合可以使用接口
 */
interface TimeStamped{
    long getStamp();
}

class TimeStampedImp implements TimeStamped{

    private final long timeStamp;

    public TimeStampedImp() {
        this.timeStamp = new Date().getTime();
    }

    @Override
    public long getStamp() {
        return timeStamp;
    }
}


interface SerialNumbered{
    long getSerialNumber();
}


class SerialNumberedImp implements SerialNumbered{

    private static long counter = 1;
    private final long serialNumber = counter++;

    @Override
    public long getSerialNumber() {
        return serialNumber;
    }
}


interface Basic{
    void set(String val);

    String get();
}


class BasicImp implements Basic{

    private String value;

    @Override
    public void set(String val) {
        value = val;
    }

    @Override
    public String get() {
        return value;
    }
}


class Mixin extends BasicImp implements TimeStamped, SerialNumbered{

    private TimeStamped timeStamped= new TimeStampedImp();
    private SerialNumberedImp serialNumberedImp= new SerialNumberedImp();

    @Override
    public long getStamp() {
        return timeStamped.getStamp();
    }

    @Override
    public long getSerialNumber() {
        return serialNumberedImp.getSerialNumber();
    }
}


class Mixins{
    public static void main(String[] args) {
        Mixin
                mixin1 = new Mixin(),
                mixin2 = new Mixin();
        System.out.println("mixin1: " + mixin1.getStamp() + ": " + mixin1.getSerialNumber());
        System.out.println("mixin2: " + mixin2.getStamp() + ": " + mixin2.getSerialNumber());
    }
}