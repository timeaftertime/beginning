package cn.milai.common.uniform.mapper;

/**
 * A map operation
 * @author milai
 * @date 2022.10.29
 */
public interface MapOP<S, T> {

	/**
	 * map source into target
	 * @param source
	 * @param target
	 */
	void map(S source, T target);

}