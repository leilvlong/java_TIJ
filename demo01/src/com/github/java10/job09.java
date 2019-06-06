package com.github.java10;

/*
java 正则表达式:
    正则表达式以匹配符为单位匹配
[ ] 匹配包含在这个中括号内的内容
{1,9}  匹配次数在这个大括号具体值区间 或者指定匹配次数 {5} 紧跟匹配符使用
| 或运算  匹配符|匹配符 返回匹配到其中对应的匹配符
() 匹配该小括号内的内容
制表符  \w \d 等
特殊符号 @#$,? 匹配特殊符号需转义  \@   \?
?  惰性匹配
+ 贪婪匹配
 */
public class job09 {
    public static void main(String[] args) {
        //匹配手机电话 (伪)
        String match = "1[358][0-9]\\d{8}";

        System.out.println("13698976938".matches(match));
    }
}
