package cn.milai.common.util;

import java.util.Random;

/**
 * 线程安全的随机工具类
 * @author milai
 * @date 2020.12.27
 */
public final class Randoms {

	private static final ThreadLocal<Random> rands = new ThreadLocal<Random>();

	private static Random getRand() {
		if (rands.get() != null) {
			return rands.get();
		}
		synchronized (rands) {
			Random random = rands.get();
			if (random == null) {
				random = new Random();
			}
			return random;
		}
	}

	private Randoms() {}

	/**
	 * 获取从 alphabet 中随机挑选 length 个字符组成的字符串
	 * @param alphabet
	 * @param length
	 * @return
	 */
	private static String randStr(char[] alphabet, int length) {
		if (length < 0) {
			throw new IllegalArgumentException("长度必须大于等于 0");
		}
		if (alphabet == null) {
			alphabet = new char[0];
		}
		if (alphabet.length == 0 && length != 0) {
			throw new IllegalArgumentException("生成长度不为 0 的随机字符串时字符表不能为空");
		}
		char[] result = new char[length];
		for (int i = 0; i < length; i++) {
			result[i] = alphabet[getRand().nextInt(alphabet.length)];
		}
		return new String(result);
	}

	/**
	 * 获取从 alphabet 中随机挑选 length 个字符组成的字符串
	 * @param alphabet
	 * @param length
	 * @return
	 */
	private static String randStr(String alphabet, int length) {
		return randStr(alphabet.toCharArray(), length);
	}

	/**
	 * 获取指定长度的小写字母和数字组成的随机字符串
	 * @param length
	 * @return
	 */
	public static String randLowerOrDigit(int length) {
		return randStr(Chars.LOWERS + Chars.DIGITS, length);
	}

	/**
	 * 返回下一个随机浮点数 [0~1) 是否小于 limit
	 * 
	 * @param limit
	 * @return
	 */
	public static boolean nextLess(double limit) {
		return getRand().nextDouble() < limit;
	}

	/**
	 * 返回下一个 [0~limit) 的随机整数
	 * @return
	 */
	public static int nextInt(int limit) {
		return getRand().nextInt(limit);
	}

}
