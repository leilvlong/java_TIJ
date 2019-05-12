package com.github.java07;

/*
接口内部类(嵌套类):
    内部类可以作为接口的一部分并实现这个这个接口本身,而且这个内部类自动被public static修饰
    可以通过在接口中定义一个成员变量为其返回引用 该成员变量自动被public static final 修饰
 */

public class job12 {
    public static void main(String[] args) {
        ClassInInterface.t.howdy();
    }
}


interface ClassInInterface{
    void howdy();

    public static class Test implements ClassInInterface{
        @Override
        public void howdy() {
            System.out.println("howdy");
        }
    }

    Test t = new Test();
}


//使用嵌套类中的main方法
class Test{
    public void fun(){
        System.out.println("Class Test method fun");
    }

    public static class InnerTest{
        public static void main(String[] args) {
            Test test = new Test();
            test.fun();
        }
    }
}