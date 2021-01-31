package cn.milai.common.base;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class ClassesTest {

	static class A {
	}

	@Test
	public void testIsSingle() {
		assertFalse(Classes.isSingle(Map.class));
		assertFalse(Classes.isSingle(List.class));
		assertFalse(Classes.isSingle(Set.class));
		assertFalse(Classes.isSingle(ArrayList.class));
		assertFalse(Classes.isSingle(A.class));
		assertFalse(Classes.isSingle(ClassesTest.class));
		assertTrue(Classes.isSingle(int.class));
		assertTrue(Classes.isSingle(Integer.class));
		assertTrue(Classes.isSingle(String.class));
		assertTrue(Classes.isSingle(float.class));
		assertTrue(Classes.isSingle(Double.class));
		assertTrue(Classes.isSingle(Boolean.class));
	}
}
