package cn.milai.common.ex;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具类
 * @author milai
 * @date 2021.10.05
 */
public class Exceptions {

	/**
	 * 获取 {@link Throwable} 的异常堆栈信息并返回对应的字符串
	 * @param throwable
	 * @return
	 */
	public static String getStackTrace(final Throwable throwable) {
		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw, true);
		throwable.printStackTrace(pw);
		return sw.getBuffer().toString();
	}

}
