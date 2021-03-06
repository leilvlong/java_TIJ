package com.github.java10;

import java.util.Scanner;
import java.util.regex.MatchResult;

public class job24 {
    public static void main(String[] args) {
        String threatData =
                "58.27.82.161@02/10/2005\n"+
                "204.45.234.40@02/11/2005\n"+
                "58.27.82.161@02/11/2005\n"+
                "58.27.82.161@02/12/2005\n"+
                "58.27.82.161@02/12/2005\n"+
                "[Next log section with different data format]";

        Scanner sc = new Scanner(threatData);
        String reg = "(\\d+[.]\\d+[.]\\d+[.]\\d+)@(\\d{2}/\\d{2}/\\d{4})";

        while (sc.hasNext(reg)) {
            sc.next(reg);
            MatchResult match = sc.match();
            String ip = match.group(1);
            String date = match.group(2);
            System.out.format("Threat on %s from %s \n",date,ip);
        }


    }
}
