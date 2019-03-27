package com.xue.concurrency.aqs;

import com.xue.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** CyclicBarrier 回环栅栏，类似于CountDownLatch，它可以重置计数器，可等待其它线程然后一同执行操作
 * Created by mingway on Date:2019-03-27 11:31.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@ThreadSafe
@Slf4j
public class CyclicBarrierTest01 {

	private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);	// 需要同步等待的线程数

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int num = i;
			Thread.sleep(1000);
			exec.execute(() -> {
				try {
					test(num);
				} catch (Exception e) {
					log.error("exception", e);
				}
			});
		}
		exec.shutdown();
	}

	private static void test(int num) throws Exception {
		Thread.sleep(1000);
		log.info("{} is ready", num);
		cyclicBarrier.await();			//只有线程数达到5时，后面才会执行
		log.info("{},continue", num);
	}
}
