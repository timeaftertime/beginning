package cn.milai.common.io;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;

import cn.milai.common.ex.unchecked.RethrownException;

/**
 * {@link Files} 的测试类
 * @author milai
 * @date 2021.01.28
 */
public class FilesTest {

	@Test
	public void testToBytes() {
		File file = new File(TestRes.getTestFilePath(TestRes.THIS_IS));
		assertTrue(file.exists());
		assertArrayEquals(TestRes.thisIsBytes(), Files.toBytes(file));
	}

	@Test
	public void testFileNotExists() {
		String fileNotExists = TestRes.getTestFilePath(TestRes.NOT_EXISTS_FILE);
		try {
			Files.toBytes(new File(fileNotExists));
		} catch (RethrownException e) {
			assertEquals(FileNotFoundException.class, e.getCause().getClass());
			return;
		}
		Assert.fail();
	}

	@Test
	public void testSave() throws FileNotFoundException, IOException {
		String pathname = TestRes.getTestFilePath(TestRes.EMPTY_FILE);
		File file = new File(pathname);
		if (file.exists()) {
			file.delete();
		}
		byte[] bytes = "这是一段需要写的文件，\n包含 English，,.?".getBytes(StandardCharsets.UTF_8);
		Files.saveRethrow(pathname, bytes);
		assertTrue(file.exists());
		assertArrayEquals(bytes, read(file));
	}

	@Test
	public void testOverride() throws IOException {
		String pathname = TestRes.getTestFilePath("testOverride.txt");
		File file = new File(pathname);
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
		assertTrue(file.exists());
		assertArrayEquals(new byte[] {}, Files.toBytes(file));
		byte[] bytes = "some data....".getBytes(StandardCharsets.UTF_8);
		Files.saveRethrow(pathname, bytes, false);
		assertArrayEquals(new byte[] {}, Files.toBytes(file));
		Files.saveRethrow(pathname, bytes, true);
		assertArrayEquals(bytes, Files.toBytes(file));
	}

	private static byte[] read(File file) throws FileNotFoundException, IOException {
		try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			return bytes;
		}
	}

}
