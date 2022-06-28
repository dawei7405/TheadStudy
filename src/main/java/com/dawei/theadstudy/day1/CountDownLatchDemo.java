package com.dawei.theadstudy.day1;

/**
 * @ClassName d
 * @Description TODO
 * @Author weirongsheng
 * @Date 2022/6/29 0:21
 * @Version 1.0
 **/

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 工厂中，质检，5个工人检查，所有人都认为通过，才通过
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i+1;
            executorService.submit(() -> {
                try {
                    Thread.sleep(new Random().nextInt(10000));
                    System.out.println("NO." + no + "完成了检查");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        System.out.println("等待5个人检查完......");

        countDownLatch.await();
        System.out.println("所有人都完成了工作，等待进入下一环节");
    }
}
