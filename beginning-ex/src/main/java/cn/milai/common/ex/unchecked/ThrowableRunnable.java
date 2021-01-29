package cn.milai.common.ex.unchecked;

/**
 * 可能抛出受检异常的代码块
 * @author milai
 * @date 2021.01.26
 */
public interface ThrowableRunnable {

	/**
	 * 运行代码块
	 * @throws Exception
	 */
	public void run() throws Exception;
}