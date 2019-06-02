package com.github.java09;

/*
以下案例在TIJ中被称为异常信息丢失:
    作者提到以后的版本可能会修复此类问题,然而JDK8并没有修复
    这或许并不是一个问题(系统级的bug),究其原因
    1.在out的try作用域内又划分了一块inner的try作用域
    2.每个try的作用域都是单独的
    3.在inner的try中若不对异常处理,外围try根本不会察觉到这个异常信息
    4.换言之,每个try作用域都应做他们该做的事,jvm会默认程序员已经处理过
    5.有try就必须要有catch,只有使用finally时是例外
    6.每个catch只能捕获一个异常,剩余没有被捕获的异常只能由下一个离他最近的catch捕获
    7.每一个异常都由离他最近的catch捕获
    8.外围try会捕获内围try的异常,内类try无法捕获外围try的异常
        因为内围try在外围try的作用域下,而外围try不在内围try的作用域中
    9.try.finally若没有catch则代表异常信息已经不重要了:
      因为它认为使用finally语句的程序员已经意识到了这种问题, 即:
            不管异常是否发生,我都已经做好了最坏的打算,
            正是因为已经做了最坏的打算,所以在finally中有没有异常信息已经不重要了
            这也是为什么finally为什么除了关机什么都无法阻止他运行的原因

    10.每一份try catch finally是成套的,一体的,尽管作用域不同
    11.抛在方法上的异常必须得到处理

以下有对于TIJ对于这种异常丢失的案例的各种翻版来对以上特性佐证
java的特性很难和作用域区分开来单独来解释

异常信息丢失的原因通过以上分析已经得到:
    编译器认为你对异常做了处理,编译允许通过
    而这类异常在被catch时,catch又只能捕获一个
    此时程序员和编译器都犯了一个错误:
       编译器认为程序员了异常问题
       而程序员处理力度又不够

针对以上特性的解决方案建议:
    不要嵌套使用try catch, 尤其是有try finally语句时
    即便嵌套,也要将 try catch写全 即:
        考虑到每一个可能出现的异常信息而:
            try ctacth finally
            try catch finally
            try catch catch catch ....finally
            防止错误信息丢失难以定位程序bug
java的开发团队显然更认为这种错误应该由开发人员自己想办法通过特性处理


 */
public class job12 {
    public static void main(String[] args) throws VeryImportantException, HohumException {
        //fun1();
        //fun2();
        //fun3();
        //fun4();
        fun5();
        //fun6();
    }

    /**
     * TIJ所描述的异常丢失信息案例
     * 输出捕获的异常的信息:
     *      A Hohum Exception
     */
    public static void fun1(){
        try{
            LostMassage lm = new LostMassage();
            try{
                lm.fun();
            } finally {
                lm.dispose();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * TIJ所描述的异常丢失信息案例
     * 将其小幅度修改后得到的异常信息
     *     A very Important Exception
     */
    public static void fun2(){
        try{
            LostMassage lm = new LostMassage();
            try{
                lm.fun();
            } finally {
                //lm.dispose();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * 两个catch都有输出语句 但是只有一条输出语句:
     *                      A very Important Exception
     * 此时外围try中的异常如果没猜错应该是null
     */
    public static void fun3(){
        try{
            LostMassage lm = new LostMassage();
            try{
                lm.fun();
            }catch (Exception e){
                System.out.println(e);
            }
            finally {
                //lm.dispose();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * 对fun3佐证:
     *      真是奇怪,out exception既不为null也不会产生任何信息
     *      同时也不会产生空指针异常
     * 对是否为null都进行判断结果没有一条输出信息
     * 这至少不会推翻之前的全部结论
     */
    public static void fun4(){
        try{
            LostMassage lm = new LostMassage();
            try{
                //lm.fun();
            }catch (Exception e){
                if(e != null){
                    System.out.println("inner Exception is not null");
                }
                if (e == null){
                    System.out.println("inner Exception is null");

                }
                System.out.println(e);
            }
            finally {
                //lm.dispose();

            }
        }catch (Exception e){
            if(e != null){
                System.out.println("out Exception is not null");
            }
            if (e == null){
                System.out.println("out Exception is null");

            }
            System.out.println(e);
        }

    }

    /**
     *  输出语句:
     *      finally exception:A Hohum Exception
     *      outException: A very Important Exception
     *  可以看到 finally中的try作用域出现的异常成功的被捕获
     *  而out try的作用域也成功捕获到了丢失的异常信息
     *  究其原因还是:
     *      每一个异常都由离他最近的catch捕获
     *      每一个catch都会捕获离他最近的异常
     *      finally中的try作用域出现的异常成功的被捕获
     *      所以剩余的异常有了可以被捕获的空间
     */
    public static void fun5(){
        try{
            LostMassage lm = new LostMassage();
            try{
                lm.fun();
            } finally {
                try{
                    lm.dispose();
                }catch (Exception e){
                    System.out.println("finally exception:" + e);
                }
            }

            //throw new RuntimeException("我错了"); //异常信息再度丢失
        }catch (Exception e){
            System.out.println("outException: "+ e);
        }
    }


    /**
     * 抛在方法上的异常必须得到处理
     * 此时要么必须写catch捕获要么在方法上抛出异常供编译器检查
     */
    public static void fun6() throws VeryImportantException, HohumException {
        LostMassage lm = new LostMassage();
        try{
            lm.fun();
        } finally {
            lm.dispose();
        }

    }



}


class VeryImportantException extends Exception{
    @Override
    public String toString() {
        return "A very Important Exception";
    }
}


class HohumException extends Exception{
    @Override
    public String toString() {
        return "A Hohum Exception";
    }
}


class LostMassage{

    void fun() throws VeryImportantException{
        throw new VeryImportantException();
    }

    void dispose() throws HohumException{
        throw new HohumException();
    }


}