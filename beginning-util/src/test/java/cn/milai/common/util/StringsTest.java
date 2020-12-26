package cn.milai.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringsTest {

	@Test
	public void testToFirstUpper() {
		assertEquals("PlayerPlane", Strings.toFirstUpper("playerPlane"));
		assertEquals("MissileBoss", Strings.toFirstUpper("MissileBoss"));
		assertEquals("Bomb", Strings.toFirstUpper("bomb"));
	}

	@Test
	public void testToFirstLower() {
		assertEquals("gameObject", Strings.toFirstLower("GameObject"));
		assertEquals("bomb", Strings.toFirstLower("Bomb"));
		assertEquals("downBullet", Strings.toFirstLower("downBullet"));
	}

	@Test
	public void testToCamel() {
		assertEquals("GameObject", Strings.toUpperCamel("game_object"));
		assertEquals("gameObject", Strings.toLowerCamel("game_object"));
		assertEquals("SnakeCaseStr", Strings.toUpperCamel("snake_case_str"));
		assertEquals("snakeCaseStr", Strings.toLowerCamel("snake_case_str"));
		assertEquals("Plane", Strings.toUpperCamel("plane"));
		assertEquals("plane", Strings.toLowerCamel("plane"));
	}

	@Test
	public void testToSnake() {
		assertEquals("game_object", Strings.toSnake("GameObject"));
		assertEquals("game_object", Strings.toSnake("gameObject"));
		assertEquals("snake_case_str", Strings.toSnake("SnakeCaseStr"));
		assertEquals("snake_case_str", Strings.toSnake("snakeCaseStr"));
		assertEquals("plane", Strings.toSnake("Plane"));
		assertEquals("plane", Strings.toSnake("plane"));
	}

	@Test
	public void testLenRange() {
		assertTrue(Strings.lenRange("abc", 1, 3));
		assertTrue(Strings.lenRange("abcd", 1, 4));
		assertFalse(Strings.lenRange("abcd", 1, 3));
		assertFalse(Strings.lenRange("abcde", 1, 4));
		assertFalse(Strings.lenRange(null, 0, 3));
		assertFalse(Strings.lenRange("", 1, 10));
	}
}
