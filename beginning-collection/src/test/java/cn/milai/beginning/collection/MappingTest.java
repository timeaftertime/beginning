package cn.milai.beginning.collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * {@link Mapping} 测试类
 * @author milai
 * @date 2021.09.03
 */
public class MappingTest {

	@Test
	public void testMapList() {
		List<Integer> origin = Arrays.asList(0, 1, 2, 3, 4, 5);
		List<Integer> back = new ArrayList<>(origin);
		List<Integer> extracted = Mapping.list(origin, i -> i + 1);
		for (int i = 0; i < extracted.size(); i++) {
			assertEquals(i + 1, (int) extracted.get(i));
		}
		assertNotSame(origin, extracted);
		assertEquals(origin, back);
	}

	@Test
	public void testMapSet() {
		Set<String> origin = TestUtil.newSet("abc", "def", "xyz");
		Set<String> back = new HashSet<>(origin);
		Set<Character> extracted = Mapping.set(origin, i -> i.charAt(1));
		assertNotSame(origin, extracted);
		assertEquals(origin, back);
		assertEquals(TestUtil.newSet('b', 'e', 'y'), extracted);
	}

	@Test
	public void testReduceList() {
		List<String> list1 = Arrays.asList("010", "20", "ab");
		List<Character> reduced = Mapping.reduceList(list1, s -> Arrays.asList(toCharacters(s)));
		assertEquals(Arrays.asList('0', '1', '0', '2', '0', 'a', 'b'), reduced);
	}

	@Test
	public void testReduceSet() {
		Set<List<Integer>> set1 = TestUtil.newSet(Arrays.asList(1, 10, 100), Arrays.asList(2, 200, 20));
		Set<Integer> reduced = Mapping.reduceSet(
			set1, list -> list.stream().map(i -> i * 2).collect(Collectors.toList())
		);
		assertEquals(TestUtil.newSet(2, 20, 200, 4, 40, 400), reduced);
	}

	@Test
	public void testGroup() {
		String marks = "!@#$%";
		String nums = "12345";
		String subs = "------";
		String letters = "qwertyui";
		List<String> list = Arrays.asList(marks, nums, subs, letters);
		Map<Integer, List<String>> map = Mapping.map(list.stream(), s -> s.length());
		assertEquals(3, map.size());
		assertEquals(map.get(5), Arrays.asList(marks, nums));
		assertEquals(map.get(6), Arrays.asList(subs));
		assertEquals(map.get(8), Arrays.asList(letters));
		assertEquals(map, Mapping.group(list, s -> s.length()));
	}

	private Character[] toCharacters(String s) {
		Character[] chs = new Character[s.length()];
		for (int i = 0; i < s.length(); i++) {
			chs[i] = s.charAt(i);
		}
		return chs;
	}
}
