package com.youmina.day0723;

/**
 * @author zhangxinhe
 * @date 2018/7/23 11:06
 */
public class Test {

    public static void main(String[] args){

        Context context = new Context(new ConcreteStrategyA());
        context.contectInterface();
    }
}
