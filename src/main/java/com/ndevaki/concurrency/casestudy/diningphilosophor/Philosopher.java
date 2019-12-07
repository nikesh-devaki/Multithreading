package com.ndevaki.concurrency.casestudy.diningphilosophor;

import com.ndevaki.concurrency.utils.RandomTimeGenerator;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

class Philosopher implements Runnable{
    int index;
    Lock leftSpoon;
    Lock rightSpoon;

    public Philosopher(Lock leftSpoon,Lock rightSpoon, int index){
        this.leftSpoon=leftSpoon;
        this.rightSpoon=rightSpoon;
        this.index=index;
    }

    public boolean acquireLockOnLeft() throws InterruptedException {
        return leftSpoon.tryLock(100,
                                    TimeUnit.MILLISECONDS);
    }

    public boolean acquireLockOnRight() throws InterruptedException {
        return rightSpoon.tryLock(100,
                TimeUnit.MILLISECONDS);
    }

    public void releaseLockonLeft(){
        leftSpoon.unlock();
    }

    public void releaseLockonRight(){
        rightSpoon.unlock();
    }

    @Override
    public void run() {
        System.out.println("runnning thread "+index);
        while(true){
            try {
                //Think for a while
                Thread.sleep(RandomTimeGenerator.getInstance().getWaitingTime());
                //acquire lock on spoons
                if(acquireLockOnLeft()){
                   if(acquireLockOnRight()){
                       //Eat for a while
                       System.out.println(index+" eating");
                       Thread.sleep(RandomTimeGenerator.getInstance().getWaitingTime());
                       releaseLockonRight();
                    }
                    //release locks
                    releaseLockonLeft();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}