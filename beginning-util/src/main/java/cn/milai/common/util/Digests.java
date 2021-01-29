package cn.milai.common.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息散列相关工具类
 * @author milai
 * @date 2020.12.27
 */
public final class Digests {

	private Digests() {}

	public static final Charset UTF_8 = Charset.forName("UTF-8");

	private static final MessageDigest SHA256;

	static {
		try {
			SHA256 = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 获取 sha-256 散列后的 16 进制字符串(长度为64)
	 * @param str
	 * @return
	 */
	public static synchronized String sha256(String str) {
		byte[] digest = SHA256.digest(str.getBytes(UTF_8));
		return to16Str(digest);
	}

	/**
	 * 转换为 16 进制字符串
	 * @param digest
	 * @return
	 */
	private static String to16Str(byte[] digest) {
		StringBuilder sb = new StringBuilder();
		for (byte b : digest) {
			sb.append(to16Char((b & 0xf0) >> 4));
			sb.append(to16Char(b & 0xf));
		}
		return sb.toString();
	}

	/**
	 * 小于 16 的整数转换为长度为 2 的 16 进制字符
	 * @param value
	 * @return
	 */
	private static char to16Char(int value) {
		if (value >= 16) {
			throw new IllegalArgumentException("参数必须 < 16 :" + value);
		}
		if (value < 10) {
			return (char) ('0' + value);
		}
		return (char) ('A' + value - 10);
	}

}
