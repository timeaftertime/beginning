package cn.milai.common.decoupling;

/**
 * 响应状态码
 * @author milai
 * @date 2020.12.20
 */
public interface RespCode {

	/**
	 * 获取唯一标识码
	 * @return
	 */
	int getCode();

	/**
	 * 获取描述
	 * @return
	 */
	String getDesc();
}
