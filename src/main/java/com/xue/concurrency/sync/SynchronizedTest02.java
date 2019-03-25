package com.xue.concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 使用synchronized同步（修饰静态代码块和静态方法），作用于所有对象
 * Created by Mingway on 2019/3/25.
 */
@Slf4j
public class SynchronizedTest02 {
    public static synchronized void test1(int j) {
        for (int i = 0; i < 10; i ++) {
            log.info("test1 {} - {}", j, i);
        }
    }

    public static void test2(int j) {
        synchronized (SynchronizedTest02.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test2 {} - {}", j, i);
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedTest02 test01 = new SynchronizedTest02();
        SynchronizedTest02 test02 = new SynchronizedTest02();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            test01.test1(1);
        });

        executorService.execute(() -> {
            test01.test2(2);      //有序输出
//            test02.test1(2);    //有序输出（此时都有序输出，锁住了类）
        });
    }
}
