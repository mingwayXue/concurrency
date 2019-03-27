package com.xue.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/** FutureTask<T> 示例
 * Created by mingway on Date:2019-03-27 21:45.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
public class FutureTest02 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<String> futureTask = new FutureTask<String>(() -> {
			log.info("do something in callable");
			Thread.sleep(5000);
			return "Done";
		});

		new Thread(futureTask).start();

		log.info("do something in main");

		String result = futureTask.get();
		log.info("result:{}", result);
	}
}
