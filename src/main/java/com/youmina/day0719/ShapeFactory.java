package com.youmina.day0719;

/**
 * @author zhangxinhe
 * @date 2018/7/19 10:36
 */
public class ShapeFactory {

    public Shape getShape(String shape){
        if(shape == null){
            return null;
        }
        if(shape.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        }else if(shape.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }
}
