package com.youmina.day0921;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangxinhe
 * @date 2018/9/21 15:10
 */
public class ConsumerProducerUsingBlockingQueue {
    private static ArrayBlockingQueue<Integer> buffer =
            new ArrayBlockingQueue<Integer>(100);

    public static void main(String[] args) {
        // Create a thread pool with two threads
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new ConsumerTask());
        executor.execute(new ProducerTask());
        executor.shutdown();
    }

    // A task for adding an int to the buffer
    private static class ProducerTask implements Runnable {
        public void run() {
            try {
                int i = 1;
                while (true) {
                    System.out.println("Producer writes：" + i);
                    buffer.put(i++); // Add any value to the buffer, say, 1
                    // Put the thread into sleep
                    Thread.sleep((int)(Math.random() * 1000));
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    // A task for reading and deleting an int from the buffer
    private static class ConsumerTask implements Runnable {
        public void run() {
            try {
                while (true) {
                    System.out.println("\t\t\tConsumer reads：" + buffer.take());
                    // Put the thread into sleep
                    Thread.sleep((int)(Math.random() * 1000));
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
