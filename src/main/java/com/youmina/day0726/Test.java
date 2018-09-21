package com.youmina.day0726;

/**
 * @author zhangxinhe
 * @date 2018/7/26 16:05
 */
public class Test {

    public static void main(String [] args){
        MealBuilder builder = new MealBuilder();

        Meal vegMeal = builder.prepareVegMeal();
        System.out.println("veg Meal");

        vegMeal.showItems();
        System.out.println("Total Cost : "+vegMeal.getCost());

        Meal nonVegMeal = builder.prepareNonVegMeal();
        System.out.println("\n\nnon-veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost : "+nonVegMeal.getCost());
    }
}
