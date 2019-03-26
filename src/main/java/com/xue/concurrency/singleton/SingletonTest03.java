package com.xue.concurrency.singleton;

import com.xue.concurrency.annotations.NotRecommend;
import com.xue.concurrency.annotations.ThreadSafe;

/** 单例对象示例：懒汉模式(synchronized实现线程安全，加锁存在性能问题)
 * Created by mingway on Date:2019-03-26 11:08.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@ThreadSafe
@NotRecommend	//不推荐
public class SingletonTest03 {

	// 私有构造函数（外部不可new）
	private SingletonTest03() {}

	// 单例对象
	private  static SingletonTest03 instance = null;

	// 静态的工厂方法
	public static synchronized SingletonTest03 getInstance() {
		if (instance == null) {
			instance = new SingletonTest03();
		}
		return instance;
	}

}
