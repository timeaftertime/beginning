package cn.milai.common.collection;

import java.util.Collection;

/**
 * 集合工具类
 * 
 * @author milai
 *
 */
public class CollectionUtils {

	private CollectionUtils() {

	}

	/**
	 * 判断集合是否为 null 或空
	 * @param c
	 * @return
	 */
	public static boolean isEmpty(Collection<?> c) {
		return c == null || c.isEmpty();
	}

}
