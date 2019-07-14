package com.github.java12;


class Base{}


class Derived extends Base{}


interface OrdinaryGetter{
    Base get();
}


interface DerivedGetter extends OrdinaryGetter{
    Derived get();
}


class CovariantReturnType{
    void test(DerivedGetter d){
        Derived derived = d.get();
    }
}


interface GenericGetter<T extends GenericGetter<T>>{
    T get();
}


interface Getter extends GenericGetter<Getter>{
}


class GenericAndReturnTypes{
    void test(Getter g){
        Getter getter = g.get();
        GenericGetter gg = g.get();
    }
}


class OrdinarySetter{
    void set(Base base){
        System.out.println("OrdinarySetter set Base " );
    }
}


class DerivedSetter extends OrdinarySetter{

    /* // 此处能区分方法重写与重载
    void set(Base base){
        System.out.println("OrdinarySetter set Base " );
    }       */

    void set(Derived derived){
        System.out.println("DerivedSetter set derived " );
    }
}


class OrdinaryArguments{
    public static void main(String[] args) {
        Base base = new Base();
        Derived derived = new Derived();

        DerivedSetter setter = new DerivedSetter();
        setter.set(base);
        setter.set(derived);

    }
}


class GenericSetter<T>{
    void set(T arg){
        System.out.println("GenericSetter set " + arg.getClass().getSimpleName());
    }
}


class DerivedGS extends GenericSetter<Base>{
    void set(Derived derived){
        System.out.println("DerivedGS set " + derived.getClass().getSimpleName());
    }
}


class PlainGenericInheritance{
    public static void main(String[] args) {
        Base base = new Base();
        Derived derived = new Derived();

        DerivedGS derivedGS = new DerivedGS();

        derivedGS.set(base);
        derivedGS.set(derived);
    }

}


abstract class BaseGeneric <T extends BaseGeneric<T>>{

    abstract T get(T arg);

    public void fun(T arg){
        T t = get(arg);
        System.out.println(t.getClass().getSimpleName());
    }
}


class SubGeneric extends BaseGeneric<SubGeneric>{

    @Override
    SubGeneric get(SubGeneric arg) {
        return arg;
    }
}


class TestSubGeneric{
    public static void main(String[] args) {
        SubGeneric subGeneric = new SubGeneric();
        subGeneric.fun(subGeneric);
    }
}