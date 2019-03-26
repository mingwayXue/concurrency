package com.xue.concurrency.immutable;

import com.google.common.collect.Maps;
import com.xue.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/** 不可变对象： final
 * Created by mingway on Date:2019-03-26 15:58.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
@NotThreadSafe
public class ImmutableTest01 {
	private static final Integer a = 1;
	private static final String b = "2";
	private static final Map<Integer, Integer> maps = Maps.newHashMap();

	static {
		maps.put(1, 2);
	}

	public static void main(String[] args) {
//		使用final修饰，不可变
//		a = 2;
//		b = "3";
//		maps = Maps.newHashMap();

		//但是可以修改对象里面的值
		log.info("修改前：{}", maps.get(1));
		maps.put(1, 3);
		log.info("修改后：{}", maps.get(1));
	}

}
