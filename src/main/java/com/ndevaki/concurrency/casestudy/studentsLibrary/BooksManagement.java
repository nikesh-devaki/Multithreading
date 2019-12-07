package com.ndevaki.concurrency.casestudy.studentsLibrary;

public class BooksManagement {
    Book[] books=new Book[5];
    private static BooksManagement booksManagement=null;
    private BooksManagement(){
        for(int i=0;i<5;i++){
            books[i]=new Book(i);
        }
    }

    public static synchronized BooksManagement getInstance(){
        if(booksManagement==null){
            booksManagement=new BooksManagement();
        }
        return booksManagement;
    }

    public boolean getBook(int i) throws InterruptedException {
        return books[i].acquireLock();
    }

    public void returnBook(int i){
        books[i].releaseLock();
    }
}
