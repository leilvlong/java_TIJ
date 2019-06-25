package com.github.java11;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*

通过代理模式返回空对象:
    通过代理模式返回空对象,它会返回你所创建类型的确切名字

 */

public class job18 {
    public static void main(String[] args) {
        Robot[] robots = {new SnowRemoveRobot("SnowBee"),
          NullRobot.newNullRobot(SnowRemoveRobot.class)
        };

        for (Robot r : robots) {
            System.out.println(r.getClass().getSimpleName());
            Robot.Test.test(r);
        }
    }
}


interface Operation{
    String description();
    void command();
}


interface Robot{
    String name();
    String model();
    List<Operation> operations();

    class Test{
        public static void test(Robot r){
            if (r instanceof Null){
                System.out.println("Null Robot");
            }
            System.out.println("Robot Name: " + r.name());
            System.out.println("Robot Model: " + r.model());
            for (Operation operation : r.operations()) {
                operation.command();
            }
        }
    }
}


class SnowRemoveRobot implements Robot{
    private String name;

    public SnowRemoveRobot(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String model() {
        return "SnowBot Series 11";
    }

    @Override
    public List<Operation> operations() {
        return Arrays.asList(
                new Operation() {
                    @Override
                    public String description() {
                        return name+" can shovel snow";
                    }

                    @Override
                    public void command() {
                        System.out.println(name + " shoveling snow");
                    }
                },
                new Operation() {

                    @Override
                    public String description() {
                        return name + " can chip ice";
                    }

                    @Override
                    public void command() {
                        System.out.println(name + "chipping ice");
                    }
                },
                new Operation() {
                    @Override
                    public String description() {
                        return name + " can clear the roof";
                    }

                    @Override
                    public void command() {
                        System.out.println(name + "clearing roof");
                    }
                }
        );
    }
}


class NullRobotProxytHandler implements InvocationHandler{

    private String nullName;
    private Robot proxied = new NRobot();

    public NullRobotProxytHandler(Class<? extends Robot> type) {
        this.nullName = type.getSimpleName() + " NullRobot";
    }

    private class NRobot implements Robot{

        @Override
        public String name() {
            return nullName;
        }

        @Override
        public String model() {
            return nullName;
        }

        @Override
        public List<Operation> operations() {
            return Collections.emptyList();
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxied,args);
    }
}


class NullRobot{
    public static Robot newNullRobot(Class<? extends Robot> type){
        return (Robot) Proxy.newProxyInstance(
                NullRobot.class.getClassLoader(),
                new Class[]{Null.class,Robot.class},
                new NullRobotProxytHandler(type)
        );
    }
}