package com.github.java12;


import com.github.typeinfo.pets.Cat;
import com.github.typeinfo.pets.Dog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * oldStyleMethod的方法参数将接受原生List
 * 使其可以向狗中添加猫
 * Collections.checkedList() 将接受List与他的泛型参数
 * 即便是原生List也将拥有具体的类型标记
 * 向狗中添加猫将出现异常:ClassCastException
 * 这是一种向后兼容的手段
 * JAVA SE5向后兼容的手段
 */
class CheckedList{

    @SuppressWarnings("unchecked")
    static void oldStyleMethod(List proBadlyDogs){
        proBadlyDogs.add(new Cat());
    }

    public static void main(String[] args) {
        List<Dog> dog1 = new ArrayList<>();
        oldStyleMethod(dog1);

        try{
            List<Dog> dog2 = Collections.checkedList(dog1, Dog.class);
            oldStyleMethod(dog2);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
