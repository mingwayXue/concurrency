package com.xue.concurrency.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Executors.newCachedThreadPool	可缓存线程池
 * Created by mingway on Date:2019-03-28 17:20.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
public class ThreadPoolTest01 {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int index = i;
			exec.execute(() -> {
				log.info("task:{}", index);
			});
		}

		exec.shutdown();
	}
}
