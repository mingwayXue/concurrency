package com.xue.concurrency.aqs;

import com.xue.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/** Semaphore 信号量 示例
 * Created by mingway on Date:2019-03-27 10:06.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
@ThreadSafe
public class SemaphoreTest02 {
	private final static int threadCount = 200;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(20);	// 同时并发数为20个
		for (int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			exec.execute(() -> {
				try {
					semaphore.acquire();		// 获取一个许可
					test(threadNum);
					semaphore.release();		// 释放一个许可
				} catch (InterruptedException e) {
					log.error("exception", e);
				}
			});
		}

//		log.info("finished");
		exec.shutdown();
	}

	private static void test(int threadNum) throws InterruptedException {
		log.info("{}", threadNum);
		Thread.sleep(1000);
	}

}
