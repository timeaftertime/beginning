package cn.milai.beginning.collection;

import static org.junit.Assert.assertArrayEquals;
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
 * {@link Merge} 测试类
 * @author milai
 * @date 2021.08.30
 */
public class MergeTest {

	@Test
	public void testList() {
		List<String> list1 = Arrays.asList("a", "b", "def");
		List<String> list2 = Arrays.asList("1", "2", "000");
		List<String> back1 = new ArrayList<>(list1);
		List<String> back2 = new ArrayList<>(list2);
		List<String> merged = Merge.list(list1, list2);
		assertEquals(list1.size() + list2.size(), merged.size());
		for (int i = 0; i < list1.size() + list2.size(); i++) {
			assertEquals(i >= list1.size() ? list2.get(i - list1.size()) : list1.get(i), merged.get(i));
		}
		assertNotSame(list1, merged);
		assertNotSame(list2, merged);
		assertEquals(back1, list1);
		assertEquals(back2, list2);
	}

	@Test
	public void testMergeToList() {
		List<Long> list = new LinkedList<>();
		List<Long> res1 = Merge.list(list, 1L, 2L, 4L);
		assertNotSame(res1, list);
		assertEquals(Arrays.asList(1L, 2L, 4L), res1);
		List<Long> res2 = Merge.list(list, 2L, 8L);
		assertNotSame(res2, list);
		assertEquals(Arrays.asList(2L, 8L), res2);
	}

	@Test
	public void testSet() {
		Set<String> list1 = TestUtil.newSet("a", "b", "def");
		Set<String> list2 = TestUtil.newSet("1", "2", "000");
		Set<String> back1 = new HashSet<>(list1);
		Set<String> back2 = new HashSet<>(list2);
		Set<String> merged = Merge.set(list1, list2);
		assertEquals(list1.size() + list2.size(), merged.size());
		assertEquals(TestUtil.newSet("1", "2", "a", "b", "000", "def"), merged);
		assertNotSame(list1, merged);
		assertNotSame(list2, merged);
		assertEquals(back1, list1);
		assertEquals(back2, list2);
	}

	@Test
	public void testArray() {
		assertArrayEquals(new byte[] { 1, 2, 3 }, Merge.array(new byte[] { 1, 2, 3 }));
		assertArrayEquals(new byte[] { 1, 2, 3 }, Merge.array(new byte[] { 1, 2 }, (byte) 3));
		assertArrayEquals(new byte[] { 1, 2, 3, 4 }, Merge.array(new byte[] { 1, 2 }, new byte[] { 3, 4 }));
		assertArrayEquals(new byte[] { 1, 2, 3, 4 }, Merge.array(new byte[] { 1, 2 }, (byte) 3, (byte) 4));
	}

	@Test
	public void testListDuplicate() {
		List<Integer> list1 = Arrays.asList(1, 2);
		List<Integer> list2 = Arrays.asList(1, 2);
		List<Integer> list3 = Merge.list(list1, list2);
		assertEquals(4, list3.size());
		assertEquals(list3, Arrays.asList(1, 2, 1, 2));

		byte[] b1 = new byte[] { 3, 4 };
		byte[] b2 = new byte[] { 3, 4 };
		byte[] b3 = Merge.array(b1, b2);
		assertEquals(4, b3.length);
		assertArrayEquals(new byte[] { 3, 4, 3, 4 }, b3);
	}
}
