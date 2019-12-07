package com.ndevaki.concurrency.utils;

import java.util.Random;

public class RandomTimeGenerator {

    private Random random=new Random(100);
    private static RandomTimeGenerator randomTimeGenerator=null;
    private RandomTimeGenerator(){
        Random random=new Random();
    }
    public static synchronized RandomTimeGenerator getInstance(){
        if(randomTimeGenerator==null){
            randomTimeGenerator= new RandomTimeGenerator();
        }
        return randomTimeGenerator;
    }
    public int getWaitingTime(){
       return random.nextInt(1000);
    }
}
