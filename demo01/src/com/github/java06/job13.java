package com.github.java06;

/*
接口与工厂:
    接口是在java中实现多重继承的途径,而生成遵循某个接口的对象 的典型方式就是工厂方法设计模式
    与直接调用构造器不同,在工厂对象上调用创建方法,而该工厂对象将生成接口的某个实现对象
    以下示例中:
        对象接口为Service    通过实现该接口而允许获得一个可以向上转型为Service 的对象
        工厂接口为ServiceFactory  通过实现该工厂接口得到工厂对象 通过调用该工厂对象的创建方法得到一个具体的实现对象
    理论上这种方式将完全与接口的实现分离, 这就使得可以透明的将某个实现替换为另外一个实现

使用工厂模式:
    这种创建对象的方式无疑添加了更多的间接性,使得不太容易理解
 */


public class job13 {
    public static void main(String[] args) {
        serviceConsumer(new Implementation1Factory());  // 1

        serviceConsumer(new Implementation2Factory());  //2
    }

    public static void  serviceConsumer(ServiceFactory factory){
        Service service = factory.getService();
        service.method1();
        service.method2();
    }

}


interface Service{
    void method1();
    void method2();
}


interface ServiceFactory{
    Service getService();
}

// 111111111111111
class Implementation1 implements Service{

    @Override
    public void method1() {
        System.out.println("Class Implementation1 Interface Service methood1 ");
    }

    @Override
    public void method2() {
        System.out.println("Class Implementation1 Interface Service methood2 ");
    }
}


class Implementation1Factory implements ServiceFactory{

    @Override
    public Service getService() {
        return new Implementation1();
    }
}

//222222222222222222
class Implementation2 implements Service{


    @Override
    public void method1() {
        System.out.println("Class Implementation2 Interface Service methood1 ");
    }

    @Override
    public void method2() {
        System.out.println("Class Implementation2 Interface Service methood2 ");
    }
}


class Implementation2Factory implements ServiceFactory{

    @Override
    public Service getService() {
        return new Implementation2();
    }
}



