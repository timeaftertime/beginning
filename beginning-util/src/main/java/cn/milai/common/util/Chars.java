package cn.milai.common.util;

/**
 * char 相关工具类
 * @author milai
 * @date 2020.12.27
 */
public final class Chars {

	private Chars() {}

	/**
	 * 小写字母按照（a-z）组成的字符串
	 */
	public static final String LOWERS = "abcdefghijklmnopqrstuvwxyz";

	/**
	 * 大写字母按照（A-Z）组成的字符串
	 */
	public static final String UPPERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * 数字按照（0-9）组成的字符串
	 */
	public static final String DIGITS = "0123456789";

	/**
	 * 字母按照（a-z A-Z)的组成的字符串
	 */
	public static final String LETTERS = LOWERS + UPPERS;

	/**
	 * 获取小写字母按照（a-z）的数组
	 * @return
	 */
	public static char[] lowers() {
		return LOWERS.toCharArray();
	}

	/**
	 * 获取大写字母按照（A-Z）的数组
	 * @return
	 */
	public static char[] uppers() {
		return UPPERS.toCharArray();
	}

	/**
	 * 获取数字按照（0-9）组成的数组
	 * @return
	 */
	public static char[] digits() {
		return DIGITS.toCharArray();
	}

	/**
	 * 字母按照（a-z A-Z）组成的数组
	 * @return
	 */
	public static char[] letters() {
		return LETTERS.toCharArray();
	}

	/**
	 * 判定指定字符是否为小写字母（a-z）
	 * @param c
	 * @return
	 */
	public static boolean isLower(char c) {
		return c >= 'a' && c <= 'z';
	}

	/**
	 * 判定指定字符是否为大写字母（A-Z）
	 * @param c
	 * @return
	 */
	public static boolean isUpper(char c) {
		return c >= 'A' && c <= 'Z';
	}

	/**
	 * 判定指定字符是否为数字字符（0-9）
	 * @param c
	 * @return
	 */
	public static boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

}
