package com.youmina.day0712;

/**
 * @author zhangxinhe
 * @date 2018/7/12 11:04
 */
public class DeadThreadDemo {

    private static String A = "A";
    private static String B ="B";

    public static void main(String[] args){
        new DeadThreadDemo().deadLock();
    }

    private void deadLock(){
        Thread thread1= new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (A){
                    try {
                        Thread.currentThread().sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println("1");
                    }
                }

            }
        });
        Thread thread2= new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (B){
                    try {
                        Thread.currentThread().sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    synchronized (A){
                        System.out.println("2");
                    }
                }

            }
        });
        thread1.start();
        thread2.start();
    }
}
