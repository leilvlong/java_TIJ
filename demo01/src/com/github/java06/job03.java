package com.github.java06;

import java.lang.reflect.Array;
import java.util.Arrays;
/*
完全解耦带来的复用性:
    代理与适配器模式的使用

Filter尽管没有实现 Processor接口 ,通过适配器与代理依然使用了Apply.process方法
class 更像是一套数据类型规范(自定义数据类型)  is-a
而接口则更像是一套行为模式规范(多个对象都具有类似的行为)  is-like-a
 */
public class job03{
    public static void main(String[] args) {
        Waveform wf = new Waveform();
        Apply.process(new FilterAdapter(new Filter()),wf);
        Apply.process(new FilterAdapter(new Lowpass()),wf);
        Apply.process(new FilterAdapter(new Highpass()),wf);
    }
}


class FilterAdapter implements Processor{
    Filter filter;

    public FilterAdapter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public String name() {
        return filter.name();
    }

    @Override
    public Waveform process(Object input) {
        return filter.process((Waveform) input);
    }
}