package cn.milai.common.collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 测试工具类
 * @author milai
 * @date 2021.09.07
 */
public class TestUtil {

	/**
	 * 将指定元素转换为 {@link Set}
	 * @param <T>
	 * @param ts
	 * @return
	 */
	@SafeVarargs
	public static <T> Set<T> newSet(T... ts) {
		return new HashSet<>(Arrays.asList(ts));
	}

}
