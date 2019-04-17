package com.github.java05;
/*
运用组合与多态:
    在运行时将对象引用的指向发生改变,使其在运行期间获得了动态灵活性（其行为也会发生变化）
继承的缺点：
    在不正确使用这项技术时会带来繁杂的业务场景（也是因为使用起来太过方便而造成的不便）
    打个不正确的比方，车可以让我们快速到达某个地点，但随着有车的人越来越多，也就造就了堵车这个词
    继承的通用准则：
        用继承来表达行为间的差异，并用字段表达状态上的变化
以下案例将展示以上所述

 */
public class job12 {
    public static void main(String[] args) {
        Actortion actortion = new Actortion();
        actortion.Hapfun();
        actortion.updateActor();
        actortion.Hapfun();
    }
}

class Actortion{
    private Actor  actor= new HapActor();

    public void Hapfun(){
        actor.act();
    }

    public void updateActor(){
        actor = new EnActor();
    }


}





class Actor{
    public void act(){

    }
}

class HapActor extends Actor{

    @Override
    public void act(){
        System.out.println("HapActor act");
    }
}

class EnActor extends Actor{
    @Override
    public void act(){
        System.out.println("EnActor act");
    }
}