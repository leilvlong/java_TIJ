package com.github.java07;

/*
在内部类中使用 .this
 .new 创建内部类对象
尽管内部类已经拥有外部类所有成员的访问权
在命名冲突时仍然能使用 外部类.this的方式使用

 普通内部类若想创建对象必须依靠外部类对象的方式
 这也解决了内部类命名作用域的问题
 内部类的创建必须依赖外部类


 */
public class job04 {
    public static void main(String[] args) {
        /*DotThis dt = new DotThis();
        DotThis.Inner innerClass = dt.getInnerClass();
        innerClass.getOuterClass().fun();
        innerClass.fun();*/

        /*DotThis dt = new DotThis();
        DotThis.Inner inner = dt.new Inner();
        inner.fun();
        inner.getOuterClass().fun();*/

        Parcel3 parcel3 = new Parcel3();
        Parcel3.Contens contens = parcel3.new Contens();
        Parcel3.Destination tasmania = parcel3.new Destination("Tasmania");

        new DotThis().out(parcel3);
    }
}


class DotThis{
    private int i = 10;

    void fun(){
        System.out.println("Class DotThis method fun");
    }

    class Inner{
        private  int i = 15;
        public DotThis getOuterClass(){
            return DotThis.this;
        }
        public void fun(){
            System.out.println("innerClass Inner method fun");
            System.out.println("innerClass Inner member "+ this.i);
            System.out.println("Class DotThis member " + DotThis.this.i);
        }
    }

    public Inner getInnerClass(){
        return new Inner();
    }

    public void out(Parcel3 p){
        System.out.println(p.new Contens().getI());
    }

}


class Parcel3{

    class Contens{

        private int i = 11;

        public int getI(){
            return i;
        }
    }


    class Destination{

        private String label;

        Destination(String label) {
            this.label = label;
        }

        String getLabel(){
            return label;
        }
    }
}