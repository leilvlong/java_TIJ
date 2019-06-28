package com.github.java12;

/*
以下是一个泛型工厂:
    该工程通过静态方法返回实例,省去了麻烦的 new BasicGenerator<T>(type)
    另外Jdk8的编译器对类型推断做了很好的优化,通过声明或字节码对象就可以得到具体的泛型
    使用工厂另外的好处是提供额外的间接性,添加灵活度

 */

public class job05 {
    public static void main(String[] args) {
        Generator<CountObject> countObjectGenerator = BasicGenerator.create(CountObject.class);
        for (int i = 0; i < 10; i++) {
            System.out.println(countObjectGenerator.next());
        }
    }
}


class BasicGenerator<T> implements Generator<T> {

    Class<T> type;

    private BasicGenerator(Class<T> type) {
        this.type = type;
    }

    @Override
    public T next() {
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Generator<T> create(Class<T> type) {
        return new BasicGenerator<T>(type);
    }
}


class CountObject {
    private static long counter = 0;
    private final long id = counter++;



    @Override
    public String toString() {
        return "CountObject Id: " + id;

    }
}