package com.github.java06;

import java.lang.reflect.Field;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Random;
/*
尽管这种模式也是可行的:
    但是无疑在扩展性上会差很多,这是因为java拒绝菱形继承 只允许单继承带来的
    job07的案例不应当忘记

原则:
    任何方法或构造器都可以接收参数,其参数都可以是接口类型或数据类型
    这意味着任何类都都可以对该方法(构造器)的参数进行适配
    在编写方法或设计类时更应当思考好你所要接收的是一个行为还是一种数据类型

    class : 数据类型规范 is-a
    interface: 数据行为规范 is-like-a
 */
public class job12 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Scan sc = new Scan(new NextDoubles(10));
        while (sc.hasNext()) {
            System.out.println(sc.netx());
        }

        Scan sa = new Scan(new NextIntS(10));
        while (sa.hasNext()) {
            System.out.println(sa.netx());
        }
    }
}



class Scan{



    private ReadableClass rc;
    private boolean status = true;

    private CharBuffer allocate = CharBuffer.allocate(1024);

    // 构造时先加载一次 避免输出空行
    Scan(ReadableClass rc) {
        this.rc = rc;
        rc.read(allocate);
    }

    //简单模仿 Scanner hasNext
    public boolean hasNext() {
        if(allocate.array()[0] == '\0'){
            return  false;
        }
        return  status;
    }

    // 模仿Scanner next
    public String netx(){
        // 因为构造时已经加载一次了 所以直接转换然后清除缓冲区
        char[] array = allocate.array();
        String s = new String(array, 0, array.length);
        allocate.clear();

        //重新加载供hasNext判断是否还有内容 当再次调用此方法时直接将缓冲区内容转为字符串
        // 重复此流程 直到 rc.read方法使用次数用完为此
        int read = rc.read(allocate);
        if(read == -1){
            status = false;
        }
        // 返回缓冲区操作得到的字符串对象
        return s;
    }


}







abstract class ReadableClass {
    abstract int read(CharBuffer cb);
}


class NextDoubles extends ReadableClass{
    private Random rand = new Random(47);
    private Integer count;

    public NextDoubles(Integer count) {
        this.count = count;
    }

    @Override
    int read(CharBuffer cb) {
        if(count-- == 0){
            return  -1;
        }
        String s = Double.toString(rand.nextDouble()) + " ";
        cb.append(s);
        return s.length();
    }
}

class NextIntS extends ReadableClass{
    private Integer count;
    private Random rand = new Random(47);

    public NextIntS(Integer count) {
        this.count = count;
    }

    @Override
    int read(CharBuffer cb) {
        if (count-- ==0){
            return -1;
        }
        String s = "";
        for (int i = 0; i < 5; i++) {
            s += rand.nextInt(100-10)+10;
        }
        s = s+ " ";
        cb.append(s);
        return s.length();
    }
}