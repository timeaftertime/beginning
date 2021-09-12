package cn.milai.beginning.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 集合合并工具类
 * @author milai
 * @date 2021.08.30
 */
public class Merge {

	private Merge() {
	}

	/**
	 * 顺序合并两个集合所有元素到一个新的 {@link List}
	 * @param <T>
	 * @param c1
	 * @param c2
	 * @return
	 */
	public static <T, C extends Collection<? extends T>> List<T> list(C c1, C c2) {
		List<T> result = new ArrayList<>();
		result.addAll(c1);
		result.addAll(c2);
		return result;
	}

	/**
	 * 返回一个新 {@link List}，其元素为指定 {@link List} 顺序拼接所有指定元素
	 * @param <T>
	 * @param c
	 * @param ts
	 * @return
	 */
	@SafeVarargs
	public static <T, C extends Collection<? extends T>> List<T> list(C c, T... ts) {
		List<T> res = new ArrayList<>(c);
		for (T t : ts) {
			res.add(t);
		}
		return res;
	}

	/**
	 * 将指定 {@link Collection} 的元素合并到一个新的 {@link Set}，返回该 {@link Set}
	 * @param <T>
	 * @param c1
	 * @param c2
	 * @return
	 */
	public static <T, C extends Collection<? extends T>> Set<T> set(C c1, C c2) {
		Set<T> res = new HashSet<>();
		res.addAll(c1);
		res.addAll(c2);
		return res;
	}

	/**
	 * 顺序合并两个字节数组所有元素到一个新的字节数组
	 * @param a1
	 * @param a2
	 * @return
	 */
	public static byte[] array(byte[] a1, byte... a2) {
		byte[] a = new byte[a1.length + a2.length];
		System.arraycopy(a1, 0, a, 0, a1.length);
		System.arraycopy(a2, 0, a, a1.length, a2.length);
		return a;
	}

}
