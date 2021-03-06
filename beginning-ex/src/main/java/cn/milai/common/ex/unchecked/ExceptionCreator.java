package cn.milai.common.ex.unchecked;

/**
 * 构造 {@link RuntimeException} 的工厂接口
 * @author milai
 * @date 2021.03.05
 */
public interface ExceptionCreator {

	/**
	 * 以指定 {@link Throwable} 为 {@code cause} 创建一个 {@link RuntimeException}
	 * @param cause
	 * @return
	 */
	RuntimeException create(Throwable cause);

}
