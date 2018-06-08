package com.youmina.day0606;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxinhe
 * @date 2018/6/6 09:31
 */
public class TestThread {

    public static void main(String[] args) throws InterruptedException {

        List<Thread> threads = new ArrayList<>();
        Thread thread = null;
        for(int i = 0;i<3000;i++){
            thread = new Thread(new ThreadDemo());
            thread.setName("thread_"+i);
            threads.add(thread);
        }
        //finish takes 23s
        for(Thread th : threads){
            th.start();
        }

    }
}
