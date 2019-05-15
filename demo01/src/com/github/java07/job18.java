package com.github.java07;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/*
应用程序框架:
    应用程序框架是被设计用以解决某类特定问题的一个类或一组类
    要运用某个应用程序框架,通常是继承一个或多个类,并覆写某些方法
    在覆写的方法中,编写代码制定应用程序框架提供的通用解决方案 以用来解决某些特定问题
    举个例子:
        数据库连接池、线程连接池等池子提供的性能优化解决方案:
            1.容器放入已创建连接的服务或对象
            2.获取连接从池子中获取
            3.关闭连接将对象或服务归还到池子中供下次使用
            4.这必定设计到这些对象或服务的某些方法的覆写或增强 运用一些设计模式(如动态代理,装饰者模式)来解决此类问题

设计模式的本质:
    将变化的事物与保持不变的事物分离开
    以上举例中池子中的服务与对象是不变的,变化的是他们的行为
    这种变化通过覆写或增强方法来实现

控制框架:
    一套特殊的应用程序框架,用来解决响应事件的需求,程序主要用来响应事件的系统被称为事件驱动系统
    在控制框架中,当事件准备就绪时就执行该事件
    如何准备就绪可以指任何事,用户输入时,用户按下按键时,基于时间等
    对于要控制什么,框架并不包含具体的信息,这些实现过程是通过继承由你决定 何时 以什么方式触发时间

事件驱动:
    以下是一个以时间判断是否可执行事件的控制框架


 */


public class job18{
    public static void main(String[] args) {
        GreenHouseController.main(new String[]{"2000"});
    }
}



 class GreenHouseController {
     /**
      * 一个可执行的任务初始化静态方法
      */
    public static void main(String[] args) {
        GreenhouseControls gc = new GreenhouseControls();
        gc.addEvent(gc.new Bell(900));
        Event[] eventList = {
            gc.new ThermostatNight(0),
            gc.new LightOn(200),
            gc.new LightOff(400),
            gc.new WaterOn(600),
            gc.new WaterOff(800),
            gc.new ThermostatDay(1400)
        };

        gc.addEvent(gc.new Restart(2000,eventList));

        if (args.length==1){
            gc.addEvent(new GreenhouseControls.Terminate(Integer.parseInt(args[0])));
        }
        gc.run();
    }
}




/**
 * eventTime 可执行时间
 * delayTime 等待时间
 * start() 设置可执行时间 规则  当前时间+等待时间
 * 构造器 设置等待时间并调用start
 * ready() 是否可执行
 * action() 抽象方法 执行任务触发条件
 */
abstract class Event{
    private long eventTime;
    protected final long delayTime;

    public Event(long delayTime) {
        this.delayTime = delayTime;
        start();
    }

    public void start(){
        eventTime = System.nanoTime() + delayTime;
    }

    public boolean ready(){
        return System.nanoTime() >=eventTime;
    }

    public abstract void action();
}

/**
 * eventList 执行任务队列
 * addEvent(Event c) 向任务队列添加任务
 * run() 运行任务队列中的任务
 */
class Controller{
    private List<Event> eventList = new ArrayList<Event>();
    public void addEvent(Event c){
        eventList.add(c);
    }

    public void run(){
        while (eventList.size()>0){
            for(Event e: new ArrayList<Event>(eventList)){
                if(e.ready()){
                    System.out.println(e);
                    e.action();
                    eventList.remove(e);
                }
            }
        }
    }
}


class GreenhouseControls extends Controller{
    private boolean light = false;

    /**
     * 构造器 super()
     * action 设置light为true
     * toString() 输出该任务信息
     */
    public class LightOn extends Event{

        public LightOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            light = true;
        }

        @Override
        public String toString() {
            return "Light is on";
        }
    }

    /**
     * 构造器 super()
     * action() 设置light为false
     * toString() 输出该任务信息
     */
    public class LightOff extends Event{

        public LightOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            light = false;
        }

        @Override
        public String toString() {
            return "Light is off";
        }
    }


    private boolean water = false;
    /**
     * 构造器 super()
     * action() 设置water为true
     * toString() 输出该任务信息
     */
    public class WaterOn extends Event{

        public WaterOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            water = true;
        }

        @Override
        public String toString() {
            return "Water is on";
        }
    }

    /**
     *构造器 super()
     * action() 设置water为false
     * toString() 输出该任务信息
     */
    public class WaterOff extends Event{

        public WaterOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            water = false;
        }

        @Override
        public String toString() {
            return "Water is Off";
        }
    }

    private String thermostat = "Day";

    /**
     *构造器 super()
     * action() 设置thermostat 为night
     * toString() 输出该任务信息
     */
    public class ThermostatNight extends Event{

        public ThermostatNight(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            thermostat = "Night";
        }

        @Override
        public String toString() {
            return "ThermostatNight on Night setting";
        }
    }

    /**
     * 构造器 super()
     * action() 设置thermostat为Day
     * toString() 输出该任务信息
     */
    public class ThermostatDay extends Event{

        public ThermostatDay(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            thermostat = "Day";
        }

        @Override
        public String toString() {
            return "ThermostatDay on Day setting";
        }
    }

    /**
     * 构造器 super()
     * action() new this 并 addEvent
     * toString() 输出该任务信息
     */
    public class Bell extends Event{

        public Bell(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            addEvent(new Bell(delayTime));
        }

        @Override
        public String toString() {
            return "Bing";
        }
    }

    /**
     * eventList 任务队列
     * 构造器 super() 设置任务队列 将任务队列中的任务添加到任务列表
     * action() 遍历任务队列中的任务,设置执行时间,添加到任务列表 设置自身执行时间,添加到任务列表
     * toString() 输出该任务信息
     */
    public class Restart extends Event{
        private Event[] eventList;

        public Restart(long delayTime, Event[] eventList) {
            super(delayTime);
            this.eventList = eventList;
            for (Event event : eventList) {
                addEvent(event);
            }
        }

        @Override
        public void action() {
            for (Event event : eventList) {
                event.start();
                addEvent(event);
            }
            start();
            addEvent(this);
        }

        @Override
        public String toString() {
            return "Restarting System";
        }
    }

    /**
     * 构造器 super()
     * action() 终止java虚拟机,结束所有任务
     * toString() 输出该任务信息
     */
    public static class Terminate extends Event{

        public Terminate(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            System.exit(0);
        }

        @Override
        public String toString() {
            return "Terminating";
        }
    }
}



