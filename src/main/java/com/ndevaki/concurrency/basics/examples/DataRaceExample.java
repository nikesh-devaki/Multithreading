package com.ndevaki.concurrency.basics.examples;

public class DataRaceExample {
    static XY xy=new XY();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new WriteWorkerThread();
        Thread t2= new ReadWorkerThread();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
    static class ReadWorkerThread extends Thread{
        public void run(){
            while(true) {
                xy.getData();
            }
        }
    }
    static class WriteWorkerThread extends Thread{
        public void run(){
            while(true) {
                xy.increment();
            }
        }
    }
    static class XY{
        volatile int x=0;
        volatile int y=0;

        public synchronized void increment(){
            x++;
            y++;
        }

        public synchronized void getData(){
            if(x<y){
                System.out.println("Data race occured"+x+" "+y);
            }
        }
    }
}
