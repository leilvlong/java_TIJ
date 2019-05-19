package com.github.java07;

import com.github.java07.WithInner.Inner;

/*
内部类继承:
    和上个例子不同,上个例子继承的外部类所以不存在问题
    而该案例中的内部类类继承则构造器需要显示的指明,不然编译不会通过
    因为内部类尽管是独立的字节码文件,但初始化环境依赖外部类

 */
public class job20 {
    public static void main(String[] args) {
        WithInner withInner = new WithInner();
        InheritInner inheritInner = new InheritInner(withInner, 10);
        inheritInner.outValue();

    }
}


class WithInner{
    class Inner{
        private Integer num;

        public Inner(Integer num) {
            this.num = num;
        }

        public void outValue(){
            System.out.println(num);
        }
    }
}


class InheritInner extends WithInner.Inner{

    public InheritInner(WithInner wi,Integer num) {
        wi.super(num);
    }
}