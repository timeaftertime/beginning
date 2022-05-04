package cn.milai.common.base;

import java.util.ArrayList;
import java.util.List;

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
	 * 将指定字符串列表通过换行符拼接一个字符串
	 * @param lines
	 * @return
	 */
	public static String joinLineSeparator(Iterable<String> lines) {
		return String.join(Chars.LF, lines);
	}

	/**
	 * 将指定字符串列表拼接成一个字符串
	 * @param lines
	 * @return
	 */
	public static String appends(Iterable<String> lines) {
		return String.join("", lines);
	}

	/**
	 * 将字符串以换行切割，返回切割后字符串的列表
	 * @param str
	 * @return
	 */
	public static final List<String> toLines(String str) {
		List<String> strs = new ArrayList<>();
		for (String line : str.split("\n")) {
			strs.add(line);
		}
		return strs;
	}

	/**
	 * 获取 format 使用指定参数格式化后的结果，若格式化失败，返回 format
	 * @param format
	 * @param args
	 * @return
	 */
	public static String format(String format, Object... args) {
		try {
			return String.format(format, args);
		} catch (Exception e) {
			return format;
		}
	}

}
