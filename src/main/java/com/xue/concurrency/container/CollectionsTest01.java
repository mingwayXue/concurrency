package com.xue.concurrency.container;

import com.google.common.collect.Lists;
import com.xue.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/** 使用Collections.synchronizedXXX方法实现同步容器
 * Created by Mingway on 2019/3/26.
 */
@Slf4j
@ThreadSafe
public class CollectionsTest01 {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行线程数
    public static int threadTotal = 200;

    public static List<Integer> list = Collections.synchronizedList(Lists.newArrayList());

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();  // 定义线程池
        final Semaphore semaphore = new Semaphore(threadTotal);     // 信号量
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);  // 计数器
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}", list.size());
    }

    private static void add() {
        list.add(1);
    }
}
