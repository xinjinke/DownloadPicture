package com.youmina.model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by xinhezhang on 2017/12/19.
 */
public class TraceHandel implements InvocationHandler {

    private Object target;
    public TraceHandel(Object t){
        target = t;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(target);
        System.out.print("."+ method.getName()+"(");
        if(args!=null){
            for(int i = 0;i<args.length;i++){
                System.out.println(args[i]);
                if(i<args.length-1) System.out.println(",");
            }
        }
        System.out.println(")");
        return method.invoke(target,args);
    }
}