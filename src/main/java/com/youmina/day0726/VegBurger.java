package com.youmina.day0726;

/**
 * 蔬菜漢堡
 * @author zhangxinhe
 * @date 2018/7/26 15:52
 */
public class VegBurger extends Burger {
    @Override
    public String name() {
        return "veg Burger";
    }

    @Override
    public float price() {
        return 25.0f;
    }
}
