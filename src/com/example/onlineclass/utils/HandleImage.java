package com.example.onlineclass.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * @author anumbrella
 * 
 * @date 2015-8-20 下午7:07:58
 * 
 *       图片处理类(封装包含各种图片处理方法)
 */
public class HandleImage {

	/**
	 * 把图片转换为圆角(不能自定义角度)
	 * 
	 * @param bitMap
	 *            图片资源
	 * @return
	 */
	public static Bitmap getRoundedCornerbitmap(Bitmap bitmap) {
		Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(outBitmap);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		// rect的精度是int
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		// rectf的精度是float
		final RectF rectF = new RectF(rect);
		float roundPx = 90; // 圆角的角度
		paint.setAntiAlias(true);
		// 黑色画布(不透明)
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		/**
		 * 设置图像的相交模式 这是一个非常强大的转换模式，使用它， 可以使用图像合成的16条Porter-Duff规则的任意
		 * 一条来控制Paint如何与已有的Canvas图像进行交互。 在两者相交的地方绘制源图像，并且绘制的效果会受到目标图像对应地方透明度的影响
		 * (绘制出来的图像只有源图)
		 * */
		paint.setXfermode(new PorterDuffXfermode(
				android.graphics.PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return outBitmap;
	}

	/**
	 * 把图片转换为圆角(可以自定义角度)
	 * 
	 * @param bitMap
	 *            图片资源
	 * @param angle
	 *            图片角度
	 * @return
	 */
	public static Bitmap getRoundedCornerbitmap(Bitmap bitmap, float angle) {

		Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(outBitmap);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		// rect的精度是int
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		// rectf的精度是float
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		// 黑色画布(不透明)
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, angle, angle, paint);
		/**
		 * 设置图像的相交模式 这是一个非常强大的转换模式，使用它， 可以使用图像合成的16条Porter-Duff规则的任意
		 * 一条来控制Paint如何与已有的Canvas图像进行交互。 在两者相交的地方绘制源图像，并且绘制的效果会受到目标图像对应地方透明度的影响
		 * (绘制出来的图像只有源图)
		 * */
		paint.setXfermode(new PorterDuffXfermode(
				android.graphics.PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return outBitmap;
	}

	/**
	 * 
	 * 根据路径获得图片并压缩返回Bitmap用于显示(不可以自定义高宽)
	 * 
	 * @param filePath
	 *            图片路径
	 * @return
	 */
	public static Bitmap getSmallbitmap(String filePath) {
		final BitmapFactory.Options openOptions = new BitmapFactory.Options();
		// 不会返回Bitmap,仅仅返回一个宽和高的数据
		openOptions.inJustDecodeBounds = true;
		// 定义缩放的比例
		openOptions.inSampleSize = 2;

		// 为了防止OOM(内存溢出)设置
		openOptions.inJustDecodeBounds = false;
		// 不进行图片抖动处理
		openOptions.inDither = false;
		// 设置让编码器以最佳的方式解码
		openOptions.inPreferredConfig = null;

		openOptions.inPurgeable = true;
		openOptions.inInputShareable = true;

		// 该方法在图片很大时可能出现内存溢出情况
		return BitmapFactory.decodeFile(filePath, openOptions);
	}

	/**
	 * 根据路径获得图片并压缩返回bitMap用于显示(可以自定义高宽)
	 * 
	 * @param filePath
	 *            图片路径
	 * @param width
	 *            返回图片宽度
	 * @param height
	 *            返回图片高度
	 * @return
	 */
	public static Bitmap getSmallbitmap(String filePath, int width, int height) {

		final BitmapFactory.Options openOptions = new BitmapFactory.Options();
		// 不会返回Bitmap,仅仅返回一个宽和高的数据
		openOptions.inJustDecodeBounds = true;
		// 定义缩放的比例
		openOptions.inSampleSize = calculateInSampleSize(openOptions, width,
				height);

		// 为了防止OOM(内存溢出)设置
		openOptions.inJustDecodeBounds = false;
		// 不进行图片抖动处理
		openOptions.inDither = false;
		// 设置让编码器以最佳的方式解码
		openOptions.inPreferredConfig = null;

		openOptions.inPurgeable = true;
		openOptions.inInputShareable = true;

		// 该方法在图片很大时可能出现内存溢出情况
		return BitmapFactory.decodeFile(filePath, openOptions);
	}

	/**
	 * 
	 * 计算图片的缩放值
	 * 
	 * @return 返回缩放率
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// 所返回的Bitmap的宽和高
		final int height = options.outHeight;
		final int width = options.outWidth;
		// 默认情况下保持原来的比例不变
		int inSampleSize = 1;
		if (reqHeight > height || reqWidth > width) {

			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// 两个比例中取最小的缩放比例
			inSampleSize = widthRatio > heightRatio ? heightRatio : widthRatio;

		}

		return inSampleSize;
	}

	/**
	 * 将Bitmap保存到本地
	 * 
	 * @param bitmap   原图资源
	 * @param imagePath  图片资源
	 * @param imageType  图片类型PNG、JPEG
	 */
	public static void savebitmap(Bitmap bitmap, String imagePath,
			String imageType) {
		try {
			File file = new File(imagePath);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}

	/**
	 * 根据指定的大小压缩图片
	 * 
	 * @return
	 */
	public static String compressImage() {
		return null;
	}

	/**
	 * 获得网络图片并缓存本地
	 * 
	 * @param url
	 * @param localPath
	 * @return
	 */
	public static Bitmap getbitmap(String url, String localPath) {

		return null;
	}

	/**
	 * 
	 * 获得网络图片
	 * 
	 * @param url
	 * @return
	 */
	public static Bitmap getbitmap(String url) {
		return null;
	}

	/**
	 * 获得本地的图片
	 * 
	 * @param localPath
	 *            本地图片的地址
	 * @return
	 */
	public static Bitmap getLocalBitmap(String localPath) {
		return null;
	}

	/**
	 * 复制分享app图片到本地目录
	 * 
	 * @return
	 */
	public static boolean copyAppFileToSDCard() {

		return false;
	}

	/**
	 * 批量图片压缩到指定目录
	 * 
	 * @param images
	 * @param compressDir
	 * @return
	 */
	public static ArrayList<String> compressImage(List<String> images,
			String compressDir) {

		return null;
	}

	/**
	 * 
	 * bitMap图片压缩
	 * 
	 * @return
	 */
	public static Bitmap comp() {
		return null;
	}

	/**
	 * 本地图片压缩
	 * 
	 * @return
	 */
	public static Bitmap compressLocalImage() {

		return null;
	}

	/**
	 * 图片质量压缩
	 * 
	 * @param bitmap
	 * @param size
	 * @return
	 */
	public static Bitmap compressQualityImage(Bitmap bitmap, int size) {
		return null;
	}

	/**
	 * 旋转图片
	 * 
	 * @param angle
	 * @param bitmap
	 * @return
	 */
	public static Bitmap rotateImageView(int angle, Bitmap bitmap) {
		return null;
	}

	/**
	 * 读取图片的属性:图片旋转的角度
	 * 
	 * @param imagePath
	 * @return
	 */
	public static int readPictureDegree(String imagePath) {
		return 0;
	}

	/**
	 * 读取本地的图片得到缩略图，如图片需要旋转则旋转
	 * 
	 * @param path
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap getLocalThumbImage(String path, int width, int height) {
		return null;
	}

	/**
	 * 图片去色,返回灰色图片
	 * 
	 * @param bitmapOriginal
	 * @return
	 */
	public static Bitmap wipeGrayscale(Bitmap bitmapOriginal) {
		return null;
	}

	/**
	 * 图片去色并加上圆角
	 * 
	 * @param bitmapOriginal
	 * @param pixels
	 * @return
	 */
	public static Bitmap wipeGrayscaleAddRoundCorner(Bitmap bitmapOriginal,
			int pixels) {
		return null;
	}

	/**
	 * 把图片变成圆角
	 * 
	 * @param bitmap
	 * @param pixels
	 * @return
	 */
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
		return null;
	}

	/**
	 * 把图片变成圆角，同时支持BitmapDrawable
	 * 
	 * @param bitmapDrawable
	 * @param pixels
	 * @return
	 */
	public static Bitmap toRoundCorner(BitmapDrawable bitmapDrawable, int pixels) {
		return null;
	}

	/**
	 * 
	 * 为图片创建(水印)
	 * 
	 * @param src
	 * @param watermark
	 * @return
	 */
	public static Bitmap createBitmapForWatrmark(Bitmap src, Bitmap watermark) {
		return null;
	}

	/**
	 * 图片合成
	 * 
	 * @param direction
	 * @param bitmaps
	 * @return
	 */
	public static Bitmap pictureToMix(int direction, Bitmap... bitmaps) {
		return null;
	}

	/**
	 * 
	 * 将两张图片合成，指定小图片的宽和高放在大图片的正中间位置
	 * 
	 * @param bigBitmap
	 * @param smallBitmap
	 * @param width
	 * @param height
	 * @param pixels
	 * @return
	 */
	public static Bitmap pictureToMix(Bitmap bigBitmap, Bitmap smallBitmap,
			int width, int height, int pixels) {
		return null;
	}

	/**
	 * 保存图片为PNG格式
	 * 
	 * @param bitmap
	 * @param name
	 */
	public static void savePNG(Bitmap bitmap, String name) {

	}

	/**
	 * 保存图片为JPGE格式
	 * 
	 * @param bitmap
	 * @param path
	 */
	public static void saveJPGE(Bitmap bitmap, String path) {

	}

	/**
	 * 将bitmap转换为指定大小的图片
	 * 
	 * @param bitmap
	 * @param width
	 * @param heihgt
	 * @return
	 */
	public static Bitmap createBitmapBySize(Bitmap bitmap, int width, int heihgt) {
		return null;
	}

	/**
	 * 将Bitmap 装换为 Drawable
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Drawable bitmapToDrawable(Bitmap bitmap) {
		return null;
	}

	/**
	 * 将Drawable 装换为 Bitmap
	 * 
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		return null;
	}

	/**
	 * 将byte[] 装换为 Bitmap
	 * 
	 * @return
	 */
	public static Bitmap bytesToBitmap() {
		return null;
	}

	/**
	 * 将Bitmap 转换为 byte[]
	 * 
	 * @return
	 */
	public static byte[] bitmapToBytes() {
		return null;
	}

}
