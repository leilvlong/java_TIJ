package com.github.job09;

/*
构造与清理2:
    在以下案例中 1 与 2相当简单,如果对象构造不能失败就不需要catch,
    因为他们的构造器并未声明抛出异常,如果无论如何都要需要清理就使用finally
    3 展示了如何处理那些些具有可以失败的构造器和需要清理的对象
    为了正确处理这种情况,得小心翼翼的try catch起来调用清理方法
    外围try依然是如果构造出现异常则构造失败,无需处理
    内围try则是在声明一个类的对象时假设出现异常或无论如何都需要清理之前创建的对象
    这就需要finally来执行清理操作
 */

public class job15 {

    public static void main(String[] args) {
        //1.
        NeedsCleanup nc1 = new NeedsCleanup();
        try{
        }finally {
            nc1.dispose();
        }

        //2.
        NeedsCleanup nc2 = new NeedsCleanup();
        NeedsCleanup nc3 = new NeedsCleanup();
        try{
        }finally {
            nc2.dispose();
            nc3.dispose();
        }

        //3.
        try{
            NeedsCleanup2 nc4 = new NeedsCleanup2();
            try{

                NeedsCleanup2 nc5 = new NeedsCleanup2();
                try{
                }finally {
                    nc5.dispose();
                }

            }catch (ConstructionException e){
            }finally {
                nc4.dispose();
            }

        }catch (ConstructionException e){
            System.out.println("ConstructionException");
        }


    }
}


class NeedsCleanup{
    private static long counter = 1;

    private final long id = counter++;

    public void dispose(){
        System.out.println("NeedsCleanup: " + id +" dispose");
    }
}


class ConstructionException extends Exception{

}


class NeedsCleanup2 extends NeedsCleanup{
    public NeedsCleanup2() throws ConstructionException{
    }
}


