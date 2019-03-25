package com.xue.concurrency.automic;

import com.xue.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/** AtomicXXXXFieldUpdater 原子性地更新某个对象的实例
 * Created by Mingway on 2019/3/22.
 */
@Slf4j
@ThreadSafe
public class ConcurrencyTest05 {
    // 实例化AtomicIntegerFieldUpdater对象，后面的"count"必须是指定的字段
    private static AtomicIntegerFieldUpdater<ConcurrencyTest05> updater = AtomicIntegerFieldUpdater.newUpdater(ConcurrencyTest05.class, "count");

    @Getter
    public volatile int count = 100;    // 这里的字段必须是volatile的

    public static ConcurrencyTest05 test05 = new ConcurrencyTest05();

    public static void main(String[] args) {
        if (updater.compareAndSet(test05, 100, 200)) {
            log.info("update success 1, {}", test05.getCount());
        }

        if (updater.compareAndSet(test05, 120, 111)) {
            log.info("update success 2, {}", test05.getCount());
        } else {
            log.info("update failed, {}", test05.getCount());
        }
    }
}
