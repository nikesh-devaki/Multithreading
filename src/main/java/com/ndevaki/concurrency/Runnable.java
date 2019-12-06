package com.ndevaki.concurrency;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;

class ReativeDemo{
    static List<Integer> list= Arrays.asList(1,2,3,4,5,6,7,8,9);

    public static Observable getObservable(){
       return Observable.fromIterable(list)
                  .map(num->5*num);
    }
}
 class BackroundProcess1 implements java.lang.Runnable {

     @Override
    public void run() {
        for(int i=1;i<10;i++){
            System.out.println((5*i)+" Thread "+Thread.currentThread().getName());
        }
    }
}

 class BackroundProcess2 implements java.lang.Runnable {

     @Override
    public void run() {
        for(int i=1;i<10;i++){
            System.out.println((3*i)+" Thread "+Thread.currentThread().getName());
        }
    }
}

public class Runnable {



    public static void main(String[] args) throws InterruptedException {
        Thread process1=new Thread(new BackroundProcess1());
        Thread process2= new Thread(new BackroundProcess2());
        System.out.println("Main Thread "+Thread.currentThread().getName());
        process1.start();
        process2.start();
        //join is blocking operation.
        process1.join();
        process2.join();
        System.out.println("Both the tasks finished");

        ReativeDemo.getObservable().observeOn(Schedulers.io()).subscribe(System.out::println);
    }
}
