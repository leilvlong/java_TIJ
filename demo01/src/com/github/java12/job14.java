package com.github.java12;



import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @param <T> 在编译期并不允许直接创建泛型数组,泛型并不具备明确的类型,只是个占位符
 *            尽管看似可以创建一个Object的数组将其转换为泛型,编译器也确实允许这种行为
 *            当持有泛型类的外部发生边界操作时,将影响到内部,这是合法的
 *            先编写好泛型类,然后在外部使用它,就好像生产组装机器完工了才能使用一样,在那之前只是一堆零件
 *            但是这种内部的强制类型转换,归根结底运行时还是Object类型的对象,而不是指定的泛型
 *            Java并不维护向下继承的关系,Object处于基类顶端,所以向下类型转换会出现类型转换异常
 *            因此更好的做法是创建Object类型的数组,填充泛型内容,使用容器元素时做类型转换
 *            ArrayList基于此实现了数组的泛用,并扩展了众多实用的功能
 */
class ListOfGenerics<T> {

    public T[] objs;

    public ListOfGenerics() {
        // T[] ts = new T[10];
        this.objs = (T[]) new Object[10];
    }

    public static void main(String[] args) {
        ListOfGenerics<String> strArray = new ListOfGenerics<>();
        strArray.objs[1] = "1";
        System.out.println(Arrays.toString(strArray.objs));

    }
}


class BaseClass {
    @Override
    public String toString() {
        return "BaseClass";
    }
}

class Subclass extends BaseClass {
    @Override
    public String toString() {
        return "Subclass";
    }
}

/**
 * @param <T> 基于以上分析做转型验证
 *            指定泛型为基类,数组类型为导出类,虽然这听着有点绕
 *            Java并不会维护向下的转型关系啊,但是此处所做的是向上的继承关系
 *            将导出类数组指向了泛型,泛型指定为基类的引用 即:子类对象使用父类引用
 *            而发生在上文的现象则是基类对象指向了导出类引用
 *            永远都不要因为这个特性搞混了继承关系:
 *                  导出类对象使用基类引用后,又强转回导出类引用
 *                  该导出类对象只是做回了自己而已,并未实质的发生向下转型
 */
class ListOfType<T> {
    public T[] objs;

    @SuppressWarnings("unchecked")
    public ListOfType() {
        this.objs = (T[]) new Subclass[5];
    }

    public static void main(String[] args) {
        ListOfType<BaseClass> ofType = new ListOfType<>();
        ofType.objs[0] = new Subclass();
        System.out.println(ofType.objs[0]);
    }

    public String fun (String str){
        return str.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(str));
    }
}


/**
 * @param <T>
 *     该类经过优化,所有的类型操作都发生运行时
 *     尽管会给出警告,也没什么关系
 *     set元素时会被转型为Object
 *     get元素时会回归原型
 *     rep时则会发生异常,虽然看起来奇怪,
 *     但该方法行为的本质与上文分析的错误原因是一样的
 *     尽管持有的元素是泛型,但数组本身是Object的
 *     你不能将一个早就声明好为Object类型的数组向下转型
 *     就如同仓库装满了货物,但仓库本身不做为货物
 *     但是有一个很有趣的地方,在Arrays.toString方法中使用rep方法是合法的
 *          rep方法确实会执行,同时还会跳过类型检查将整个数组作为字符串返回
 *          我需要JVM指令分析一波
 *          根本就没有验证类型转换,也就是说,只有当赋予引用类型时才会做类型转转
 *          才会验证类型转换的合法性,此时才会抛出类型转换的异常
 *          直白点说: 泛型的类型转换合法性验证只会发生在给变量赋值的时候
 */
class GenericArras2<T>{
    private Object[] objs;

    public GenericArras2(int size) {
        this.objs = new Object[size];
    }

    public void put(int index, T item){
        objs[index] = item;
    }

    public T get(int index){
        return (T)objs[index];
    }

    public T[] rep(){
        return (T[]) objs;
    }

    public static void main(String[] args) {
        GenericArras2<Integer> array = new GenericArras2<>(10);
        for (int i = 0; i < array.objs.length; i++) {
            array.put(i,i);
        }

        // String[] rep = array.rep(); 此处会出现类型转换异常
        array.rep();
        System.out.println(Arrays.toString(array.rep()));

    }
}


/**
 * @param <T>
 *     程序员是无所不能的,如果做不到,那也只是因为暂时没想到,多抓掉几根头发就好了 --<某产品经理>
 *     此处实现安全的泛型数组
 */
class GenericArrayWithType<T>{
    private T[] objs;

    @SuppressWarnings("unchecked")
    public GenericArrayWithType(Class<T> type) {
        this.objs = (T[]) Array.newInstance(type,10);
    }

    public void put(int index, T item){
        objs[index] = item;
    }

    public T get(int index){
        return (T)objs[index];
    }

    public T[] rep(){
        return (T[]) objs;
    }

    public static void main(String[] args) {
        GenericArrayWithType<String> array = new GenericArrayWithType<>(String.class);
        for (int i = 0; i < array.objs.length; i++) {
            array.put(i,String.valueOf(i));
        }
        for (int i = 0; i < array.objs.length; i++) {
            array.get(i);
        }
        String[] rep = array.rep();
        System.out.println(Arrays.toString(rep));
    }
}












