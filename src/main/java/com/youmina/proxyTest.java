package com.youmina;

import com.youmina.model.TraceHandel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by xinhezhang on 2017/12/19.
 */
public class proxyTest
{
    public static void main(String[] args){
        Object[] elements = new Object[1000];
        for(int i=0;i<elements.length;i++){
            i = i + 1;
            Integer value = i;
            InvocationHandler handler = new TraceHandel(value);
            elements[i] = Proxy.newProxyInstance(null,new Class[]{Comparable.class},handler);
        }
        Integer key = new Random().nextInt(elements.length) + 1;
        System.out.println(key);
        int result = Arrays.binarySearch(elements,key);
        if(result >0){
            System.out.println(elements[result]);
        }
    }

}
