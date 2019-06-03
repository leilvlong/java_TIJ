package com.github.java10;

import java.util.Arrays;

/*
编译器对字符串拼接的优化程度:
    可以看到在循环中拼接字符串是可怕的行为。它每次都会创造一个StringBuilder对象
    然后将其toString(new String),一次循环new了两次对象

    +=进行的运算相当于:
        String str1 = "abc";
        String str2 = "def";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str1);
        stringBuilder.append(str2);
    相当于以下代码的操作:
        String str1 = "abc";
        String str2 = "def";
        char[] chars = new char[120];
        int count = 0;
        str1.getChars(0,str1.length(),chars,count);
        count+=str1.length();
        str2.getChars(0,str2.length(),chars,count);
        count+=str2.length();
        str1 = new String(chars,0,count);
        System.out.println(str1);
    注:
        一个忘记提到的点,StringBuilder的默认字符缓冲区不够使用时
        会动态扩展,它会验证属性count+字符串长度是否大于字符串缓冲区容量
        如果大于,就根据这个数值进行运算动态扩展,使用Arrays.copyOf(老数组,新长度)

而在使用StringBuilder的循环中可以看到:
    字符串的拼接操作友好了许多,从头到尾创建了两个对象
    方法运行启动时的StringBuilder与返回时的字符串对象
    而在循环过程中更是只执行了一次 StringBuilder.append方法


该案例的启示:
    在书写上下文中使用字符串拼接编译器会自动帮我们优化
    但是在复杂的业务场景情况下,更稳妥的方式是自己动手

 */
public class job03{
    public static void main(String[] args) {

    }


}


class WhitherStringBuilder{
    public String implicit(String[] fields){
        String result = "";
        for (int i = 0; i < fields.length; i++) {
            result+=fields[i];
        }
        return result;
    }

    public String explicit(String[] fields){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            result.append(fields[i]);
        }
        return result.toString();
    }
}

/*
class com.github.java10.WhitherStringBuilder {
        com.github.java10.WhitherStringBuilder();
        Code:
        0: aload_0
        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
        4: return

public java.lang.String implicit(java.lang.String[]);
        Code:
        0: ldc           #2                  // String
        2: astore_2
        3: iconst_0
        4: istore_3
        5: iload_3
        6: aload_1
        7: arraylength
        8: if_icmpge     38
        11: new           #3                  // class java/lang/StringBuilder
        14: dup
        15: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
        18: aload_2
        19: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        22: aload_1
        23: iload_3
        24: aaload
        25: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        28: invokevirtual #6                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        31: astore_2
        32: iinc          3, 1
        35: goto          5
        38: aload_2
        39: areturn

public java.lang.String explicit(java.lang.String[]);
        Code:
        0: new           #3                  // class java/lang/StringBuilder
        3: dup
        4: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
        7: astore_2
        8: iconst_0
        9: istore_3
        10: iload_3
        11: aload_1
        12: arraylength
        13: if_icmpge     30
        16: aload_2
        17: aload_1
        18: iload_3
        19: aaload
        20: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        23: pop
        24: iinc          3, 1
        27: goto          10
        30: aload_2
        31: invokevirtual #6                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        34: areturn
}*/
