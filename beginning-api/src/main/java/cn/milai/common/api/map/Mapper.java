package cn.milai.common.api.map;

/**
 * 模型转换器
 * @author milai
 * @date 2021.03.04
 */
public interface Mapper<S, T> {

	/**
	 * 将 {@code source} 所有属性复制到 {@code target} 对应的属性，并返回 {@code target}
	 * @param source 从哪里复制
	 * @param target 复制到哪里
	 * @param 需要忽略的属性名
	 * @return
	 */
	T map(S source, T target, String... ignores);

	/**
	 * 使用 {@code S} 的默认构造方法创建 {@code T} 实例，并返回 {@link #map(Object, Object, String...)} 的结果
	 * @param source
	 * @param ignores
	 * @return
	 */
	T map(S source, String... ignores);
}