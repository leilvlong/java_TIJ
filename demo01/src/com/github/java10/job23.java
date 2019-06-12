package com.github.java10;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class job23 {
    public static void main(String[] args) {
        /*BaseDataToString baseDataToString = new BaseDataToString("12\n13\n12.5\n13.85\nahah");
        System.out.println(baseDataToString);*/

        Scanner scanner = new Scanner("12, 42, 78, 99, 42");
        scanner.useDelimiter("\\s*,\\s*");
        while (scanner.hasNextShort()){
            System.out.println(scanner.next());
        }

    }
}



class BaseDataToString{
    private int integer;
    private long aLong;
    private float aFloat;
    private double aDouble;
    private String string;

    public BaseDataToString(String valueOf) {
        Scanner sc = new Scanner(valueOf);
        this.integer=sc.nextInt();
        this.aLong=sc.nextLong();
        this.aFloat=sc.nextFloat();
        this.aDouble=sc.nextDouble();
        this.string=sc.next();
    }

    @Override
    public String toString() {
        return "BaseDataToString{" +
                "integer=" + integer +
                ", aLong=" + aLong +
                ", aFloat=" + aFloat +
                ", aDouble=" + aDouble +
                ", string='" + string + '\'' +
                '}';
    }
}
