package com.github.java11;


import com.github.typeinfo.pets.*;

import java.util.HashMap;

/*
1)传统的类型转换（object），若出现异常则抛出ClassCastException
2)代表对象的类型的Class对象（Class<T>）,通过查询Class对象可以获取运行时所需的信息
3)使用instanceof判断,会返回一个布尔值,但这并非一个好的方式
 */
public class job08 {
    public static void main(String[] args) {
        PetCount.countPets(new ForNameCreator());
    }
}


class PetCount{
    static class PetCounter extends HashMap<String,Integer>{
        public void count(String type){
            Integer quantity = get(type);
            if(quantity == null){
                put(type,1);
            }
            else{
                put(type,quantity+1);
            }
        }
    }

    public static void countPets(PetCreator creator){
        PetCounter counter = new PetCounter();
        for (Pet pet : creator.createArray(20)) {
            if (pet instanceof Pet){
                counter.count("Pet");
            }
            if (pet instanceof Dog){
                counter.count("Dog");
            }
            if (pet instanceof Mutt){
                counter.count("Mutt");
            }
            if (pet instanceof Pug){
                counter.count("Pug");
            }
            if (pet instanceof Cat){
                counter.count("Cat");
            }
            if (pet instanceof Manx){
                counter.count("Manx");
            }
            if (pet instanceof Rodent){
                counter.count("Rodent");
            }
            if (pet instanceof Rat){
                counter.count("Rat");
            }
            if (pet instanceof Mouse){
                counter.count("Mouse");
            }
            if (pet instanceof Hamster){
                counter.count("Hamster");
            }
        }
        System.out.println(counter);
    }




}



