package com.github.java11;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 利用java I/O 仿事务模型
 */

public class CacheScanner {

    private static boolean isCommit = true;

    private static Map<String,Integer> rollbackMap = new HashMap<>();

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

                /**
                 * 命令区
                 */

                // 停止执行指令
                if (s.equals("break"))
                    break;

                // 出现异常回滚指令
                if (s.equals("error"))
                    throw new RuntimeException();

                // 设置rollback回滚点指令
                if (s.matches("setIsCommit\\(.*\\)")) {
                    String param = matcherParam(s);
                    setIsCommit(param, data);
                    s = null;
                }
                // 回到指定回滚点指令
                else if (s.matches("rollback\\(.*\\)")){
                    String param = matcherParam(s);
                    data = rollback(param, data);
                    s = null;
                }

                /**
                 * 提交区
                 */

                // 自动提交
                if (isCommit)
                    commit(outputStream, s);

                // 指定回滚点提交指令
                else if(s != null && s.matches("commit\\(.*\\)")){
                    String param = matcherParam(s);
                    commit(param,data,outputStream);
                }

                // 不提交,添加到缓存区
                else{
                    if (s != null){
                        data.add(s);
                    }
                }
            }
            commit(outputStream, data);
        }catch (Exception E){
            rollback(data);
        }


    }

    public static void setIsCommit(boolean isCommit){
        CacheScanner.isCommit = isCommit;
    }

    public static void setIsCommit(String rollbackPoint, List<String> data){
        rollbackMap.put(rollbackPoint, data.size());
        CacheScanner.isCommit = false;
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

    public static void commit(String rollbackPoint,List<String> data, OutputStream printStream) throws IOException {
        Integer rollbackIndex = rollbackMap.get(rollbackPoint);
        for (Integer i = 0; i < rollbackIndex; i++) {
            printStream.write(data.get(i).getBytes());
            printStream.write("\n".getBytes());
        }
        data.clear();
    }

    public static void rollback(List<String> data){
        System.out.println("出现异常 回滚");
        data.clear();
    }

    public static List<String> rollback(String rollbackPoint, List<String> data){
        Integer rollbackIndex = rollbackMap.get(rollbackPoint);
        return data.subList(0,rollbackIndex );

    }

    public static String matcherParam(String method){
        Matcher matcher = Pattern.compile(".*\\((.*)\\)").matcher(method);
        while (matcher.find()){
            return matcher.group(1);
        }

        return method;
    }

}

class Mat{
    public static void main(String[] args) {
        String str = "revome(hello)" ;
        String reg = ".*\\((.*)\\)";


        String str1 = "#{test}";
        String reg1 = ".*\\{(.*)\\}";


        Matcher matcher = Pattern.compile(reg1).matcher(str1);

        while (matcher.find()){
            System.out.println(matcher.group(1));
        }

    }
}


