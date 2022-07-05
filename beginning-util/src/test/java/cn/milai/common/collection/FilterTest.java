package cn.milai.common.collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * {@link Filter} 测试类
 * @author milai
 * @date 2021.09.01
 */
public class FilterTest {

	@Test
	public void testFilterSet() {
		Set<Integer> s1 = TestUtil.newSet(1, 2, 2, 8);
		assertNotSame(Filter.set(s1, i -> true), s1);
		assertEquals(TestUtil.newSet(1), Filter.set(s1, i -> i < 2));
		assertEquals(TestUtil.newSet(1, 8), Filter.set(s1, i -> i != 2));
		assertEquals(TestUtil.newSet(1, 2, 8), s1);
		assertEquals(TestUtil.newSet(8), Filter.nset(s1, i -> i < 7));
		assertEquals(TestUtil.newSet(1), Filter.nset(s1, i -> i % 2 == 0));

		assertNotSame(Filter.set(s1.stream(), i -> true), s1);
		assertEquals(TestUtil.newSet(1), Filter.set(s1.stream(), i -> i < 2));
		assertEquals(TestUtil.newSet(1), Filter.nset(s1.stream(), i -> i % 2 == 0));
	}

	@Test
	public void testFilterList() {
		List<String> list1 = Arrays.asList("888", "aaaa", "测试", "~");
		assertNotSame(Filter.list(list1, i -> true), list1);
		assertEquals(Arrays.asList("~"), Filter.list(list1, s -> s.length() == 1));
		assertEquals(Arrays.asList("aaaa"), Filter.list(list1, s -> s.contains("a")));
		assertEquals(Arrays.asList("888", "aaaa", "测试", "~"), list1);
		assertEquals(Arrays.asList("aaaa", "~"), Filter.nlist(list1, s -> s.equals("测试") || s.equals("888")));
		assertEquals(Arrays.asList(), Filter.nlist(list1, s -> true));

		assertEquals(Arrays.asList("aaaa", "~"), Filter.nlist(list1.stream(), s -> s.equals("测试") || s.equals("888")));
		assertEquals(Arrays.asList(), Filter.nlist(list1.stream(), s -> true));
	}

	@Test
	public void testRemain() {
		List<Integer> list1 = new ArrayList<>();
		list1.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		List<Integer> list2 = Filter.remain(list1, i -> i % 2 == 0);
		assertSame(list1, list2);
		assertEquals(Arrays.asList(2, 4, 6), list1);
		List<String> list = new ArrayList<>();
		String e1 = "这是一个字符串";
		String e2 = "abc";
		String e3 = "-===";
		String e4 = "00000";
		list.addAll(Arrays.asList(e1, e2, e3, e4));
		List<String> filtered = Filter.remain(list, e -> e.length() <= 3);
		assertSame(list, filtered);
		assertEquals(Arrays.asList(e2), list);
	}

	@Test
	public void testRemove() {
		Set<Long> s1 = TestUtil.newSet(9L, 8L, -1L, -2L);
		Set<Long> s2 = Filter.remove(s1, l -> l > 0);
		assertSame(s1, s2);
		assertEquals(TestUtil.newSet(-1L, -2L), s1);
		assertEquals(TestUtil.newSet(), Filter.remove(s1, l -> l < 0));
		List<Long> list = new ArrayList<>();
		long e1 = 123;
		long e2 = -12111;
		long e3 = 99999999999999L;
		long e4 = 0;
		long e5 = 0;
		list.addAll(Arrays.asList(e1, e2, e3, e4, e5));
		List<Long> unfiltered = Filter.remove(list, e -> e == 0);
		assertSame(list, unfiltered);
		assertEquals(Arrays.asList(e1, e2, e3), list);
	}

	@Test
	public void testFirst() {
		String s1 = "元素1";
		String s3 = "element3";
		List<String> list = Arrays.asList(s1, "元素2", s3);
		assertEquals(s3, Filter.first(list, s -> s.equals(s3)).get());
		assertEquals(s1, Filter.first(list, s -> s.length() > 2).orElse(null));
		assertEquals(null, Filter.first(list, s -> s == null).orElse(null));
	}

	@Test
	public void testStream() {
		List<Long> list = Arrays.asList(1L, 11L, 22L, 87L);
		assertEquals(Arrays.asList(22L), Filter.stream(list, n -> n % 2 == 0).collect(Collectors.toList()));
		assertEquals(Arrays.asList(1L, 11L, 87L), Filter.nstream(list, n -> n % 2 == 0).collect(Collectors.toList()));
	}

}
