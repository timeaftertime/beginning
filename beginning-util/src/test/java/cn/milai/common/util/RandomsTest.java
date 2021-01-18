package cn.milai.common.util;

import static org.junit.Assert.assertEquals;
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

}
