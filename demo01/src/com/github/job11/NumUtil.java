package com.github.job11;


/*
/*
移位符 >>  << :
    二进制位右移左移 ,后面跟的参数是位数
    左移三位 n<<3相当于:
        n*2*2*2
    右移三位 n>>3相当于:
        n/2/2/2
    n>>0则相当于不位移
32位int:
    MAX: 2147483647:
        1111111111111111111111111111111 长度为31bits
    MIN: -2147483648:
        -10000000000000000000000000000000 不算符号-为32位
这个区间是一个协调的区间,计算机会补码计算二进制:
    二进制使用最高位表示符号位，用1表示负数，用0表示正数
    即:2147483647的二进制前面默认有个0,但0是false所以不显示
    补码表示就是在原码(二进制)表示的基础上取反(将0变为1，1变为0)然后加1(表示这是一个负数)

 */


public class NumUtil {

    private static final int[] ascll = ascllInit();

    private static int[] ascllInit(){
        int[] ascll = new int[32];
        for (int i = ascll.length-1,y = 0; i>=0; i--,y++) {
            ascll[i] = 1<<y;
        }
        return ascll;
    }


    /**
     * 获取二进制字符串
     * @param num
     * @return
     */
    public static String getBinary(int num){
        if (0 == num){
            return "0";
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (true){
            if (num == 0){
                break;
            }
            stringBuilder.append(num%2);
            num = num/2;
        }
        String s = stringBuilder.reverse().toString();
        return s.matches("[1][-].*") ? s.replaceAll("-","").replaceFirst("","-"):s;
        //return 0>num ? s.replaceAll("-","").replaceFirst("","-"):s;
    }


    public static void checkBinaryString(String binaryString){
        if (! binaryString.matches("([-][1][0-1]*)|[0-1]*|0")){
            throw new RuntimeException("is not binary string");
        }

        boolean flag = binaryString.contains("-") ?
                (binaryString.replaceAll("-", "").length()<=ascll.length ?
                        (binaryString.length()==ascll.length+1 ?
                                !binaryString.matches("-1.*[1].*"):true):false)
                : binaryString.length()<ascll.length;

        if (!flag){
            throw new RuntimeException("is binary string, but string too long");
        }


    }

    /**
     * 将二进制字符串转为10进制数字
     * @param binaryString
     * @return
     */
    public static int getDecimal(String binaryString){
        checkBinaryString(binaryString);

        char[] binarys = new char[ascll.length];
        boolean flag = false;
        char[] chars;
        if (binaryString.matches("-.*")){
            chars = binaryString.replaceAll("-", "").toCharArray();
            flag = true;
        }else{
            chars = binaryString.toCharArray();
        }
        System.arraycopy(chars,0,binarys,binarys.length-chars.length,chars.length);

        int count = 0;
        for (int i = 0; i < binarys.length; i++) {
            if ('1' == binarys[i]) {
                count += ascll[i];
            }
        }
        return flag ? 0-count : count;
    }


    public static void main(String[] args) {

    }
}
