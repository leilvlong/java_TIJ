package com.github.java12;

import java.util.ArrayList;
import java.util.List;

class Botany{
    @Override
    public String toString() {
        return "Botany";
    }
}

class Fruit extends Botany{
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
 * 如何骗过编译期检查:
 *      创建父类引用
 *      实际创建对象数组是子类
 *      然后向其添加父类对象
 *      成功躲过编译期检查
 *      尽管拿出元素时依然是父类引用
 *      但是实际对象类型是apple
 *      当进行引用转换时,父类引用不可以转换成子类
 *      因此在转换为字节码时会抛出数组存储异常
 */
class CovariantArrays {
    public static void main(String[] args) {
        Fruit[] fruits =new Apple[10];
        fruits[0] =new Apple();
        fruits[1] =new Jonathan();
        fruits[3] = new Fruit(); // error: ArrayStoreException
        Fruit fruit = fruits[0];
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
 * 而非创建new ArrayList<Apple>()容器却给泛型引用List<Fruit>
 * 即: 不能把一个涉及Apple的泛型赋给一个涉及Fruit的泛型
 */
class  UpperBoundaryDemo{
    public static void main(String[] args) {
        // List<Fruit> fruits = new ArrayList<Apple>();
    }
}


/**
 *  ? 表示实例对象
 *  List<? extends Fruit> 更像是一种表达式,表明实例对象继承自Fruit
 *  用于表示实例化时可以确定父类型的未知类型
 *  因此被称为上边界通配符
 *  编译器在泛型容器中只关心进来的对象和出去的对象
 */
class GenericAndCovariance{
    public static void main(String[] args) {

        List<? extends Fruit> fruitsList = new ArrayList<>();

        /*
        // 因为无法确定最后会有多少子类信息,所以拒绝直接添加任何元素
        fruitsList.add(new Fruit());
        fruitsList.add(new Apple());
        fruitsList.add(new Jonathan());
        fruitsList.add(new Orange());             */

        // 但是管理非常松散，允许你随意转型，
        // 只要是我的子类即可，转型后即可添加元素
        // 这当然是合理的，容器若无法持有对象，有什么意义呢？
        List<Fruit> fruit = (List<Fruit>) fruitsList;
        fruit.add(new Fruit());
        System.out.println("Returns the same hash code as List: "+ System.identityHashCode(fruit));
        System.out.println("容器引用:List<Fruit>  容器内容: "+ fruit);

        List<Apple> apple = (List<Apple>) fruitsList;
        apple.add(new Apple());
        System.out.println("Returns the same hash code as List: "+System.identityHashCode(apple));
        System.out.println("容器引用:List<Apple>  容器内容: "+apple);

        List<Jonathan> jonathan = (List<Jonathan>) fruitsList;
        jonathan.add(new Jonathan());
        System.out.println("Returns the same hash code as List: "+System.identityHashCode(jonathan));
        System.out.println("容器引用:List<Jonathan>  容器内容: "+jonathan);

        List<Orange> orange = (List<Orange>) fruitsList;
        orange.add(new Orange());
        System.out.println("Returns the same hash code as List: "+System.identityHashCode(orange));
        System.out.println("容器引用:List<Orange>  容器内容: "+orange);

        /*
        // 语法上是合法的
        // 这很显然是个设计的缺陷，既然限定了边界还允许向上转型就显得不怎么合适
        // 尤其是编译成class文件时能够检查出就显得语法上的合法更加不合适
        List<Botany> botanies = (List<Botany>) fruitsList;
        botanies.add(new Botany());     */

        System.out.println("-------------------对容器遍历获取元素-------------------");

        /*
        // 语法上是合法的
        //获取元素时则需要当心，并非都orange
        for (Orange ora : orange) {
            System.out.println(ora);  //error: ClassCastException
        }*/

        // 该继承体系的根级引用自然是合法的
        for (Fruit fru : fruitsList) {
            System.out.println("容器引用:List<fruit>  容器元素: "+fru);  //里面的元素一定继承自Fruit
        }

    }
}


/**
 *  ? 表示实例对象
 *  List<? super Apple> 更像是一种表达式,表明实例对象到Apple为止,不可以再向下了
 *  用于表示实例化时可以确定子类型的未知类型。
 *  因此被称为下边界通配符
 */
class LowerBoundaryDemo{
    public static void main(String[] args) {

        List<? super Apple> list = new ArrayList<>();

        list.add(new Jonathan());
        list.add(new Apple());

        /*
        // 拒绝添加基类
        list.add(new Fruit());
         */

        List<Apple> apples = (List<Apple>) list;

        for (Apple apple : apples) {
            System.out.println(apple);
        }

        /*
        // 禁止向下转型:
        List<Jonathan> jonathans = (List<Jonathan>) list;   */

        // 但是可以向上转型
        List<Fruit> fruits = (List<Fruit>) list;
        for (Fruit fruit : fruits) {
            System.out.println(fruit);
        }

        // 可以向上转型
        List<Botany> botanies = (List<Botany>) list;
        for (Botany botany : botanies) {
            System.out.println(botany);
        }

        // 还记得继承时子类对象创建一定会初始化基类吗 所以这是合法的(向上转型)
        // 而基类对象创建时不会创建子类对象,这是不可维护的,因此禁止向下转型

        // 因此List<? super Apple>拒绝添加基类Fruit的实例对象
        // 因为一旦当引用向上转换后，再转回，Fruit对象是无法当Apple对象使用的
        // Fruit无法知道Apple里面在继承后又新做了些什么

        // 因此List<? super Apple>允许添加Jonathan是合理的
        // Jonathan对象的初始化会让Apple也初始化

        // 因此List<? super Apple>不允许转型为List<Jonathan>也是合理的
        // 理由同上

        // 所以下边界中 元素的添加只允许添加相对Apple的子类
        // 转型的边界则是 Apple 且相对Apple只能向上转型

        // 相较于上边界，下边界的管理严厉了很多

        // 当未指定具体的类型而需要拿出元素时，则默认为Object
        // Object的原因当然是因为没指定具体的类型咯
        for (Object o : list) {
            System.out.println(o);
        }
    }
}