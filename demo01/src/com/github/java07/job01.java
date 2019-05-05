package com.github.java07;
/*
内部类:
    这种语法与python中的函数嵌套很像(尽管两者并无任何联系只是语法像 毕竟Java是以class为单位的)
    python中函数嵌套带来的闭包是因为: 外部函数提供运行环境,内部函数去干具体的事,防止被垃圾回收
    python中的垃圾回收使用的是引用计数,这种机制导致一旦失去引用数据将会丢失,使用闭包则可以避免这点
    但java使用的垃圾回收机制并非如此,且回收机制也是惰性的

 def outMethod(obj):
    def innerMethod():
        return obj
    return innerMethod
 */
/*
在外部类Parcel1中:
    在ship方法中可以为两个内部类创建对象
    并且与正常的类创建对象没什么区别

 */
public class job01 {
    public static void main(String[] args) {
        Parcel1 parcel1 = new Parcel1();
        parcel1.ship("Parcel1");
    }
}


class Parcel1{

    class Contens{
        private int i = 11;
        public int value(){
            return i;
        }
    }

    class Destination{
        private String lable;

        public Destination(String lable) {
            this.lable = lable;
        }

        String readLable(){
            return lable;
        }
    }

    public void ship(String dest){
        Contens c = new Contens();
        Destination d = new Destination(dest);
        Outlying1 o = new Outlying1("outlying class");
        System.out.println("innerClass Content member variable i: " + c.i);
        System.out.println("innerClass Destination method readLable: " +d.readLable());
        System.out.println("Class describe: " + o.out());
    }


}


class Outlying1{
    private String describe;

    public Outlying1(String describe) {
        this.describe = describe;
    }

    public String out(){
        return describe;
    }
}