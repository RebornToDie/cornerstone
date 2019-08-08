package com.example.cornerstone.test.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class LogbackTest {

    private static Logger logger = LoggerFactory.getLogger(LogbackTest.class);


    public static void main(String[] args) {
        final AtomicInteger aLoggertomicInteger = new AtomicInteger();
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        printLog(aLoggertomicInteger.incrementAndGet());
                    }
                }
            }).start();
        }

    }

    private static void printLog(int i) {
        logger.error("=========" + i + "print debug log. thread is " + Thread.currentThread());
        logger.info("=========" + i + "print debug log." + Thread.currentThread());
    }
}
