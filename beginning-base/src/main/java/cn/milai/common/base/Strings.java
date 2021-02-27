package cn.milai.common.base;

/**
 * String 相关工具类
 * @author milai
 * @date 2020.12.27
 */
public final class Strings {

	private Strings() {}

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
	 * 将指定字符串列表通过换行符拼接一个字符串
	 * @param lines
	 * @return
	 */
	public static String toLines(Iterable<String> lines) {
		return String.join("\n", lines);
	}

	/**
	 * 将指定字符串列表拼接成一个字符串
	 * @param lines
	 * @return
	 */
	public static String toLine(Iterable<String> lines) {
		return String.join("", lines);
	}

}
