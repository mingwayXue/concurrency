package com.xue.concurrency.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/** Executors.newScheduledThreadPool	可定时调度线程池
 * Created by mingway on Date:2019-03-28 17:20.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
public class ThreadPoolTest04 {
	public static void main(String[] args) {
		ScheduledExecutorService exec = Executors.newScheduledThreadPool(3);
		exec.schedule(new Runnable() {
			@Override
			public void run() {
				log.info("schedule done...");

			}
		}, 5, TimeUnit.SECONDS);

		exec.shutdown();
	}
}
