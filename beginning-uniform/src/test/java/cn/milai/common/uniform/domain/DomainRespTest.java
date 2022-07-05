package cn.milai.common.uniform.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Test for {@link DomainResp}
 * @author milai
 * @date 2022.07.02
 */
public class DomainRespTest {

	static class IntDomainEvent implements DomainEvent {
		int v;

		public IntDomainEvent(int v) {
			this.v = v;
		}
	}

	@Test
	public void testCreateDomainResp() {
		String data1 = "abc";
		DomainResp<String, DomainEvent> resp1 = DomainResp.of(data1);
		assertNotNull(resp1);
		assertSame(data1, resp1.getData());
		assertNotNull(resp1.getDomainEvents());
		assertTrue(resp1.getDomainEvents().isEmpty());

		IntDomainEvent e0 = new IntDomainEvent(0);
		DomainResp<Void, DomainEvent> resp2 = DomainResp.of(e0);
		assertNull(resp2.getData());
		assertNotNull(resp2.getDomainEvents());
		assertEquals(1, resp2.getDomainEvents().size());
		assertSame(e0, resp2.getDomainEvents().get(0));

		IntDomainEvent e1 = new IntDomainEvent(3);
		IntDomainEvent e2 = new IntDomainEvent(5);
		DomainResp<Integer, IntDomainEvent> resp3 = DomainResp.of(1, e1, e2);
		assertNotNull(resp3.getData());
		assertEquals(1, (int) resp3.getData());
		assertEquals(2, resp3.getDomainEvents().size());
		assertSame(e1, resp3.getDomainEvents().get(0));
		assertSame(e2, resp3.getDomainEvents().get(1));

		try {
			resp3.getDomainEvents().add(null);
		} catch (UnsupportedOperationException e) {
			return;
		}
		fail("returning of domainEvents must be unmodifiable");
	}
}
