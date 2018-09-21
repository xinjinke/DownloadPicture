package com.youmina.day0726;

/**
 * 冷飲
 * @author zhangxinhe
 * @date 2018/7/26 15:50
 */
public abstract class ColdDrink implements Item{

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();

}
