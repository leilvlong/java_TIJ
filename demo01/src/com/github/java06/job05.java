package com.github.java06;

/*
当将其所有的fun方法设置为无参后
尽管通过idea工具指向的都是各自的接口
但是在同一个类中却因重写方法同名却无法方法重载而给出编译异常.
 */
public class job05 {
}



interface One1{
    String fun();
}


interface Two1{
    Integer fun();
}


abstract class Three1{
    abstract void fun();
}


/*
class Count1 extends Three1 implements One1, Two1{

    @Override
    public String fun() {
        return null;
    }

    @Override
    public Integer fun(){
        return null;
    }

    @Override
    public void fun(){

    }

}*/
