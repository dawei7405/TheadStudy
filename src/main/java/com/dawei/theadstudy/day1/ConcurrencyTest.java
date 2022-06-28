package com.dawei.theadstudy.day1;

import com.dawei.theadstudy.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @ClassName ConcurrencyTest
 * @Description TODO
 * @Author weirongsheng
 * @Date 2022/6/29 0:00
 * @Version 1.0
 **/
@Slf4j
@NotThreadSafe
public class ConcurrencyTest {
    //并发的线程数
    private static int threadTotal=2000;
    //请求总数
    private static int clientTotal = 5000;

    private static long count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        long start = System.currentTimeMillis();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch= new CountDownLatch(clientTotal);
        for (int i= 0 ;i< clientTotal;i++){
            executorService.submit(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error(" InterruptedException",e);
                }
                countDownLatch.countDown();
            });
        }

//        CountDownLatch最重要的方法是countDown()和await()，前者主要是计数减一，后者是等待计数到0，如果没有到达0，就继续阻塞等待。
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println(count);
        System.out.println("-------Totaltime------"+(end-start));

    }

    public static   void add(){
        count++;
    }


}
