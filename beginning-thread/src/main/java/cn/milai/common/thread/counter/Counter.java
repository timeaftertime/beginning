package cn.milai.common.thread.counter;

import cn.milai.common.thread.Condition;

/**
 * 线程安全的计数器
 * @author milai
 * @date 2021.02.26
 */
public interface Counter extends Condition {

	/**
	 * 计数一次
	 */
	default void count() {}

	/**
	 * 获取当前计数
	 * @return
	 */
	default long getCount() { return 0; }

	/**
	 * 重置当前计数器
	 */
	default void reset() {}

}
