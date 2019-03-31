package com.github.java02;
/*
将类的引用变量作为成员变量放入另一个类中称之为组合关系:
    例;  class A{
         }
         class B{
         A a = new A();
         }

当某一类事(物)扩展新的属性而又不必改变原来的属性时,抽象并继承是不错的选择

在继承与组合之间的中庸之道被称之为代理,当一类事物并非另一类事物时却又需要他的方法时
(属性不同但行为类似 例如汽车和宇宙飞船 都可以左转 右转 前进 停止前进等)
 */
public class job14 {
    public static void main(String[] args) {
        // 通过对象sp.forward方法传给 SpaceShipControls的foeward方法
        SpcaeShipDelegation sp = new SpcaeShipDelegation("car");
        sp.forward(100);
    }
}

class SpaceShipControls{
    void up(int Velocity){}
    void down(int Velocity){}
    void left(int Velocity){}
    void right(int Velocity){}
    void forward(int Velocity){}
    void back(int Velocity){}
    void turboBoost(){}
}


class SpaceShip extends SpaceShipControls{
    private String name;

    public SpaceShip(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SpaceShip{" +
                "name='" + name + '\'' +
                '}';
    }
}


// 代理类
class SpcaeShipDelegation{
    private String name;
    private SpaceShipControls controls = new SpaceShipControls();

    public SpcaeShipDelegation(String name) {
        this.name = name;
    }


    // 代理方法


    public void up(int Velocity) {
        controls.up(Velocity);
    }

    public void down(int Velocity) {
        controls.down(Velocity);
    }

    public void left(int Velocity) {
        controls.left(Velocity);
    }

    public void right(int Velocity) {
        controls.right(Velocity);
    }

    public void forward(int Velocity) {
        controls.forward(Velocity);
    }

    public void back(int Velocity) {
        controls.back(Velocity);
    }

    public void turboBoost() {
        controls.turboBoost();
    }
}