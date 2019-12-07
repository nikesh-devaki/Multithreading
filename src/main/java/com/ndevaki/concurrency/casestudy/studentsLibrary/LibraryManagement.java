package com.ndevaki.concurrency.casestudy.studentsLibrary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LibraryManagement {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService= Executors.newFixedThreadPool(5);
        for(int i=0;i<5;i++){
            executorService.execute(new Student(i));
        }
        Thread.sleep(10000000);
        executorService.shutdown();
    }
}
