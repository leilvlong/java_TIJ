package com.github.java07;
/*
泛定义内部类字节码文件:
    之前job06的“泛定义”内部中已经总结过过内部类字节码文件的规则:
    1.外部类中的内部类 : 外部内名$内部类名
    2.方法、域中的内部类: 外部类名$升序编号(id) + 内部类名
    3.匿名内部类 :  在哪个类中 就以该类的 类名$升序编号(id)

嵌套类:
    在外部类下被static修饰的内部类,字节码文件命名规则并不不同,但是其特性具有:
    1.创建嵌套类对象无需外部类对象 (静态修饰加载至静态内存区)
    2.不能从嵌套中访问非静态的外围类对象
    针对特性1补充:
        普通内部类只有当外部类被new分配内存空间时内部类以及普通成员,
        成员变量才会初始化且每次new对象时都会进行这种初始化,对象生存
        于堆上,失去所有引用后将被标记为垃圾等待清理
        静态修饰则会随着类被第一次访问时将其初始化进静态内存区域以类名.属性名的方式获取并可供反复使用

区分数字1和字母L; 字母L的小写比数字1更为细长一些
 */
public class job11 {
    public static void main(String[] args) {
        Contents contents = Parcel11.getContents();
        System.out.println(contents.value());

        Destination game = Parcel11.getDestination("Game");
        System.out.println(game.readLable());
    }
}


class Parcel11{

    private static class PContents implements Contents{
        private int i = 11;
        @Override
        public int value() {
            return i;
        }
    }

    protected static class PDestination implements Destination{
        private String label;

        public PDestination(String label) {
            this.label = label;
        }

        @Override
        public String readLable() {
            return label;
        }
    }

    public static Contents getContents(){
        return  new PContents();
    }

    public static Destination getDestination(String label){
        return new PDestination(label);
    }
}