package cn.milai.common.util;

import java.util.Random;

/**
 * 随机相关工具类
 * @author milai
 * @date 2020.12.27
 */
public final class Randoms {

	private Randoms() {
	}

	private static final Random rand = new Random();

	/**
	 * 获取从 alphabet 中随机挑选 length 个字符组成的字符串
	 * @param alphabet
	 * @param length
	 * @return
	 */
	private static String randStr(char[] alphabet, int length) {
		char[] result = new char[length];
		for (int i = 0; i < length; i++) {
			result[i] = alphabet[rand.nextInt(alphabet.length)];
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

}
