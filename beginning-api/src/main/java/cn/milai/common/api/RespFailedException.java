package cn.milai.common.api;

/**
 * {@link Resp} 返回非成功的异常
 * @author milai
 * @date 2021.12.24
 */
public class RespFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RespFailedException(String msg) {
		super(msg);
	}

}
