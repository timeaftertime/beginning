package cn.milai.common.base;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * {@link ArrayUtil} 测试类
 * @author milai
 * @date 2021.01.31
 */
public class ArrayUtilTest {

	@Test
	public void testUnion() {
		assertArrayEquals(new byte[] { 1, 2, 3 }, ArrayUtil.union(new byte[] { 1, 2, 3 }));
		assertArrayEquals(new byte[] { 1, 2, 3 }, ArrayUtil.union(new byte[] { 1, 2 }, (byte) 3));
		assertArrayEquals(new byte[] { 1, 2, 3, 4 }, ArrayUtil.union(new byte[] { 1, 2 }, new byte[] { 3, 4 }));
		assertArrayEquals(new byte[] { 1, 2, 3, 4 }, ArrayUtil.union(new byte[] { 1, 2 }, (byte) 3, (byte) 4));
	}

	@Test
	public void testContainers() {
		String[] arr = new String[] { "abc", "123", "字符串" };
		assertTrue(ArrayUtil.contains(arr, "abc"));
		assertTrue(ArrayUtil.contains(arr, "字符串"));
		assertTrue(ArrayUtil.contains(arr, "123"));
		assertFalse(ArrayUtil.contains(arr, "45"));
		assertFalse(ArrayUtil.contains(arr, "xx"));
		assertFalse(ArrayUtil.contains(arr, null));
	}
}
