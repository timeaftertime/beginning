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
	public RespFailedException(Resp<?> resp) {
		this.resp = (Resp<Void>) resp;
	}

	public RespFailedException(RespCode code, String msg) {
		this.resp = Resp.fail(code, msg);
	}

	public Resp<Void> getResp() { return resp; }
}
