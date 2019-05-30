package com.github.job09;

/*
异常匹配:
    1.每个catch只能捕获一个异常,剩余没有被捕获的异常只能由下一个离他最近的catch捕获
        (catch又出现异常或者finally导致的异常信息丢失又被重新捕获)
    2.每一个异常都由离他最近的catch捕获
    3.外围try会捕获内围try的异常,内类try无法捕获外围try的异常
        因为内围try在外围try的作用域下,而外围try不在内围try的作用域中
    4.继承也可以作用域异常匹配
    5.基类会屏蔽导出类异常

以下案例可以揭示特种特性(运用了异常限制):
    可以任意组合来捕获抛出的异常
    但值得注意的是异常继承中爸爸也不认识儿子,除非儿子带着信物找上门来(extends 关键字)
 */
public class job16 {
    public static void main(String[] args) {
        fun2();
    }

    /**
     * 基类屏蔽导出类异常导致的编译错误
     */
    /*public static void fun1(){
        try{
            throw new Sneeze();
        }
        catch (Annoyance e){
            System.out.println(e);
        }
        catch (Sneeze e){
            System.out.println(e);
        }
    }*/

    public static void fun2(){
        Base bs = new Sub();
        try{
            bs.fun();
        }catch (Annoyance e){
            System.out.println(e);
        }
    }

}


class Annoyance extends Exception{
    @Override
    public String toString() {
        return "Annoyance Exception";
    }
}


class Sneeze extends Annoyance{
    @Override
    public String toString() {
        return " Sneeze Exception Extends Annoyance";
    }
}


class Snotty extends Sneeze{
    @Override
    public String toString() {
        return " Snotty Exception Extends Sneeze Extends Annoyance";
    }
}


class Base{
    public void fun() throws Annoyance{
        throw new Annoyance();
    }
}


class Father extends Base{

    @Override
    public void fun() throws Sneeze{
        throw new Sneeze();
    }
}


class Sub extends Father{
    @Override
    public void fun() throws Snotty{
        throw new Snotty();
    }
}






