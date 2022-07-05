package cn.milai.common.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 构造工具类
 * @author milai
 * @date 2021.09.18
 */
public class Creator {

	/**
	 * 使用指定元素构造一个不可修改的 {@link Set}
	 * @param <T>
	 * @param ts
	 * @return
	 */
	@SafeVarargs
	public static <T> Set<T> asSet(T... ts) {
		return Collections.unmodifiableSet(hashSet(ts));
	}

	/**
	 * 使用指定元素构造一个 {@link HashSet}
	 * @param <T>
	 * @param ts
	 * @return
	 */
	@SafeVarargs
	public static <T> Set<T> hashSet(T... ts) {
		return new HashSet<>(Arrays.asList(ts));
	}

	/**
	 * 使用指定 {@link Iterable} 构造一个 {@link HashSet}
	 * @param <T>
	 * @param iter
	 * @return
	 */
	public static <T> Set<T> hashSet(Iterable<T> iter) {
		Set<T> set = new HashSet<>();
		iter.forEach(e -> set.add(e));
		return set;
	}

	/**
	 * 使用指定元素构造一个 {@link ArrayList}
	 * @param <T>
	 * @param ts
	 * @return
	 */
	@SafeVarargs
	public static <T> List<T> arrayList(T... ts) {
		return new ArrayList<>(Arrays.asList(ts));
	}

	/**
	 * 使用指定 {@link Iterable} 构造一个 {@link ArrayList}
	 * @param <T>
	 * @param iter
	 * @return
	 */
	public static <T> List<T> arrayList(Iterable<T> iter) {
		List<T> set = new ArrayList<>();
		iter.forEach(e -> set.add(e));
		return set;
	}

	/**
	 * 使用指定 k, v 创建一个 {@link HashMap}
	 * @param <K>
	 * @param <V>
	 * @param k
	 * @param v
	 * @return
	 */
	public static <K, V> Map<K, V> hashMap(K k, V v) {
		Map<K, V> m = new HashMap<>();
		m.put(k, v);
		return m;
	}

}
