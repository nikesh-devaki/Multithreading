package com.ndevaki.concurrency;

import java.util.ArrayList;
import java.util.concurrent.*;

class Counter implements Callable<Integer>{

    private int id;

    public Counter(int id){
        this.id=id;
    }
    private  int sum(){
        return id*(id+1)/2;
    }
    @Override
    public Integer call() throws Exception {
        return sum();
    }
}
public class FutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> futuresList = new ArrayList<Future<Integer>>();
        for (int i = 1; i < 10; i++) {
            futuresList.add(executorService.submit(new Counter(i)));
        }
        for (Future<Integer> future : futuresList) {
            System.out.println(future.get()+" ");
        }
    }
}
