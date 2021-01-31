package cn.milai.common.base;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CharsTest {

	@Test
	public void testCaseJudge() {
		for (int i = 0; i < 26; i++) {
			char lower = (char) ('a' + i);
			char upper = (char) ('A' + i);
			assertTrue(Chars.isLower(lower));
			assertTrue(Chars.isUpper(upper));
			assertFalse(Chars.isLower(upper));
			assertFalse(Chars.isUpper(lower));
			assertFalse(Chars.isDigit(lower));
			assertFalse(Chars.isDigit(upper));
		}
		for (int i = 0; i < 9; i++) {
			char digit = (char) ('0' + i);
			assertFalse(Chars.isLower(digit));
			assertFalse(Chars.isUpper(digit));
			assertTrue(Chars.isDigit(digit));
		}
		for (char c : "`-=~!@#$%^&*()_+[]{}\\|;\'\",.<>/?中 文".toCharArray()) {
			assertFalse(Chars.isLower(c));
			assertFalse(Chars.isUpper(c));
			assertFalse(Chars.isDigit(c));
		}
	}

}
