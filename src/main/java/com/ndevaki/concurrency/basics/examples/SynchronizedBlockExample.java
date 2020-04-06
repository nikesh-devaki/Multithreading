package com.ndevaki.concurrency.basics.examples;

public class SynchronizedBlockExample {

    private static  int counter1=0;
    private static  int counter2=0;

    public static Object object1=new Object();
    public static Object object2=new Object();
    public static void increment1(){
        synchronized (object1){
            counter1++;
        }
    }
    public static void increment2(){
        synchronized (object2){
            counter2++;
        }
    }

    public static void getThread() throws InterruptedException {
        Thread t1= new Thread(new java.lang.Runnable(){

            @Override
            public void run() {
                for(int i=1;i<=200;i++) {
                    System.out.println(Thread.currentThread().getName());
                    increment1();
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
                    System.out.println(Thread.currentThread().getName());
                    increment2();
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
        System.out.println(counter1);
        System.out.println(counter2);
    }

    public static void main(String[] args) throws InterruptedException {
        getThread();


    }
}

