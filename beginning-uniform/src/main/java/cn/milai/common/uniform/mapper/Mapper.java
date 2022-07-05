package cn.milai.common.uniform.mapper;

/**
 * 模型转换器
 * @author milai
 * @date 2021.03.04
 */
public interface Mapper<S, T> {

	/**
	 * 获取 source 类型
	 * @return
	 */
	Class<S> sourceClass();

	/**
	 * 获取 target 类型
	 * @return
	 */
	Class<T> targetClass();

	/**
	 * 将 {@code source} 所有属性复制到 {@code target} 对应的属性，并返回 {@code target}
	 * @param source 从哪里复制
	 * @param target 复制到哪里
	 * @return
	 */
	T map(S source, T target);

	/**
	 * 使用 {@code S} 的默认构造方法创建 {@code T} 实例，并返回 {@link #map(Object, Object, String...)} 的结果
	 * @param source
	 * @return
	 */
	T map(S source);
}