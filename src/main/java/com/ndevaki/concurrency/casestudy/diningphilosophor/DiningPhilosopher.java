package com.ndevaki.concurrency.casestudy.diningphilosophor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosopher {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor= Executors.newFixedThreadPool(5);
        Lock[] locks=new Lock[5];
        for(int i=0;i<5;i++) {
            locks[i] = new ReentrantLock(true);
        }
        for(int i=0;i<5;i++) {
            int left=i;
            int right=i-1>=0?i-1:4;
            executor.execute(new Philosopher(locks[left],locks[right],i));
        }
        Thread.sleep(10000000);
        executor.shutdown();
    }
}
