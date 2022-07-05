package cn.milai.common.base;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.junit.Test;

/**
 * {@link BytesBuilder} 测试类
 * @author milai
 * @date 2021.10.06
 */
public class BytesBuilderTest {

	@Test
	public void testAppendAndToBytes() throws IOException {
		String s = "test字符串拼接 ";
		int b = 23;
		int sh = -12387;
		byte[] bs = new byte[] { 0x8, 0x70 };
		BytesBuilder builder = new BytesBuilder();
		builder.append(s).appendInt8(b).appendInt16(sh).append(bs);
		assertSame(builder, builder.append(new byte[] {}));

		DataInputStream in = new DataInputStream(new ByteArrayInputStream(builder.toBytes()));
		assertEquals(s, in.readUTF());
		assertEquals(b, in.readByte());
		assertEquals(sh, in.readShort());
		byte[] res = new byte[bs.length];
		in.readFully(res);
		assertArrayEquals(bs, res);
	}
}
