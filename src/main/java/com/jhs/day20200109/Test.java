package com.jhs.day20200109;

import java.util.Scanner;

/**
 *
 * 判定两个指定的字符串是否异构同质；异构同质的定义为：一个字符串的字符重新排列后，能变成另一个字符串
 * @author zhangxinhe
 * @date 2020/1/9 13:22
 */
public class Test {



    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        String[] str = scanner.nextLine().split(" ");

        String a = str[0];
        String b = str[1];


        int s = yihuo(a) ^ yihuo(b);

        System.out.println(s);
        if(s != 0){
            System.out.println("false");
        }else {
            System.out.println("true");
        }

    }


    public static int yihuo(String a){
        char[] chars = a.toCharArray();
        int length = chars.length;
        int s = 1;
        for(int i = 0; i< length;i++){
            s =  (s ^ chars[i]);
        }
        return s;
    }
}
