package cn.milai.common.api.generator;

/**
 * {@link String} 构造器，每次调用应返回不同的值
 * @author milai
 * @date 2021.12.30
 */
public interface StringGenerator {

	/**
	 * 构造并返回下一个 {@link String}
	 * @return
	 */
	String next();
}
