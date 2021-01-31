package cn.milai.common.base;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link Bytes} 测试类
 * @author milai
 * @date 2021.01.31
 */
public class BytesTest {

	@Test
	public void testFromShort() throws IOException {
		for (int value : new int[] { 123, 89, -12, 0, 222, Short.MAX_VALUE, Short.MIN_VALUE }) {
			assertArrayEquals(getShortBytes(value), Bytes.fromShort(value));
		}
		try {
			Bytes.fromShort(Short.MAX_VALUE + 1);
		} catch (IllegalArgumentException e1) {
			try {
				Bytes.fromShort(Short.MIN_VALUE - 1);
			} catch (IllegalArgumentException e2) {
				return;
			}
			return;
		}
		Assert.fail();
	}

	@Test
	public void testFromInt() throws IOException {
		int value = 123;
		assertArrayEquals(getBytes(value), Bytes.fromInt(value));
		value = -128;
		assertArrayEquals(getBytes(value), Bytes.fromInt(value));
		value = 0;
		assertArrayEquals(getBytes(value), Bytes.fromInt(value));
		value = 11223344;
		assertArrayEquals(getBytes(value), Bytes.fromInt(value));
	}

	@Test
	public void testFromLong() throws IOException {
		long value = 999888777012L;
		assertArrayEquals(getBytes(value), Bytes.fromLong(value));
		value = 0L;
		assertArrayEquals(getBytes(value), Bytes.fromLong(value));
		value = -12345987600L;
		assertArrayEquals(getBytes(value), Bytes.fromLong(value));
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

	public static byte[] getShortBytes(int value) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream writer = new DataOutputStream(out);
		writer.writeShort(value);
		return out.toByteArray();
	}

	public static byte[] getBytes(float value) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream writer = new DataOutputStream(out);
		writer.writeFloat(value);
		return out.toByteArray();
	}
}
