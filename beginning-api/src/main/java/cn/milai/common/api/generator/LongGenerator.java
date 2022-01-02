package cn.milai.common.api.generator;

/**
 * long 类型构造器，每次调用应返回不同的值
 * @author milai
 * @date 2021.12.30
 */
public interface LongGenerator {

	/**
	 *构造并返回下一个 long
	 * @return
	 */
	long next();
}
