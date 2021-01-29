package cn.milai.common.ex.unchecked;

/**
 * 返回结果的可抛出异常的代码块
 * @author milai
 * @date 2021.01.27
 */
public interface ThrowableCallable<T> {

	/**
	 * 运行代码并返回结果
	 * @return
	 */
	T call() throws Exception;
}
