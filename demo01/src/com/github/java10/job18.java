package com.github.java10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class job18 {
    public static int regId;
    public static String input =
            "As long as there is injustice, whenever a\n" +
            "Targathian baby cries out, wherever a distress\n" +
            "signal sounds among the stars ... We'll be there.\n" +
            "This finee ship, and this fine crew ...\n" +
            "Never give up! Never surrender!";
    public static void main(String[] args) {
        for (String s : job17.poem.split("\n")) {
            System.out.println("input: " + s);
            for (String reg : new String[]{"\\w*ere\\w*", "\\w*ever", "T.+", "Never.*?!"}) {
                StartEnd.examine(s,reg);
            }
            regId = 0;
            System.out.println();
        }

        /*Matcher matcher = Pattern.compile("([A-Z]((\\w+'\\w+)|\\w+))").matcher(input);
        while (matcher.find()){
            System.out.println(matcher.group());
        }*/


        /*//判断字符串是否包含某个字符使用正则来做 该正则写的太蠢,以修正
        System.out.println("adfgdg".matches("\\b((.+[aA].+)|([aA].+)|(.+[aA])|[aA])"));*/

        /*//unicode中文编码字符范围
        System.out.println("dcbs你我aa".matches(".*[\\u4E00-\\u9FA5].*"));*/



        /* //正则与contains的性能测试
        String str = "dfgdsdsga";
        long s1 = System.nanoTime();
        System.out.println(str.matches(".*[aA].*"));
        long e1 = System.nanoTime();
        System.out.println(e1-s1);

        long s2 = System.nanoTime();
        System.out.println(str.contains("a"));
        long e2 = System.nanoTime();
        System.out.println(e2-s2);  */

    }
}


class StartEnd{

    private static class Display{
        private boolean regexPrinted = false;
        private String regex;

        private Integer id = 0;

        public Display(String regex) {
            this.regex = regex;
        }

        public void display(String message){

            if(! regexPrinted){
                System.out.println("regex"+(job18.regId++)+": " + regex);
                regexPrinted = true;
            }
            System.out.println(message);
        }
    }

    static void examine(String s, String regex){
        Display d = new Display(regex);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);

        while (m.find()) {
            d.display("find: '" + m.group() + "' start=" + m.start() + "; end=" + m.end() + ";" );
        }
        if (m.lookingAt()) {
            d.display("lookingAt: " + "start=" + m.start() + "; end=" + m.end() + ";");
        }
        if (m.matches()) {
            d.display("matches: " + "start=" + m.start() + "; end=" + m.end() + ";");
        }

    }


}