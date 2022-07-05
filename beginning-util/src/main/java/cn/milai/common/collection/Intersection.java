package cn.milai.common.collection;

import java.util.Collection;
import java.util.Set;

/**
 * 交集工具类
 * @author milai
 * @date 2021.09.20
 */
public class Intersection {

	/**
	 * 将两个 {@link Collection} 的交集元素复制到一个新的 {@link Set} 并返回该 {@link Set}
	 * @param <T>
	 * @param c1
	 * @param c2
	 * @return
	 */
	public static <T> Set<T> set(Collection<T> c1, Collection<T> c2) {
		return Filter.set(c1, e -> c2.contains(e));
	}

}
