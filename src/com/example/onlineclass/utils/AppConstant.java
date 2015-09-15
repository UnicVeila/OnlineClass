package com.example.onlineclass.utils;

import android.os.Environment;

/**
 * @author anumbrella
 * 
 * @date 2015-8-19 下午4:09:26
 * 
 *       app应用的常量类
 */
public class AppConstant {

	/**
	 * 应用开启异常记录日志监听
	 */
	public static final boolean CHECK_UP = true;

	/**
	 * 应用程序根目录
	 */
	public static final String ROOT = Environment.getExternalStorageDirectory()
			.getPath() + "/OnlineClass/";

	/**
	 * 相机目录
	 */
	public static final String CAMERA = Environment
			.getExternalStorageDirectory().getPath() + "DCIM/Camera/";

	/**
	 * 应用程序图片目录
	 */
	public static final String CAMERA_IMG = ROOT + "cache/images/";

	/**
	 * 应用程序分享目录
	 */
	public static final String CAMERA_SHARE = ROOT + "cache/share/";

	/**
	 * 应用程序log日志目录
	 */
	public static final String APP_LOG_PATH = ROOT + "log/";

	/**
	 * 应用程序日志文件
	 */
	public static final String APP_LOGFILE = APP_LOG_PATH + "log.txt";

	/**
	 * 轮播加载的图片资源url地址
	 */
	public static String[] imageUrl = {
			"http://www.anumbrella.net/app/OnlineClass/Image/a.jpg",
			"http://www.anumbrella.net/app/OnlineClass/Image/b.jpg",
			"http://www.anumbrella.net/app/OnlineClass/Image/c.jpg",
			"http://www.anumbrella.net/app/OnlineClass/Image/d.jpg" };

}
