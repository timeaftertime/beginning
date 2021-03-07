package cn.milai.common.io;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * IO 相关单元测试的资源
 * @author milai
 * @date 2021.01.29
 */
public class TestRes {

	public static final String THIS_IS = "testToBytes.txt";

	public static final String EMPTY_FILE = "testSaveRethrow.txt";

	public static final String NOT_EXISTS_FILE = "file~not@exists..";

	public static final List<String> THIS_IS_LINES = Arrays.asList("这是中文。", "This is English.", "これは日本語です.");

	public static final byte[] THIS_IS_BYTES = String.join(System.lineSeparator(), THIS_IS_LINES).getBytes(
		StandardCharsets.UTF_8
	);

	/**
	 * 获取文件 {@link #THIS_IS} 中的字节数据
	 * @return
	 */
	public static byte[] thisIsBytes() {
		return Arrays.copyOf(THIS_IS_BYTES, THIS_IS_BYTES.length);
	}

	/**
	 * 获取文件 {@link #THIS_IS} 中的字符串数据
	 * @return
	 */
	public static List<String> thisIsLines() {
		return new ArrayList<>(THIS_IS_LINES);
	}

	/**
	 * 获取测试文件的绝对路径
	 * @param fileName 相对于 test class path 的文件路径
	 * @return
	 */
	public static String getTestFilePath(String fileName) {
		return FilesTest.class.getResource("/").getPath() + fileName;
	}

}
