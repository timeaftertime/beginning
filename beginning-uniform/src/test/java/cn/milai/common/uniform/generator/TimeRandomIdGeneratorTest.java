package cn.milai.common.uniform.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * {@link TimeRandomIdGenerator} 测试类
 * @author milai
 * @date 2021.12.30
 */
public class TimeRandomIdGeneratorTest {

	private TimeRandomIdGenerator g = new TimeRandomIdGenerator();

	@Test
	public void testFixedAndNoRepeat() {
		Set<String> pres = new HashSet<>();
		for (int i = 0; i < 1000; i++) {
			String s = g.next();
			assertEquals(TimeRandomIdGenerator.LEN, s.length());
			assertTrue(pres.add(s));
		}
	}

	@Test
	public void testGetTime() {
		assertEquals(1640872721606L, TimeRandomIdGenerator.getTime("0000017e0ba2bcc6yf43u39vu78eclhe"));
		assertEquals(1640872778737L, TimeRandomIdGenerator.getTime("0000017e0ba39bf16cw5apu3hivolxsv"));
		assertEquals(1640872799081L, TimeRandomIdGenerator.getTime("0000017e0ba3eb69mbwvr0vnjxb82rhk"));
		assertEquals(1640873022473L, TimeRandomIdGenerator.getTime("0000017e0ba75409zy712g37mbs8mxq2"));
		assertEquals(1640873053587L, TimeRandomIdGenerator.getTime("0000017e0ba7cd933wc01s3e7q9nwg4r"));
	}
}
