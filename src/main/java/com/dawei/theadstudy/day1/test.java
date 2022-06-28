package com.dawei.theadstudy.day1;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @ClassName test
 * @Description TODO
 * @Author weirongsheng
 * @Date 2022/6/28 0:47
 * @Version 1.0
 **/
public class test {
    private static int threadTotal=200;
    private static int clientTotal = 5000;
    private static long count = 0;
    private static Map map = new ConcurrentHashMap();

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newCachedThreadPool();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(200, 200, 20L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5000));
        Semaphore semaphore = new Semaphore(threadTotal);
        for (int i=0;i<clientTotal;i++){
//            executorService.execute(()->{});
            int finalI = i;
            executorService.submit(()->{
                try {
                    semaphore.acquire();
                    add(finalI);
                    semaphore.release();

                }catch (Exception e){

                }
            });
        }
//        List<Runnable> list = executorService.shutdownNow();
//        System.out.println(list.size());
//        boolean terminated = executorService.isTerminated();
//        System.out.println(terminated);
        System.out.println(count);
        System.out.println(map.size());

    }
    private static void add(int c) throws InterruptedException {
        map.put(c,c);
        ++count;

    }
}
