package com.example.onlineclass.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.util.Log;

/**
 * @author anumbrella
 * 
 * @date 2015-8-21 下午3:49:15
 * 
 *       app日志管理工具类
 */
public class AppLog {

	/**
	 * 日志开关
	 */
	private static final boolean LOG_OPEN_DEBUG = true;
	private static final boolean LOG_OPEN_POINT = false;

	/**
	 * 日志调试类型(必须在开启LOG_OPEN_DEBUG日志调试方可选择日志类型)
	 */
	private static final boolean logOpeni = true;
	private static final boolean logOpend = true;
	private static final boolean logOpenw = true;
	private static final boolean logOpene = true;

	/**
	 * 各个日志类型存储地址目录
	 * 
	 */
	private static final String PATH_LOG_INFO = AppConstant.APP_LOG_PATH
			+ "info/";
	private static final String PATH_LOG_DEBUG = AppConstant.APP_LOG_PATH
			+ "debug/";
	private static final String PATH_LOG_WARING = AppConstant.APP_LOG_PATH
			+ "waring/";
	private static final String PATH_LOG_ERROR = AppConstant.APP_LOG_PATH
			+ "error/";

	// 自定义各日志的显示方法
	public static void d(String tag, String message) {
		if (tag != null && message != null) {
			if (LOG_OPEN_DEBUG && logOpend) {
				Log.d(tag, message);
			}
			if (LOG_OPEN_POINT) {
				point(PATH_LOG_DEBUG, tag, message);
			}
		}

	}

	public static void i(String tag, String message) {

		if (tag != null && message != null) {
			if (LOG_OPEN_DEBUG && logOpeni) {
				Log.d(tag, message);
			}
			if (LOG_OPEN_POINT) {
				point(PATH_LOG_INFO, tag, message);
			}
		}

	}

	public static void w(String tag, String message) {
		if (tag != null && message != null) {
			if (LOG_OPEN_DEBUG && logOpenw) {
				Log.d(tag, message);
			}
			if (LOG_OPEN_POINT) {
				point(PATH_LOG_WARING, tag, message);
			}
		}

	}

	public static void e(String tag, String message) {
		if (tag != null && message != null) {
			if (LOG_OPEN_DEBUG && logOpene) {
				Log.d(tag, message);
			}
			if (LOG_OPEN_POINT) {
				point(PATH_LOG_ERROR, tag, message);
			}
		}

	}

	/**
	 * 日志要点记录文件当中
	 */
	private static void point(String logPointPath, String tag, String message) {
		// TODO Auto-generated method stub
		// 判断手机是否支持SDCard
		if (HandlePhone.ExistSDCard()) {
			Date date = new Date();
			SimpleDateFormat dataFormat = new SimpleDateFormat("",
					Locale.SIMPLIFIED_CHINESE);
			// 设置显示的时间格式
			dataFormat.applyPattern("yyyy");
			logPointPath = logPointPath + dataFormat.format(date) + "/";
			dataFormat.applyPattern("MM");
			logPointPath = logPointPath + dataFormat.format(date) + "/";
			dataFormat.applyPattern("dd");
			logPointPath += dataFormat.format(date) + ".log";
			dataFormat.applyPattern("[yyyy-MM-dd HH:mm:ss]");
			String time = dataFormat.format(date);
			File file = new File(logPointPath);
			if (!file.exists()) {
				// 创建文件
				createFileByPath(logPointPath);
			}
			BufferedWriter out = null;
			try {
				// 参数true表示文件存在，不会删除，在原文件上追加内容
				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(file, true)));
				out.write(time + " " + tag + " " + message + "\r\n");

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				try {
					if (out != null) {
						out.close();
					}
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 根据文件路径创建新的文件
	 * 
	 * @param logPointPath
	 */
	private static void createFileByPath(String filePath) {
		// TODO Auto-generated method stub
		String parentFilePath = filePath
				.substring(0, filePath.lastIndexOf("/"));
		File originalFile = new File(filePath);
		File parentFile = new File(parentFilePath);
		// 如果源文件不存在
		if (!originalFile.exists()) {
			parentFile.mkdirs();
			try {
				// 创建新文件
				originalFile.createNewFile();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}

	}

}
