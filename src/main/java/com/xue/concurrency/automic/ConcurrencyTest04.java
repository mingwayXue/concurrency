package com.xue.concurrency.automic;

import com.xue.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

/** AtomicReference 对对象进行原子操作
 * Created by Mingway on 2019/3/22.
 */
@Slf4j
@ThreadSafe
public class ConcurrencyTest04 {
    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0, 1);  //count -> 1
        count.compareAndSet(0, 3);  //不更新
        count.compareAndSet(1, 5);  //count -> 5
        count.compareAndSet(3, 7);  //不更新

        log.info("count:{}", count);
    }
}
