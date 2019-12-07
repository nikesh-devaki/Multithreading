package com.ndevaki.concurrency.casestudy.studentsLibrary;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {
    int id;
    Lock lock=null;

    public Book(int id){
        this.id=id;
        lock=new ReentrantLock(true);
    }

    public boolean acquireLock() throws InterruptedException {
        return lock.tryLock(100, TimeUnit.MILLISECONDS);
    }

    public void releaseLock(){
        lock.unlock();
    }
}
