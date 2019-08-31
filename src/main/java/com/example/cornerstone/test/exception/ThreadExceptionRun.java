package com.example.cornerstone.test.exception;


import java.util.HashMap;
import java.util.Hashtable;

public class ThreadExceptionRun implements Runnable {
    @Override
    public void run() {
        int i = Integer.parseInt("sss");
        System.out.println(i);
    }

    public static void main(String[] args){
        try {
            Thread thread = new Thread(new ThreadExceptionRun());
            thread.setUncaughtExceptionHandler(new ThreadException());
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap hashMap = new HashMap<>();
        Hashtable hashtable = new Hashtable();

    }

}

