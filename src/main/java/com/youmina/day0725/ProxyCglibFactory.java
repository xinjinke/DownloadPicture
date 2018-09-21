package com.youmina.day0725;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib子类代理工厂
 * @author zhangxinhe
 * @date 2018/7/25 16:11
 */
public class ProxyCglibFactory  implements MethodInterceptor {


    private Object target;

    public ProxyCglibFactory(Object target){
        this.target  = target;
    }

    public Object getProxyInstance(){
        Enhancer en = new Enhancer();
        en.setSuperclass(target.getClass());
        en.setCallback(this);
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事务。。。");
        Object returnValue = method.invoke(target,objects);
        System.out.println("提交事务。。。");
        return returnValue;
    }
}
