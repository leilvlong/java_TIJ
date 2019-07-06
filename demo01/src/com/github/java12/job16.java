package com.github.java12;

import java.util.ArrayList;
import java.util.List;

class Fruit{
    @Override
    public String toString() {
        return "Fruit";
    }
}

class Apple extends Fruit{
    @Override
    public String toString() {
        return "Apple";
    }
}

class Jonathan extends Apple{
    @Override
    public String toString() {
        return "Jonathan";
    }
}

class Orange extends Fruit{
    @Override
    public String toString() {
        return "Orange";
    }
};

/**
 * 创建一个Fruit[]引用的Apple[]数组
 * 这里令人迷惑之处：向上转型不适合这里
 * 因为引用是Fruit[]的，所以编译器在编译期允许你向数组添加 new Fruit()
 * 然而实际工作时数组本身是 Apple[]，java不会维护向下的转型
 * new Fruit()无法像 new Apple() 一样工作
 * 即: Apple[] apples = (Apple[]) fruits; 时,
 * 里面的 new Fruit()无法做到像 Apple()一样工作
 */
class CovariantArrays {
    public static void main(String[] args) {
        Fruit[] fruits =new Apple[10];
        fruits[0] =new Apple();
        fruits[1] =new Jonathan();
        fruits[3] = new Fruit(); // error: ArrayStoreException
        Apple[] apples = (Apple[]) fruits;
    }
}


/*
关于java不会维护向下转型:
    向上转型：
        1）导出类继承基类一定会初始化基类，因为导出类一定会依赖基类的属性与行为
           不然就没有继承的必要
        2）导出类向上查找只需关键字标识 extends与 implements,成本低效率高
        3）导出类一定具有基类的属性与行为，导出类中存在super引用指向基类
    向下转型：
        1）基类不会依赖导出类，因此基类无必要初始化导出类
        2）导出类会扩展基类不具备的属性与行为，而这些对基类时未知的
        3）总不能愚蠢到每导出一个子类都到基类那里注册一下，
           然后基类初始化把所有注册的导出类也初始化，这样成本高，效率低
           不是每一个导出类都会被用到
综上所述:
    以上代码可以在运行时允许添加Apple的子类而无法添加基类Fruit
    其数组引用本身指向基类也是没有问题的
    至于编译器在编译期允许添加Fruit是因为引用指向了Fruit
    编译期检查当然认为这是合理的
    因为编译期不具备对象的实体信息
    编译期只能检查类型信息
    通过一些手段擦掉类型信息或混淆类型信息就可以在编译器骗过编译器

该章节的多个案例都在提醒:
    注意区分容器类型与容器持有类型
 */


/**
 * 在泛型容器中会更加友好一些
 * 它不会出现前文中那种令人迷惑的数组类型与元素类型的混淆
 * List本身没有类型,你指定的泛型意味着该容器将持有这种类型
 * List<Fruit>中可以持有Apple
 * 但不会允许你将指定的泛型Fruit转换为Apple
 * 声明为Fruit就必须创建持有Fruit的容器
 * 而非创建new ArrayList<Apple>()容器
 */
class NomCovariantArrays{
    public static void main(String[] args) {
        // List<Fruit> fruits = new ArrayList<Apple>();
    }
}


/**
 * 使用通配符表示:声明任何继承自Fruit的 new ArrayLis<>();
 * 这意味着实际上这个容器除了装null,什么也做不了,
 * 正是因为它可以是任何继承自 Fruit 的
 * 这种不确定性使得它无法安全的转型:
 *      发生在下文中的容器转型会使得里面的元素很尴尬
 *      而Java并不维护向下转型
 * 当然,当你对List<T>进行具体的转型后,它看似可以正常的工作:
 *      毕竟ArrayList的内部持有的是Object[]
 *      但是实际获取容器持有的对象时可能会遇到意料之外的麻烦
 *      实际异常处在本文中最后一行获取容器元素时
 * 为了容器的安全性:
 *      这种通配符的声明不应出现在创建泛型容器中
 *      当然要是你有额外的手段确保安全就另说了
 */
class GenericAndCovariance{
    public static void main(String[] args) {

        List<? extends Fruit> fruits = new ArrayList<>();
        /*
        fruits.add(new Fruit());
        fruits.add(new Apple());
        fruits.add(new Jonathan());
        fruits.add(new Orange());       */

        List<Fruit> fruit = (List<Fruit>) fruits;
        fruit.add(new Fruit());
        System.out.println(System.identityHashCode(fruit));
        System.out.println(fruit);

        List<Apple> apple = (List<Apple>) fruits;
        apple.add(new Apple());
        System.out.println(System.identityHashCode(apple));
        System.out.println(apple);

        List<Jonathan> jonathan = (List<Jonathan>) fruits;
        jonathan.add(new Jonathan());
        System.out.println(System.identityHashCode(jonathan));
        System.out.println(jonathan);

        List<Orange> orange = (List<Orange>) fruits;
        orange.add(new Orange());
        System.out.println(System.identityHashCode(orange));
        System.out.println(orange);

        //  再一次欺骗在编译期欺骗了编译器 然而实体对象实际是Apple
        Orange or = orange.get(1); //error: ClassCastException
    }
}