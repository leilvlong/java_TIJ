package com.github.job09;

/*
异常限制:
    当覆盖方法时,只能抛出在基类方法的异常说明里列出的异常
    这意味着,当基类使用的代码应用到其派生类对象的时候,一样能够工作,异常也不例外

案例解析:
    1.在Inning类中,构造器和event方法都声明将抛出异常,但实际没有抛出
    这种方式强制在使用对象的该方法(覆盖后的该方法)时必须捕获异常,这种方式对于抽象方法atBat也适用
    2.接口Storm包含了一个在Inning中定义的方法event和一个自身独有的方法rainHard
    这两个方法都声明抛出异常RainedOut,StormyInning继承Inning又实现Storm接口,
    它不能改变在Inning中的event方法中的异常,否则在使用基类引用时就不能判断是否捕获了正确的异常
    覆写又是另外一个说法了,而在不是来自基类中的方法时则不会有此限制
    3.构造器不会有继承方法上的异常限制,但是导出类的构造器上的异常说明必须包含基类构造器的异常说明
    因为基类的构造器在导出类中被隐式地自动调用,导出类构造器不能捕获基类构造器抛出的异常
    因为导出类对象的建立前提是基类被成功初始化,基类若不能成功初始化,导出类也无法创建对象(关于继承可以回顾java05中的案例)
    4.StormyInning.walk中的问题与分析2同理,在涉及到多态的向上转型时,基类的方法并不具备此异常,
    而导出类却具备,这有可能导致异常机制运行失灵(这种编译不能通过的也没办法尝试与证实,但肯定会有冲突才不会允许这种机制存在)
    通过强制导出类遵守基类的方法异常声明,对象引用的可替换性得到了保证
    5.覆盖后的event方法可以没有声明异常,即使基类有异常声明,因为这并不会影响导出类对象的使用
    但是反过来导出类方法增加异常则会影响基类对象引用的使用,见分析2 4
    6.异常即便是继承也会被正确的捕获,在main方法中编译器在处理不同的对象引用时,只会强制要求捕获这个类所抛出的异常

    总结:
        在继承过程中,编译器会对异常说明做强制要求,但异常说明并不属于方法的一部分,
        方法是由方法的名字与参数组成的,因此不能基于异常说明来重载方法,此外一个出
        现在基类中的异常不一定会出现在导出类中,这与继承相反,在继承中,基类的方法
        一定会出现在导出类中(不管是隐式的还是显示的),而异常的方法声明为了确保对象
        引用的可替代性,只会少,不能多

 */

public class job13 {
    public static void main(String[] args) {

        try {
            StormInning si = new StormInning();
            si.atBat();
        }
        catch (PopFoul e){
            System.out.println("PopFoul extends Foul extends BaseBallException");
        }
        catch (RainedOut e) {
            System.out.println("RainedOut extends StormException extends Exception");
        }
        catch (BaseBallException e) {
            System.out.println("BaseBallException");
        }

        try{
            Inning i = new StormInning();
            i.atBat();
        }catch (Strike e){
            System.out.println("Strike extends BaseBallException ");
        }catch (Foul e){
            System.out.println("Foul extends BaseBallException");
        }catch (RainedOut e){
            System.out.println("ReainedOut extends StormException");
        }catch (BaseBallException e){
            System.out.println("StormException");
        }

        try {
            Inning i = new StormInning();
            i.atBat();
        } catch (RainedOut rainedOut) {
            System.out.println("ReainedOut extends StormException");
        } catch (BaseBallException e) {
            System.out.println("BaseBallException");
        }
    }
}


class BaseBallException extends Exception{
}


class Foul extends BaseBallException{
}


class Strike extends BaseBallException{
}


abstract class Inning{
    public Inning() throws BaseBallException{
    }

    public void event() throws BaseBallException{
    }

    public abstract void atBat() throws Foul,Strike;

    public void walk(){

    }

}


class StormException extends Exception{
}


class RainedOut extends StormException{
}


class PopFoul extends Foul{
}


interface Strom{
    public void event() throws RainedOut;

    public void rainHard() throws RainedOut;
}


class StormInning extends Inning implements Strom{

    public StormInning() throws RainedOut,BaseBallException {

    }

    public StormInning(String str) throws Foul,BaseBallException{

    }

    @Override
    public void atBat() throws PopFoul {
        throw new PopFoul();
    }

    @Override
    public void event(){
    }

    @Override
    public void rainHard() throws RainedOut {

    }

    // 因为异常限制的原因不能编写的方法


    /*
    @Override
    public void walk() throws PopFoul {
    }*/

    /*
    @Override
    public void event() throws RainedOut{
    }*/


}

