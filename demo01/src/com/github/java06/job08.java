package com.github.java06;
/*
java9以后对接口的支持可能带来的问题:
    java9以后对接口的支持大大加强,但这种支持所带来的如何示例 我写完之后甚至不想再看一眼
    对接口的支持的进步鼓励了更多的使用接口,若使用不当也更容易带来屎一样的代码(整体观感上)
    使用接口的核心原则:
         需要并为了能够向上转型为多个基类型
 */

/*
java8 并不全部支持 9以后这些代码是完全可行的
并且在接口中提供了常量
 */
public class job08 {
    public static void main(String[] args) {
/*        Realize1 realize1 = new Realize1();
        realize1.baseFun();
        realize1.abstractFun();
        realize1.defaultFun();
        realize1.fun2();
        // 来自三个不同的接口 参数不同而已
        realize1.defaultFun1("1");
        realize1.defaultFun1(1);
        realize1.defaultFun1(1.0);
        realize1.fun();
        // 调用接口静态方法
        Port1.staticFun();
        Port1.staticFun1("1");

        System.out.println("新的子类 新的接口实现________");
        Realize2 realize2 = new Realize2();
        // 上面能用的下面都能用
        realize2.baseFun();
        realize2.abstractFun();
        realize2.defaultFun();
        realize2.fun2();
        // 来自三个不同的接口 参数不同而已
        realize2.defaultFun1("1");
        realize2.defaultFun1(1);
        realize2.defaultFun1(1.0);
        realize2.fun();*/
    }
}




/*
*//**
 * 接口1
 *//*
interface Port1{
    // 接口的常量 默认有final修饰
    String name="zhang";

    // 接口的抽象方法 默认有 public abstract修饰
    void abstractFun();

    //接口的无参默认方法 默认有 public修饰
    default void defaultFun(){
        System.out.println("与接口23同名");
    }
    // 接口的有参默认方法 默认有 public 修饰
    default int defaultFun1(String num){
        System.out.println("接口1的字符串参数 默认方法: "+ num  + "  ");
        return Integer.parseInt(num);
    }
    //接口的无参静态方法 默认有 public修饰
    static void staticFun(){
        System.out.println("接口1的无参静态方法");
    }
    //接口的有参静态方法 默认有 public修饰
    static void staticFun1(String num){
        System.out.println("接口1的有参静态方法: " +num );
    }
    // 接口的私有静态方法
    private static void privateStaticFun(){
        System.out.println("接口1的私有静态方法");
    }

    // 接口的普通私有方法
    private void privateDefaultFun1(){
        System.out.println("接口1的普通私有方法");
    }
}

*//**
 * 接口2
 *//*
interface Port2{
    void abstractFun();

    default void defaultFun(){
        System.out.println("与接口13同名");
    }

    default int defaultFun1(int num){
        System.out.println("接口2的int参数 默认方法: "+ num );
        return num;
    }

    static void staticFun(){
        System.out.println("接口2的无参静态方法");
    }
    static void staticFun(String num){
        System.out.println("接口2的有参静态方法: " +num );
    }

    void fun2();
}

*//**
 * 接口3
 *//*
interface Port3{
    void abstractFun();

    default void defaultFun(){
        System.out.println("与接口12同名");
    }

    default double defaultFun1(double num){
        System.out.println("接口3的double参数 默认方法: "+ num );
        return num;
    }

    static void staticFun(){
        System.out.println("接口3的无参静态方法");
    }
    static void staticFun(String num){
        System.out.println("接口3的有参静态方法: " +num );
    }
    void fun2();

}

*//**
 * 继承接口123
 *//*
interface Ptot4 extends Port1,Port2,Port3{
    @Override
    default void defaultFun() {

    }
}

*//**
 * 基类
 *//*
abstract class BaseClass{
    private String name;
    int age;
    abstract void baseFun();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

*//**
 * 子类 继承基类
 * 实现接口123
 *//*
class Realize1 extends BaseClass implements Port1, Port2, Port3{
    static String name;

    @Override
    public void abstractFun() {
        System.out.println("我必须重写接口的抽象方法");
    }

    @Override
    public void fun2() {
        System.out.println("接口2的新抽象方法");
    }

    @Override
    public void defaultFun() {
        System.out.print("接口默认方法同名 必须重写 ");
        System.out.println("我是用了接口1的常量: "+ Port1.name);
    }

    @Override
    void baseFun() {
        System.out.println("我必须重写基类的抽象方法");
    }

    public void fun(){
        System.out.println(Port1.name);
    }
}

*//*
子类 继承BaseClass
实现 Prot4
 *//*
class Realize2 extends BaseClass implements Ptot4{

    @Override
    void baseFun() {
        System.out.println("我必须重写父类的抽象方法");
    }

    @Override
    public void abstractFun() {
        System.out.println("实现接口4 ,接口4抽象方法继承接口1(23)");
    }
    @Override
    public void fun2(){
        System.out.println("实现接口2的抽象方法");
    }

    public void fun(){
        System.out.println(name);
    }
}*/
