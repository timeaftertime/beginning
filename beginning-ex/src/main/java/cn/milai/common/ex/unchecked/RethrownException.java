package cn.milai.common.ex.unchecked;

/**
 * 用于包装后再次抛出的 {@link Exception}
 * @author milai
 * @date 2021.01.25
 */
public class RethrownException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 构造 cause by 指定异常的 {@link RethrownException}
	 * @param e
	 */
	public RethrownException(Throwable e) {
		super(e);
	}

	/**
	 * 构造 cause by 指定异常和错误信息的 {@link RethrownException}
	 * @param msg
	 * @param e
	 */
	public RethrownException(String msg, Throwable e) {
		super(msg, e);
	}

}
