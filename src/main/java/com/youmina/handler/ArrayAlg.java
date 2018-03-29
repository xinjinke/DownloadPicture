package com.youmina.handler;



/**
 * Created by xinhezhang on 2018/3/29.
 */
public class ArrayAlg {

    public static <T extends Comparable> Pair<T> minMax(T[] a){
        if(a == null || a.length == 0 ) return null;
        T min = a[0];
        T max = a[0];
        int length = a.length;
        for(int i = 1;i < length;i++){
            if(a[i].compareTo(max)>0) {
                max = a[i];
            }
            if(a[i].compareTo(min)<0){
                min = a[i];
            }
        }
        return new Pair<>(min,max);
    }
}
