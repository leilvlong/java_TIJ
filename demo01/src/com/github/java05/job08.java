package com.github.java05;
/*
引用计数的实现原理与引用计数清理:
    对该对象的每一个引用计数
    每当该对象被引用调用其计数方法计数一次,只有当计数为0才允许dispose

其中:
    private static long count = 0;
    private final long id = count++
    记录该对象被创建次数

    private int refcount = 0; 与
    addref()
    记录该对象引用次数

    dispose()
    则确保该对象计数归0则才能清除该对象(确保不会因为有其他地方依赖该对象而该对象被删除导致异常)
 */
public class job08 {
    public static void main(String[] args) {
        Shared shared = new Shared();
        Composing[] composings = {
          new Composing(shared),
          new Composing(shared),
          new Composing(shared),
          new Composing(shared),
          new Composing(shared),
        };
        for (Composing composing : composings) {
            composing.dispose();
        }
    }
}

class Shared{
    private int refcount = 0;
    private static long count = 0;
    private final long id = count++;

    public Shared() {
        System.out.println("Creating: " + this);
    }

    public void addref(){
        refcount++;
    }

    protected void dispose(){
        if(--refcount==0) {
            System.out.println("disposing: " + this);
        }
    }

    @Override
    public String toString() {
        return "Shared" + id;
    }
}

class Composing{
    private Shared shared;
    private static long count= 0;
    private final long id=count++;

    public Composing(Shared shared) {
        this.shared = shared;
        System.out.println("Creating: " + this);
        this.shared.addref();
    }

    protected void dispose(){
        System.out.println("disposing; " + this);
        this.shared.dispose();
    }

    @Override
    public String toString() {
        return "Composing id: "+ id;
    }
}