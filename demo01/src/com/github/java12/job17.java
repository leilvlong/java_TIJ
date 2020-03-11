package com.github.java12;
import java.util.*;
/**
 * 泛型容器的(容器元素)合法向上转型:
 *      这种转型本质List还是Apple的,但是里面的元素引用确定了上边界
 *      另外对于这种不确定性,容器会拒绝添加任何元素,但即便如此,类型安全依然难以保障
 *      需要将一个泛型容器的元素做向上转型时,至少这种做法还是不错的
 *      它会根据上边界对元素进行统一管理
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
 * Java作为强类型语言意味着任何无法确定具体类型或持有大量对象时类型不统一都是非法的
 * 它会拒绝对任何非法类型的操作
 * 但是当有统一的上边界时: 即所持有的多个对象都可以转型为某种基类类型时是合法的
 * 因为Java不会维护继承体系中的向下关系,因此根本不会有真正的下边界:下边界也是利用了继承的特性与关键字\
 * 理清容器泛型边界的关键字; 同构
 */



/**
 * 逆变 超类型通配符:
 *      <? super Apple> Java并不会维护向下的关系这一原则不会改变
 *      与<? extends Apple>的使用有很多类似之处,
 *      不同的是,因为是下边界,所以确定了一定可以装 Apple :Apple一定可以统一向上转型
 *      因此编译器允许直接 apple.add(new Apple())及其导出类
 *      但是容器拒绝添加apple.add(new Fruit()),这对当前容器来说是未知的
 *      除非将容器引用转型为具体类型:它会通过Apple类上的extends关键字查找,验证这种转型的合法性
 *      同样,因为无法确定所有元素的上边界与具体类型,使用下边界类型引用时统一返回Object
 *      使用下边界具有读和取的属性
 *      容器可以向上转型,并且放入合适当前引用的元素
 *      但是容器向下转型时必须是使用超类型通配符
 */
class SuperTypeWildcards{
    public static void main(String[] args) {
        List<? super Apple> apple = new ArrayList<>();
        apple.add(new Apple());
        apple.add(new Jonathan());
        // apple.add(new Fruit()) //b


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


/**
 * 以上对上下边界通配符做了大量的案例总结,包括如何骗过编译器在运行时抛出异常
 * <? super Apple>与<? extends Apple>更像是两个尺子,做衡量使用
 *
 * <? super Apple>:
 *      所有的元素最终都会被验证是否是继承于它,编译器会帮助检查,根据元素的
 *      类上的继承关键字,因此可以添加。取元素时因为不确定元素类型,基于java
 *      万物基于Object,一律按Object算,是能存,不易操作的边界通配符标识
 *      准确来说是规范类型体系的边界通配符
 *
 * <? extends Apple>:
 *     与以上描述刚好相反,会验证所有添加的元素是否是它的子类,但是检查标准不是
 *     元素，而是通配符泛型本身，因为无法维护向下继承关系，因此实际无法检查。
 *     取元素时因为确定都可以最终转型为标识的泛型，因此取元素时会转为该类型，
 *     是易操作而不易存的边界通配符标识
 *     准确来说是规范行为与数据的边界通配符
 *     若要向其添加元素，可对容器类型边界具体转换，该转换由编译器通过继承关键字
 *     实现，再向其添加元素（java的类型转换都由编译器去验证合法性）
 *
 *
 *
 */
