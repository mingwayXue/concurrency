package com.xue.concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 使用synchronized同步（修饰代码块和方法），作用于当前对象
 * Created by Mingway on 2019/3/25.
 */
@Slf4j
public class SynchronizedTest01 {
    // 当前对象
    public synchronized void test1(int j) {
        for (int i = 0; i < 10; i ++) {
            log.info("test1 {} - {}", j, i);
        }
    }

    public void test2(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test2 {} - {}", j, i);
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedTest01 test01 = new SynchronizedTest01();
        SynchronizedTest01 test02 = new SynchronizedTest01();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            test01.test1(1);
        });

        executorService.execute(() -> {
//            test01.test2(2);      //有序输出，同个对象
            test02.test1(2);    //乱序输出，不是同个对象
        });
    }
}
