package com.example.onlineclass.utils;

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

	}

}
