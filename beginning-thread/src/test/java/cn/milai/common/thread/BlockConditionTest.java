package cn.milai.common.thread;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;

/**
 * {@link BlockCondition} 测试类
 * @author milai
 * @date 2021.03.09
 */
public class BlockConditionTest {

	@Test(timeout = 1000)
	public void testBlock() {
		Thread mainThread = Thread.currentThread();
		AtomicBoolean p1 = new AtomicBoolean();
		AtomicBoolean p2 = new AtomicBoolean();
		BlockCondition c1 = new BlockCondition(() -> p1.get(), null);
		BlockCondition c2 = new BlockCondition(() -> p2.get(), null);
		Thread childThread = new Thread(() -> {
			c1.await();
			p2.set(true);
			mainThread.interrupt();
		});
		childThread.start();
		for (int i = 0; i < 10; i++) {
			assertFalse(p1.get());
			assertFalse(p2.get());
		}
		p1.set(true);
		childThread.interrupt();
		c2.await();
		assertTrue(p2.get());
		assertFalse(Thread.interrupted());
	}
}
