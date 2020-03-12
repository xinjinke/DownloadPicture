package com.jhs.day20200311;

/**
 * @author zhangxinhe
 * @date 2020/3/11 13:35
 */
public class Test {
    public static void main(String [] args){


        Father fatherA = new Father();
        fatherA.setName("付清");
        fatherA.setAge(52);
        Son son = new Son();
        son.setAge(18);
        son.setName("香");
        fatherA.setSon(son);

        Father fatherB = (Father) fatherA.clone();

        System.out.println("fatherA == FatherB: "+(fatherA == fatherB));
        System.out.println("fatherA hash: "+fatherA.hashCode());
        System.out.println("fatherB hash: "+fatherB.hashCode());
        System.out.println("fatherA.son == fatherB.son: "+(fatherA.getSon() == fatherB.getSon()));
        System.out.println("father.son hash: "+fatherA.getSon().hashCode());
        System.out.println("fatherB.son hash: "+fatherB.getSon().hashCode());


    }
}
