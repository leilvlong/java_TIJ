package com.github.java07;

/*
从多重嵌套类中访问外部成员:
    如果需要,内部类可以一直套内部类,但这不是重点
    重点是不管嵌套多少层,内部类都可以无条件访问外部类的所有成员
    (在外部类的作用域内)
 */

public class job13 {
    public static void main(String[] args) {
        MNA.A.B b = new MNA().new A().new B();
        b.fun_B();

        MNA.A.B.C c = new MNA().new A().new B().new C();
        c.fun_C();
    }
}


class MNA{
    private void fun_MNA(){
        System.out.println("Class MNA method fun");
    }

    class A{
        private void fun_A(){
            System.out.println("Class A method fun");
        }

        class B{
            void fun_B(){
                System.out.println("执行 Class B method fun_B");
                MNA.this.fun_MNA();
                fun_MNA();

                A.this.fun_A();
                fun_A();
            }

            class C{
                void fun_C(){
                    System.out.println("执行 Class C method fun_C");
                    fun_MNA();
                    fun_A();
                    fun_B();
                }
            }
        }
    }
}