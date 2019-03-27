package com.xue.concurrency.aqs;

import com.xue.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** CountDownLatch简单示例
 * Created by mingway on Date:2019-03-27 10:06.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
@ThreadSafe
public class CountDownLatchTest01 {
	private final static int threadCount = 200;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			exec.execute(() -> {
				try {
					test(threadNum);
				} catch (InterruptedException e) {
					log.error("exception", e);
				} finally {
					countDownLatch.countDown();		//表示当前线程执行完毕，计数值减1
				}
			});
		}
		countDownLatch.await();		//保证所有线程执行完毕
		log.info("finished");
		exec.shutdown();
	}

	private static void test(int threadNum) throws InterruptedException {
		Thread.sleep(100);
		log.info("{}", threadNum);
		Thread.sleep(100);
	}

}
