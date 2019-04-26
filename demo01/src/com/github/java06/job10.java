package com.github.java06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

/*
适配器模式:
    通过构造的方式适配
    在以下案例中 Scanner 构造器接受一个Readable接口,这个接口单独为 Scanner创建
    使得Scanner不必将其参数限制为某个特定类,只要是实现Readable接口即可
    这带来更多的灵活性与通用性
    以下Scanner 案例中的构造器分别接受了自定义Readable System.in InputStreamReader
    通过为Scanner 配置一个专门的接口Readable 使得Scanner可以适配任意Readable的导出对象
 */
public class job10{
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new RandomWords(10));
        while (scanner.hasNext()){
            System.out.println(scanner.next());
        }

        Scanner sc = new Scanner(System.in);
        if(sc.hasNext()){
            String next = sc.next();
            System.out.println(next);
        }

        Scanner sa = new Scanner(new InputStreamReader(new FileInputStream("demo01\\funs\\aaa.txt")));
        while (sa.hasNext()){
            System.out.println(sa.next());
        }
    }
}


class RandomWords implements Readable{
    private Random rand = new Random(47);
    private final  char[] capitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final char[] lowers = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private final char[] vowels = "aeiou".toCharArray();
    private  int count;

    public RandomWords(int count) {
        this.count = count;
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        if(count-- == 0){
            return  -1;
        }

        cb.append(capitals[rand.nextInt(capitals.length)]);
        for (int i = 0; i < 4; i++) {
            cb.append(vowels[rand.nextInt(vowels.length)]);
            cb.append(lowers[rand.nextInt(lowers.length)]);
        }
        cb.append(" ");
        return 10;

    }
}


