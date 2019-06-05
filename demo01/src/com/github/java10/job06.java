package com.github.java10;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Random;

/*
格式化输出;
    [width] 格式化所占宽度(默认会使用空格填充不足部分),默认右对齐,可以使用 -改变为左对齐
    [.precision]精度指定 不适用于整数 ,应用于字符串时表截取长度输出
    conversion


 */

public class job06 {
    public static void main(String[] args) {
        Receipt receipt = new Receipt();
        receipt.printTitle();
        receipt.print("Jack's Magic Beans", 4, 4.25);
        receipt.print("Princess peas", 3, 5.1);
        receipt.print("Three Bears Porridge", 1, 14.29);
        receipt.printTotal();
    }
}


class Receipt{

    private Integer priceFormat = 10;

    private final String formatStrint = "%-15s %5s";
    private double total = 0;

    private Formatter f = new Formatter(System.out);

    public void printTitle(){
        f.format(formatStrint+" %"+priceFormat+"s\n","Item","Qty","Price");
        f.format(formatStrint+" %"+priceFormat+"s\n","----","---","-----");
    }

    public void print(String name,int qty, double price){
        f.format("%-15.15s %5d %"+priceFormat+".2f\n",name,qty,price);
        total+=price;
    }

    public void printTotal(){
        f.format(formatStrint+" %"+priceFormat+".2f\n", "Tax","",total*0.06);
        f.format(formatStrint+" %"+priceFormat+"s\n","","","-----");
        f.format(formatStrint+" %"+priceFormat+".2f\n","Total","",total*1.06);
    }
}


