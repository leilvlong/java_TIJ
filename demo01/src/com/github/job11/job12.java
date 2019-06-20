package com.github.job11;
/*
instanceof与Class对象:
   都可以用来做类型判断,但是有着根本的不同:
        instanceof比较的是obj任意对象(显示的extends对象默认是同一类型):
            instanceof比较类型(对继承也考虑进去了,但所有类都是默认继承自obj的,
            所以可能会有歧义,我才指出显示的extends)
        而Class对象则只会单纯的 比较Class对象
            Class比较的是两个对象是否是同一个类的Class对象
        这在接口中一样适用

 */
public class job12 {
    public static void main(String[] args) {
        FamilyVsExactType.test(new Base());
        System.out.println("-------------------------------------------");
        FamilyVsExactType.test(new Derived());
        System.out.println("-------------------------------------------");
        FamilyVsExactType2.test(new DerivedInterface());
    }
}


class Base{}


class Derived extends Base{}


class FamilyVsExactType{
    static void test(Object obj){
        System.out.println("obj instanceof BaseClass: " + (obj instanceof Base) );
        System.out.println("BaseClass isInstance obj: " +Base.class.isInstance(obj) );

        System.out.println("obj instanceof DerivedClass: " + (obj instanceof Derived) );
        System.out.println("DerivedClass isInstance obj: " + Derived.class.isInstance(obj));

        System.out.println("obj.class == Base.class: " + (obj.getClass() == Base.class));
        System.out.println("obj.class == Derived.class: " + (obj.getClass() == Derived.class));

        System.out.println("obj.class equals Base.class: " + (obj.getClass().equals( Base.class)));
        System.out.println("obj.class equals Derived.class: " + (obj.getClass().equals( Derived.class)));

    }
}


interface BaseInterface{}


class DerivedInterface implements BaseInterface{}


class FamilyVsExactType2{
    static void test(Object obj){
        System.out.println("obj instanceof BaseInterfaceClass: " + (obj instanceof BaseInterface) );
        System.out.println("BaseInterfaceClass isInstance obj: " +BaseInterface.class.isInstance(obj) );

        System.out.println("obj instanceof DerivedInterfaceClass: " + (obj instanceof DerivedInterface) );
        System.out.println("DerivedInterfaceClass isInstance obj: " + DerivedInterface.class.isInstance(obj));

        System.out.println("obj.class == BaseInterface.class: " + (obj.getClass() == BaseInterface.class));
        System.out.println("obj.class == DerivedInterface.class: " + (obj.getClass() == DerivedInterface.class));

        System.out.println("obj.class equals BaseInterface.class: " + (obj.getClass().equals( BaseInterface.class)));
        System.out.println("obj.class equals DerivedInterface.class: " + (obj.getClass().equals( DerivedInterface.class)));

    }
}