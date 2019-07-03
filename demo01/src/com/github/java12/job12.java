package com.github.java12;

import java.util.HashMap;
import java.util.Map;

public class job12 {
    public static void main(String[] args) {

    }
}

/**
 * @param <T>
 *     它只是个占位符,不能用来做类型信息比较
 *     前文有一点遗漏: 在给持有泛型的类“标识”后，
 *     编译期对持有泛型类的边界处也会做类型检查,检查的类型信息根据标识来
 *     因此想要做类型判断会稍微有点麻烦:只能根据实体对象来做判断
 */
class Erased<T>{
    T obj;
    /*
    public Erased(String obj) {
        if (obj instanceof T) {
            this.obj = obj;
        }
    }       */

    public Erased(T obj) {
        if (obj instanceof String) {
            this.obj = obj;
        }
    }

    public static void main(String[] args) {
        Erased<Integer> erased = new Erased<>(4);
        System.out.println(erased.obj);
    }
}


class Building{}

class House extends Building{}


/**
 * @param <T>
 *     尽管此处的Class对象不具备类型信息,但在运行时作为实体对象就不一样了
 *     同样Class对象提供了Api做类型判断
 */
class ClassTypeCapture<T>{
    Class<T> kind;

    public ClassTypeCapture(Class<T> kind) {
        this.kind = kind;
    }

    public boolean checkIsInstance(Object arg){
        return kind.isInstance(arg);
    }


    /**
     * 我算是明白新手为什么容器滥用继承了
     * 尤其是这种工厂容器中,滥用继承的问题会更大
     */
    private Map<String,Class<? extends Building>> map = new HashMap<>();

    public void addType(String typeName,Class<? extends Building> kind){
        map.put(typeName,kind);
    }

    public Building createNew(String typeName) throws IllegalAccessException, InstantiationException {
        Class<?> clazz = map.get(typeName);
        return (Building) clazz.newInstance();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        ClassTypeCapture<Building> capture = new ClassTypeCapture<>(Building.class);

        System.out.println(capture.checkIsInstance(new Building()));
        System.out.println(capture.checkIsInstance(new House()));


        capture.addType("building",Building.class);
        capture.addType("House",House.class);

        Building building = capture.createNew("building");
        Building house = capture.createNew("House");
        System.out.println(building);
        System.out.println(house);

    }
}