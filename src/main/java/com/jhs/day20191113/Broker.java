package com.jhs.day20191113;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author zhangxinhe
 * @date 2019/11/13 15:32
 */
public class Broker {

    private final static int MAX_SIZE = 3;

    private static ArrayBlockingQueue<String> messageQueue = new ArrayBlockingQueue<String>(MAX_SIZE);

    public static void produce(String msg){
        if(messageQueue.offer(msg)){
            System.out.println("成功向消息处理中心投递消息："+msg+",当前暂存消息数量是："+messageQueue.size());
        }else {
            System.out.println("消息处理中心内暂存的消息已达到最大负荷，请稍后再试！");
        }
        System.out.println("==================================================");
    }

    public static String consumer(){
        String msg = messageQueue.poll();

        if(msg != null){
            System.out.println("已经消费消息："+msg+",当前暂存的消息数量是:"+messageQueue.size());
        }else {
            System.out.println("消息处理中心内没有消息可供消费！");
        }
        System.out.println("==================================================");
        return msg;
    }

}
