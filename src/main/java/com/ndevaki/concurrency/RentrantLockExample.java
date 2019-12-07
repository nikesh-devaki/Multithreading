package com.ndevaki.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RentrantLockExample {
    private static  int count=0;

    static Lock lock=new ReentrantLock(true);
    private static void increment(){
        lock.lock();
        count++;
        lock.unlock();
    }

    public static void getThread() throws InterruptedException {
        Thread t1= new Thread(new java.lang.Runnable(){

            @Override
            public void run() {
                for(int i=1;i<=200;i++) {
                    //  System.out.println(Thread.currentThread().getName());
                    increment();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2= new Thread(new java.lang.Runnable(){

            @Override
            public void run() {
                for(int i=1;i<=200;i++) {
                    // System.out.println(Thread.currentThread().getName());
                    increment();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }

    public static void main(String[] args) throws InterruptedException {
        getThread();


    }
}
