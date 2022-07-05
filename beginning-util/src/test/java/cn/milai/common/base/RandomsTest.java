package cn.milai.common.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link Randoms} 测试类
 * @author milai
 */
public class RandomsTest {

	@Test
	public void testRandomLength() {
		for (int len : new int[] { 1, 3, 5, 10, 20, 5, 8 }) {
			assertEquals(len, Randoms.fixedLowerDigit(len).length());
		}
	}

	@Test
	public void testFixedLowerDigits() {
		for (int len : new int[] { 8, 10, 21, 9, 9, 3, 8 }) {
			String str = Randoms.fixedLowerDigit(len);
			assertEquals(len, str.length());
			for (char c : str.toCharArray()) {
				assertTrue(Chars.isLower(c) || Chars.isDigit(c));
			}
		}
	}

	@Test
	public void testRandLowerDigit() {
		for (int i = 1; i <= 100; i++) {
			String str = Randoms.randLowerDigit(i);
			assertTrue(str.length() <= i);
		}
	}

	@Test
	public void testIllegalArguments() {
		try {
			Randoms.fixedLowerDigit(-1);
		} catch (IllegalArgumentException e1) {
			try {
				Randoms.fixedLowerDigit(-100);
			} catch (IllegalArgumentException e2) {
				Randoms.fixedLowerDigit(0);
				return;
			}
			Assert.fail();
		}
		Assert.fail();
	}

	@Test
	public void testNextLess() {
		for (int i = 0; i < 100; i++) {
			assertTrue(Randoms.nextLess(1));
			assertFalse(Randoms.nextLess(-0.00001));
		}
	}

	@Test
	public void testNextInt() {
		for (int i = 1; i <= 100; i++) {
			assertTrue(Randoms.nextInt(i) < i);
			assertTrue(Randoms.nextInt() < Integer.MAX_VALUE);
		}
	}

	@Test
	public void testNoNegative() {
		for (int i = 1; i < 100; i++) {
			assertTrue(Randoms.nextInt(i) >= 0);
			assertTrue(Randoms.nextInt() >= 0);
		}
	}

}
