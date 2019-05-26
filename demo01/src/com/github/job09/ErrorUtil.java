package com.github.job09;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

class ErrorUtil{
    private static StringBuilder sb;
    private static String logErrorPath = "demo01\\funs\\error.txt";

    public static void pringErrorMassage(Exception e) {
        try{
            PrintStream ps = new PrintStream(new FileOutputStream(logErrorPath, true));
            ps.write(getErrorMassage(e));
            ps.close();
        }catch (Exception ee){
            ee.printStackTrace();
        }

    }

    private static String getErrorDate(){
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
        String errorDate = "errorDateTime:\n     "+simpleDateFormat.format(date)+"\n";
        return errorDate;
    }

    private static byte[] getErrorMassage(Exception e){
        sb = new StringBuilder();
        StackTraceElement ste = e.getStackTrace()[0];
        sb.append("----------------------------WARNING----------------------------\n").
                append(getErrorDate()).append("errorMassage:\n    ").append("FileLocation: ");

        String[] path = ste.getClassName().split("\\.");
        for (int i = 0; i < path.length-1; i++) {
            sb.append(path[i]).append("/");
        }
        String errorMassage = sb.append(ste.getFileName()).append("\n    ").
                append("ClassName: ").append(path[path.length - 1]).append("\n    ").
                append("MethodName: ").append(ste.getMethodName()).append("\n    ").
                append("ErrorLineNumber: ").append(ste.getLineNumber()).append("\n    MASSAGE: ").
                append(e.getMessage() == null ? e.getClass().getName() : e.toString()).append("\n").toString();
        return errorMassage.getBytes();
    }

    /**
     *
     * @param args
     * @throws IOException
     * 解析起来很简单
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("demo01\\funs\\error.txt")));
        while (br.read() != -1) {
            String s = br.readLine();
            if (s.contains("MASSAGE")) {
                System.out.println(s.replace("   ",""));
            }
        }
        br.close();
    }
}



