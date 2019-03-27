package com.xue.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/** Future<T> 示例
 * Created by mingway on Date:2019-03-27 21:45.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
public class FutureTest01 {

	static class MyCallable implements Callable<String> {
		@Override
		public String call() throws Exception {
			log.info("do something in callable");
			Thread.sleep(5000);
			return "Done";
		}
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<String> future = executorService.submit(new MyCallable());
		log.info("do other thing...");
		Thread.sleep(1000);
		log.info("Future：{}", future.get());		//此时会阻塞主线程，知道Callable线程执行完毕
	}
}
