package com.github.job09;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

class ErrorUtil{

    private static String logErrorPath = "testDirectory\\file\\error.txt";

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
        String format = "errorDateTime:\n     "+ simpleDateFormat.format(date)+"\nerrorMassage:\n    ";
        return format.getBytes();
    }

    private static byte[] getErrorMassage(Exception e){
        StackTraceElement ste = e.getStackTrace()[0];
        StringBuilder sb = new StringBuilder();
        StringBuilder errorMassage = sb.append("FileLocation: ").append(ste.getFileName()).append("\n    ").
                append("ClassLocation: ").append(ste.getClassName()).append("\n    ").
                append("MethodLocation: ").append(ste.getMethodName()).append("\n    ").
                append("ErrorLineNumber: ").append(ste.getLineNumber()).append("\n    ").
                append(e.getMessage() == null? "": e.getMessage());

        return errorMassage.toString().getBytes();
    }
}
