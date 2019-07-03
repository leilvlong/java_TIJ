package com.github.java03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
I/O流是对文件再内存与硬盘间的操作(客户端/服务端消息的传输)
Interface Collection<E>:
    default Stream<E> stream() :
        该默认方法对继承该接口的数据容器实现类提供了流式操作(流水线加工)
        被称之为Stream流,该流在 java.util.stream.Stream下
        由于该接口为所有我目前已知的 java容器的根接口 所以我当前所知的所有容器都支持
        Stream流操作
java 8的lambda表达式更多的是为了这种函数式接口带来便利
而且能大大提高对数据容器操作的便捷
以下是main中简单的操作案例

在案例中需要注意的是:
    Stream才是可以被操作的数据容器流 如果返回值类型不是Stream 则代表为终结方法 该流以关闭
 */
public class job06 {
    static int index = 0;
    public static void main(String[] args) {
        // 使用fun方法得到国家所有 省 区 直辖市
        ArrayList<String> China = fun();
        //1、统计三个字的省份的个数  并返回ArrayList
        ArrayList<String> province = new ArrayList<>(China.stream()
                                                    .filter((str) -> (3 == str.length()) && str.contains("省"))
                                                    .collect(Collectors.toList()));
        System.out.println(province);

        //2、统计名字中包含方位名词的省份（东西南北）的个数
        long provinceCount = China.stream()
                .filter(str -> str.contains("东") || str.contains("西") || str.contains("南") || str.contains("北"))
                .count();
        System.out.println(provinceCount);

        //3、打印名字中包含方位名词的普通省份（非自治区直辖市特别行政区）的名字
        China.stream()
                .filter(str->str.contains("省"))
                .filter(str -> str.contains("东") || str.contains("西") || str.contains("南") || str.contains("北"))
                .forEach(System.out::println);

        //4、将所有的特殊省份（自治区直辖市特别行政区）提取出来并放到新数组中
       /*   //我知道有Stream.toArray  换个方式
        String[] strings = China.stream().filter(str -> !str.contains("省")).toArray(String[]::new);
        System.out.println(Arrays.toString(strings));
        */
       // 获取数组大小
        String[] strs = new String[(int)China.stream()
                .filter(str -> !str.contains("省")).count()];
        // 以获取迭代器对象的方式
        /*Iterator<String> provinceIter = China.stream().filter(str -> !str.contains("省")).iterator();
        for (int i = 0; i < strs.length; i++) {
            strs[i] = provinceIter.next();
        }
        System.out.println(Arrays.toString(strs));*/
        ArrayList<String> strs2 = new ArrayList<>();
        List<String> collect = China.stream().filter(s -> !s.contains("省")).map(s -> s.replace("区", "省")).collect(Collectors.toList());
        System.out.println(collect);


        // 正如开头所说的 Stream流以关闭
        //System.out.println(provinceStream.count());
        // Exception in thread "main" java.lang.IllegalStateException:stream has already been operated upon or closed
        // 只要当后面操作返回的不是Stream 而企图对原先引用变量操作时 便会抛出这个异常

    }

    public static ArrayList<String> fun(){
        ArrayList<String> strList = new ArrayList<>();
        //因为不想一个个添加 所以直接将整个字符串复制过来做处理添加到 strList中
        String str = "河北省、山西省、吉林省、辽宁省、黑龙江省、陕西省、甘肃省、青海省、山东省、福建省、" +
                "浙江省、台湾省、河南省、湖北省、湖南省、江西省、江苏省、安徽省、广东省、海南省、四川省、贵州省、云南省、" +
                "北京市、天津市、上海市、重庆市、"+"内蒙古自治区、新疆维吾尔自治区、宁夏回族自治区、广西壮族自治区、西藏自治区、"+
                "香港特别行政区、澳门特别行政区";
        String[] strArr = str.split("、");
        for (String s : strArr) {
            strList.add(s);
        }
        return strList;
    }
}
