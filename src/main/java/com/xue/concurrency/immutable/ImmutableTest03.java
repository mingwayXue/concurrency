package com.xue.concurrency.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.xue.concurrency.annotations.NotThreadSafe;
import com.xue.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/** 不可变对象：使用guava创建的不可变对象，ImmutableXXX
 * Created by mingway on Date:2019-03-26 15:58.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
@ThreadSafe
public class ImmutableTest03 {
	private static final ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);
	private static final ImmutableMap<Integer, Integer> map = ImmutableMap.<Integer, Integer>builder()
			.put(1, 2)
			.put(3, 4)
			.put(5, 6)
			.build();

	public static void main(String[] args) {
//		list.add(4);	//抛出异常
//		map.put(7, 8);	//抛出异常
		log.info("map: {}", map);
	}

}
