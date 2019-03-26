package com.xue.concurrency.singleton;

import com.xue.concurrency.annotations.ThreadSafe;

/** 单例对象示例：饿汉模式，在类装载的时候就已经初始化（线程安全）
 * Created by mingway on Date:2019-03-26 11:08.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@ThreadSafe
public class SingletonTest02 {

	// 私有构造函数（外部不可new）
	private SingletonTest02() {}

	// 单例对象
	private  static SingletonTest02 instance = new SingletonTest02();

	// 静态的工厂方法
	public static SingletonTest02 getInstance() {
		return instance;
	}

}
