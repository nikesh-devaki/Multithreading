package com.ndevaki.concurrency;

public class SynchronizedExample {
    private static  int count=0;

    public static void getThread() throws InterruptedException {
        Thread t1= new Thread(new java.lang.Runnable(){

            @Override
            public void run() {
                for(int i=1;i<=200;i++) {
                    System.out.println(Thread.currentThread().getName());
                    ++count;
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
                    ++count;
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
