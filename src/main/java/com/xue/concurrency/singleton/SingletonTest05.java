package com.xue.concurrency.singleton;

import com.xue.concurrency.annotations.ThreadSafe;

/** 单例对象示例：懒汉模式，双重校验模式实现单例（线程安全，使用volatile + 双重检测 -> 禁止指令重排）
 * Created by mingway on Date:2019-03-26 11:08.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@ThreadSafe
public class SingletonTest05 {

	// 私有构造函数（外部不可new）
	private SingletonTest05() {}

	// 单例对象 volatile + 双重检测机制 -> 禁止指令重排
	private volatile static SingletonTest05 instance = null;

	// 静态的工厂方法
	public static SingletonTest05 getInstance() {
		if (instance == null) {		//双重校验锁模式
			synchronized (SingletonTest05.class) {	// 锁住整个对象
				if (instance == null) {
					/*
					对象new操作时，cpu操作指令：
					1、memory = allocate() 分配对象内存空间
					2、ctorInstance() 初始化对象
					3、instance = memory 设置instance指向刚分配的内存

					即当发生指令重排时，存在线程安全问题
					 */
					instance = new SingletonTest05();
				}
			}
		}
		return instance;
	}

}
