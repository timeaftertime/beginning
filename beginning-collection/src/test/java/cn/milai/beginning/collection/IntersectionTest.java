package cn.milai.beginning.collection;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

/**
 * {@link Intersection} 测试类
 * @author milai
 * @date 2021.09.20
 */
public class IntersectionTest {

	@Test
	public void testSet() {
		assertEquals(
			TestUtil.newSet(), Intersection.set(
				TestUtil.newSet("abc", "a", "c"), Arrays.asList("b")
			)
		);
		assertEquals(
			TestUtil.newSet(11, 121), Intersection.set(
				TestUtil.newSet(121, 11), Arrays.asList(11, 1, 121)
			)
		);
	}

}
