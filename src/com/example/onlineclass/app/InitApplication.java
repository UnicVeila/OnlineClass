package com.example.onlineclass.app;

import java.util.LinkedList;

import com.example.onlineclass.utils.AppConstant;

import android.app.Activity;
import android.app.Application;

/**
 * @author anumbrella
 * 
 * @date 2015-8-20 下午1:38:04
 * 
 *       应用程序真正的入口（应用程序的生命周期内,然后调转到MainActivity中）
 * 
 *       初始化一些全局变量
 */
public class InitApplication extends Application {

	/**
	 * 单例对象
	 */
	private static InitApplication instance;

	/**
	 * 存放Activity的栈
	 */
	private static LinkedList<Activity> activityList;

	/**
	 * 登录标示
	 */
	private static boolean isLogin = false;

	/**
	 * 当前操作的Activity
	 */
	private Activity activity;

	@Override
	public void onCreate() {

		instance = this;
		// 启动捕获异常日志记录监听检查
		if (AppConstant.CHECK_UP) {

		}
		activityList = new LinkedList<Activity>();
		super.onCreate();
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Activity getActivity() {
		return this.activity;
	}

	public static InitApplication getInstance() {
		return instance;
	}

	/**
	 * 
	 * 当某个Activity关闭时删除掉某个Activity
	 * 
	 * @param a
	 */
	public void removeActivity(Activity a) {
		activityList.remove(a);
	}

	/**
	 * 当某个Activity启动时将该Activity加入到栈当中
	 * 
	 * @param a
	 */
	public void addActivity(Activity a) {
		activityList.add(a);
	}

	/**
	 * 结束掉所有的Activity进程
	 * 
	 */
	public void finishActivity() {

		for (Activity activity : activityList) {
			if (activity != null) {
				activity.finish();
			}
		}

		activityList.clear();

		// 结束掉本应用程序(application)
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	public static void setLogin(boolean judge) {
		isLogin = judge;
	}

	public static boolean isLogin() {
		return isLogin;
	}

}
