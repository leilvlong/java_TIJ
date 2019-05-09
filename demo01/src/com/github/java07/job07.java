package com.github.java07;

/*
匿名内部类:
    在job06中实际已经使用过两个匿名内部类
    每个内都会有默认的构造器,这点内部类也不例外
匿名内部类不可以指定构造器:
    需要注意的是并非“不可以”指定构造器，而是无法指定构造器，该类并没有类名用来指定
    new Contents()实际是返回这个接口实现对象的引用 而非创建一个Contents的对象,接口是不能创建对象的

如果匿名内部类需要指定的参数怎么办,在Destination中每个实现案例中都通过构造器为lable赋值 答案:
    在名为PDestination的类中实现了Destination接口
    在Parcel7类中的destination方法中 返回一个Destination引用
    该方法的return语句 return new PDestination(label)
    尽管如此 它依然是匿名内部类(通过查看字节码文件)
    它将具体实现类当做接口来使用,在返回时又做了一次向上转型

    在Parcel7类中的players方法中将做类似的同样的事,但是不会去实现接口
    观察输出语句,这很像继承,并且也使用了super,这甚至就是继承,只不过是隐式的
    现在,换一种写法 观察输出语句后:
        这确实是一种继承,匿名内部类的私有成员label并没有获取到传递的参数
        而通过super却可以得到这足以说明问题,使用的是导出类的构造器,其构造器自然也会为自己的成员赋值

    这种机制实际上已经与匿名内部类是否实现接口无关了
    匿名内部类对接口的实现本身就已经是一种继承,在将类 作为作为接口去返回匿名内部类时 是继承关系就不奇怪了


 */
public class job07 {
    public static void main(String[] args) {
        Parcel7 parcel7 = new Parcel7();
        Contents contents = parcel7.contents();
        System.out.println(contents.value());

        Destination destination = parcel7.destination("Parcel7 Method inner Class");
        System.out.println(destination.readLable());

        Players players1 = parcel7.players("Game");
        System.out.println(players1.readLable());
        players1.out();


        Players players2 = parcel7.getPlayers("Movie");
        System.out.println(players2.readLable());
        players2.out();
    }
}





class Parcel7{

    public Contents contents(){
        return new Contents() {
            int i = 11;
            @Override
            public int value() {
                return i;
            }
        };
    }

    public Destination destination(String label){
        return new PDestination(label) {
            private String lable;

            @Override
            public String readLable() {
                return label;
            }
        };
    }

    public Players players(String label){
        return new Players(label) {
            public String readLable() {
                return super.readLable();
            }
        };
    }

    public Players getPlayers(String label){
        return new Players(label) {
            private String label;
            public String readLable() {
                return label;
            }
        };
    }
}

class PDestination implements Destination{
    private String label;

    public PDestination(String label) {
        this.label = label;
    }

    @Override
    public String readLable() {
        return label;
    }
}

class Players{
    private String label;

    public Players(String label) {
        System.out.println("匿名内部类使用了我?");
        this.label = label;
    }


    public String readLable() {
        return label;
    }

    public void out(){
        System.out.println("I want "+ label);
    }
}