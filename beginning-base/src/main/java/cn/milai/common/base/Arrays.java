package cn.milai.common.base;

/**
 * 数组工具类
 * @author milai
 * @date 2021.01.31
 */
public class Arrays {

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
}
