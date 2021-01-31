package cn.milai.common.ex.unchecked;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Marker;
import org.slf4j.helpers.NOPLogger;

/**
 * {@link Uncheckeds} 测试类
 * @author milai
 * @date 2021.01.26
 */
public class UncheckedsTest {

	static class Callable {
		private int cnt = 0;

		public void call() {
			cnt++;
		}

		public int getCallCnt() { return cnt; }
	}

	@Test
	public void testWrap() {
		Exception e = new Exception();
		RethrownException re = RethrownException.wrap(e);
		assertNotSame(e, re);
		assertNotSame(re, RethrownException.wrap(e));
		assertNotSame(re, RethrownException.wrap(re));
		assertSame(re.getCause(), RethrownException.wrap(re).getCause());
	}

	@Test
	public void testLog() {
		Callable c = new Callable();
		Uncheckeds.log(() -> {
			c.call();
		});
		assertEquals(1, c.getCallCnt());
		Uncheckeds.log(() -> {
			c.call();
			throw new Exception("应该被忽略的异常");
		});
		assertEquals(2, c.getCallCnt());
		Uncheckeds.log(() -> {}, "应该被加在前面的错误信息");
		assertEquals(2, c.getCallCnt());
	}

	@SuppressWarnings("serial")
	@Test
	public void testLogWith() {
		String errMsg = "错误信息";
		Uncheckeds.logWith(new NOPLogger() {
			@Override
			public void error(Marker marker, String msg) {
				assertTrue(msg.contains(errMsg));
				assertTrue(msg.contains(IOException.class.getName()));
				assertFalse(msg.contains(Uncheckeds.class.getCanonicalName()));
			}

		}, () -> {
			throw new IOException(errMsg);
		});
	}

	@Test
	public void testRethrow() {
		String errorMsg = "应该被重新抛出的异常";
		try {
			Uncheckeds.rethrow(() -> {
				throw new IOException(errorMsg);
			});
		} catch (RethrownException e) {
			assertTrue(e.getClass() == RethrownException.class);
			assertEquals(errorMsg, e.getCause().getMessage());
		}
		String runtimeException = "runtimeException";
		try {
			Uncheckeds.rethrow(() -> {
				throw new RuntimeException(runtimeException);
			}, errorMsg);
		} catch (RethrownException e) {
			assertEquals(e.getMessage(), errorMsg);
			assertTrue(e.getCause().getClass() == RuntimeException.class);
			assertTrue(e.getCause().getMessage().equals(runtimeException));
			return;
		}
		Assert.fail();
	}

	@Test
	public void testErrMsgFormat() {
		String errMsg = "抛出了 %s 异常";
		String arg1 = "第一种";
		try {
			Uncheckeds.rethrow(() -> {
				throw new Exception();
			}, errMsg, arg1);
		} catch (Exception e) {
			assertEquals(e.getMessage(), String.format(errMsg, arg1));
			return;
		}
		Assert.fail();
	}

}
