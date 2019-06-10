package com.github.java10;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
组:
(表达式)(表达式(表达式))
以一对括号为单位
第一组为整个括号
其次为最外围的括号
 */
public class job17 {
    static String poem =
            "Twas brillig, and the slithy toves\n" +
                    "Did gyre and gimble in the wabe.\n" +
                    "All mimsy were the borogoves,\n" +
                    "And the mome raths outgrabe.\n\n" +
                    "Beware the Jabberwock, my son,\n" +
                    "The jaws that bite, the claws that catch.\n" +
                    "Beware the Jubjub bird, and shun\n" +
                    "The frumious Bandersnatch.";
    public static void main(String[] args) {


        /*
        Matcher matcher =
                Pattern.compile("(?m)(\\S+)\\s+((\\S+)\\s+(\\S+))$")
                        .matcher(poem);
        while (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println("[" + matcher.group(i) + "]");
                count++;
            }
        }              */

        //习题思路解法 找出所有非大写开头的单词 计算个数
        //以词边界
        /*Matcher matcher =
                Pattern.compile("(?m)(\\b[a-z]|\\s+\\b[a-z])\\w+")
                        .matcher(poem);*/
        //以行边界
        Matcher matcher =
                Pattern.compile("(^[a-z]|\\s+[a-z])\\w+")
                        .matcher(poem);
        Set<String> words = new HashSet<>();
        while (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.print("[" + matcher.group(i) + "]");
                words.add(matcher.group());
            }
            System.out.println();
        }
        System.out.println(words.size());
        System.out.println(words);


    }
}

/*[ the,  borogoves,  in,  jaws,  my,  claws,  gimble,
        wabe,  outgrabe,  toves,  mimsy,  were,  brillig,
        slithy,  raths,  son,  and,  catch,  bird,  bite,
        mome,  frumious,  gyre,  shun,  that]*/

/*[ the,  borogoves,  in,  jaws,  my,  claws,  gimble,
        wabe,  outgrabe,  toves,  mimsy,  were,  brillig,
        slithy,  raths,  son,  and,  catch,  bird,  bite,
        mome,  frumious,  gyre,  shun,  that]*/
