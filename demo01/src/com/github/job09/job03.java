package com.github.job09;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class job03 {
    public static void main(String[] args) {
        try{
            throw new LoggingException();
        }catch (LoggingException e){
            System.err.println(e);
        }
    }
}


class LoggingException extends Exception{
    private static Logger logger = Logger.getLogger("LoggingException");

    public LoggingException() {
        StringWriter writer = new StringWriter();
        printStackTrace(new PrintWriter(writer));
        logger.severe(writer.toString());
    }
}


