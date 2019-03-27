package com.xue.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/** ReentrantReadWriteLock读写锁
 * Created by mingway on Date:2019-03-27 17:27.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
public class LockTest03 {
	private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	private final Map<String, Data> map = new TreeMap<>();

	private final Lock readLock = lock.readLock();		// 读锁

	private final Lock writeLock = lock.writeLock();		// 写锁

	/**
	 * 读
	 * @param key
	 * @return
	 */
	public Data get(String key) {
		readLock.lock();
		try {
			return map.get(key);
		} finally {
			readLock.unlock();
		}
	}

	/**
	 * 写
	 * @param key
	 * @param data
	 * @return
	 */
	public Data put(String key, Data data) {
		writeLock.lock();
		try {
			return map.put(key, data);
		} finally {
			writeLock.unlock();
		}
	}

	public Set<String> getAllKey() {
		readLock.lock();
		try {
			return map.keySet();
		} finally {
			readLock.unlock();
		}
	}

	class Data {

	}
}
