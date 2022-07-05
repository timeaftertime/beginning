package cn.milai.common.collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * {@link Creator} 测试类
 * @author milai
 * @date 2021.09.18
 */
public class CreatorTest {

	@Test
	public void testAsSet() {
		Set<String> set = Creator.asSet("ab", "c", "ab");
		assertEquals(TestUtil.newSet("ab", "c"), set);
		try {
			set.add("");
		} catch (UnsupportedOperationException e) {
			return;
		}
		fail();
	}

	@Test
	public void testNewCollection() {
		Set<String> set = Creator.hashSet("q", "q", "w");
		assertEquals(TestUtil.newSet("q", "w"), set);
		set.remove("q");
		assertEquals(TestUtil.newSet("w"), set);
		assertEquals(Creator.hashSet("a", "1"), Creator.hashSet(Arrays.asList("a", "1")));

		List<Integer> list = Creator.arrayList(1, 2, 3);
		assertEquals(Arrays.asList(1, 2, 3), list);
		list.add(1);
		assertEquals(Arrays.asList(1, 2, 3, 1), list);
		assertEquals(Creator.arrayList("a", "1"), Creator.arrayList(Arrays.asList("a", "1")));

		String key = "abc";
		Map<String, List<Integer>> map = Creator.hashMap(key, Creator.arrayList(1, 2, 3));
		assertEquals(1, map.size());
		assertEquals(Arrays.asList(1, 2, 3), map.get(key));
	}
}
