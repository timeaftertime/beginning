package cn.milai.common.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

public class RandomsTest {

	@Test
	public void testRandomLength() {
		for (int len : new int[] { 1, 3, 5, 10, 20, 5, 8 }) {
			assertEquals(len, Randoms.randLowerOrDigit(len).length());
		}
	}

	@Test
	public void testLowerOrDigits() {
		for (int len : new int[] { 8, 10, 21, 9, 9, 3, 8 }) {
			for (char c : Randoms.randLowerOrDigit(len).toCharArray()) {
				assertTrue(Chars.isLower(c) || Chars.isDigit(c));
			}
		}
	}

	@Test
	public void testIllegalArguments() {
		try {
			Randoms.randLowerOrDigit(-1);
		} catch (IllegalArgumentException e1) {
			try {
				Randoms.randLowerOrDigit(-100);
			} catch (IllegalArgumentException e2) {
				Randoms.randLowerOrDigit(0);
				return;
			}
			Assert.fail();
		}
		Assert.fail();
	}

	@Test
	public void testNextLess() {
		for (int i = 0; i < 1000; i++) {
			assertTrue(Randoms.nextLess(1));
			assertFalse(Randoms.nextLess(-0.00001));
		}
	}

	@Test
	public void testNextInt() {
		for (int i = 1; i <= 100; i++) {
			assertTrue(Randoms.nextInt(i) < i);
		}
	}

}
