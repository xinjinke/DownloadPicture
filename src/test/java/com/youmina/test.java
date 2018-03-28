package com.youmina;

import java.util.Random;
import java.util.UUID;

/**
 * Created by xinhezhang on 2018/2/14.
 */
public class test {
    public static void main(String[] args){

        for(int i=0;i<4;i++){
            String str = UUID.randomUUID().toString().toUpperCase();
            System.out.println(str);
        }
        int x= -1;
        assert x>=0;
    }
}
