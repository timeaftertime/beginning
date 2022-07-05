package cn.milai.common.uniform.resp;

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

	private Resp<Void> resp;

	@SuppressWarnings("unchecked")
	public RespFailedException(Resp<?> resp, String msg) {
		super(msg);
		this.resp = (Resp<Void>) resp;
	}

	public Resp<Void> getResp() { return resp; }
}
