package com.ndevaki.concurrency.forkJoinFramework;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

class SimpleForkJoinAction extends RecursiveAction{
    int id;
    public SimpleForkJoinAction(int id){
        this.id=id;
    }

    @Override
    protected void compute() {
        if(id>100){
            SimpleForkJoinAction action1=new SimpleForkJoinAction(id/2);
            SimpleForkJoinAction action2=new SimpleForkJoinAction((id/2));
            action1.fork();
            action2.fork();
        }else{
            System.out.println("Small size input received");
        }
    }
}

public class RecursiveTaskExample {
    public static int[] nums=null;

    public static void main(String[] args){
        ForkJoinPool pool=new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        pool.invoke(new SimpleForkJoinAction(1002));
      //  pool.
    }

}
