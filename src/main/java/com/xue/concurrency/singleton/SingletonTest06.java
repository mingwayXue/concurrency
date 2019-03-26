package com.xue.concurrency.singleton;

import com.xue.concurrency.annotations.Recommend;
import com.xue.concurrency.annotations.ThreadSafe;

/** 使用枚举实现单例模式(线程安全)
 * Created by mingway on Date:2019-03-26 11:57.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@ThreadSafe
@Recommend
public class SingletonTest06 {
	private SingletonTest06() {}

	public SingletonTest06 getInstance() {
		return Singleton.INSTANCE.getInstance();	//使用枚举内部类，在实际使用时才进行初始化
	}

	private enum Singleton {
		INSTANCE;
		private SingletonTest06 singletonTest06;

		// JVM保证绝对只实例化一次
		Singleton() {
			//加载资源
			singletonTest06 = new SingletonTest06();
		}

		public SingletonTest06 getInstance() {
			return singletonTest06;
		}

	}
}
