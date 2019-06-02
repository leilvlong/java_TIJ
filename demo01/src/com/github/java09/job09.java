package com.github.java09;


/*
异常链:
    在捕获一个异常后抛出另外一个异常,并保留上一个异常的信息
提供cause参数的基类(没有cause实现异常链会很麻烦):
    Error jvm虚拟机
    Exception
    RuntimeException
    使用其他异常类型需要异常链时则使用initCause方法
异常的抛出点:
    在throw 行
异常的定位点:
    在new 异常对象的行
 */



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;


public class job09 {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        DynamicFields df = new DynamicFields(3);
        System.out.println("fields:\n"+ df);

        try {
            df.setField("d", "A value for d");
            df.setField("number1", 47);
            df.setField("number2", 48);
            System.out.println("fields:\n"+ df);

            df.setField("d","A new value forb");
            df.setField("number3", 11);

            System.out.println("fields:\n"+ df);

            System.out.println("getFiled d: " + df.getField("d"));


            Object d = df.setField("d", null);
            System.out.println(d);

        }catch (NoSuchFieldException ee){
            ee.printStackTrace();
        } catch (DynamicFieldException e) {
            e.printStackTrace();
        }


    }

    @Deprecated
    public  void fun(){
        Object[][] objects = {{1,2},{3,4}};
        for (Object[] object : objects) {
            System.out.println(object[0]);
            System.out.println(object[1]);
            System.out.println(Arrays.toString(object));
        }
    }

    public static void fun2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Deprecated annotation = job09.class.getMethod("fun").getAnnotation(Deprecated.class);
        if (annotation != null){
            job09 job09 = job09.class.getDeclaredConstructor().newInstance();
            Method fun = job09.class.getMethod("fun");
            fun.invoke(job09,null);

        }
    }

}


class DynamicFieldException extends Exception{
}


class DynamicFields{
    private Object[][] fields;

    public DynamicFields(int initSize) {
        fields = new Object[initSize][2];
        for (int i = 0; i < initSize; i++) {
            fields[i] = new Object[]{null,null};
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Object[] field : fields) {
            result.append(field[0]);
            result.append(": ");
            result.append(field[1]);
            result.append("\n");
        }
        return result.toString();
    }

    private int hasField(String id){
        for (int i = 0; i < fields.length; i++) {
            if (id.equals(fields[i][0])){
                return i;
            }
        }
        return -1;
    }

    private int getFieldNumber(String id) throws NoSuchFieldException{
        int field = hasField(id);
        if (field==-1){
            throw new NoSuchFieldException();
        }
        return field;
    }

    private int makeField(String id){
        for (int i = 0; i < fields.length; i++) {
            if(fields[i][0] == null){
                fields[i][0] = id;
                return i;
            }
        }

        Object[][] tmp = new Object[fields.length+1][2];
        for (int i = 0; i < fields.length; i++) {
            tmp[i] = fields[i];
        }

        for(int i=fields.length; i<tmp.length;i++){
            tmp[i] = new Object[]{null,null};
        }
        fields =tmp;
        return makeField(id);
    }

    public Object getField(String id) throws NoSuchFieldException {
        return fields[getFieldNumber(id)][1];
    }

    public Object setField(String id, Object value) throws DynamicFieldException {
        if (value == null){
            DynamicFieldException dfe = new DynamicFieldException();
            dfe.initCause(new NullPointerException());
            throw dfe;
        }

        int fieldNumber = hasField(id);
        if (fieldNumber == -1){
            fieldNumber = makeField(id);
        }
        Object result = null;
        try {
            result = getField(id);
        }catch (NoSuchFieldException e){
            throw new RuntimeException(e);
        }
        fields[fieldNumber][1] = value;
        return result;
    }
}
