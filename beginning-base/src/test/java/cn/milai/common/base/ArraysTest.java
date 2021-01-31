package cn.milai.common.base;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * {@link Arrays} 测试类
 * @author milai
 * @date 2021.01.31
 */
public class ArraysTest {

	@Test
	public void testUnion() {
		assertArrayEquals(new byte[] { 1, 2, 3 }, Arrays.union(new byte[] { 1, 2, 3 }));
		assertArrayEquals(new byte[] { 1, 2, 3 }, Arrays.union(new byte[] { 1, 2 }, (byte) 3));
		assertArrayEquals(new byte[] { 1, 2, 3, 4 }, Arrays.union(new byte[] { 1, 2 }, new byte[] { 3, 4 }));
		assertArrayEquals(new byte[] { 1, 2, 3, 4 }, Arrays.union(new byte[] { 1, 2 }, (byte) 3, (byte) 4));
	}
}
