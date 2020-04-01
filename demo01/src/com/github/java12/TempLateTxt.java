package com.github.java12;
/*
最低限度的实现模板引擎
 */


import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TempLateTxt {
    private static String templatPath = "demo01/funs/template.txt";
    private static String outPath = "demo01/funs/templateOut.txt";
    private static String regex = "\\$\\{([\\w\\p{Punct}]*)}";

    private static String replaceKey(String key) {
        return "\\$\\{" + key + "}";
    }


    private static Map<String, String> replaceData;

    static {
        replaceData = new HashMap<>();
        replaceData.put("name", "hello word");
        replaceData.put("open", "happy");
        replaceData.put("status","大雨");
        replaceData.put("with","可以的!!!");

    }

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader(new File(templatPath));
        FileWriter writer = new FileWriter(new File(outPath));

        int i = 0;
        char[] chars = new char[1024*8];
        while ((i = fileReader.read(chars)) != -1) {
            String s = new String(chars, 0, i);

            Matcher matcher = Pattern.compile(regex).matcher(s);
            while (matcher.find()) {
                String key = matcher.group(1);
                String data = replaceData.get(key);
                String regexKey = replaceKey(key);
                s = s.replaceFirst(regexKey,data);
            }

            writer.write(s);
        }

        writer.close();
        fileReader.close();

    }
}


/*
demo01/funs/template.txt:
        你好 ${name}!! 我很 ${open}!!
        今天的天气--${status}

demo01/funs/templateOut.txt:
        你好 hello word!! 我很 happy!!
        今天的天气--大雨

 */

class TEST{
    public static void main(String[] args) {

        String template = "你好${hellD88**}}, 我很${open}";

        Map<String, String> map = new HashMap<>();
        map.put("hello","hello world" );
        map.put("open", "open star");

        String reg = "\\$\\{([\\w\\p{Punct}]*)}";


        Matcher matcher = Pattern.compile(reg).matcher(template);
        while (matcher.find()){
            String group = matcher.group(1);
            System.out.println(group);
        }




    }
}