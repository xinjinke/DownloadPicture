package com.jhs.day20200311;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangxinhe
 * @date 2020/3/11 13:32
 */
@Getter
@Setter
public class Father implements Cloneable{
    private String name;

    private int age;

    private Son son;

    @Override
    protected Object clone() {
        try {

            Father clonFather = (Father) super.clone();
            clonFather.son = (Son) this.son.clone();
            return clonFather;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
