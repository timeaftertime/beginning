package cn.milai.common.base;

import static org.junit.Assert.assertEquals;
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
			assertTrue(Chars.isLetter(lower));
			assertTrue(Chars.isLetter(upper));
		}
		for (int i = 0; i < 9; i++) {
			char digit = (char) ('0' + i);
			assertFalse(Chars.isLower(digit));
			assertFalse(Chars.isUpper(digit));
			assertTrue(Chars.isDigit(digit));
			assertFalse(Chars.isLetter(digit));
		}
		for (char c : "`-=~!@#$%^&*()_+[]{}\\|;\'\",.<>/?中 文".toCharArray()) {
			assertFalse(Chars.isLower(c));
			assertFalse(Chars.isUpper(c));
			assertFalse(Chars.isDigit(c));
			assertFalse(Chars.isLetter(c));
		}
	}

	@Test
	public void testToLower() {
		assertEquals('c', Chars.toLower('C'));
		assertEquals('d', Chars.toLower('D'));
		assertEquals('_', Chars.toLower('_'));
		assertEquals('-', Chars.toLower('-'));
		assertEquals('1', Chars.toLower('1'));
		assertEquals(' ', Chars.toLower(' '));
		assertEquals('\t', Chars.toLower('\t'));
	}

	@Test
	public void testToUpper() {
		assertEquals('P', Chars.toUpper('p'));
		assertEquals('F', Chars.toUpper('f'));
		assertEquals('=', Chars.toUpper('='));
		assertEquals('|', Chars.toUpper('|'));
		assertEquals('2', Chars.toUpper('2'));
		assertEquals(' ', Chars.toUpper(' '));
		assertEquals('\n', Chars.toUpper('\n'));
	}

}
