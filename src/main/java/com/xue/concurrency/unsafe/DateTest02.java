package com.xue.concurrency.unsafe;

import com.xue.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/** 使用线程封闭，即将SimpleDateFormat放在局部变量中，每个线程都有自己的SimpleDateFormat对象，线程安全的
 * Created by mingway on Date:2019-03-26 17:41.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
@ThreadSafe
public class DateTest02 {

	// 请求总数
	public static int clientTotal = 5000;

	// 同时并发执行线程数
	public static int threadTotal = 200;

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
//		log.info("size:{}", sb.length());
	}

	private static void add() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");		// 线程封闭，定义成局部变量
		try {
			sdf.parse("20190101");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}