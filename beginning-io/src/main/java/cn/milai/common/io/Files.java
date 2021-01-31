package cn.milai.common.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.milai.common.ex.unchecked.RethrownException;
import cn.milai.common.ex.unchecked.Uncheckeds;

/**
 * {@link File} 相关工具类
 * @author milai
 * @date 2021.01.25
 */
public class Files {

	private static final Logger LOG = LoggerFactory.getLogger(Files.class);

	private static final String SAVE_FILE_ERROR = "保存文件失败: path = %s, override = %s";

	/**
	 * 保存数据到 path 指向的文件，若中间目录不存在，将被创建
	 * 若发生 {@link IOException} 将打印异常信息并转换为 {@link RethrownException}
	 * 若目标文件已存在，将被覆盖
	 * @param path
	 * @param data
	 * @throws RethrownException 若发生异常
	 */
	public static void saveRethrow(String path, byte[] data) throws RethrownException {
		saveRethrow(path, data, true);
	}

	/**
	 * 保存数据到 path 指向的文件，若中间目录不存在，将被创建
	 * 若发生 {@link IOException} 将打印异常信息并转换为 {@link RethrownException}
	 * @param path
	 * @param data
	 * @param override 目标文件已存在时是否覆盖
	 * @throws RethrownException 若发生异常
	 */
	public static void saveRethrow(String path, byte[] data, boolean override) throws RethrownException {
		Uncheckeds.rethrow(() -> doSave(path, data, override), SAVE_FILE_ERROR, path, override);
	}

	/**
	 * 保存数据到 path 指向的文件，若中间目录不存在，将被创建
	 * 若发生异常，将打印错误堆栈并忽略异常
	 * @param path
	 * @param data
	 * @param override 目标文件已存在时是否覆盖
	 */
	public static void save(String path, byte[] data, boolean override) {
		Uncheckeds.logWith(LOG, () -> doSave(path, data, override), SAVE_FILE_ERROR, path, override);
	}

	private static void doSave(String path, byte[] data, boolean override) throws IOException {
		File targetFile = new File(path);
		if (targetFile.exists() && !override) {
			LOG.info("文件已存在，不再覆盖：path = {}, override = {}", path, override);
			return;
		}
		new File(targetFile.getParent()).mkdirs();
		FileOutputStream out = new FileOutputStream(targetFile);
		out.write(data);
		out.close();
	}

	/**
	 * 读取文件并转换为字节数组
	 * @param file
	 * @return
	 * @throws RethrownException
	 */
	public static byte[] toBytes(File file) throws RethrownException {
		return Uncheckeds.rethrow(() -> {
			return InputStreams.toBytes(new FileInputStream(file));
		}, "读取文件失败, file = %s", file);
	}

	/**
	 * 判断指定路径的文件是否存在
	 * @see File#exists()
	 * @param path
	 * @return
	 */
	public static boolean exists(String path) {
		return new File(path).exists();
	}

	/**
	 * 创建指定目录的文件夹，返回是否真正创建了对应的文件夹
	 * @see File#mkdir()
	 * @param path
	 * @return
	 */
	public static boolean mkdir(String path) {
		return new File(path).mkdir();
	}

}
