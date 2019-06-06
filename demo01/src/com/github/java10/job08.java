package com.github.java10;

/*
String.format:
    public static String format(String format, Object... args) {
        return new Formatter().format(format, args).toString();
    }
 */

public class job08 {
    public static void main(String[] args) throws DatabaseException {
        throw new DatabaseException(3,7,"Write failed");
    }
}


class DatabaseException extends Exception{
    public DatabaseException(int transactionID, int queryID, String message) {
        super(String.format("(t%d, q%d) %s)",transactionID,queryID,message));
    }
}