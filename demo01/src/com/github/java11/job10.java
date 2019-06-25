package com.github.java11;

import com.github.typeinfo.pets.Pet;

import java.util.*;

public class job10 {
    public static void main(String[] args) {
        TypeCounter typeCounter = new TypeCounter(Pet.class);
        for (Pet pet : Pets.createArray(20)) {
            typeCounter.count(pet);
        }
        System.out.println(typeCounter);
    }


    /**
     * Tij的章节提到了计数'
     * 于是便想到了这个计数排列
     * 要求;
     * 1.元素出现次数计数
     * 2.排序要求 元素出现次数升序,如果出现次数相同,元素升序
     */
    public static void fun(){
        List<Integer> nums = new ArrayList<>(Arrays.asList(
                1,2,3,4,5,6,7,8,9,1,2,3,4,5,6,7,8,9,9,1,2,3,4,5,1,2,3,4,5,6
        ));

        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer num : nums) {
            Integer integer = map.get(num);
            map.put(num,integer == null ? 1:integer+1);
        }

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        ArrayList<Map.Entry<Integer, Integer>> listEntries = new ArrayList<>(entries);
        listEntries.sort((o1, o2) -> {
            return o1.getValue() - o2.getValue() != 0 ? o1.getValue() - o2.getValue() : o1.getKey() - o2.getKey();
        });
        System.out.println(listEntries);


        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> integerIntegerEntry : listEntries) {
            linkedHashMap.put(integerIntegerEntry.getKey(),integerIntegerEntry.getValue());
        }
        System.out.println(linkedHashMap);
    }
}



class TypeCounter extends HashMap<Class<?>,Integer>{
    private Class<?> baseType;

    public TypeCounter(Class<?> baseType) {
        this.baseType = baseType;
    }

    public void count(Object obj){
        Class<?> type = obj.getClass();

        if (! baseType.isAssignableFrom(type)) {
            throw new RuntimeException("incorrect type : "+type+" . should bt type or subtype of" + baseType);
        }
        countClass(type);
    }

    private void countClass(Class<?> type){
        Integer quantity = get(type);
        put(type,quantity == null ? 1 : quantity+1);
        Class<?> superClass = type.getSuperclass();
        if(superClass != null && baseType.isAssignableFrom(superClass)){
            countClass(superClass);
        }
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("{");
        for (Entry<Class<?>, Integer> pair : entrySet()) {
            str.append(pair.getKey().getSimpleName());
            str.append(":");
            str.append(pair.getValue());
            str.append(",");
        }

        str.delete(str.length()-1,str.length());
        str.append("}");
        return str.toString();
    }
}