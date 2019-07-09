package com.github.java12;
import java.util.*;
/**
 * 泛型容器的(容器元素)合法向上转型:
 *      这种转型本质List还是Apple的,但是里面的元素引用确定了上边界
 *      另外对于这种不确定性,容器会拒绝添加任何元素,但即便如此,类型安全依然难以保障
 *      需要将一个泛型容器的元素做向上转型时,至少这种做法还是不错的
 */
class Holder{
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>(Arrays.asList(new Apple()));
        List<? extends Fruit> fruits = apples;
        for (Fruit fruit : fruits) {
            System.out.println(fruit);
        }
    }
}


/**
 * 逆变 超类型通配符:
 *      <? super Apple> Java并不会维护向下的关系这一原则不会改变
 *      超类型通配符实际是通过下边界 Apple 类上的extends关键字逐级查找确定了关系
 *      与<? super Apple>的使用有很多类似之处,
 *      不同的是,因为是下边界,所以确定了一定可以装 Apple :Apple一定可以统一向上转型
 *      编译器允许直接 apple.add(new Apple())
 *      同样,因为无法确定所有元素的上边界与具体类型,使用下边界类型引用时统一返回Object
 *      使用下边界具有读和取的属性
 *      容器可以向上转型,并且放入合适当前引用的元素
 *      但是容器向下转型时必须是使用超类型通配符
 */
class SuperTypeWildcards{
    public static void main(String[] args) {
        List<? super Apple> apple = new ArrayList<>();
        apple.add(new Apple());
        List<Fruit> fruit = (List<Fruit>)apple;
        fruit.add(new Fruit());

        System.out.println("容器元素:" + apple);

        System.out.println("----------------------遍历导出类的引用容器----------------------");
        for (Object o : apple) {
            System.out.println(o.getClass().getSimpleName());
        }


        System.out.println("----------------------遍历基类的引用容器----------------------");
        for (Fruit o : fruit) {
            System.out.println(o.getClass().getSimpleName());
        }

        System.out.println("----------------------超类型通配符引用与转型----------------------");
        fruit.add(new Orange());
        List<? super Apple> apples = fruit;
        for (Object o : apples) {
            System.out.println(o.getClass().getSimpleName());
        }
    }
}




