package com.github.java12;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.getAllStackTraces;

class MyIterable <T> implements Iterable<T> {

    private List<T> list;

    private int size;

    public MyIterable(Collection<? extends T > iterator) {
        this.list = new ArrayList<>(iterator);
        this.size = this.list.size();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int marked;

            @Override
            public boolean hasNext() {
                return marked <= (size-1);
        }

            @Override
            public T next() {
                return list.get(marked++);
            }
        };
    }


    /**
     * 测试迭代器
     * @param args
     */
    public static void main(String[] args) {
        MyIterable<String> strings = new MyIterable<>(Arrays.asList("1", "2", "3", "4", "5"));

        for (String string : strings) {
            System.out.println(string);
        }

    }
}


/**
 * 将file对象保存到ArrayList中
 */
class TestFileBytes{

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("F:\\project_java\\java_TIJ\\demo01\\funs\\1553649184303.jpg");

        ArrayList<byte[]> bytes = new ArrayList<>();

        byte[] readByte = new byte[1024];
        int count = 0;
        while((count=fileInputStream.read(readByte)) != -1){
            byte[] saveByte = new byte[1024];
            System.arraycopy(readByte, 0, saveByte, 0, count);
            bytes.add(saveByte);
        }
        fileInputStream.close();

        FileOutputStream fileOutputStream = new FileOutputStream("F:\\project_java\\java_TIJ\\demo01\\funs\\123.jpg");

        for (byte[] aByte : bytes) {
            fileOutputStream.write(aByte);
        }
        fileOutputStream.close();


    }
}


class TestStack{

    public static void main(String[] args) {

        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[0];
        System.out.println(stackTraceElement.getMethodName());

    }

}

class TestNumStr{

    static final String str = "shdii{67=23}nksd{34}nj{23}";

    public static void main(String[] args) {
        String reg = "\\{\\d+\\p{Punct}\\d+}";
        Matcher matcher = Pattern.compile(reg).matcher(str);
        while (matcher.find()){
            System.out.println(matcher.group());
        }


    }
}

class User{

    String name;

    private List list;


    private User(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Method[] methods = User.class.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }



}




