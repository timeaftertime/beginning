package cn.milai.common.base;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * {@link Namings} 测试类
 * @author milai
 * @date 2021.02.27
 */
public class NamingsTest {

	@Test
	public void testToFirstUpper() {
		assertEquals("PlayerPlane", Namings.toFirstUpper("playerPlane"));
		assertEquals("MissileBoss", Namings.toFirstUpper("MissileBoss"));
		assertEquals("Bomb", Namings.toFirstUpper("bomb"));
	}

	@Test
	public void testToFirstLower() {
		assertEquals("gameObject", Namings.toFirstLower("GameObject"));
		assertEquals("bomb", Namings.toFirstLower("Bomb"));
		assertEquals("downBullet", Namings.toFirstLower("downBullet"));
	}

	@Test
	public void testToCamel() {
		assertEquals("GameObject", Namings.toUpperCamel("game_object"));
		assertEquals("gameObject", Namings.toLowerCamel("game_object"));
		assertEquals("SnakeCaseStr", Namings.toUpperCamel("snake_case_str"));
		assertEquals("snakeCaseStr", Namings.toLowerCamel("snake_case_str"));
		assertEquals("Plane", Namings.toUpperCamel("plane"));
		assertEquals("plane", Namings.toLowerCamel("plane"));
	}

	@Test
	public void testToSnake() {
		assertEquals("game_object", Namings.toSnake("GameObject"));
		assertEquals("game_object", Namings.toSnake("gameObject"));
		assertEquals("snake_case_str", Namings.toSnake("SnakeCaseStr"));
		assertEquals("snake_case_str", Namings.toSnake("snakeCaseStr"));
		assertEquals("plane", Namings.toSnake("Plane"));
		assertEquals("plane", Namings.toSnake("plane"));
		assertEquals("plane_b", Namings.toSnake("planeB"));
	}

}
