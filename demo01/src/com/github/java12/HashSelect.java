package com.github.java12;


import java.util.HashMap;

class ArrayValue{
    private Object[] values;

    public ArrayValue() {
        this.values = new Object[10];
    }

    public ArrayValue(int length) {
        this.values = new Object[length];
    }

    public Object getValues(Object key ) {
        return values[key.hashCode()];
    }

    public void setValues(Object key,Object value) {
        this.values[key.hashCode()] = value;
    }
}


class HashKey{

    @Override
    public int hashCode() {
        return System.identityHashCode(this)>>18;
    }

    public static void main(String[] args) {
        ArrayValue arrayValue = new ArrayValue(10000);
        HashKey hashKey1 = new HashKey();
        HashKey hashKey2 = new HashKey();
        HashKey hashKey3 = new HashKey();
        HashKey hashKey4 = new HashKey();

        arrayValue.setValues(hashKey1,"hello");
        arrayValue.setValues(hashKey2,"word");
        arrayValue.setValues(hashKey3,"High");
        arrayValue.setValues(hashKey4,"Fuck");

        System.out.println(arrayValue.getValues(hashKey4));
        System.out.println(arrayValue.getValues(hashKey3));
        System.out.println(arrayValue.getValues(hashKey1));
        System.out.println(arrayValue.getValues(hashKey2));
    }
}
