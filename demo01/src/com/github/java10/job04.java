package com.github.java10;

/*

 */

import java.util.ArrayList;
import java.util.List;


/*
容器toString实际也是字符串拼接,对每个元素都使用toString方法然后拼接

以下案例重写了toString方法,用来打印自身的地址值,但是这会造成无意识的递归因为this:
    1)容器toString的时候,会调用元素的toString
    2)进入元素的toString方法
    3)进行字符串拼接,但this不是字符串对象
    4)创建StringBuilder对象
    5)调用StringBuilder.append(Object obj)方法 因为this是个对象
    6)StringBuilder.append(Object obj)方法里面又调用了String.valueOf(Object obj)
        还是因为this是对象所以用的重载方法参数是Object
    7)String.valueOf(Object obj) 又使用了obj.toString
    8)又回到2) 所以递归了  栈内存华丽的溢出

以下是相关源码:
    @Override
    public StringBuilder append(Object obj) {
        return append(String.valueOf(obj));
    }

    public static String valueOf(Object obj) {
        return (obj == null) ? "null" : obj.toString();
    }

最下文有相关JVM指令

 */
public class job04 {
    public static void main(String[] args) {
        List<InfiniteRecursion> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new InfiniteRecursion());
        }

        System.out.println(list.toString());
    }
}

class InfiniteRecursion{

    @Override
    public String toString() {
        //return "InfiniteRecursion:" + super.toString()+"\n";
        return "InfiniteRecursion:" + this+"\n";
    }
}


/*
class com.github.java10.InfiniteRecursion {
  com.github.java10.InfiniteRecursion();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public java.lang.String toString();
    Code:
       0: new           #2                  // class java/lang/StringBuilder
       3: dup
       4: invokespecial #3                  // Method java/lang/StringBuilder."<init>":()V
       7: ldc           #4                  // String InfiniteRecursion:
       9: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      12: aload_0
      13: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      16: ldc           #7                  // String \n
      18: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      21: invokevirtual #8                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
      24: areturn
}
 */