package com.ndevaki.concurrency.casestudy.studentsLibrary;

import com.ndevaki.concurrency.utils.RandomTimeGenerator;

public class Student implements Runnable {
    int id;

    public Student(int id){
        this.id=id;
    }

    @Override
    public void run() {
        while (true) {
            int bookId = RandomTimeGenerator.getInstance().getWaitingTime() % 5;
            try {
                if (BooksManagement.getInstance().getBook(bookId)) {
                    //read book
                    System.out.println(id + " is reading book " + bookId);
                    Thread.sleep(RandomTimeGenerator.getInstance().getWaitingTime() % 100);
                    BooksManagement.getInstance().returnBook(bookId);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
