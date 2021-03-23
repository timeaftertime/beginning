package cn.milai.common.thread;

/**
 * 线程安全的条件
 * @author milai
 * @date 2021.03.09
 */
public interface Condition {

	/**
	 * 当前是否已经满足条件
	 * @return
	 */
	default boolean isMet() { return false; }

	/**
	 * 等待到当前 {@link #isMet()} 返回 {@code true} 后从当前方法返回
	 */
	default void await() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 使当前条件变为满足并让所有调用 {@link #await()} 的线程返回
	 */
	default void toMet() {}

}
