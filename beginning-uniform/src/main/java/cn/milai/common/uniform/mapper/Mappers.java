package cn.milai.common.uniform.mapper;

import java.util.HashMap;
import java.util.Map;

/**
 * 模型属性复制工具类
 * @author milai
 * @date 2021.03.01
 */
public class Mappers {

	private Mappers() {
	}

	private static final Map<String, Mapper<?, ?>> MAPPERS = new HashMap<>();

	/**
	 * 使用指定 {@link MapOP} 构造一个浅拷贝的 {@link BaseMapper}，若已经创建过，返回之前的 
	 * @param <S>
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <S, T> Mapper<S, T> getShallowMapper(Class<S> sourceClass, Class<T> targetClass) {
		return ((Mapper<S, T>) MAPPERS.computeIfAbsent(
			Mapper.keyOf(sourceClass, targetClass),
			k -> new BaseMapper<>(sourceClass, targetClass)
		));
	}

	/**
	 * 浅复制，复制 {@code source} 所有 {@code ignores} 以外属性到 {@code target} ，并返回该 {@code target} 
	 * @param <T>
	 * @param source
	 * @param target
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <S, T> T map(S source, T target) {
		if (source == null) {
			return null;
		}
		return ((Mapper<S, T>) getShallowMapper(source.getClass(), target.getClass())).map(source, target);
	}

	@SuppressWarnings("unchecked")
	public static <S, T> T map(S source, Class<T> targetClass) {
		if (source == null) {
			return null;
		}
		return ((Mapper<S, T>) getShallowMapper(source.getClass(), targetClass)).map(source);
	}

	/**
	 * 返回转换  {@code sourceClass} 为 {@code targetClass} 的 {@link Mapper} 的构造器。其被初始化为  {@link #SHALLOW_MAPPER} 
	 * @param <S>
	 * @param <T>
	 * @param source
	 * @param target
	 * @return
	 */
	public static <S, T> MapperBuilder<S, T> mapThen(Class<S> source, Class<T> target) {
		return new MapperBuilder<>(source, target).then(getShallowMapper(source, target));
	}

}
