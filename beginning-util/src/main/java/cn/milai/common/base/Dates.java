package cn.milai.common.base;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * {@link Date} 工具类
 * @author milai
 * @date 2022.03.27
 */
public class Dates {

	public static final String DEF_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static final DateTimeFormatter DEF_FORMATTER = DateTimeFormatter.ofPattern(DEF_FORMAT);

	private Dates() {
	}

	/**
	 * 使用 {@link #DEF_FORMAT} 格式化指定时间
	 * @param dateTime
	 * @return
	 */
	public static String format(LocalDateTime dateTime) {
		return dateTime.format(DEF_FORMATTER);
	}

	/**
	 * 使用 {@link #DEF_FORMAT} 格式化指定时间
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date, DEF_FORMAT);
	}

	/**
	 * 使用指定格式对指定时间格式化
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, String format) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 将时间字符串按照指定格式转换为 {@link LocalDateTime}，若转换失败，返回 <code>null</code>
	 * @param date
	 * @param format
	 * @return
	 */
	public static LocalDateTime from(String date, String format) {
		DateTimeFormatter formatter = DEF_FORMATTER;
		if (format != null) {
			formatter = DateTimeFormatter.ofPattern(format);
		}
		try {
			return LocalDateTime.parse(date, formatter);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将时间字符串按照 {@link #DEF_FORMAT} 转换为 {@link LocalDateTime}，若转换失败，返回 <code>null</code>
	 * @param date
	 * @return
	 */
	public static LocalDateTime from(String date) {
		return from(date, DEF_FORMAT);
	}

}
