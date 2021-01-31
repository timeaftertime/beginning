package cn.milai.common.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cn.milai.common.base.Charsets;
import cn.milai.common.ex.unchecked.RethrownException;
import cn.milai.common.ex.unchecked.Uncheckeds;

/**
 * 输入流相关工具类
 * @author milai
 * @date 2021.01.27
 */
public class InputStreams {

	private InputStreams() {}

	private static final String READ_INPUTSTREAM_ERROR = "读取输入流失败";

	/**
	 * 从输入流读取所有字节，并返回对应的字节数组
	 * @param in
	 * @return
	 * @throws RethrownException
	 */
	public static byte[] toBytes(InputStream in) throws RethrownException {
		return Uncheckeds.rethrow(() -> {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int count = 0;
			while ((count = (in.read(buf))) > 0) {
				out.write(buf, 0, count);
			}
			in.close();
			return out.toByteArray();
		}, READ_INPUTSTREAM_ERROR);
	}

	/**
	 * 从输入流中读取字节，直到填满字节数组或读完输入流
	 * 返回读取的字节数
	 * @param in
	 * @param data
	 * @return
	 * @throws
	 */
	public static int toBytes(InputStream in, byte[] data) throws RethrownException {
		return Uncheckeds.rethrow(() -> {
			int off = 0;
			int read = -1;
			int len = data.length;
			while (off < data.length && (read = in.read(data, off, len)) != -1) {
				off += read;
				len -= read;
			}
			return off;
		}, READ_INPUTSTREAM_ERROR);
	}

	/**
	 * 将字节数组转换为输入流
	 * @param data
	 * @return
	 */
	public static InputStream toInputStream(byte[] data) {
		return new ByteArrayInputStream(data);
	}

	/**
	 * 以 UTF-8 编码逐行读取输入流
	 * 返回每行组成的 List
	 * @param in
	 * @return
	 * @throws RethrownException
	 */
	public static List<String> readLines(InputStream in) throws RethrownException {
		return Uncheckeds.rethrow(() -> {
			List<String> lines = new ArrayList<>();
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charsets.UTF_8))) {
				String line = null;
				while ((line = reader.readLine()) != null) {
					lines.add(line);
				}
				in.close();
			}
			return lines;
		}, READ_INPUTSTREAM_ERROR);
	}

	/**
	 * 以 UTF-8 读取输入流所有数据，并转换为以 \n 表示换行的整个字符串
	 * @param in
	 * @return
	 * @throws RethrownException
	 */
	public static String readString(InputStream in) throws RethrownException {
		return String.join("\n", readLines(in));
	}
}
