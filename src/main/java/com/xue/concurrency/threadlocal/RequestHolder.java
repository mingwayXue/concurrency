package com.xue.concurrency.threadlocal;

/**
 * Created by mingway on Date:2019-03-26 16:44.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class RequestHolder {
	private static final ThreadLocal<Long> requestHolder = new ThreadLocal<>();

	public static void add(Long id) {
		requestHolder.set(id);
	}

	public static void remove() {
		requestHolder.remove();
	}

	public static Long getId() {
		return requestHolder.get();
	}
}
