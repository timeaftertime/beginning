package cn.milai.common.api;

/**
 * 接口统一返回模型
 * @date 2020.01.27
 * @author milai
 * @see RespCode
 */
public class Resp<T> {

	/**
	 * 成功的状态码
	 */
	static String SUCCESS = "SUCCESS";

	/**
	 * 唯一标识
	 */
	private String code;

	/**
	 * 详细信息
	 */
	private String desc;

	/**
	 * 数据
	 */
	private T data;

	/**
	 * 判断当前状态是否为成功
	 * @return
	 */
	public boolean isSuccess() {
		return code != null && SUCCESS.equals(code);
	}

	/**
	 * 构造一个所有字段为默认值的响应
	 */
	public Resp() {
	}

	/**
	 * 构造一个指定状态、描述和数据的响应
	 * @param code
	 * @param desc
	 * @param data
	 * @param 用于 format desc 中的参数
	 */
	public Resp(String code, String desc, T data, String... descArgs) {
		this.code = code;
		this.desc = String.format(desc, (Object) descArgs);
		this.data = data;
	}

	/**
	 * 返回一个指定 code、desc 的失败请求响应
	 * @param code
	 * @param desc
	 * @param descArgs
	 * @return
	 */
	public static <T> Resp<T> fail(String code, String desc, String... descArgs) {
		return new Resp<>(code, desc, null, descArgs);
	}

	/**
	 * 返回一个与参数相同 code、desc 且 data 为 null 的失败请求响应
	 * @param <T>
	 * @param r
	 * @return
	 */
	public static <T> Resp<T> fail(Resp<?> r) {
		return Resp.fail(r.getCode(), r.getDesc());
	}

	/**
	 * 返回一个指定 code 和描述参数请求失败响应
	 * @param <T>
	 * @param code
	 * @param descArgs
	 * @return
	 */
	public static <T> Resp<T> fail(RespCode code, String... descArgs) {
		return new Resp<>(code.getCode(), String.format(code.getDesc(), (Object) descArgs), null);
	}

	/**
	 * 返回一个以 data 为数据的请求成功响应
	 * @param <T>
	 * @param data
	 * @return
	 */
	public static <T> Resp<T> success(T data) {
		return new Resp<>(SUCCESS, "", data);
	}

	/**
	 * 返回一个 data 为 null 的请求成功响应
	 * @param <T>
	 * @return
	 */
	public static <T> Resp<T> success() {
		return Resp.success(null);
	}

	/**
	 * 获取响应唯一标识
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 获取响应描述
	 * @return
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 获取响应的数据
	 * @return
	 */
	public T getData() {
		return data;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setData(T data) {
		this.data = data;
	}

}
