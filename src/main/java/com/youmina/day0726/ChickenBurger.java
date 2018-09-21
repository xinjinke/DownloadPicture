package com.youmina.day0726;

/** 鷄肉漢堡
 * @author zhangxinhe
 * @date 2018/7/26 15:54
 */
public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public float price() {
        return 50.5f;
    }
}
