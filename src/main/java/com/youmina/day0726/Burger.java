package com.youmina.day0726;

/**食物：漢堡包
 * @author zhangxinhe
 * @date 2018/7/26 15:43
 */
public abstract class Burger implements Item{

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
