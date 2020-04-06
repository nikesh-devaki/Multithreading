package com.ndevaki.concurrency.casestudies;

import java.util.Random;

public class VaultTheifSimulation {
    static final Vault vault=Vault.getInstance();
    //Vault
    static class Vault{
        int password;
        private Vault(){
            password= (new Random()).nextInt(100);
            System.out.println("password is "+password);
        }
        //password check
        boolean unlockVault(int inputPassword){
            return inputPassword==password;
        }

        public static Vault getInstance(){
            Vault v=new Vault();
            return v;
        }

    }
    //Police simulation

    //Theives simulation
    static class Theif extends Thread{
        @Override
        public void run(){
            while(true) {
                int attemptPassword = (new Random()).nextInt(100);
                try {
                    this.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(this.getName()+" interrupted");
                }
                boolean result = vault.unlockVault(attemptPassword);
                System.out.println(attemptPassword + (result? "PasswordMatched" :" password did not match"));
                if(result){
                    break;
                }
            }
        }

    }

    public static void main(String[]  args){
        Thread[] theives=new Theif[5];
        for(int i=0;i<theives.length;i++){
            theives[i]=new Theif();
            theives[i].start();
        }
    }
}
