package com.xue.concurrency.immutable;

import com.google.common.collect.Maps;
import com.xue.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/** 不可变对象： Collections.unmodifiableMap
 * Created by mingway on Date:2019-03-26 15:58.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
@ThreadSafe
public class ImmutableTest02 {
	private static Map<Integer, Integer> map = Maps.newHashMap();

	static {
		map.put(1, 2);
		map = Collections.unmodifiableMap(map);		//使用改方法修饰后，则map变为不可变对象
	}

	public static void main(String[] args) {

		//但是可以修改对象里面的值
		log.info("修改前：{}", map.get(1));
		map.put(1, 3);	//抛出异常，此时map为不可变对象
		log.info("修改后：{}", map.get(1));
	}

}
