package cn.milai.common.util;

/**
 * Class 相关工具类
 * @author milai
 * @date 2021.01.02
 */
public class Classes {

	private Classes() {
	}

	/**
	 * 判断参数 c 是否为 int 或 {@link Integer}
	 * @param c
	 * @return
	 */
	public static boolean isInts(Class<?> c) {
		return c == int.class || c == Integer.class;
	}

	/**
	 * 判断参数 c 是否为 long 或 {@link Long}
	 * @param c
	 * @return
	 */
	public static boolean isLong(Class<?> c) {
		return c == long.class || c == Long.class;
	}

	/**
	 * 判断参数 c 是否为 boolean 或 {@link Boolean}
	 * @param c
	 * @return
	 */
	public static boolean isBools(Class<?> c) {
		return c == boolean.class || c == Boolean.class;
	}

	/**
	 * 判读参数 c 是否为 float 或 {@link Float}
	 * @param c
	 * @return
	 */
	public static boolean isFloats(Class<?> c) {
		return c == float.class || c == Float.class;
	}

	/**
	 * 判断参数 c 是否为 double 或 {@link Double}
	 * @param c
	 * @return
	 */
	public static boolean isDoubles(Class<?> c) {
		return c == double.class || c == Double.class;
	}

	/**
	 * 判断参数 c 是否为普通类型，即字符串、数字、布尔
	 * @param c
	 * @return
	 */
	public static boolean isSingle(Class<?> c) {
		return c == String.class || isInts(c) || isLong(c) || isFloats(c) || isDoubles(c) || isBools(c);
	}

}
