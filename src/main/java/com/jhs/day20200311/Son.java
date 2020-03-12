package com.jhs.day20200311;

import lombok.Data;

/**
 * @author zhangxinhe
 * @date 2020/3/11 13:34
 */
@Data
public class Son implements Cloneable{
    private String name;

    private int age;

    @Override
    protected Object clone()  {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
