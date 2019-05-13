package com.github.java07;

/*
工厂方法与匿名内部类:

 */


public class job09 {

    public static void serviceConsumer(ServiceFactory serviceFactory){
        Service service = serviceFactory.getService();
        service.method1();
        service.method2();
    }

    public static void main(String[] args) throws InterruptedException {
        serviceConsumer(Implementation1.serviceFactory);
        serviceConsumer(Implementation2.serviceFactory);

        ServiceFactory il1 = Implementation1.serviceFactory;
        ServiceFactory il2 = Implementation1.serviceFactory;
        System.out.println(il1 == il2);



    }


}


interface Service{
    void method1();
    void method2();
}


interface ServiceFactory{
    Service getService();
}


class Implementation1 implements  Service{

    private Implementation1() {
    }

    public static ServiceFactory serviceFactory =  new ServiceFactory() {
        @Override
        public Service getService() {
            return new Implementation1();
        }
    };

    public static ServiceFactory getServiceFactory = null;

    @Override
    public void method1() {
        System.out.println("Class Implementation1 method1 Interface Service");
    }

    @Override
    public  void method2() {
        System.out.println("Class Implementation1 method2 Interface Service");
    }

}


class Implementation2 implements Service{
    private Implementation2() {
    }

    public static ServiceFactory serviceFactory = new ServiceFactory() {
        @Override
        public Service getService() {
            return new Implementation2();
        }
    };


    @Override
    public void method1() {
        System.out.println("Class Implementation2 method1 Interface Service");

    }

    @Override
    public void method2() {
        System.out.println("Class Implementation2 method2 Interface Service");
    }
}

