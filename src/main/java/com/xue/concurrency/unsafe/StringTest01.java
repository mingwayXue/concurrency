package com.xue.concurrency.unsafe;

import com.xue.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/** StringBuilder,线程不安全
 * Created by mingway on Date:2019-03-26 17:26.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
@NotThreadSafe
public class StringTest01 {

	// 请求总数
	public static int clientTotal = 5000;

	// 同时并发执行线程数
	public static int threadTotal = 200;

	public static StringBuilder sb = new StringBuilder();

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
		log.info("size:{}", sb.length());
	}

	private static void add() {
		sb.append("1");
	}
}

