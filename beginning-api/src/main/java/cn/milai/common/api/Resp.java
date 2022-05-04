package cn.milai.common.api;

import java.util.function.Function;

import cn.milai.common.base.Strings;

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
	public static int SUCCESS = 0;

	/**
	 * 未知错误状态码
	 */
	public static int UNKOWN_ERROR = -1;

	/**
	 * 唯一标识
	 */
	private int code;

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
	public boolean isSuccess() { return SUCCESS == code; }

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
	public Resp(int code, String desc, T data, Object... descArgs) {
		this.code = code;
		this.desc = Strings.format(desc, descArgs);
		this.data = data;
	}

	/**
	 * 若 {@link #isSuccess()} 返回 <code>true</code>，返回当前 {@link Resp}；
	 * 否则抛出 {@link RespFailedException}
	 * @param msg 要覆盖的 msg
	 * @param msg format 参数
	 * @return
	 * @throws RespFailedException
	 */
	public Resp<T> throwOrGet(String msg, Object... args) throws RespFailedException {
		if (!isSuccess()) {
			throw new RespFailedException(this, Strings.format(msg, args));
		}
		return this;
	}

	/**
	 * 若 {@link #isSuccess()} 返回 <code>true</code>，返回当前 {@link Resp}；
	 * 否则抛出 {@link RespFailedException}
	 * @param msg
	 * @return
	 * @throws RespFailedException
	 */
	public Resp<T> throwOrGet() throws RespFailedException {
		return throwOrGet("");
	}

	/**
	 * 返回一个指定 code、desc 的失败请求响应
	 * @param code
	 * @param desc
	 * @param descArgs
	 * @return
	 */
	public static <T> Resp<T> fail(int code, String desc, Object... descArgs) {
		return new Resp<>(code, desc, null, descArgs);
	}

	/**
	 * 返回一个未知
	 * @param <T>
	 * @param desc
	 * @param descArgs
	 * @return
	 */
	public static <T> Resp<T> fail(String desc, Object... descArgs) {
		return fail(UNKOWN_ERROR, desc, descArgs);
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
	 * 返回一个未知异常响应
	 * @param <T>
	 * @return
	 */
	public static <T> Resp<T> fail() {
		return Resp.fail(UNKOWN_ERROR, "");
	}

	/**
	 * 返回一个指定 code 和描述参数请求失败响应
	 * @param <T>
	 * @param code
	 * @param descArgs
	 * @return
	 */
	public static <T> Resp<T> fail(RespCode code, Object... descArgs) {
		return new Resp<>(code.getCode(), Strings.format(code.getDesc(), (Object[]) descArgs), null);
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
	 * 复制指定 {@link Resp}，返回复制的新 {@link Resp}
	 * @param <I>
	 * @param <O>
	 * @param r
	 * @param mapper
	 * @return
	 */
	public static <I, O> Resp<O> of(Resp<I> r, Function<I, O> mapper) {
		O data = r.data == null ? null : mapper.apply(r.data);
		return new Resp<>(r.code, r.desc, data);
	}

	/**
	 * 复制指定 {@link Resp}，返回复制的 data 为 <code>null</code> 的 {@link Resp}
	 * @param <I>
	 * @param r
	 * @return
	 */
	public static <I> Resp<Void> of(Resp<I> r) {
		return new Resp<>(r.code, r.desc, null);
	}

	/**
	 * 获取响应唯一标识
	 * @return
	 */
	public int getCode() { return code; }

	/**
	 * 获取响应描述
	 * @return
	 */
	public String getDesc() { return desc; }

	/**
	 * 获取响应的数据
	 * @return
	 */
	public T getData() { return data; }

	public void setCode(int code) { this.code = code; }

	public void setDesc(String desc) { this.desc = desc; }

	public void setData(T data) { this.data = data; }

}
