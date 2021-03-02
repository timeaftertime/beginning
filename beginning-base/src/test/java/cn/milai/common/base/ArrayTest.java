package cn.milai.common.base;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * {@link Array} 测试类
 * @author milai
 * @date 2021.01.31
 */
public class ArrayTest {

	@Test
	public void testUnion() {
		assertArrayEquals(new byte[] { 1, 2, 3 }, Array.union(new byte[] { 1, 2, 3 }));
		assertArrayEquals(new byte[] { 1, 2, 3 }, Array.union(new byte[] { 1, 2 }, (byte) 3));
		assertArrayEquals(new byte[] { 1, 2, 3, 4 }, Array.union(new byte[] { 1, 2 }, new byte[] { 3, 4 }));
		assertArrayEquals(new byte[] { 1, 2, 3, 4 }, Array.union(new byte[] { 1, 2 }, (byte) 3, (byte) 4));
	}

	@Test
	public void testContainers() {
		String[] arr = new String[] { "abc", "123", "字符串" };
		assertTrue(Array.contains(arr, "abc"));
		assertTrue(Array.contains(arr, "字符串"));
		assertTrue(Array.contains(arr, "123"));
		assertFalse(Array.contains(arr, "45"));
		assertFalse(Array.contains(arr, "xx"));
		assertFalse(Array.contains(arr, null));
	}
}
