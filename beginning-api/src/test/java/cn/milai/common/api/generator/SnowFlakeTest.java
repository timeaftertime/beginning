package cn.milai.common.api.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

import cn.milai.common.api.generator.SnowFlake.Builder;

/**
 * {@link SnowFlake} 测试类
 * @author milai
 * @date 2021.12.22
 */
public class SnowFlakeTest {

	@Test
	public void testNextIsGreaterAndNoRepeat() {
		SnowFlake sf = new SnowFlake(0, 1);
		int threadCnt = 15;
		Thread[] threads = new Thread[threadCnt];
		CountDownLatch latch = new CountDownLatch(threadCnt);
		Set<Long> ids = Collections.newSetFromMap(new ConcurrentHashMap<>());
		for (int i = 0; i < threadCnt; i++) {
			threads[i] = new Thread(() -> {
				latch.countDown();
				try {
					latch.await();
				} catch (InterruptedException e) {
				}
				long last = 0;
				for (int j = 0; j < 5000; j++) {
					long next = sf.next();
					assertTrue(next > last);
					assertTrue(ids.add(next));
					last = next;
				}
			});
		}
		long start = System.currentTimeMillis();
		for (int i = 0; i < threadCnt; i++) {
			threads[i].start();
		}
		assertTrue(System.currentTimeMillis() - start <= 2);
	}

	@Test
	public void testGetTime() {
		SnowFlake s1 = new SnowFlake(10, 2);
		assertEquals(3673007448352L, s1.getTime(0x7654377748392812L));
		assertEquals(2180332362467L, s1.getTime(2265767896543245678L));
		assertEquals(1640850159901L, s1.getTime(3015536389922816L));
		assertEquals(1640850202167L, s1.getTime(3015713666375680L));
		assertEquals(1640850481607L, s1.getTime(3016885722685440L));
		SnowFlake s2 = new SnowFlake.Builder()
			.datacenterBits(2)
			.datacenter(2)
			.machineBits(3)
			.machine(5)
			.timeBits(50)
			.build();
		assertEquals(1640851065201L, s2.getTime(5897135731968L));
		assertEquals(1640851103706L, s2.getTime(5897451164928L));
		assertEquals(1640851120708L, s2.getTime(5897590445312L));
		assertEquals(1640851216390L, s2.getTime(5898374272256L));
		assertEquals(1640131200000L, s2.getTime(0L));
	}

	@Test
	public void testNoInvalidParam() {
		Builder builder = new SnowFlake.Builder();
		try {
			builder.datacenter(-1);
		} catch (IllegalArgumentException e1) {
			try {
				builder.datacenterBits(0);
			} catch (IllegalArgumentException e2) {
				try {
					builder.timeBits(-2);
				} catch (IllegalArgumentException e3) {
					builder.datacenter(1).build();
					return;
				}
				fail();
			}
			fail();
		}
		fail();
	}

}
