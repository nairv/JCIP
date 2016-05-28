package org.vineet.jcip;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Starter multithreading app using executors.
 * Created by vineet on 5/23/16.
 */
public class MultiThreading implements Runnable {
    private final int threadNumber;
    private static final Executor EXEC = Executors.newFixedThreadPool(5);
    public static AtomicInteger done = new AtomicInteger(0);

    public MultiThreading(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        System.out.println("ThreadNumber starting : "+ this.threadNumber);
        try{
            Random r = new Random();
            Thread.sleep(r.nextInt(10000));
        } catch (InterruptedException e) {
            System.out.println("Interrupted..");
        }
        System.out.println("Thread finishing: "+this.threadNumber);
        done.getAndAdd(1);
    }

    public static void main(String args[]) throws InterruptedException {

        MultiThreading[] m = new MultiThreading[5];
        int count = 0;
        for(MultiThreading thread: m) {
            thread = new MultiThreading(count++);
            EXEC.execute(thread);
        }
        while(done.get() != 5) {
            Thread.sleep(1000);
        }
        System.exit(0);
    }
}
