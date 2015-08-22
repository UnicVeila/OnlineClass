package com.example.onlineclass.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author anumbrella
 * 
 * @date 2015-8-21 下午3:39:38
 * 
 *       文件处理类工具类
 * 
 */
public class FileUtil {

	/**
	 * 日志记录TAG标签
	 */
	private static final String TAG = "FileUtil";

	/**
	 * 根据文件路径 递归创建文件
	 * 
	 * @param filePath
	 */
	public static void createDipPath(String filePath) {

		String parentFilePath = filePath
				.substring(0, filePath.lastIndexOf("/"));
		File originalFile = new File(filePath);
		File parentFile = new File(parentFilePath);
		if (!originalFile.exists()) {
			parentFile.mkdirs();
			try {
				originalFile.createNewFile();
				AppLog.i(TAG, "Create a new file :" + filePath);
			} catch (Exception e) {
				// TODO: handle exception
				// 记录日志的错误信息
				AppLog.e(TAG, e.getMessage());
			}
		}
	}

	/**
	 * 根据文件的路径 删除文件
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean deleteFile(String filePath) {
		boolean result = false;
		File file = new File(filePath);
		if (file.exists()) {
			result = file.delete();
		}
		return result;
	}

	/**
	 * 复制文件方法(根据路径复制)
	 * 
	 * @param sourcePath
	 *            源文件路径
	 * @param toPath
	 *            目标路径
	 */
	public static void copyFile(String sourcePath, String toPath) {
		File sourceFile = new File(sourcePath);
		File targetFile = new File(toPath);
		// 根据路径创建文件
		createDipPath(toPath);
		try {
			// 定义文件的输入、输出流
			BufferedOutputStream outBuff = null;
			BufferedInputStream inBuff = null;
			try {
				// 新建文件输入流并对它进行缓冲
				inBuff = new BufferedInputStream(
						new FileInputStream(sourceFile));
				// 新建文件输出流并对它进行缓冲
				outBuff = new BufferedOutputStream(new FileOutputStream(
						targetFile));
				// 缓冲数组
				byte[] b = new byte[1024 * 5];
				int length = 0;
				while ((length = inBuff.read(b)) != -1) {
					outBuff.write(b, 0, length);
				}
				// 刷新此缓冲的输出流
				outBuff.flush();

			} finally {
				if (inBuff != null) {
					inBuff.close();
				}
				if (outBuff != null) {
					outBuff.close();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 复制文件方法(直接复制文件)
	 * 
	 * @param sourceFile
	 *            源文件
	 * @param targetFile
	 *            目标文件
	 */
	public static void copyFile(File sourceFile, File targetFile) {
		try {
			BufferedOutputStream outBuff = null;
			BufferedInputStream inBuff = null;
			try {
				// 创建文件输出流并对其进行缓冲
				outBuff = new BufferedOutputStream(new FileOutputStream(
						targetFile));
				// 创建文件输入流并对其进行缓冲
				inBuff = new BufferedInputStream(
						new FileInputStream(sourceFile));

				// 缓冲数组
				byte[] b = new byte[1024 * 5];

				int length = 0;
				while ((length = inBuff.read(b)) != -1) {
					outBuff.write(b, 0, length);
				}
				// 刷新此缓冲的输出流
				outBuff.flush();

			} finally {
				if (inBuff != null) {
					inBuff.close();
				}
				if (outBuff != null) {
					outBuff.close();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
