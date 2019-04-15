package com.github.java05;
/*
一般来讲 java是不需要手动清理的 ,自带的垃圾清理机制已经足够强大 在继承中也是如此
特殊情况:本地方法
模拟一下在继承中 垃圾清理发生的事

构造与清理图:
 关系                       构造行为                     清理行为
  ↑     LivingCreature   extends ↑    构造↓           super↑
  ↑     Animal           extends ↑    构造↓           super↑
  ↑     Amphibian        extends ↑    构造↓           super↑
  ↑     Frog             extends ↑    构造↓           super↑

1. 导出类与其他类有组合关系时,成员对象与导出类销毁应与声明顺序相反，保证不会因依赖关系导致无法正确清理
2. 对于基类,应对导出类先进行清理,然后才是基类,因为导出类可能调用基类的某些方法，导致基类无法正确清理或导出类异常
    就好像某些程序打开多层级子窗口如果不关闭这些子窗口就不允许关闭该程序


 */
public class job07 {
    public static void main(String[] args) {
        System.out.println("__________________构造行为__________________");
        Frog frog = new Frog();
        System.out.println("__________________清理行为__________________");
        frog.dispose();
    }
}

class Characteristic{
    private String str;

    public Characteristic(String str) {
        this.str = str;
        System.out.println("Characteristic 构造: " + str);
    }

    protected void dispose(){
        System.out.println("Dispose Characteristic: " + str);
    }
}

class Description{
    private String str;

    public Description(String str) {
        this.str = str;
        System.out.println("Description 构造: " + str);
    }

    protected void dispose(){
        System.out.println("Dispose Description: " + str);
    }
}

class LivingCreature{
    private Characteristic characteristic = new Characteristic("is alive");
    private Description description = new Description("Basic Living creature");

    public LivingCreature() {
        System.out.println("Creating LivingCreatrue ");
    }

    protected void dispose(){
        System.out.println("LivingCreature Dispose");
        description.dispose();
        characteristic.dispose();
    }
}

class  Animal extends LivingCreature{
    private Characteristic characteristic = new Characteristic("has heart");
    private Description description = new Description("Animal not vegetable");

    public Animal() {
        System.out.println("Creating Animal");
    }

    protected void dispose(){
        System.out.println("Animal Dispose");
        description.dispose();
        characteristic.dispose();
        super.dispose();
    }
}

class Amphibian extends Animal{
    private Characteristic characteristic = new Characteristic("can live in water");
    private Description description = new Description("both water and land");

    public Amphibian() {
        System.out.println("Creating Amphibian");
    }

    protected void dispose(){
        System.out.println("Ampgibian Dispose");
        description.dispose();
        characteristic.dispose();
        super.dispose();
    }
}

class Frog extends Amphibian{
    private Characteristic characteristic = new Characteristic("Croaks");
    private Description description = new Description("Eats Bugs");

    public Frog() {
        System.out.println("Creating Frog");
    }

    protected void dispose(){
        System.out.println("Frog Dispose");
        description.dispose();
        characteristic.dispose();
        super.dispose();
        this.dispose();
    }
}