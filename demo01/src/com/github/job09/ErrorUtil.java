package com.github.job09;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

class ErrorUtil{
    private static StringBuilder sb;
    private static String logErrorPath = "demo01\\funs\\error.txt";

    public static void pringErrorMassage(Exception e) {
        try{
            PrintStream ps = new PrintStream(new FileOutputStream(logErrorPath, true));
            ps.write(getErrorDate());
            ps.write(getErrorMassage(e));
            ps.close();
        }catch (Exception ee){
            ee.printStackTrace();
        }

    }

    private static byte[] getErrorDate(){
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
        String errorDate = "errorDateTime:\n     "+simpleDateFormat.format(date)+"\n";
        return errorDate.getBytes();
    }

    private static byte[] getErrorMassage(Exception e){
        sb = new StringBuilder();
        StackTraceElement ste = e.getStackTrace()[0];
        sb.append("errorMassage:\n    ").append("FileLocation: ");

        String[] path = ste.getClassName().split("\\.");
        for (int i = 0; i < path.length-1; i++) {
            sb.append(path[i]).append("/");
        }
        String errorMassage = sb.append(ste.getFileName()).append("\n    ").
                append("ClassName: ").append(path[path.length - 1]).append("\n    ").
                append("MethodName: ").append(ste.getMethodName()).append("\n    ").
                append("ErrorLineNumber: ").append(ste.getLineNumber()).append("\n    massage: ").
                append(e.getMessage() == null ? e.getClass().getName() : e.toString()).append("\n").
                append("-------------------------------------------------------------\n").toString();

        return errorMassage.getBytes();
    }
}
