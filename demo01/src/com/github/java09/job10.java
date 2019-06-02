package com.github.java09;

/*
对异常来说:
    关键是理解概念以及如何使用,
    对程序会沿着这样的轨迹运行,感到意外,这并不是想要的结果
    抛出异常阻止这种错误的轨迹
程序是已知的,将人的指令转换为机器指令并加以执行
异常也应当如此,异常信息应明确指出这不是预定的执行轨迹(执行指令),并告知产生这种行为的原因

java的三种异常:
   1.JVM异常:
        能力不足时只能想办法避免
   2.运行时异常:
             程序运行时产生的异常，期望参数不符，期望执行流程不符
        等一部分是由JVM虚拟机自动检测的，一部分是由开发人员抛出的
        可捕获并回滚处理或选择不处理!(这是开发中每天都要打交道的)
            运行时异常继承自RuntimeException,这构成了一组具有相同
        特征和行为的异常类型，并且这类异常不受编译检查,即不必在方
        法上声明这种异常属于错误,若没有手动捕获将被自动捕获
            倘若未手动捕获，则会直达main方法中（最后一个运行调用，程序终止时）
            Exception in thread "main" java.lang.RuntimeException
	            at com.github.job09.MyExceptionStart.fun(job10.java:48)
	            at com.github.job09.MyExceptionTets.fun(job10.java:41)
	            at com.github.job09.job10.main(job10.java:32)

   3.编译时异常:
        肉眼可见（但又几乎不会注意到de）的语法错误，自定义异常等
        注意:
            1.  只有继承RuntimeException的异常可以不受编译检查,因为这是JVM由检查的,
            这类异常被视为编程错误
            2.  自定义异常这类是由编译器强制实施检查的,在编译的过程中,若方法上未声
            明或未使用异常捕捉而抛出了自定义异常,编译便不会通过。并且编译通过后当执
            到异常处时也只会当做普通的代码执行然后抛出异常


 */


public class job10 {

    public static void main(String[] args) {
        new MyExceptionTets().fun(new MyExceptionStart());
    }


}

class MyExceptionTets{

    public void fun(MyExceptionStart ms){
        ms.fun();
    }
}


class MyExceptionStart{
    public  void fun()  {
        throw new RuntimeException();
    }
}