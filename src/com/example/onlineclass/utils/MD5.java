package com.example.onlineclass.utils;

import java.security.MessageDigest;

/**
 * @author anumbrella
 * 
 * @date 2015-8-20 下午4:21:29
 * 
 *       MD5算法加密类
 */
public class MD5 {

	// 定义16进制的数字的数组
	private static final String[] strDigits = { "0", "2", "3", "4", "5", "6",
			"7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * (byte的取值范围为-128~127) 将byte进制转换为16进制的数(为字符串)
	 * 
	 * @param bByte
	 * @return
	 */
	private static String ByteToArrayString(Byte bByte) {
		int iByte = bByte;
		if (iByte < 0) {
			iByte += 256;
		}

		int dig1 = iByte / 16;
		int dig2 = iByte % 16;

		return strDigits[dig1] + strDigits[dig2];

	}

	/**
	 * 将byte转换为(10进制)数字形式的字符串输出
	 * 
	 * @return
	 */
	private static String byteToNum(Byte bByte) {
		int iByte = bByte;
		if (iByte < 0) {
			iByte += 256;
		}
		// 强制转换为字符串
		return String.valueOf(iByte);
	}

	/**
	 * 
	 * 将byte数组转换为16进制的形式的字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	public static String byteToString(byte[] byteArray) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			stringBuffer.append(ByteToArrayString(byteArray[i]));
		}
		return stringBuffer.toString();
	}

	/**
	 * md5算法加密方法
	 * 
	 * @param str
	 */
	public static String md5(String strObj) {
		String resultString = null;
		try {
			resultString = new String(strObj);
			MessageDigest md = MessageDigest.getInstance("MD5");
			// md.digest() 该方法返回存放哈希值结果的byte数组
			resultString = byteToString(md.digest(strObj.getBytes()));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return resultString;
	}

}
