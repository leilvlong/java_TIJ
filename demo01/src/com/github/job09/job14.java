package com.github.job09;

/*
构造异常后正确清理:
    以下关于文件输入流就是一个典型的例子
    在构造器里工作的代码将通过fileName打开文件
    倘若出现异常抛出FileNotFoundException文件尚未打开,此时无需close操作
    而其它捕获任意异常的catch则必须进行清理,因为此时文件已经打开
    在做完清理操作后还应该将异常抛给这个类的使用者,因为这个类的异常被处理后核心功能并不可用
    而假如使用finally进行清理 finally总会执行,若在finally中进行close操作,这个构造器毫无意义

使用构造异常正确清理:
    对于构造阶段可能会抛出异常并且该类要求清理最安全的方式是使用嵌套try
    main方法里的逻辑:
        在创建InputFile对象时可能出现的异常由外围try捕获处理
        构造失败无需担心,因为InputFile已经足够细心的处理了可能出现的情况
        在创建对象成功后使用该对象时,则要考虑使用方法可能出现的异常,
        并且使用完毕后也应做清理操作,finally语句的含义 做好最坏的打算与无论如何都会执行
        此时就可以用在这里了,使用try catch finally 确保正确使用,正确捕获异常信息 正确清理

 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class job14 {
    public static void main(String[] args) {
        try{
            InputFile in = new InputFile("demo01\\funs\\error.txt");
            try{
                String s;
                while ((s = in.getLine())!= null){
                    System.out.println(s);
                }
            }catch (Exception e){
                System.out.println("Caught Exception in main");
            }finally {
                in.dispose();
            }
        }catch (Exception e){
            System.out.println("InputFile Construction failed");
        }
    }
}


class InputFile{
    private BufferedReader in;

    public InputFile(String fileName) throws Exception {
        try{
            in = new BufferedReader(new FileReader(fileName));
        }catch (FileNotFoundException e){
            System.out.println("Could not open: " + fileName);
            throw e;
        }catch (Exception e){
            try{
                in.close();
            }catch (IOException e2){
                System.out.println("in.close unsuccessful");
            }
            throw e;
        }finally {
            System.out.println("finally ??? 总会执行,在这里close in 就无法使用了");
        }
    }

    public String getLine(){
        String s;
        try{
            s = in.readLine();
        }catch (IOException e){
            throw new RuntimeException("readLine failed");
        }
        return s;
    }

    public void dispose(){
        try{
            in.close();
        }catch (IOException e){
            throw new RuntimeException("in.close failed");
        }
    }
}

