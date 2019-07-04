package com.github.java12;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class job13 {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    }
}



class IsEmptyClass{}


/**
 * @param <T>
 *     内建工厂,不会接受编译期安全检查
 *     为此得考虑可能发生的创建对象时异常
 */
class ClassAsFactory<T>{
    T obj;

    public ClassAsFactory(Class<T> type) throws IllegalAccessException, InstantiationException {
        this.obj = type.newInstance();
    }

    public T getObj() {
        return obj;
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        ClassAsFactory<IsEmptyClass> factory1 = new ClassAsFactory<>(IsEmptyClass.class);
        System.out.println(factory1.getObj());


        try {
            ClassAsFactory<Integer> factory2 = new ClassAsFactory<>(Integer.class);
            System.out.println(factory2.getObj());
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}


interface Factory<T,F>{
    T create(F obj);
}


class IntegerFactory implements Factory<Integer,String>{
    @Override
    public Integer create(String obj) {
        return new Integer(obj);
    }
}


class StringFactory implements Factory<String,char[]>{

    @Override
    public String create(char...obj) {
        return new String(obj,0,obj.length);
    }
}


/**
 * @param <T>
 *     显式工厂对象,编译期会受到安全检查
 *     通过传入工厂对象而生成具体的对象
 *     添加了更多的间接性、复杂性换来灵活性与安全性
 */
class FooOne<T,K>{
    private T obj;

    public <L extends Factory<T,K>> FooOne(L factory, K obj){
        this.obj = factory.create(obj);
    }

    public static void main(String[] args) {
        IntegerFactory integerFactory = new IntegerFactory();
        StringFactory stringFactory = new StringFactory();

        FooOne<Integer, String> factory1 = new FooOne<>(integerFactory,"345");
        FooOne<String, char[]> factory2 = new FooOne<>(stringFactory,new char[]{'1','2','3'});

        System.out.println(factory1.obj);
        System.out.println(factory2.obj);

    }
}


abstract class GenericWithCreate<T>{
    final Class<T> element;

    public GenericWithCreate(Class<T> element) {
        this.element = element;
    }

    abstract T create(Object...arg);
}



class X{
    public X() {
        System.out.println("X");
    }

    public X(Integer integer){
        System.out.println("X integer");
    }

}

class Create extends GenericWithCreate<X>{

    private X x;

    public Create(Class<X> element, Object...arg) {
        super(element);
        this.x = create(arg);
    }

    @Override
    public X create(Object... arg){
        try {
            Class<?>[] clazz = new Class[arg.length];
            for (int i = 0; i < arg.length; i++) {
                clazz[i] = arg[i].getClass();
            }
            return element.getConstructor(clazz).newInstance(arg);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        new Create(X.class);

        new Create(X.class,1);

    }


}


