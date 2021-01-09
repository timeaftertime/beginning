package cn.milai.common.util;

/**
 * String 相关工具类
 * @author milai
 * @date 2020.12.27
 */
public final class Strings {

	private Strings() {
	}

	/**
	 * 获取字符串的切片
	 * 包含下标为 start 的字符，不包含下标为 end 的字符
	 * 若 start < 0 表示下标 str.length() + start
	 * 若 end < 0  表示下标 str.length() + end
	 * 若 start > end 表示反顺序取 substring
	 * @param str
	 * @param start
	 * @param end
	 * @return
	 */
	public static String slice(String str, int start, int end) {
		if (start < 0) {
			start = str.length() + start;
		}
		if (end < 0) {
			end = str.length() + end;
		}
		int grad = start < end ? 1 : -1;
		StringBuilder sb = new StringBuilder();
		for (int i = start; i != end; i += grad) {
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}

	/**
	 * 判断 str 不为 null 且的长度是否在 low 和 hig 之间（左闭右闭）
	 * @param str
	 * @param low
	 * @param hig
	 * @return
	 */
	public static boolean lenRange(String str, int low, int hig) {
		if (str == null)
			return false;
		return low <= str.length() && str.length() <= hig;
	}

	/**
	 * 返回蛇形（下划线分割）的字符串的首字母大写的驼峰形式
	 * @param snakeStr
	 * @return
	 */
	public static final String toUpperCamel(String snakeStr) {
		String[] splits = snakeStr.split("_");
		StringBuilder sb = new StringBuilder();
		for (String word : splits) {
			sb.append(toFirstUpper(word));
		}
		return toFirstUpper(sb.toString());
	}

	/**
	 * 返回蛇形（下划线分割）的字符串的首字母小写的驼峰形式
	 * @param snakeStr
	 * @return
	 */
	public static final String toLowerCamel(String snakeStr) {
		return toFirstLower(toUpperCamel(snakeStr));
	}

	/**
	 * 返回驼峰字符串的蛇形（下划线分割）形式
	 * @param camelStr
	 * @return
	 */
	public static final String toSnake(String camelStr) {
		StringBuilder sb = new StringBuilder();
		char[] chs = toFirstLower(camelStr).toCharArray();
		for (int i = 0; i < chs.length; i++) {
			if (Character.isUpperCase(chs[i])) {
				sb.append('_');
			}
			sb.append(Character.toLowerCase(chs[i]));
		}
		return sb.toString();
	}

	/**
	 * 返回字符串的首字母大写形式
	 * @param word
	 * @return
	 */
	public static final String toFirstUpper(String word) {
		char[] chs = word.toCharArray();
		chs[0] = Character.toUpperCase(chs[0]);
		return new String(chs);
	}

	/**
	 * 返回字符串的首字小写形式
	 * @param word
	 * @return
	 */
	public static final String toFirstLower(String word) {
		char[] chs = word.toCharArray();
		chs[0] = Character.toLowerCase(chs[0]);
		return new String(chs);
	}

}
