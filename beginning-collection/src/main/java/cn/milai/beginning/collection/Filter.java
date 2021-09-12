package cn.milai.beginning.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 集合筛选工具类
 * @author milai
 * @date 2021.08.30
 */
public class Filter {

	private Filter() {
	}

	/**
	 * 返回一个新 {@link Set}，其元素为 {@code c} 中所有满足条件 {@code p} 的元素
	 * @param <T>
	 * @param c
	 * @param p
	 * @return
	 */
	public static <T> Set<T> set(Collection<T> c, Predicate<T> p) {
		return c.stream().filter(p).collect(Collectors.toSet());
	}

	/**
	 * 返回一个新 {@link Set}，其元素为 {@code c} 中所有不满足条件 {@code p} 的元素
	 * @param <T>
	 * @param c
	 * @param p
	 * @return
	 */
	public static <T> Set<T> nset(Collection<T> c, Predicate<T> p) {
		return c.stream().filter(p.negate()).collect(Collectors.toSet());
	}

	/**
	 * 返回一个新 {@link List}，其元素为 {@code c} 中所有满足条件 {@code p} 的元素
	 * @param <T>
	 * @param c
	 * @param p
	 * @return
	 */
	public static <T> List<T> list(Collection<T> c, Predicate<T> p) {
		return c.stream().filter(p).collect(Collectors.toList());
	}

	/**
	 * 返回一个新 {@link List}，其元素为 {@code c} 中所有不满足条件 {@code p} 的元素
	 * @param <T>
	 * @param c
	 * @param p
	 * @return
	 */
	public static <T> List<T> nlist(Collection<T> c, Predicate<T> p) {
		return c.stream().filter(p.negate()).collect(Collectors.toList());
	}

	/**
	 * 从指定 {@link Collection} 中移除所有不满足条件的元素，返回移除后的原 {@link Collection}
	 * @param <C>
	 * @param <T>
	 * @param c
	 * @param p
	 * @return
	 */
	public static <C extends Collection<T>, T> C remain(C c, Predicate<T> p) {
		List<T> toRemove = new ArrayList<>();
		for (T t : c) {
			if (!p.test(t)) {
				toRemove.add(t);
			}
		}
		c.removeAll(toRemove);
		return c;
	}

	/**
	 * 移除指定 {@link Collection} 中所有满足指定条件的元素，返回移除后的原 {@link Collection}
	 * @param <C>
	 * @param <T>
	 * @param c
	 * @param p
	 * @return
	 */
	public static <C extends Collection<T>, T> C remove(C c, Predicate<T> p) {
		return remain(c, p.negate());
	}

	/**
	 * 获取集合中第一个满足条件的元素的 {@link Optional}
	 * @param <C>
	 * @param <T>
	 * @param c
	 * @param p
	 * @return
	 */
	public static <C extends Collection<T>, T> Optional<T> first(C c, Predicate<T> p) {
		return c.stream().filter(p).findFirst();
	}

}
