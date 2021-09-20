package cn.milai.common.base;

/**
 * char 相关工具类
 * @author milai
 * @date 2020.12.27
 */
public final class Chars {

	private Chars() {
	}

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
	 *  \n 换行符
	 */
	public static final String LF = "\n";

	/**
	 * \r 回车符
	 */
	public static final String CR = "\r";

	/**
	 * \n 换行符
	 */
	public static final char C_LF = LF.charAt(0);

	/**
	 * \r 回车符
	 */
	public static final char C_CR = CR.charAt(0);

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
	 * 判断指定字符是否为英文字母(A-Z a-z)
	 * @param c
	 * @return
	 */
	public static boolean isLetter(char c) {
		return isUpper(c) || isLower(c);
	}

	/**
	 * 判定指定字符是否为数字字符（0-9）
	 * @param c
	 * @return
	 */
	public static boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

	/**
	 * 转换指定大写字母为对应的小写字母字符，若不是大写字母，返回原字符
	 * @param c
	 * @return
	 */
	public static char toLower(char c) {
		if (!isUpper(c)) {
			return c;
		}
		return (char) (c + 32);
	}

	/**
	 * 转换指定小写字母为对应的大写字母字符，若不是小写字母，返回原字符
	 * @param c
	 * @return
	 */
	public static char toUpper(char c) {
		if (!isLower(c)) {
			return c;
		}
		return (char) (c - 32);
	}
}
