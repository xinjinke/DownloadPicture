package com.youmina.day0719;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author zhangxinhe
 * @date 2018/7/19 09:51
 */
public class Test {

    private final static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args){
       ShapeFactory shapeFactory = new ShapeFactory();

       Shape shape1 = shapeFactory.getShape("RECTANGLE");
       shape1.draw();

       Shape shape2 = shapeFactory.getShape("SQUARE");
       shape2.draw();

       String a = "hello";
       String b = a.intern();

        System.out.println(a == b);
        String c = "a"+"b";

        String d = "a";

        String e = "b";

        String s = d + e;

        logger.info("c等于s:{},c={},s={}",c==s,c,s);

        ThreadLocal local = new ThreadLocal();

        printDate();
        s.substring(1,3);

    }


    public static void printDate(){
        Date date=new Date();
        //c的使用
        System.out.printf("全部日期和时间信息：%tc%n",date);
        //f的使用
        System.out.printf("年-月-日格式：%tF %tT%n",date,date);
        //d的使用
        System.out.printf("月/日/年格式：%tD%n",date);
        //r的使用
        System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n",date);
        //t的使用
        System.out.printf("HH:MM:SS格式（24时制）：%tT%n",date);
        //R的使用
        System.out.printf("HH:MM格式（24时制）：%tR",date);
    }
}
