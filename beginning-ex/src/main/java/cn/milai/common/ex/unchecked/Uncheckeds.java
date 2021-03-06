package cn.milai.common.ex.unchecked;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 转换受检异常的工具类
 * @author milai
 * @date 2021.01.25
 */
public class Uncheckeds {

	private static final Logger LOG = LoggerFactory.getLogger(Uncheckeds.class);

	private static final String DEF_ERR_MSG = "发生异常";

	private Uncheckeds() {}

	/**
	 * 执行指定代码块
	 * 若发生异常，使用默认 {@link Logger} 打印错误堆栈后忽略异常
	 * @param t
	 */
	public static void log(ThrowableRunnable t) {
		log(t, "");
	}

	/**
	 * 执行指定代码块
	 * 若发生异常，使用默认 {@link Logger} 打印错误堆栈后忽略异常
	 * @param t
	 * @return {@link ThrowableCallable} 返回值，若发生异常，返回 null
	 */
	public static <T> T log(ThrowableCallable<T> t) {
		return log(t, "");
	}

	/**
	 * 执行指定代码块，若发生异常，使用默认 {@link Logger} 打印指定错误信息和异常堆栈后忽略异常
	 * @param t
	 * @param errMsg
	 */
	public static void log(ThrowableRunnable t, String errMsg) {
		logWith(LOG, t, errMsg);
	}

	/**
	 * 执行指定代码块，若发生异常，使用默认 {@link Logger} 打印指定错误信息和异常堆栈后忽略异常
	 * @param t
	 * @param errMsg
	 * @return {@link ThrowableCallable} 返回值，若发生异常，返回 null
	 */
	public static <T> T log(ThrowableCallable<T> t, String errMsg) {
		return logWith(LOG, t, errMsg);
	}

	/**
	 * 执行指定代码块，若发生异常，使用指定 {@link Logger} 打印异常堆栈后忽略异常
	 * @param log
	 * @param t
	 */
	public static void logWith(Logger log, ThrowableRunnable t) {
		logWith(log, t, "");
	}

	/**
	 * 执行指定代码块
	 * 若发生异常，使用指定 {@link Logger} 打印异常堆栈后忽略异常
	 * @param log
	 * @param t
	 * @return {@link ThrowableCallable} 返回值，若发生异常，返回 null
	 */
	public static <T> T logWith(Logger log, ThrowableCallable<T> t) {
		return logWith(log, t, "");
	}

	/**
	 * 执行指定代码块，若发生异常，使用指定 {@link Logger} 打印指定错误信息和异常堆栈后忽略异常
	 * @param log
	 * @param t
	 * @param errMsg
	 * @param args
	 */
	public static void logWith(Logger log, ThrowableRunnable t, String errMsg, Object... args) {
		try {
			t.run();
		} catch (Exception e) {
			log.error("{}, err = {}", buildErrMsg(errMsg, args), ExceptionUtils.getStackTrace(e));
		}
	}

	/**
	 * 执行指定代码块，并返回运行结果
	 * 若发生异常，使用指定 {@link Logger} 打印指定错误信息和异常堆栈后忽略异常
	 * @param log
	 * @param t
	 * @param errMsg
	 * @param args
	 * @return {@link ThrowableCallable} 返回值，若发生异常，返回 null
	 */
	public static <T> T logWith(Logger log, ThrowableCallable<T> t, String errMsg, Object... args) {
		try {
			return t.call();
		} catch (Exception e) {
			log.error("{}, err = {}", buildErrMsg(errMsg, args), ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	/**
	 * 执行指定代码块，若发生异常，打印日志并通过 {@link RethrownException#wrap(Throwable)} 包装并重新抛出
	 * @param t
	 * @throws RethrownException
	 */
	public static void rethrow(ThrowableRunnable t) throws RethrownException {
		rethrow(t, "");
	}

	/**
	 * 返回指定代码块返回结果，若发生异常，打印日志并通过 {@link RethrownException#wrap(Throwable)} 包装并重新抛出
	 * @param t
	 * @return  {@link ThrowableCallable} 返回值
	 * @throws RethrownException
	 */
	public static <T> T rethrow(ThrowableCallable<T> t) throws RethrownException {
		return rethrow(t, "");
	}

	/**
	 * 执行指定代码块，若发生异常，打印日志并通过 {@link RethrownException#wrap(Throwable, String)} 包装并重新抛出
	 * @param log
	 * @param t
	 * @param format
	 * @param args
	 * @throws RethrownException
	 */
	public static void rethrow(ThrowableRunnable t, String format, Object... args) throws RethrownException {
		try {
			t.run();
		} catch (Exception e) {
			RethrownException rethrow = newRethrowException(e, format, args);
			LOG.error("{}", ExceptionUtils.getStackTrace(rethrow));
			throw rethrow;
		}
	}

	/**
	 * 返回指定代码块返回结果，若发生异常，打印日志并通过 {@link RethrownException#wrap(Throwable)} 包装并重新抛出
	 * @param log
	 * @param t
	 * @param format
	 * @param args
	 * @return  {@link ThrowableCallable} 返回值
	 * @throws RethrownException
	 */
	public static <T> T rethrow(ThrowableCallable<T> t, String format, Object... args) throws RethrownException {
		try {
			return t.call();
		} catch (Exception e) {
			RethrownException rethrow = newRethrowException(e, format, args);
			LOG.error("{}", ExceptionUtils.getStackTrace(rethrow));
			throw rethrow;
		}
	}

	/**
	 * 执行指定代码块，若发生异常，使用 {@link ExceptionCreator} 创建的异常包装并抛出
	 * @param t
	 * @param creator
	 * @throws RuntimeException
	 */
	public static void rethrow(ThrowableRunnable t, ExceptionCreator creator) throws RuntimeException {
		try {
			t.run();
		} catch (Exception e) {
			throw (RuntimeException) creator.create(e).fillInStackTrace();
		}
	}

	public static <T> T rethrow(ThrowableCallable<T> t, ExceptionCreator creator) throws RuntimeException {
		try {
			return t.call();
		} catch (Exception e) {
			throw (RuntimeException) creator.create(e).fillInStackTrace();
		}
	}

	private static RethrownException newRethrowException(Throwable e, String format, Object... args) {
		return RethrownException.wrap(e, buildErrMsg(format, args));
	}

	private static String buildErrMsg(String format, Object... args) {
		return format.isEmpty() ? DEF_ERR_MSG : String.format(format, args);
	}

}
