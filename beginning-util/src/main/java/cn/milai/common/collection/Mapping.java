package cn.milai.common.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 集合映射工具类
 * @author milai
 * @date 2021.09.03
 */
public class Mapping {

	private Mapping() {
	}

	/**
	 * 顺序使用指定 {@link Collection} 中每个元素调用 {@link Function} 并顺序返回结果的 {@link List}
	 * @param <T>
	 * @param <R>
	 * @param c
	 * @param mapper
	 * @return
	 */
	public static <T, R> List<R> list(Collection<T> c, Function<T, R> mapper) {
		return c.stream().map(mapper).collect(Collectors.toList());
	}

	/**
	 * 顺序使用指定数组中每个元素调用 {@link Function} 并顺序返回结果的 {@link List}
	 * @param <T>
	 * @param <R>
	 * @param a
	 * @param mapper
	 * @return
	 */
	public static <T, R> List<R> list(T[] a, Function<T, R> mapper) {
		return list(Arrays.asList(a), mapper);
	}

	/**
	 * 对 {@link Collection} 中每个元素应用  {@link Function} 并将所有返回值合并为 {@link List} 返回
	 * @param <T>
	 * @param <E>
	 * @param <R>
	 * @param c
	 * @param mapper
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T, E, R extends Collection<E>> List<E> reduceList(Collection<T> c, Function<T, R> mapper) {
		return (List<E>) c.stream().map(mapper).reduce((R) new ArrayList<E>(), (c1, c2) -> (R) Merge.list(c1, c2));
	}

	/**
	 * 对 {@link Collection} 中每个元素应用  {@link Function} 并将所有返回值合并为 {@link Set} 返回
	 * @param <T>
	 * @param <E>
	 * @param <R>
	 * @param c
	 * @param mapper
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T, E, R extends Collection<E>> Set<E> reduceSet(Collection<T> c, Function<T, R> mapper) {
		return (Set<E>) c.stream().map(mapper).reduce((R) new HashSet<E>(), (c1, c2) -> (R) Merge.set(c1, c2));
	}

	/**
	 * 顺序使用指定 {@link Collection} 中每个元素调用 {@link Function} 并顺序返回结果的 {@link Set}
	 * @param <T>
	 * @param <R>
	 * @param c
	 * @param mapper
	 * @return
	 */
	public static <T, R> Set<R> set(Collection<T> c, Function<T, R> mapper) {
		return c.stream().map(mapper).collect(Collectors.toSet());
	}

	/**
	 * 顺序使用指定数组中每个元素调用 {@link Function} 并顺序返回结果的 {@link Set}
	 * @param <T>
	 * @param <R>
	 * @param a
	 * @param mapper
	 * @return
	 */
	public static <T, R> Set<R> set(T[] a, Function<T, R> mapper) {
		return set(Arrays.asList(a), mapper);
	}

	/**
	 * 使用 {@link Collection} 中每个元素调用指定 {@link Function}，以结果为分组 key 构造 {@link Map} 
	 * @param <T>
	 * @param <K>
	 * @param c
	 * @param mapper
	 * @return
	 */
	public static <T, K> Map<K, List<T>> map(Collection<T> c, Function<T, K> mapper) {
		return c.stream().collect(Collectors.groupingBy(mapper));
	}

	/**
	 * 使用 {@link Stream} 中每个元素调用指定 {@link Function}，以结果为分组 key 构造 {@link Map} 
	 * @param <T>
	 * @param <K>
	 * @param c
	 * @param mapper
	 * @return
	 */
	public static <T, K> Map<K, List<T>> map(Stream<T> c, Function<T, K> mapper) {
		return c.collect(Collectors.groupingBy(mapper));
	}

}
