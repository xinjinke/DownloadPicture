package com.youmina.day0723;

/**
 * @author zhangxinhe
 * @date 2018/7/23 11:00
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public void contectInterface(){
        strategy.strategyInterface();
    }

}
