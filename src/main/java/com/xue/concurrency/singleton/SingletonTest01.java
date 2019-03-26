package com.xue.concurrency.singleton;

import com.xue.concurrency.annotations.NotThreadSafe;

/** 单例对象示例：懒汉模式，只有在使用时才初始化（多线程会出现问题，线程不安全）
 * Created by mingway on Date:2019-03-26 11:08.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@NotThreadSafe
public class SingletonTest01 {

	// 私有构造函数（外部不可new）
	private SingletonTest01() {}

	// 单例对象
	private  static SingletonTest01 instance = null;

	// 静态的工厂方法
	public static SingletonTest01 getInstance() {
		if (instance == null) {
			instance = new SingletonTest01();
		}
		return instance;
	}

}
