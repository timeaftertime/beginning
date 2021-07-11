package cn.milai.common.io;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import cn.milai.common.ex.unchecked.RethrownException;

/**
 * {@link InputStreams} 测试类
 * @author milai
 * @date 2021.01.29
 */
public class InputStreamsTest {

	@Test
	public void testToBytes() throws RethrownException, FileNotFoundException {
		assertArrayEquals(TestRes.thisIsBytes(), InputStreams.toBytes(thisIsInputStream()));
		byte[] tmp = new byte[TestRes.thisIsBytes().length];
		assertEquals(tmp.length, InputStreams.toBytes(thisIsInputStream(), tmp));
		assertArrayEquals(TestRes.thisIsBytes(), tmp);
	}

	@Test
	public void testReadLines() throws RethrownException, FileNotFoundException {
		assertEquals(TestRes.thisIsLines(), InputStreams.readLines(thisIsInputStream()));
	}

	@Test
	public void testReadString() throws RethrownException, FileNotFoundException {
		assertEquals(String.join("\n", TestRes.thisIsLines()), InputStreams.readString(thisIsInputStream()));
	}

	private static FileInputStream thisIsInputStream() throws FileNotFoundException {
		return new FileInputStream(TestRes.getTestFilePath(TestRes.THIS_IS));
	}

	@Test
	public void testFromFile() {
		try {
			InputStreams.fromFile(new File(TestRes.getTestFilePath(TestRes.NOT_EXISTS_FILE)));
		} catch (RethrownException e) {
			assertEquals(FileNotFoundException.class, e.getCause().getClass());
			return;
		}
		fail();
	}

}
