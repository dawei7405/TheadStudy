package com.dawei.theadstudy.day1;

/**
 * @ClassName dd
 * @Description TODO
 * @Author weirongsheng
 * @Date 2022/6/29 0:21
 * @Version 1.0
 **/

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 模拟100米跑步，5名选手都准备好了，只等裁判员一声令下，所有人同时开跑
 */
public class CountDownLatchDemo2 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i+1;
            executorService.submit(() -> {
                System.out.println("No." + no + "，准备完毕，等待发令枪");
                try {
                    countDownLatch.await();
                    System.out.println("No." + no + "，开始跑步");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        //裁判员检查发令枪......
        Thread.sleep(5000);
        System.out.println("发令枪响，比赛开始......");
        countDownLatch.countDown();
    }
}
