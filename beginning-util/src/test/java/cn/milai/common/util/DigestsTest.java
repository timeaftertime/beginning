package cn.milai.common.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DigestsTest {

	@Test
	public void testSHA256() {
		assertEquals("765DBB8C38A58A5DC019D7B3133DFFB251D643CB291328AD8E86D4F05655E68B", Digests.sha256("ib"));
		assertEquals("6ED231FF4D6DD3F9AA00DA9AA2E7E7D6C67F35EA31EC290EDD41E51251591496", Digests.sha256("ib-repo"));
		assertEquals(
			"428BF913B35EEF2AAC449C03DB63601ACA7260FAED7A6EC892B62FFF1E8FFB88", Digests.sha256(
				"DigestUtil"
			)
		);
	}

}
