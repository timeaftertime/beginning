package cn.milai.common.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

/**
 * {@link Collects} 测试类
 * @author milai
 * @date 2021.01.29
 */
public class CollectsTest {

	@Test
	public void testMapList() {
		List<Integer> origin = Arrays.asList(0, 1, 2, 3, 4, 5);
		List<Integer> back = new ArrayList<>(origin);
		List<Integer> extracted = Collects.mapList(origin, i -> i + 1);
		for (int i = 0; i < extracted.size(); i++) {
			assertEquals(i + 1, (int) extracted.get(i));
		}
		assertNotSame(origin, extracted);
		assertEquals(origin, back);
	}

	@Test
	public void testFilter() {
		Set<Integer> origin = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5));
		Set<Integer> back = new HashSet<>(origin);
		Set<Integer> filtered = Collects.filter(origin, i -> i % 2 == 0);
		assertEquals(new HashSet<>(Arrays.asList(0, 2, 4)), filtered);
		assertNotSame(origin, filtered);
		assertEquals(origin, back);
	}

	@Test
	public void testFilterList() {
		List<Integer> origin = Arrays.asList(0, 1, 2, 3, 4, 5);
		List<Integer> back = new ArrayList<>(origin);
		List<Integer> filtered = Collects.filter(origin, i -> i % 2 == 0);
		assertEquals(Arrays.asList(0, 2, 4), filtered);
		assertNotSame(origin, filtered);
		assertEquals(origin, back);
	}

	@Test
	public void testUnfilter() {
		Set<Integer> origin = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5));
		Set<Integer> back = new HashSet<>(origin);
		Set<Integer> unfiltered = Collects.unfilter(origin, i -> i % 2 == 0);
		assertEquals(new HashSet<>(Arrays.asList(1, 3, 5)), unfiltered);
		assertNotSame(origin, unfiltered);
		assertEquals(origin, back);
	}

	@Test
	public void testUnfilterList() {
		List<Integer> origin = Arrays.asList(0, 1, 2, 3, 4, 5);
		List<Integer> back = new LinkedList<>(origin);
		List<Integer> unfiltered = Collects.unfilter(origin, i -> i % 2 == 0);
		assertEquals(Arrays.asList(1, 3, 5), unfiltered);
		assertNotSame(origin, unfiltered);
		assertEquals(origin, back);
	}

	@Test
	public void testMerge() {
		List<String> list1 = Arrays.asList("a", "b", "def");
		List<String> list2 = Arrays.asList("1", "2", "000");
		List<String> back1 = new ArrayList<>(list1);
		List<String> back2 = new ArrayList<>(list2);
		List<String> union = Collects.merge(list1, list2);
		assertEquals(list1.size() + list2.size(), union.size());
		for (int i = 0; i < list1.size() + list2.size(); i++) {
			assertEquals(i >= list1.size() ? list2.get(i - list1.size()) : list1.get(i), union.get(i));
		}
		assertNotSame(list1, union);
		assertNotSame(list2, union);
		assertEquals(back1, list1);
		assertEquals(back2, list2);
	}

}
