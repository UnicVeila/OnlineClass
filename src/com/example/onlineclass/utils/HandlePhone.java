package com.example.onlineclass.utils;

import android.content.Context;
import android.os.Environment;

/**
 * @author anumbrella
 * 
 * @date 2015-8-21 下午4:43:54
 * 
 *       手机设备参数管理类
 */
public class HandlePhone {

	private Context context;

	public HandlePhone(Context context) {
		this.context = context;
	}

	/**
	 * 判断手机是否支持SDCard或SDCard能否使用
	 * 
	 * @return
	 */
	public static boolean ExistSDCard() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

}
