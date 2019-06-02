package com.github.java10;

/*
重载 "+" 与 StringBuilder:
    String对象具有只读的特性,所有指向它的任何引用都不可能改变它的值
    为此 java为操作符 + 进行了重载

操作符重载:
    一个操作符在应用于特定的类时,被赋予了特殊的意义
    用于String的+与+=赋予的意义是字符串拼接(底层的实现是StringBuilder.append方法)


以下案例中有JVM的执行指令,最下面注释就是:
    可以看到字符串拼接执行了大量的StringBuilder.append
    这由JVM自动引入识别

 */



public class job02 {
    public static void main(String[] args) {
        String str1 = "mango: ";
        String str2 = "abc: "+ str1 + "def: " + 47;
        System.out.println(str2);

    }


}

/*
public class com.github.java10.job02 {
public com.github.java10.job02();
        Code:
        0: aload_0
        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
        4: return

public static void main(java.lang.String[]);
        Code:
        0: ldc           #2                  // String mango:
        2: astore_1
        3: new           #3                  // class java/lang/StringBuilder
        6: dup
        7: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
        10: ldc           #5                  // String abc:
        12: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        15: aload_1
        16: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        19: ldc           #7                  // String def:
        21: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        24: bipush        47
        26: invokevirtual #8                  // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        29: invokevirtual #9                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        32: astore_2
        33: getstatic     #10                 // Field java/lang/System.out:Ljava/io/PrintStream;
        36: aload_2
        37: invokevirtual #11                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        40: return
}
*/
