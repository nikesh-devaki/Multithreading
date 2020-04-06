package com.ndevaki.concurrency.casestudies;

import java.util.ArrayList;

class Processor{
    ArrayList<Integer> list=new ArrayList<Integer>();
    int maxLimit=10;
    public void produce(Object object) throws InterruptedException {
        synchronized (object) {
            while (true) {
                if (list.size() == 10) {
                    object.wait();
                }
                if (list.size() < 10) {
                        list.add(1);
                        System.out.println(list.size()+" adding");
                        object.notify();
                }
            }
        }
    }

    public void consume(Object object) throws InterruptedException {
        synchronized (object) {
            while (true) {
                if (list.size() == 0) {
                    object.wait();
                }
                if (list.size() != 0) {
                        list.remove(0);
                        System.out.println(list.size()+" removing");
                        object.notify();
                }
            }
        }
    }
}
public class ProducerNConsumer {
    public static void main(String[] args) throws InterruptedException {
    Processor processor=new Processor();

    Object obj=new Object();
    Thread t1=new Thread(new java.lang.Runnable(){

        @Override
        public void run() {
            try {
                processor.produce(obj);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    Thread t2=new Thread(new java.lang.Runnable(){

        @Override
        public void run() {
            try {
                processor.consume(obj);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }


}
