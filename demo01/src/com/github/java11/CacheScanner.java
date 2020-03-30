package com.github.java11;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 利用java I/O 仿事务
 */

public class CacheScanner {

    private static boolean isCommit = true;

    public static void main(String[] args) throws FileNotFoundException {
        // 输入
        Scanner scanner = new Scanner(System.in);

        // 持久化路径
        FileOutputStream outputStream = new FileOutputStream("F:\\project_java\\java_TIJ\\demo01\\funs\\scanner.txt", true);

        // 数据缓冲区
        List<String> data = new ArrayList<>();


        try{
            while (scanner.hasNext()){
                String s = scanner.nextLine();
                if (s.equals("break"))
                    break;

                if (s.equals("error"))
                    throw new RuntimeException();

                if (isCommit)
                    commit(outputStream, s);
                else
                    data.add(s);
            }
            commit(outputStream, data);
        }catch (Exception E){
            rollback(data);
        }


    }

    public static void setIsCommit(boolean isCommit){
        CacheScanner.isCommit = isCommit;
    }

    public static void commit(OutputStream printStream, List<String> data) throws IOException {
        for (String datum : data) {
            printStream.write(datum.getBytes());
            printStream.write("\n".getBytes());
        }
    }

    public static void commit(OutputStream printStream, String data) throws IOException {
        printStream.write(data.getBytes());
        printStream.write("\n".getBytes());
    }

    public static void rollback(List<String> data){
        System.out.println("出现异常 回滚");
        data.clear();
    }

}
