package com.example.onlineclass.app;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * @author anumbrella
 * 
 * @date 2015-8-20 下午1:42:49
 * 
 *       程序异常崩溃处理封装类
 */
public class CrashHandler implements UncaughtExceptionHandler {

	/**
	 * 错误日志文件名称
	 */
	public static final String LOG_NAME = "/crash.txt";

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {

	}

}
