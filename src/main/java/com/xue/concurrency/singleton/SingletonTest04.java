package com.xue.concurrency.singleton;

import com.xue.concurrency.annotations.NotThreadSafe;

/** 单例对象示例：懒汉模式，双重校验模式实现单例（线程不安全，发生指令重排序）
 * Created by mingway on Date:2019-03-26 11:08.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@NotThreadSafe
public class SingletonTest04 {

	// 私有构造函数（外部不可new）
	private SingletonTest04() {}

	// 单例对象
	private  static SingletonTest04 instance = null;

	// 静态的工厂方法
	public static SingletonTest04 getInstance() {
		if (instance == null) {		//双重校验锁模式
			synchronized (SingletonTest04.class) {	// 锁住整个对象
				if (instance == null) {
					/*
					对象new操作时，cpu操作指令：
					1、memory = allocate() 分配对象内存空间
					2、ctorInstance() 初始化对象
					3、instance = memory 设置instance指向刚分配的内存

					即当发生指令重排时，存在线程安全问题
					 */
					instance = new SingletonTest04();
				}
			}
		}
		return instance;
	}

}
