package com.github.job09;

/*
除了关机(电脑关机 jvm关机):
    没有什么能阻止finally执行
finally:
    用于资源回收(各种连接，文件IO, 资源管理...)
 */
public class job11 {

    public static void main(String[] args) {
        fun1();
        fun2();
    }

    public static void fun1(){
        try{
            throw new RuntimeException();
        }catch (RuntimeException e){
            return;
        }
        finally {
            System.out.println("???");
        }
    }


    public static void fun2(){
        try{
            throw new RuntimeException();
        }catch (RuntimeException e){
            System.out.println("关闭虚拟机 状态码99");
            System.exit(99);
        }
        finally {
            System.out.println("!!!");
        }
    }
}
