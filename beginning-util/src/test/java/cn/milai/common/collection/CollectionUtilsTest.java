package cn.milai.common.collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

/**
 * {@link CollectionUtils} 测试类
 * 
 * @author milai
 *
 */
public class CollectionUtilsTest {

	@Test
	public void testIsEmpty() {
		assertTrue(CollectionUtils.isEmpty(null));
		assertTrue(CollectionUtils.isEmpty(Collections.EMPTY_LIST));
		assertTrue(CollectionUtils.isEmpty(Collections.EMPTY_SET));
		assertTrue(CollectionUtils.isEmpty(new ArrayList<>()));
		assertFalse(CollectionUtils.isEmpty(Collections.singleton("")));
	}
}
