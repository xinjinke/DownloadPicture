package com.youmina.handler;




import java.time.LocalDate;

/**
 * Created by xinhezhang on 2018/3/29.
 */
public class PairTest {

    public static void main(String[] args){
        LocalDate[] birthdays ={
                LocalDate.of(1994,8,10),
                LocalDate.of(1999,10,9),
                LocalDate.of(2000,10,11),
                LocalDate.of(1989,2,13)
        };
       Pair<LocalDate> mm = ArrayAlg.minMax(birthdays);

        System.out.println(mm.getFirst());
        System.out.println(mm.getSecond());

       Integer[] ints ={
               1,3,5,10,35,0,-3
       };
        Pair<Integer> ii = ArrayAlg.minMax(ints);



        System.out.println(ii.getFirst());
        System.out.println(ii.getSecond());


        String[] strs ={"2s","11","20","s1"};

        Pair<String> ss = ArrayAlg.minMax(strs);

        System.out.println(ss.getFirst());
        System.out.println(ss.getSecond());



    }

}



