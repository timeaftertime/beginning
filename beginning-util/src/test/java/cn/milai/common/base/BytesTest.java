package cn.milai.common.base;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.junit.Test;

/**
 * {@link Bytes} 测试类
 * @author milai
 * @date 2021.01.31
 */
public class BytesTest {

	@Test
	public void testFromShort() throws IOException {
		for (int value : new int[] {
			123, 89, 0, 222,
			Short.MAX_VALUE,
			Short.MAX_VALUE + 1,
			Short.MAX_VALUE + 2,
		}) {
			assertArrayEquals(getPostiveShortBytes(value), Bytes.fromInt16(value));
		}
		for (int value : new int[] { -12, Short.MIN_VALUE, -128, -2021 }) {
			assertEquals(value, dataInput(Bytes.fromInt16(value)).readShort());
		}
	}

	@Test
	public void testFromInt() throws IOException {
		int value = 123;
		assertArrayEquals(getBytes(value), Bytes.fromInt32(value));
		value = -128;
		assertArrayEquals(getBytes(value), Bytes.fromInt32(value));
		value = 0;
		assertArrayEquals(getBytes(value), Bytes.fromInt32(value));
		value = 11223344;
		assertArrayEquals(getBytes(value), Bytes.fromInt32(value));
	}

	@Test
	public void testFromLong() throws IOException {
		long value = 999888777012L;
		assertArrayEquals(getBytes(value), Bytes.fromInt64(value));
		value = 0L;
		assertArrayEquals(getBytes(value), Bytes.fromInt64(value));
		value = -12345987600L;
		assertArrayEquals(getBytes(value), Bytes.fromInt64(value));
	}

	@Test
	public void testFromStr() throws IOException {
		String[] values = { "IFBT", "", "cn.milai.ib.compiler.constant.ByteUtilsTest", "12345" };
		for (String value : values) {
			assertArrayEquals(getMUTF8(value), Bytes.fromStr(value));
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 65536; i++) {
			sb.append('A');
		}
		try {
			Bytes.fromStr(sb.toString());
		} catch (IllegalArgumentException e) {
			return;
		}
		fail();
	}

	private byte[] getMUTF8(String str) throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(result);
		out.writeUTF(str);
		return result.toByteArray();
	}

	public static byte[] getBytes(String value) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream writer = new DataOutputStream(out);
		writer.writeUTF(value);
		return out.toByteArray();
	}

	public static byte[] getBytes(int value) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream writer = new DataOutputStream(out);
		writer.writeInt(value);
		return out.toByteArray();
	}

	public static byte[] getBytes(long value) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream writer = new DataOutputStream(out);
		writer.writeLong(value);
		return out.toByteArray();
	}

	public static byte[] getPostiveShortBytes(int value) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream writer = new DataOutputStream(out);
		writer.writeShort(value);
		return out.toByteArray();
	}

	private static DataInputStream dataInput(byte[] bytes) {
		return new DataInputStream(new ByteArrayInputStream(bytes));
	}

	public static byte[] getBytes(float value) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream writer = new DataOutputStream(out);
		writer.writeFloat(value);
		return out.toByteArray();
	}
}
