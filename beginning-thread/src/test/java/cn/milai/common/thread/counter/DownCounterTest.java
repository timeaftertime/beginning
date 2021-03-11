package cn.milai.common.thread.counter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

/**
 * {@link DownCounter} 测试类
 * @author milai
 * @date 2021.03.09
 */
public class DownCounterTest {

	@Test
	public void testCountDown() {
		int from = 10;
		DownCounter counter = new DownCounter(from);
		for (int i = 0; i < from; i++) {
			assertEquals(from - i, counter.getCount());
			assertFalse(counter.isMet());
			counter.count();
		}
		assertEquals(0, counter.getCount());
		assertTrue(counter.isMet());
	}

	@Test
	public void testCallback() {
		AtomicInteger val = new AtomicInteger(1);
		int from = 3;
		DownCounter counter = new DownCounter(from, c -> val.decrementAndGet());
		for (int i = 0; i < from; i++) {
			assertEquals(1, val.get());
			counter.count();
		}
		assertEquals(0, val.get());
		counter.count();
		assertEquals(0, val.get());
	}
}
