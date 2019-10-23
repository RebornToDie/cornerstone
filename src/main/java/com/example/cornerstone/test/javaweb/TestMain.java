package com.example.cornerstone.test.javaweb;

import org.springframework.util.StopWatch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author reborntodie
 * @date 2019/8/14 9:58
 */
public class TestMain {
    public void compareString(){
        String s = "";
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder = new StringBuilder();

    }

    public void compareList(){
        Vector v = new Vector();
        List list1 = new ArrayList();
        List list2 = new LinkedList();

    }

    public void testSynchronized() throws InterruptedException {
        printTimeInt();

    }

    public  synchronized void printTimeInt() throws InterruptedException {
        StopWatch sw = new StopWatch();
        sw.start("sleep1");
        Thread.sleep(1000);
        sw.stop();
        System.out.println("sleep1:"+sw.getLastTaskTimeMillis());
        sw.start("sleep2");
        Thread.sleep(1000);
        sw.stop();
        System.out.println("sleep2:"+sw.getLastTaskTimeMillis());
        System.out.println("totalTime:"+sw.getTotalTimeMillis());
        AbstractQueuedSynchronizer abstractQueuedSynchronizer = new AbstractQueuedSynchronizer() {
            @Override
            protected boolean tryAcquire(int arg) {
                return super.tryAcquire(arg);
            }
        };
    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        String path = "";
        //DigestUtils.md5Hex(new FileInputStream(path));


    }
}
