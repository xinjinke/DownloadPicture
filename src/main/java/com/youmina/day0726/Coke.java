package com.youmina.day0726;

/** 可樂
 * @author zhangxinhe
 * @date 2018/7/26 15:55
 */
public class Coke extends ColdDrink {
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 30.0f;
    }
}
