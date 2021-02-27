package cn.milai.common.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * {@link Strings} 测试类
 * @author milai
 * @date 2021.02.27
 */
public class StringsTest {

	@Test
	public void testLenRange() {
		assertTrue(Strings.lenRange("abc", 1, 3));
		assertTrue(Strings.lenRange("abcd", 1, 4));
		assertFalse(Strings.lenRange("abcd", 1, 3));
		assertFalse(Strings.lenRange("abcde", 1, 4));
		assertFalse(Strings.lenRange(null, 0, 3));
		assertFalse(Strings.lenRange("", 1, 10));
	}

	@Test
	public void testToLine() {
		assertEquals("abc中文~~~", Strings.toLine(Arrays.asList("abc", "中文", "~~~")));
	}

	@Test
	public void testToLines() {
		assertEquals("abc\n123\n中文", Strings.toLines(Arrays.asList("abc", "123", "中文")));
	}
}
