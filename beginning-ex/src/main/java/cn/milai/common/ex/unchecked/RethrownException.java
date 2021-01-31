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
	private RethrownException(Throwable e) {
		super(e);
	}

	/**
	 * 构造 cause by 指定异常和错误信息的 {@link RethrownException}
	 * @param msg
	 * @param e
	 */
	private RethrownException(String msg, Throwable e) {
		super(msg, e);
	}

	/**
	 * 获取异常的 {@link RethrownException} 包装。
	 * 若异常已经是 {@link RethrownException} 则包装其 {@link Throwable#getCause()}
	 * @param e
	 * @return
	 */
	public static RethrownException wrap(Throwable e) {
		return wrap(e, "");
	}

	/**
	 * 获取异常的 {@link RethrownException} 包装。
	 * 若异常已经是 {@link RethrownException} 则包装其 {@link Throwable#getCause()}
	 * @param e
	 * @param msg 需要包装到 {@link RethrownException} 中的消息
	 * @return
	 */
	public static RethrownException wrap(Throwable e, String msg) {
		if (e instanceof RethrownException) {
			return new RethrownException(msg, e.getCause());
		}
		return new RethrownException(msg, e);
	}

}
