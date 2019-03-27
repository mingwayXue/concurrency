package com.xue.concurrency.lock;

import com.xue.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/** Condition使某个线程通过某个条件唤醒
 * Created by Mingway on 2019/3/22.
 */
@Slf4j
@ThreadSafe
public class LockTest05 {
	public static void main(String[] args) {
		ReentrantLock reentrantLock = new ReentrantLock();
		Condition condition = reentrantLock.newCondition();

		new Thread(() -> {
			try {
				reentrantLock.lock();
				log.info("wait signal");		// 1
				condition.await();	// 表示将放入队列
			} catch (Exception e) {
				e.printStackTrace();
			}

			log.info("get signal");		// 4 最后执行
			reentrantLock.unlock();
		}).start();

		new Thread(() -> {
			try {
				reentrantLock.lock();
				log.info("get lock");		// 2
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			condition.signalAll();	// 唤醒所有
			log.info("send signal...");	// 3
			reentrantLock.unlock();
		}).start();
	}
}
