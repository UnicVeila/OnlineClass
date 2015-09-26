package com.example.onlineclass.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.example.onlineclass.MainActivity;
import com.example.onlineclass.utils.AppConstant;
import com.example.onlineclass.utils.FileUtil;

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

	/**
	 * 系统默认的UncaughtException处理类
	 */
	private UncaughtExceptionHandler mDefaultHandler;

	private InitApplication application;

	public CrashHandler(InitApplication application) {

		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
		this.application = application;

	}

	/**
	 * 当UncaughtException发生时会转入该函数来处理
	 * 
	 * @param thread
	 * @param ex
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {

		if (!handleException(ex) && mDefaultHandler != null) {
			mDefaultHandler.uncaughtException(thread, ex);
		} else {
			try {
				Thread.sleep(3000);
				// 设定程序可以重启
				Intent intent = new Intent(application.getApplicationContext(),
						MainActivity.class);
				PendingIntent restartIntent = PendingIntent.getActivity(
						application.getApplicationContext(), 0, intent,
						Intent.FLAG_ACTIVITY_NEW_TASK);

				AlarmManager mgr = (AlarmManager) application
						.getSystemService(Context.ALARM_SERVICE);
				mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
						restartIntent); // 1秒钟后重启应用

				application.finishActivity();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * 自定义处理异常
	 * 
	 * @return
	 */
	private boolean handleException(final Throwable ex) {

		if (ex == null) {
			return true;
		}
		ex.printStackTrace();
		// 错误信息提示，
		new Thread() {
			@Override
			public void run() {
				Looper.prepare();
				Toast.makeText(application.getApplicationContext(),
						"应用发生异常，即将退出！", Toast.LENGTH_LONG).show();
				Looper.loop();
			};

		}.start();
		saveCrashInfoToFile(ex);
		return true;
	}

	/**
	 * 
	 * 将程序崩溃的信息保存到文件当中
	 * 
	 * @param ex
	 */
	private void saveCrashInfoToFile(Throwable ex) {
		// 获得异常崩溃的栈
		final StackTraceElement[] stack = ex.getStackTrace();
		final String message = ex.getMessage();

		// 为保存文件做准备
		String filePath = AppConstant.APP_LOG_PATH + LOG_NAME;

		File logFile = new File(filePath);

		if (!logFile.exists()) {
			FileUtil.createDipPath(filePath);
		}

		// 写入错误文件
		FileWriter fw = null;
		final String lineFeed = "\r\n";

		try {
			fw = new FileWriter(logFile, true);
			fw.write(DateFormat.format("yyyy-MM-dd kk:mm:ss",
					System.currentTimeMillis()).toString()
					+ lineFeed + lineFeed);
			fw.write(message + lineFeed);
			for (int i = 0; i < stack.length; i++) {
				fw.write(stack[i].toString() + lineFeed);
			}
			fw.write(lineFeed);
			fw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fw)
					fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
