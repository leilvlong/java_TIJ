package com.github.java12;

import java.util.*;

/*
将泛型应用于Set中
 */

public class job06 {
    public static void main(String[] args) {
        Set<Watercolors> set1 = EnumSet.range(Watercolors.BRILLIANT_RED,Watercolors.VIRIDIAN_HUE);
        Set<Watercolors> set2 = EnumSet.range(Watercolors.CERULEAN_BLUE_HUE,Watercolors.BURNT_UMBER);

        System.out.println("union:" +SetS.union(set1,set2));
        System.out.println("intersection:" + SetS.intersection(set1,set2));


        System.out.println("set1: "+set1);
        System.out.println("set2: "+set2);

        System.out.println("difference:" + SetS.difference(set1,set2));
    }

    /**
     * 求交集
     * Set<Integer> ts1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
     * Set<Integer> ts2 = new HashSet<>(Arrays.asList(1, 2, 3, 6, 7, 8, 9, 5));
     */
    public static Set<Integer> fun1(Set<Integer> ts1, Set<Integer> ts2) {
        Set<Integer> ts3 = new HashSet<>();
        for (Integer int1 : ts1) {
            for (Integer int2 : ts2) {
                if (int1.equals(int2)) {
                    ts3.add(int1);
                    break;
                }
            }
        }

        return ts3;
    }

    /**
     * 求差集
     */
    public static Set<Integer> fun2(Set<Integer> ts1, Set<Integer> ts2) {
        Set<Integer> ts3 = new HashSet<>();
        Object[] objects = ts2.toArray();
        for (Integer int1 : ts1) {
            for (int i = 0; i < objects.length; i++) {
                if (int1.equals(objects[i])) {
                    break;
                }
                if (i == objects.length - 1) {
                    ts3.add(int1);
                }
            }
        }
        return ts3;
    }

    /**
     * 求双向差集
     */
    public static Set<Integer> fun3(Set<Integer> ts1, Set<Integer> ts2) {
        Set<Integer> integers = fun2(ts1, ts2);
        Set<Integer> integers1 = fun2(ts2, ts1);
        integers.addAll(integers1);
        return integers;
    }

}


class SetS {
    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> ts = new HashSet<>(a);
        ts.addAll(b);
        return ts;
    }

    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> ts = new HashSet<>(a);
        ts.retainAll(b);
        return ts;
    }

    public static <T> Set<T> difference(Set<T> superSet, Set<T> subSet) {
        Set<T> ts = new HashSet<>(superSet);
        ts.removeAll(subSet);
        return ts;
    }

    public static <T> Set<T> complement(Set<T> a, Set<T> b) {
        return difference(union(a, b), intersection(a, b));
    }
}

enum Watercolors {
    ZINC, LEMON_YELLOW, MEDIUM_YELLOW, DEEP_YELLOW, ORANGE,
    BRILLIANT_RED, CRIMSON, MAGENTA, ROSE_MADDER, VIOLET,
    CERULEAN_BLUE_HUE, PHTHALO_BLUE, ULTRAMARINE,
    COBALT_BLUE_HUE, PERMANENT_GREEN, VIRIDIAN_HUE,
    SAP_GREEN, YELLOW_OCHRE, BURNT_SIENNA, RAW_UMBER,
    BURNT_UMBER, PAYNES_GRAY, IVORY_BLACK
}
