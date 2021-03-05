package cn.milai.common.decoupling.map;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

/**
 * 模型属性复制工具类
 * @author milai
 * @date 2021.03.01
 */
public class Mappers {

	private Mappers() {}

	private static Map<String, Mapper<?, ?>> baseDeeps = new HashMap<>();
	private static Map<String, Mapper<?, ?>> baseShallows = new HashMap<>();

	static interface IMapper<S, T> {
		T map(S source, T target, String[] ignores);
	}

	private static <S, T> String keyOf(Class<S> s, Class<T> t) {
		return s.getName() + "_" + t.getName();
	}

	/**
	 * 使用指定 {@link IMapper} 构造一个浅拷贝的 {@link AbstractMapper}，若已经创建过，返回之前的 
	 * @param <S>
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <S, T> Mapper<S, T> getShallowMapper(Class<S> sourceClass, Class<T> targetClass) {
		return ((Mapper<S, T>) baseShallows.computeIfAbsent(
			keyOf(sourceClass, targetClass),
			k -> newMapper(sourceClass, targetClass, (source, target, ignores) -> {
				BeanUtils.copyProperties(source, target, ignores);
				return target;
			}
			)
		));
	}

	/**
	 * 使用指定 {@link IMapper} 构造一个深拷贝的 {@link AbstractMapper}，若已经创建过，返回之前的
	 * @param <S>
	 * @param <T>
	 * @param sourceClass
	 * @param targetClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <S, T> Mapper<S, T> getDeepMapper(Class<S> sourceClass, Class<T> targetClass) {
		return (Mapper<S, T>) baseDeeps.computeIfAbsent(
			keyOf(sourceClass, targetClass),
			k -> newMapper(sourceClass, targetClass, (source, target, ignores) -> {
				SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
				for (String ignore : ignores) {
					filter.getExcludes().add(ignore);
				}
				return JSON.parseObject(JSON.toJSONString(source, filter), targetClass);
			})
		);
	}

	private static <S, T> Mapper<S, T> newMapper(Class<S> source, Class<T> target, IMapper<S, T> m) {
		return new AbstractMapper<S, T>(source, target) {
			@Override
			public T map(S source, T target, String... ignores) {
				return m.map(source, target, ignores);
			}
		};
	}

	/**
	 * 浅复制，复制 {@code source} 所有 {@code ignores} 以外属性到 {@code target} ，并返回该 {@code target} 
	 * @param <T>
	 * @param source
	 * @param target
	 * @param ignores
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <S, T> T map(S source, T target, String... ignores) {
		return ((Mapper<S, T>) getShallowMapper(source.getClass(), target.getClass())).map(source, target, ignores);
	}

	@SuppressWarnings("unchecked")
	public static <S, T> T map(S source, Class<T> targetClass, String... ignores) {
		return ((Mapper<S, T>) getShallowMapper(source.getClass(), targetClass)).map(source, ignores);
	}

	/**
	 * 深复制，使用 {@code T} 的默认构造方法创建其实例，复制 {@code source} 所有属性深度到该实例，并返回该实例 
	 * @param <T>
	 * @param source 从哪里复制
	 * @param ignores
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <S, T> T deep(S source, Class<T> targetClass, String... ignores) {
		return ((Mapper<S, T>) getDeepMapper(source.getClass(), targetClass)).map(source, ignores);
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

	/**
	 * 返回转换  {@code sourceClass} 为 {@code targetClass} 的 {@link Mapper} 的构造器。其被初始化为  {@link #DEEP_MAPPER} 
	 * @param <S>
	 * @param <T>
	 * @param sourceClass
	 * @param targetClass
	 * @return
	 */
	public static <S, T> MapperBuilder<S, T> deepThen(Class<S> sourceClass, Class<T> targetClass) {
		return new MapperBuilder<>(sourceClass, targetClass).then(getDeepMapper(sourceClass, targetClass));
	}

}
