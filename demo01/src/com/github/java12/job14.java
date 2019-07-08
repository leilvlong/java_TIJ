package com.github.java12;
import java.lang.reflect.Array;
import java.util.Arrays;


/**
 * @param <T>
 *     在编译期并不允许直接创建泛型数组
 *     泛型并不具备明确的类型,只是个占位符
 *     尽管看似可以创建一个Object的数组将其转换为泛型
 *     编译器也确实允许这种行为：因为泛型在类的内部运行时就是Object的，
 *     数组本身引用是Object类型的,泛型在类的内部也是Object的
 *     但是当你在外部使用它时，会做类型转换
 *     这种转换基于编译期你所给出的对泛型的类型标记
 *     此时将一个Object类型的数组转为你指定泛型的数组
 *     自然会发生异常: java不会维护向下的关系,而Object是根类
 *     最直接的证明就是异常发生在调用处而非构造器中
 */
class ListOfGenerics<T> {

    public T[] objs;

    public ListOfGenerics() {
        // T[] ts = new T[10]; 不允许的语法
        this.objs = (T[]) new Object[10];
    }

    public static void main(String[] args) {
        ListOfGenerics<String> strArray = new ListOfGenerics<>();
        strArray.objs[1] = "1";
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
 * @param <T>
 *    基于以上分析做转型验证
 *    指定泛型为基类,数组类型为导出类,虽然这听着有点绕
 *    Java并不会维护向下的转型关系啊,但是此处所做的是向上的继承关系
 *    将导出类数组指向了泛型,泛型指定为基类的引用 即:子类对象使用父类引用
 *    而发生在上文的现象则是基类对象指向了导出类引用
 *    永远都不要因为这个特性搞混了继承关系:
 *           导出类对象使用基类引用后,又强转回导出类引用
 *           该导出类对象只是做回了自己而已,并未实质的发生向下转型
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
}



/**
 * @param <T>
 *     该类经过优化,所有的类型操作都发生运行时,尽管会给出警告,也没什么关系
 *     此处声明: 已经证明泛型在类的内部会作为Object类型
 *     使用 put 方法时元素会被转型为Object而被容器持有
 *     使用 get 方法时元素会回归原型(
 *          看似如此,但是有个很有趣的特性会发生在下文)
 *     使用 rep 时则会发生异常(然而事实是并非使用rep方法总会出现异常,稍后说明)
 *     虽然看起来奇怪:
 *          但该方法行为的本质与上文分析的错误原因是一样的
 *          尽管持有的元素是泛型,但数组本身是Object的
 *          你不能将一个早就声明好为Object类型的数组向下转型
 *          就如同仓库装满了货物,但仓库本身不做为货物
 *     有一个很有趣的地方,在Arrays.toString方法中使用rep方法是合法的
 *          rep方法确实会执行,同时似乎还会跳过类型检查将整个数组作为字符串返回
 *          但实际是根本就没有验证类型转换,也就是说,只有当需要使用类型信息时才会做类型转换
 *          才会验证类型转换的合法性,此时才会抛出类型转换的异常
 *          直白点说: 不管是在类的内部,还是作为返回值,泛型都将作为Object
 *          在需要类型转换的时候才会根据你编译期给出的泛型标记做类型转换
 *          这种行为模式在程序中被称作惰性行为:如果不必要,就不执行
 *          这种惰性可以很好的提高性能,但前提是你得确保它是安全的
 *          以上观点可以通过反推Jvm指令验证
 *
 */
class GenericArras2<T>{
    private Object[] objs;

    public GenericArras2(int size) {
        this.objs = new Object[size];
    }

    public void put(int index, T item){
        objs[index] = item;
    }

    @SuppressWarnings("unchecked")
    public T get(int index){
        return (T)objs[index];
    }

    @SuppressWarnings("unchecked")
    public T[] rep(){
        System.out.println("rep方法确实执行了");
        return (T[]) objs;
    }

    public void printObjs(Integer[] objs){
        System.out.println(Arrays.toString(objs));
    }

    public static void main(String[] args) {
        GenericArras2<Integer> array = new GenericArras2<>(10);
        for (int i = 0; i < array.objs.length; i++) {
            array.put(i,i);
        }

        //  此处会出现类型转换异常
        // Integer[] rep = array.rep();

        //  由于没有使用类型引用 不会发生异常
        array.rep();

        //  该方法接受Object数组 不会发生异常
        System.out.println(Arrays.toString(array.rep()));

        //  该方法参数处会验证类型,会发生转换,出现异常
        array.printObjs(array.rep());
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












