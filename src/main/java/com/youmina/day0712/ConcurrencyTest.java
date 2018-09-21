package com.youmina.day0712;

/**
 * @author zhangxinhe
 * @date 2018/7/12 10:43
 */
public class ConcurrencyTest {

    private static final long count = 100000000l;

    public static void main(String[] args){
        concurrency();
        serial();
    }

    private static void concurrency(){
        long start = System.currentTimeMillis();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for(long i= 0;i<count;i++){
                    a+=5;
                }
            }
        });
        thread.start();
        int b = 0;
        for(int i= 0;i<count;i++){
            b--;
        }
        long time = System.currentTimeMillis()-start;

        System.out.println("并发执行concurrency :" +time+"ms,b="+b);
    }

    private static void serial(){
        long start = System.currentTimeMillis();
        int a = 0;
        for(long i=0;i<count;i++){
            a += 5;
        }
        int b = 0;
        for(int i= 0;i<count;i++){
            b--;
        }
        long time = System.currentTimeMillis()-start;
        System.out.println("串行concurrency :" +time+"ms,b="+b+",a="+a);
    }

}
