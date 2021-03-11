package cn.milai.common.thread.counter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;

/**
 * {@link BlockDownCounter} 测试类
 * @author milai
 * @date 2021.03.09
 */
public class BlockDownCounterTest {

	@Test(timeout = 1000)
	public void testAwait() throws InterruptedException {
		AtomicBoolean ok = new AtomicBoolean();
		Counter c1 = new BlockDownCounter(1);
		Counter c2 = new BlockDownCounter(1);
		Thread thread = new Thread(() -> {
			c1.await();
			ok.set(true);
			c2.count();
		});
		thread.start();
		for (int i = 0; i < 10; i++) {
			thread.interrupt();
			assertEquals(1, c1.getCount());
			assertEquals(1, c2.getCount());
		}
		assertFalse(ok.get());
		c1.count();
		c2.await();
		assertTrue(ok.get());
		assertFalse(Thread.interrupted());
	}
}
