package com.ndevaki.concurrency.basics.examples;


class BackroundThreadProcess1 extends Thread{

    @Override
    public void run() {
        for(int i=1;i<10;i++){
            System.out.println((5*i)+" Thread "+Thread.currentThread().getName());
        }
    }
}

class BackroundThreadProcess2 extends Thread{

    @Override
    public void run() {
        for(int i=1;i<10;i++){
            System.out.println((3*i)+" Thread "+Thread.currentThread().getName());
        }
    }
}

public class ThreadExample {



    public static void main(String[] args){
        BackroundThreadProcess1 process1=(new BackroundThreadProcess1());
        BackroundThreadProcess2 process2=(new BackroundThreadProcess2());
        System.out.println("Main Thread "+Thread.currentThread().getName());
        process1.start();
        process2.start();

    }
}
