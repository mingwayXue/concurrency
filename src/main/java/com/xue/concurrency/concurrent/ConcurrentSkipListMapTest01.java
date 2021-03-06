package com.xue.concurrency.concurrent;

import com.xue.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;

/** 并发容器ConcurrentSkipListMap实现线程安全
 * Created by Mingway on 2019/3/26.
 */
@Slf4j
@ThreadSafe
public class ConcurrentSkipListMapTest01 {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行线程数
    public static int threadTotal = 200;

    public static Map<Integer, Integer> map = new ConcurrentSkipListMap<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();  // 定义线程池
        final Semaphore semaphore = new Semaphore(threadTotal);     // 信号量
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);  // 计数器
        for (int i = 0; i < clientTotal; i++) {
            int c = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(c);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}", map.size());
    }

    private static void add(int i) {
        map.put(i, i);
    }
}
