package com.github.java07;

/*
在支持多重继承的语言中一般都是采用广度优先或者深度优先策略来解决菱形继承这个问题的,
但实际使用起来还是很令人头疼,尤其是当因为书写左右文而导致执行时得不到想要的结果时更令人抓狂

前文已经提到过java拒绝菱形继承及其可能带来的冲突(这种单线继承是很好的)
经管java采用了允许一个类可以实现多个接口这种策略,(这种策略也是很好的):
    永远都不用担心菱形继承可能导致的冲突问题(引起这种冲突的几率很小,也因此更麻烦更具隐蔽性)
    这使得一种数据类型有了多种行为规范
    TIJ说class 定义的是除了java提供的数据类型外由我们自己定义的新数据类型是很有道理的

但是当我们需要将两种数据类型组合在一起成为一个全新的数据类型时,单线继承就不是那么友好了:
    内部类允许继承:
        这意味着一个类可以通过多个内部类来实现多继承这种需求,尤其是当继承对象为抽象类时
        (相较于组合关系,继承可以带来更多扩展)

而且内部类继承可以带来更多的灵活性:
    以下案例揭示了如何避免菱形继承
    注; 拒绝菱形继承指 class 而非interface
 */


public class job14  {
    static void takes(A a){
        a.fun();
    }
    static void takes(B b){
        b.fun();
    }


    public static void main(String[] args) {
        IsAB isAB = new IsAB();
        IsA  isA = new IsA();
        B isB = isA.makeB();

        takes((A)isAB);
        takes((B)isAB);
        takes(isA);
        takes(isB);


    }
}


interface A{
    void fun();
}

interface B{
    void fun();
}


// 永远都无需担心菱形继承这种问题 接口只有当实现其方法时该方法才具备执行能力
//不同接口的相同方法(同名同参数)只用实现其中一个即可(编译器默认你将两个都实现了)
class IsAB implements A, B{

    @Override
    public void fun() {
        System.out.println("Class IsAB method fun");
    }
}


class IsA implements A{

    @Override
    public void fun() {
        System.out.println("Class IsA method fun");
    }

    B makeB(){
        return new B() {
            @Override
            public void fun() {
                System.out.println("Lambda Class method fun");
            }
        };
    }
}





