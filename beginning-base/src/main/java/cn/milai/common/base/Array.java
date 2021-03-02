package cn.milai.common.base;

/**
 * 数组工具类
 * @author milai
 * @date 2021.01.31
 */
public class Array {

	/**
	 * 返回一个新字节数组，其元素为参数数组其后拼接参数所有元素
	 * @param a1
	 * @param a2
	 * @return
	 */
	public static byte[] union(byte[] a1, byte... a2) {
		byte[] a = new byte[a1.length + a2.length];
		System.arraycopy(a1, 0, a, 0, a1.length);
		System.arraycopy(a2, 0, a, a1.length, a2.length);
		return a;
	}

	/**
	 * 判断指定数组是否包含指定元素
	 * @param <T>
	 * @param array
	 * @param e
	 * @return
	 */
	public static <T> boolean contains(T[] array, T e) {
		if (e == null) {
			for (T t : array) {
				if (t == null) {
					return true;
				}
			}
			return false;
		}
		for (T t : array) {
			if (t.equals(e)) {
				return true;
			}
		}
		return false;
	}

}
