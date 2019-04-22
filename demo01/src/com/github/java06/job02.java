package com.github.java06;
/*
策略设计模式之二:
    Filter 与 Processor 具有相同的行为 process
    但Filter 并非继承自 processor 因此不能使用Apply的process方法
    如果将processor类设计为一个接口 让Filter实现它
    或者是其它的方式

class 更像是一套数据类型规范
而接口则更像是一套行为模式规范
    接收参数时可以决定:
        接收怎样的数据类型
        接收怎样的行为模式

 */

public class job02{
    public static void main(String[] args) {
        //Waveform wf = new Waveform();

    }
}


class Waveform{
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return "Waveform" +
                "id=" + id
                ;
    }
}


class Filter{
    public String name(){
        return getClass().getSimpleName();
    }

    public Waveform process(Waveform input){
        return input;
    }
}


class Lowpass extends Filter{
    @Override
    public Waveform process(Waveform input){
        return input;
    }
}


class Highpass extends Filter{
    @Override
    public Waveform process(Waveform input){
        return input;
    }
}