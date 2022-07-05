package cn.milai.common.base;

import java.io.ByteArrayOutputStream;

/**
 * 类似 {@link StringBuilder} 的字节数组构造器
 * @author milai
 * @date 2021.10.06
 */
public class BytesBuilder {

	private ByteArrayOutputStream res;

	public BytesBuilder() {
		res = new ByteArrayOutputStream();
	}

	/**
	 * 添加指定字节数组到最后
	 * @param data
	 * @return
	 */
	public BytesBuilder append(byte[] data) {
		res.write(data, 0, data.length);
		return this;
	}

	/**
	 * 将一个 8 位整数添加到末尾
	 * @param data
	 * @return
	 */
	public BytesBuilder appendInt8(int data) {
		return append(Bytes.fromInt8(data));
	}

	/**
	 * 将一个字符串转换为 MUTF-8 字节数组
	 * @param data
	 * @return
	 */
	public BytesBuilder append(String data) {
		return append(Bytes.fromStr(data));
	}

	/**
	 * 将一个 16 位整数添加到末尾
	 * @param data
	 * @return
	 */
	public BytesBuilder appendInt16(int data) {
		return append(Bytes.fromInt16(data));
	}

	public BytesBuilder append(BytesBuilder bb) {
		return append(bb.toBytes());
	}

	public byte[] toBytes() {
		return res.toByteArray();
	}
}
