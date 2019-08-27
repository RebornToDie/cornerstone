package com.example.cornerstone.test.logback.test.exception;


public class ThreadException implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("An exception has been captured\n");
        System.out.printf("Thread:%s\n",t.getId());
        System.out.printf("Exception:%s:%s\n",e.getClass().getName(),e.getMessage());
        System.out.println("Stack Trace:");
        e.printStackTrace(System.out);
        System.out.printf("Thread status:%s\n",t.getState());
    }
}

