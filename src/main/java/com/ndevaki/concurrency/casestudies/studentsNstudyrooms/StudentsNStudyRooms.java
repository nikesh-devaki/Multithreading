package com.ndevaki.concurrency.casestudies.studentsNstudyrooms;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class StudentsNStudyRooms {
    static class StudyRoom{
        final static Semaphore lock=new Semaphore(10,true);

        public static void getAccess() throws InterruptedException {
            lock.acquire();
            Thread.sleep(1000);
            lock.release();
        }
    }

    static class Student implements Runnable{

        @Override
        public void run() {
            long startTime=System.currentTimeMillis();
            try {

                StudyRoom.getAccess();
                System.out.println("Got access in "+(System.currentTimeMillis()-startTime));
            } catch (InterruptedException e) {
                System.out.println("Access canceled in"+(System.currentTimeMillis()-startTime));
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService=Executors.newCachedThreadPool();
        while(true){
            executorService.execute(new Student());
           Thread.sleep(300);
        }

    }
}
