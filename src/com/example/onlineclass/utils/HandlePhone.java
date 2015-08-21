package com.example.onlineclass.utils;

import java.io.File;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

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

	/**
	 * 获取手机SDCard的总大小
	 * 
	 * @return
	 */
	@SuppressLint("NewApi")
	public String getSDCardTotalSize() {
		File path = Environment.getExternalStorageDirectory();
		StatFs SDCardInfo = new StatFs(path.getPath());
		// 获取单个数据块的大小(byte为单位)
		long blockSize = SDCardInfo.getBlockSizeLong();
		// 获取所有的数据块的数量
		long allBlocks = SDCardInfo.getBlockCountLong();
		return Formatter.formatFileSize(context, blockSize * allBlocks);
	}

	/**
	 * 
	 * 获取SDCard空余的空间大小
	 * 
	 * @return
	 */
	@SuppressLint("NewApi")
	public String getSDCardFreeSize() {

		File path = Environment.getExternalStorageDirectory();
		StatFs SDCardInfo = new StatFs(path.getPath());
		// 获取单个数据块的大小(byte为单位)
		long blockSize = SDCardInfo.getBlockSizeLong();
		// 获取空余的数据块的数量
		long avaiableBlocks = SDCardInfo.getAvailableBlocksLong();
		return Formatter.formatFileSize(context, blockSize * avaiableBlocks);
	}

	/**
	 * 
	 * 获取手机的rom(内存)的总大小
	 * 
	 * @return
	 */
	@SuppressLint("NewApi")
	public String getRomTotalSize() {

		File path = Environment.getDataDirectory();
		StatFs SDCardInfo = new StatFs(path.getPath());
		// 获取单个数据块的大小(byte为单位)
		long blockSize = SDCardInfo.getBlockSizeLong();
		// 获取所有的数据块的数量
		long allBlocks = SDCardInfo.getBlockCountLong();

		return Formatter.formatFileSize(context, blockSize * allBlocks);

	}

	/**
	 * 获得手机rom(内存)可以用的空间
	 * 
	 * @return
	 */
	@SuppressLint("NewApi")
	public String getRomAvailableSize() {

		File path = Environment.getDataDirectory();
		StatFs SDCardInfo = new StatFs(path.getPath());
		// 获取单个数据块的大小(byte为单位)
		long blockSize = SDCardInfo.getBlockSizeLong();
		// 获取可用的数据块的数量
		long availableBlocks = SDCardInfo.getAvailableBlocksLong();

		return Formatter.formatFileSize(context, availableBlocks * blockSize);
	}

	/**
	 * 
	 * 将dp转换为px
	 * 
	 * @param context
	 * @param dpValue
	 * @return
	 */
	public static int dp2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 获取手机的DPI(一英寸的像素数量)
	 * 
	 * @param context
	 * @return
	 */
	public static int densityDPI(Context context) {
		return context.getResources().getDisplayMetrics().densityDpi;
	}

	/**
	 * 获取手机的屏幕的宽度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * 获取手机的屏幕的高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		return context.getResources().getDisplayMetrics().heightPixels;
	}

}
