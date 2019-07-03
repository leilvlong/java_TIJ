package com.github.java12;

import javax.jws.Oneway;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class job11 {
    public static void main(String[] args) {

    }
}

/**
 * @param <T> 经管已经传入了一个String.class
 *            但是这个泛型的Class对象并不具有任何信息
 *            因为创建该数组后依然要强制转型,不然返回该数组会出现编译异常
 */
class ArrayMake<T> {

    private Class<T> kind;

    public ArrayMake(Class<T> kind) {
        this.kind = kind;
    }

    @SuppressWarnings("unchecked")
    T[] create(int size) {
        return (T[]) Array.newInstance(kind, size);
    }

    public static void main(String[] args) {
        ArrayMake<String> arrayMake = new ArrayMake<>(String.class);
        String[] strings = arrayMake.create(5);
        System.out.println(Arrays.toString(strings));
    }
}


/**
 * @param <T>
 *      在JDK8后,泛型容器会根据引用类型去推断持有的泛型
 *      此处则是根据返回值的引用类型持有的泛型推断 <此处默认有泛型,不需要再编写了>
 *      假如没有显示的持有泛型 <> 则编译器会给出警告
 */
class ListMaker<T> {

    @SuppressWarnings("unchecked")
    List<T> create() {
        //return new ArrayList<>();
        return new ArrayList();
    }

    public static void main(String[] args) {
        ListMaker<String> stringListMaker = new ListMaker<>();
        List<String> strings = stringListMaker.create();
        System.out.println(strings);
    }
}


/**
 * @param <T>
 *     经管在编译期无法通过占位泛型获取任何的类型信息
 *     但是在运行时仍然可以通过实体对象获取信息,
 *     这种对泛型的“限制”只会发生编译期
 *     java的泛型是折中的,,我所理解的泛型应当是指定是什么类型就应该是什么类型
 *     泛型应当是大于Object的,即如果有两个根类,那么泛型应当也能指定另一种根类
 *     但是实际情况是java虽然做到了“指定是什么类型就是什么类型”
 *     但它仅仅限于Object中,这意味着泛型依然受继承的影响
 *     倘若限定了泛型的边界,可以在泛型的边界中任意使用
 *     倘若没有边界,只能当做object使用
 *     java的泛型虽然做到了兼容,但是却没有做到“泛型”
 *     即：在泛型类的内部，持有泛型引用时，倘若不指定边界，你什么也做不了
 *     而这种泛型的占位符就像是一种标记,在运行时,访问到这里了,才知道持有的泛型具体是什么类型
 */
class FilledListMaker<T> {
    List<T> create(T t, int n) {
        System.out.println(t.getClass().getName());
        ArrayList<T> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(t);
        }
        return list;
    }

    public static void main(String[] args) {
        FilledListMaker<String> maker = new FilledListMaker<>();
        List<String> list = maker.create("hello", 4);
        System.out.println(list);
    }
}


/**
 * 不同于持有具体的类型引用
 * 泛型在java中意义就像是做一个标记,这种标记只在运行时发挥作用
 * 因此,在编译期才会有对泛型的诸多苦恼
 * 反射会在运行期发生作用,以下是对以上观点的佐证
 */
class CheckT<T> {
    T obj;

    @SuppressWarnings("unchecked")
    public CheckT(T obj, char[] chars) throws Exception {
        this.obj = obj;
        System.out.println(obj);

        obj = (T) obj.getClass().getConstructor(char[].class).newInstance(chars);
        System.out.println(obj);
    }

    public static void main(String[] args) throws Exception {
        CheckT<String> stringCheckT = new CheckT<>(
                "hello", new char[]{'a', 'b', 'c', ',', '1', '2', '3'});
    }
}


/**
 * @param <T>
 *     对持有泛型与持有具体对象的字节码分析
 *     可以看到在get时最终都执行了类型转换
 *     18: checkcast     #9                  // class java/lang/String
 *     43: checkcast     #15                 // class java/lang/Integer
 *     尽管在编译期持有泛型类的内部不再具备类型信息,
 *     但是在编译时,在泛型类的外部,一旦给出了类型标识
 *     那么你在操作这个持有泛型类的对象时必须遵守这种标识
 *     操作点正是边界,也是泛型发生改变的地方
 *     或者换个说法:  操作持有泛型对象的地方就是边界,泛型的类型在这里发生改变
 *     Object是所有类的根类,向上转型为Object不需要任何特殊的语法,
 *     因此反编译指令没有给出这种信息,反编译指令也再次证明了泛型在类的内部当做Object持有
 */
class SimpleHolder<T>{
    private Object obj;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }


    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }



    public static void main(String[] args) {
        SimpleHolder<Integer> holder = new SimpleHolder<>();

        holder.setObj("Item");
        String obj = (String) holder.getObj();
        System.out.println(obj);

        holder.setT(1234);
        Integer t = holder.getT();
        System.out.println(t);

        Object tbj = new SimpleHolder<String>();


    }
}


/*
class com.github.java12.SimpleHolder<T> {
        com.github.java12.SimpleHolder();
        Code:
        0: aload_0
        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
        4: return

public java.lang.Object getObj();
        Code:
        0: aload_0
        1: getfield      #2                  // Field obj:Ljava/lang/Object;
        4: areturn

public void setObj(java.lang.Object);
        Code:
        0: aload_0
        1: aload_1
        2: putfield      #2                  // Field obj:Ljava/lang/Object;
        5: return

public T getT();
        Code:
        0: aload_0
        1: getfield      #3                  // Field t:Ljava/lang/Object;
        4: areturn

public void setT(T);
        Code:
        0: aload_0
        1: aload_1
        2: putfield      #3                  // Field t:Ljava/lang/Object;
        5: return
public static void main(java.lang.String[]);
    Code:
       0: new           #4                  // class com/github/java12/SimpleHolder
       3: dup
       4: invokespecial #5                  // Method "<init>":()V
       7: astore_1
       8: aload_1
       9: ldc           #6                  // String Item
      11: invokevirtual #7                  // Method setObj:(Ljava/lang/Object;)V
      14: aload_1
      15: invokevirtual #8                  // Method getObj:()Ljava/lang/Object;
      18: checkcast     #9                  // class java/lang/String
      21: astore_2
      22: getstatic     #10                 // Field java/lang/System.out:Ljava/io/PrintStream;
      25: aload_2
      26: invokevirtual #11                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      29: aload_1
      30: sipush        1234
      33: invokestatic  #12                 // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
      36: invokevirtual #13                 // Method setT:(Ljava/lang/Object;)V
      39: aload_1
      40: invokevirtual #14                 // Method getT:()Ljava/lang/Object;
      43: checkcast     #15                 // class java/lang/Integer
      46: astore_3
      47: getstatic     #10                 // Field java/lang/System.out:Ljava/io/PrintStream;
      50: aload_3
      51: invokevirtual #16                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
      54: return

}*/
