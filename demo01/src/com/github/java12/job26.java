package com.github.java12;


import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Decorator extends BasicImp{
    protected Basic basic;

    public Decorator(Basic basic) {
        this.basic = basic;
    }

    public void set(String val ){
        basic.set(val);
    }

    public String  get(){
        return basic.get();
    }
}


class TimeStampedS extends Decorator{

    private final long timeStamp;

    public TimeStampedS(Basic basic) {
        super(basic);
        timeStamp = new Date().getTime();
    }

    public long getTimeStamp(){
        return timeStamp;
    }
}


class SerialNumberedS extends Decorator{

    private static long count = 1;
    private final long serialNumbered = count++;

    public SerialNumberedS(Basic basic) {
        super(basic);
    }

    public long getSerialNumbered(){
        return serialNumbered;
    }

    public static void main(String[] args) {
        TimeStampedS
                t1 = new TimeStampedS(new BasicImp()),
                t2 = new TimeStampedS(new SerialNumberedS(new BasicImp()));
        t1.set("123");
        System.out.println(t1.get());
        System.out.println(t1.getTimeStamp());

        t2.set("456");
        System.out.println(t2.get());
        System.out.println(t2.getTimeStamp());

        SerialNumberedS
                s1 = new SerialNumberedS(new BasicImp()),
                s2 = new SerialNumberedS(new TimeStampedS(new BasicImp()));
        s1.set("789");
        System.out.println(s1.get());
        System.out.println(s1.getSerialNumbered());

        s2.set("adb");
        System.out.println(s2.get());
        System.out.println(s2.getSerialNumbered());

    }
}

/**
 *
 */
class QueueTest{
    public static void main(String[] args) throws IOException {

//        LinkedList<Integer> list = new LinkedList<>();
//
//        for (int i = 0; i < 10; i++) {
//            list.addFirst(i);
//        }
//            System.out.println(list.removeLast());
//        }
//        for (int i = 0; i < 10; i++) {


        /*int[] nums = new int[]{0,1,2,3,4};
        int[] numss = new int[10];

        System.arraycopy(nums,3 ,numss ,6 , nums.length-3);
        System.out.println(Arrays.toString(numss));

        int[] nums2 = new int[]{5,6,7,8,9};
        System.arraycopy(nums2,0 ,numss ,nums2.length , nums2.length);
        System.out.println(Arrays.toString(numss));

        int[] ints = Arrays.copyOf(nums, 15);
        System.out.println(Arrays.toString(ints));*/

        HashMap<String, String> map = new HashMap<>();
        map.put("1","1" );
        map.put("2","1" );
        map.put("3","1" );
    }

    public static void list(int start, int end){
        int[] ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        int newEnd = end > 0 ? end : ints.length + end;
        int newLength = (newEnd - start)+1;
        int[] newInts = new int[newLength];
        System.arraycopy(ints, start, newInts, 0, newLength);

        System.out.println(Arrays.toString(newInts));
    }

}


class MyHash<K,V>{

    private Object[] table;

    public MyHash() {
        table = new Object[10000];
    }

    private int hash(K key){
        return key.hashCode() & (table.length-1);
    }

    public V put(K key, V value){
        int hash = hash(key);
        System.out.println(hash);
        Object o = table[hash];
        table[hash] = value;
        return (V) o;
    }

    public V get(K key){
        int hash = hash(key);
        return (V) table[hash];
    }

}

class TestMyHashKey{
    private String name;

    private Integer age;

    public TestMyHashKey(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestMyHashKey tets = (TestMyHashKey) o;
        return Objects.equals(name, tets.name) &&
                Objects.equals(age, tets.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age,System.identityHashCode(this));
    }
}


/**
 * 理解hash与hash实现
 */
class TestMyHash{
    public static void main(String[] args) {
        MyHash<TestMyHashKey, Integer> myHash = new MyHash<>();
        TestMyHashKey tets1 = new TestMyHashKey("张三",18);
        TestMyHashKey tets2 = new TestMyHashKey("张三",18);
        TestMyHashKey tets3 = new TestMyHashKey("张三",18);
        myHash.put(tets1, 1);
        myHash.put(tets2, 2);
        myHash.put(tets3, 3);

        System.out.println(myHash.get(tets1));
        System.out.println(myHash.get(tets2));
        System.out.println(myHash.get(tets3));

    }
}


/**
 * 字符串翻转
 */
class TestINT{
    public static void main(String[] args) {
        String str = "987654321";

        char[] chars = str.toCharArray();

        for (int i = 0, n = chars.length-1;i < n; i++, n--) {
            char temp = chars[i];
            chars[i] = chars[n];
            chars[n] = temp;
        }
        System.out.println(Arrays.toString(chars));
        System.out.println(new String(chars));

    }
}


class Exceptionnew extends Exception{
    public Exceptionnew() {
    }
}


/**
 * throw与throws的区别
 */
class ExceptionTest{

    public static void newExceptionTest1(int a){

        if (3>a){
            throw new RuntimeException("我出现异常了>>>>>>>>>>>");
        }
        // 这个方法表示一定会抛出异常
    }

    public static void newExceptionTets2() throws Exceptionnew {
        // 这个表示该方法可能出现异常,需要调用方法的人去处理
        throw new Exceptionnew();
    }


    public static void main(String[] args)  {
        try {
            newExceptionTest1(2);
        } catch (Exception exceptionnew) {
            System.out.println("出现异常>>>>>>>>>>>>>>>>>");
            exceptionnew.printStackTrace();
            System.out.println(exceptionnew.getMessage());
        }
    }
}


/**
 * 冒泡排序
 */
class MaoPaoSort{

    public static void main(String[] args) {

        int[] list = {9,7,8,4,5,6,1,3,2};

        for (int i = 0; i < list.length; i++) {
            for (int y = 0; y < i; y++) {
                int num = list[i];
                int subNum = list[y];
                if (num<subNum){
                    list[i] = subNum;
                    list[y] = num;
                }

            }
        }
        System.out.println(Arrays.toString(list));
    }

}


class TestStr{

    public static void main(String[] args) {

        /*long start = System.currentTimeMillis();
        System.out.println((int)( Math.random()*1000000));
        long end = System.currentTimeMillis();
        System.out.println("end :" +(end-start));*/
//        String str = "abcdabcdabcd";
//        String reg = "(abc)+";
//
//        Matcher matcher = Pattern.compile(reg).matcher(str);
//        while (matcher.find()){
//            System.out.println(matcher.group(1));
//        }
        Map<String,String> map = new HashMap<>();
        map.put("abc", "123");
        map.put("def", "456");
        map.put("ghd", "789");

        String str = "${abc}d${def}d${ghd}d";

        for (String s : map.keySet()) {
            String reg = "(\\$\\{("+s+")})";
            str = str.replaceAll(reg, map.get(s));
        }
        System.out.println(str);
    }
}

/**
 * 循环队列实现
 */
class ArrayQueue{
    public static void main(String[] args) {
        Integer [] nums = {1,2,3,4,5,6};

        for (int i = 0; i < 100; i++) {
            queueArray(nums);
        }
    }

    public static Integer queueArray(Integer[] nums){
        Integer result = nums[nums.length-1];
        nums[nums.length-1] = null;
        System.arraycopy(nums, 0, nums, 1, nums.length-1);
        nums[0]=result;
        System.out.println(Arrays.toString(nums));
        return result;
    }
}














