package com.example.onlineclass.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;

/**
 * @author anumbrella
 * 
 * @date 2015-8-20 下午7:07:58
 * 
 *       图片处理类(封装包含各种图片处理方法)
 */
public class HandleImage {

	/**
	 * 日志tag
	 */
	private static final String TAG = "HandleImage";

	/**
	 * 多张图片合成的指定方向
	 */
	private static final int LEFT = 0;

	private static final int RIGHT = 1;

	private static final int TOP = 2;

	private static final int BOTTOM = 3;

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
	 * @param bitmap
	 *            原图资源
	 * @param imagePath
	 *            保存图片资源(不是路径)
	 * @param imageType
	 *            图片类型PNG、JPEG
	 */
	public static void savebitmap(Bitmap bitmap, String imagePath,
			String imageType) {
		try {
			File file = new File(imagePath);
			// 传入的要是一个文件(可以虚构)
			FileUtil.createDipPath(imagePath);
			FileOutputStream outputStream = new FileOutputStream(file);
			if ("PNG".equalsIgnoreCase(imageType)) {
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
			} else if ("JPG".equalsIgnoreCase(imagePath)
					|| "JPEG".equalsIgnoreCase(imageType)) {
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
			} else if ("WEBP".equalsIgnoreCase(imageType)) {
				bitmap.compress(Bitmap.CompressFormat.WEBP, 100, outputStream);
			} else {
				AppLog.e(TAG, "图片保存失败,无法确定图片类型。类型为:" + imageType);
			}
			outputStream.flush();
			outputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * 将Bitmap保存到本地,可以指定图片保存的质量
	 * 
	 * @param bitmap
	 *            原图资源
	 * @param imagePath
	 *            保存图片资源
	 * @param quality
	 *            保存图片的质量
	 * 
	 *            默认保存为WEBP格式
	 * @return
	 */
	public static void savebitmap(Bitmap bitmap, String imagePath, int quality) {
		File file = new File(imagePath);
		FileUtil.createDipPath(imagePath);
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		bitmap.compress(Bitmap.CompressFormat.WEBP, quality, outputStream);
		try {
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 按默认比例压缩图片 默认大小为480x800
	 * 
	 * @param sourceImagePath
	 *            原图片资源
	 * @param outDirectory
	 *            目标路径
	 * @return 压缩后的图片路径,如果没有压缩，返回原路径
	 */
	public static String compressImage(String sourceImagePath,
			String outDirectory) {
		int maxWidth = 480;
		int maxHeight = 800;
		// 压缩后图片的路径
		String compressImagePath = null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(sourceImagePath, options);
		// 定义缩放比例
		double ratio = 1;
		if (options.outHeight < options.outWidth && options.outWidth > maxWidth) {
			ratio = options.outWidth / maxWidth;
		} else if (options.outHeight > options.outHeight
				&& options.outHeight > maxHeight) {
			ratio = options.outHeight / maxHeight;
		} else {
			return sourceImagePath;
		}

		BitmapFactory.Options newOptions = new BitmapFactory.Options();
		newOptions.inSampleSize = (int) (ratio + 1);
		newOptions.outHeight = (int) (options.outHeight / ratio);
		newOptions.outWidth = (int) (options.outWidth / ratio);
		Bitmap bitmap = BitmapFactory.decodeFile(sourceImagePath, newOptions);
		compressImagePath = outDirectory
				+ UUID.randomUUID().toString()
				+ sourceImagePath.substring(sourceImagePath.lastIndexOf("."),
						sourceImagePath.length());
		File outFile = new File(compressImagePath);
		try {
			File parentPath = outFile.getParentFile();
			if (!parentPath.exists()) {
				parentPath.mkdirs();
			}
			outFile.createNewFile();
			FileOutputStream outputStream = new FileOutputStream(outFile);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
			outputStream.close();
			// 回收机制(实际是促使底层释放内存)
			bitmap.recycle();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return compressImagePath;
	}

	/**
	 * 获得网络图片并缓存本地
	 * 
	 * @param url
	 * @param localPath
	 * @return
	 */
	public static Bitmap getbitmap(String url, String localPath) {
		Bitmap bitmap = null;
		File file = new File(localPath);
		if (file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				bitmap = BitmapFactory.decodeStream(inputStream);

			} catch (FileNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else {
			bitmap = getHttpBitmap(url);
		}
		return bitmap;
	}

	/**
	 * 
	 * 获得网络图片
	 * 
	 * @param url
	 * @return
	 */
	public static Bitmap getHttpBitmap(String url) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		try {
			myFileUrl = new URL(url);
			// 获得图片链接
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			// 设置超时连接 conn.setConnectionTiem(0);表示没有时间限制
			conn.setConnectTimeout(6000);
			conn.setDoInput(true); // 设置可以获得数据流
			// 设置不使用缓存
			conn.setUseCaches(false);
			// 连接
			conn.connect();
			// 获取数据流(输入流)
			InputStream inputStream = conn.getInputStream();
			// 解析图片
			bitmap = BitmapFactory.decodeStream(inputStream);
			// 关闭数据流
			inputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * 获得本地的图片
	 * 
	 * @param localPath
	 *            本地图片的地址
	 * @return
	 */
	public static Bitmap getLocalBitmap(String localPath) {
		File file = new File(localPath);
		Bitmap bitmap = null;
		if (file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				bitmap = BitmapFactory.decodeStream(inputStream);
			} catch (FileNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return bitmap;
	}

	/**
	 * 复制分享图片到本地目录
	 * 
	 * @param context
	 *            环境变量
	 * @param imageFromPath
	 *            原图的资源int值
	 * @param toPath
	 *            目标图片路径
	 * @return
	 */
	public static boolean copyAppFileToSDCard(Context context,
			int imageFromPath, String toPath) {
		// 设定复制的结果
		boolean copyResult = true;
		try {
			FileUtil.createDipPath(toPath);
			InputStream inputStream = context.getResources().openRawResource(
					imageFromPath);
			FileOutputStream outputStream = new FileOutputStream(new File(
					toPath));
			// 定义读取数组大小
			byte[] b = new byte[8 * 1024];
			int length = 0;
			while ((length = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, length);
			}
			inputStream.close();
			outputStream.close();

		} catch (Exception e) {
			// TODO: handle exception
			copyResult = false;
		}

		return copyResult;
	}

	/**
	 * 批量图片压缩到指定目录
	 * 
	 * @param imagePaths
	 *            图片路径数组
	 * @param compressDir
	 *            要压缩的目录
	 * @return
	 */
	public static ArrayList<String> compressImage(List<String> imagePaths,
			String compressDir) {
		if (imagePaths == null) {
			return null;
		}
		// 定义图片最大尺寸 300KB
		int maxSize = 300 * 1024;
		ArrayList<String> resultImagePaths = new ArrayList<String>();
		for (int i = 0; i < imagePaths.size(); i++) {
			String imagePath = imagePaths.get(i);
			File file = new File(imagePath);
			if (file.exists()) {
				// 获得图片文件的大小(byte)单位
				long length = file.length();
				if (length > maxSize) {
					BitmapFactory.Options options = new BitmapFactory.Options();
					// 获取原图的高和宽
					options.inJustDecodeBounds = true;
					// 此时所返回的值为null,即bitmap为空
					Bitmap bitmap = BitmapFactory
							.decodeFile(imagePath, options);

					int sourceWidth = options.outWidth;
					int sourceHeight = options.outHeight;

					// 根据主流手机设定缩放参考的比例(主流手机比较多是800*480分辨率)
					float compressWidth = 800f;
					float compressHeight = 480f;

					// 定义缩放率
					int ratio = 1;

					if (sourceWidth > sourceHeight
							&& sourceWidth > compressWidth) {
						ratio = (int) (sourceWidth / compressWidth);
					} else if (sourceHeight > sourceWidth
							&& sourceHeight > compressHeight) {
						ratio = (int) (sourceHeight / compressHeight);
					}
					if (ratio <= 0) {
						ratio = 1;
					}
					// 设置图片的缩放比例
					options.inSampleSize = ratio;

					options.inJustDecodeBounds = false;
					// 获得压缩的图片
					bitmap = BitmapFactory.decodeFile(imagePath, options);

					// 将图片保存
					String fileName = file.getName();
					String newFileName = null;
					if (fileName.indexOf(".") > 0) {
						newFileName = compressDir
								+ UUID.randomUUID().toString()
								+ fileName.substring(fileName.lastIndexOf("."),
										fileName.length());
					} else {
						newFileName = compressDir
								+ UUID.randomUUID().toString() + ".jpg";
					}

					File saveNewFile = new File(newFileName);
					FileUtil.createDipPath(newFileName);
					FileOutputStream outputStream = null;
					try {
						outputStream = new FileOutputStream(saveNewFile);
					} catch (FileNotFoundException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					// 开始压缩图片(默认图片全部压缩为JPEG)
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
							outputStream);
					try {
						outputStream.flush();
						outputStream.close();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					resultImagePaths.add(newFileName);
				} else {
					String newFileName = compressDir
							+ UUID.randomUUID().toString()
							+ imagePath.substring(imagePath.lastIndexOf("."),
									imagePath.length());
					FileUtil.copyFile(imagePath, newFileName);
					resultImagePaths.add(newFileName);
				}

			}

		}

		return resultImagePaths;
	}

	/**
	 * Bitmap图片压缩(按质量压缩)
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap comp(Bitmap bitmap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 把图片数据流写入到ByteArrayOutputStream的输出流当中
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		// 如果图片的大小大于512kb就进行压缩,避免（BitmapFactory.decodeStream）时溢出
		if (baos.toByteArray().length / 1024 > 512) {
			// 清空baos
			baos.reset();
			// 对图片进行一半的压缩
			bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
		}

		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		// 此时imageBitmap为空
		Bitmap imageBitmap = BitmapFactory.decodeStream(bais);

		int width = options.outWidth;
		int height = options.outHeight;

		// 现在主流手机比较多是800*480分辨率
		float compressWidth = 480f;
		float compressHeight = 800f;

		int ratio = 1;
		if (width > height && width > compressWidth) {
			ratio = (int) (width / compressWidth);
		} else if (height > width && height > compressHeight) {
			ratio = (int) (height / compressHeight);
		}

		if (ratio <= 0) {
			ratio = 1;
		}
		options.inSampleSize = ratio;
		options.inJustDecodeBounds = false;
		bais = new ByteArrayInputStream(baos.toByteArray());
		imageBitmap = BitmapFactory.decodeStream(bais, null, options);
		try {
			bais.close();
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return compressImage(imageBitmap, 100); // 图片质量进行压缩(图片资源,图片大小)
	}

	/**
	 * 图片压缩(根据图片的大小进行压缩)
	 * 
	 * @param bitmap
	 *            原图资源
	 * @param size
	 *            图片大小(kb)
	 * @return
	 */
	private static Bitmap compressImage(Bitmap bitmap, int size) {
		// TODO Auto-generated method stub
		try {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// 解析图片
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

			int options = size;
			// 如果压缩的图片大于size继续压缩
			while (baos.toByteArray().length / 1024 > size) {
				baos.reset();
				bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
				// 每次减少10kb
				options -= 10;
			}
			ByteArrayInputStream bais = new ByteArrayInputStream(
					baos.toByteArray());
			Bitmap imageBitmap = BitmapFactory.decodeStream(bais, null, null);
			baos.close();
			bais.close();
			return imageBitmap;

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	/**
	 * 本地图片压缩
	 * 
	 * @param SDCardImagePath
	 *            本地图片地址
	 * @return
	 */
	public static Bitmap compressLocalImage(String SDCardImagePath) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		// 读入图片的数据
		options.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(SDCardImagePath); // 此时bitmap返回为空

		int width = options.outWidth;
		int height = options.outHeight;

		// 现在主流手机比较多是800*480分辨率
		float compressWidth = 480f;
		float compressHeight = 800f;

		int ratio = 1;
		if (width > height && width > compressWidth) {
			ratio = (int) (width / compressWidth);
		} else if (height > width && height > compressHeight) {
			ratio = (int) (height / compressHeight);
		}

		if (ratio <= 0) {
			ratio = 1;
		}
		options.inSampleSize = ratio;
		options.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeFile(SDCardImagePath, options);
		return compressImage(bitmap, 100); // 再对图片的大小进行压缩
	}

	/**
	 * 旋转图片
	 * 
	 * @param angle
	 *            旋转角度
	 * @param bitmap
	 *            原图资源
	 * @return
	 */
	public static Bitmap rotateImageView(int angle, Bitmap bitmap) {
		if (bitmap == null) {
			return null;
		}
		// 定义矩阵旋转图片
		Matrix matrix = new Matrix();
		// 旋转图片
		matrix.postRotate(angle);

		// 最后一个参数为true,当有多个动画同时进行时可以改善动画的效果
		Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);
		return newBitmap;
	}

	/**
	 * 读取图片的属性:图片旋转的角度
	 * 
	 * @param imagePath
	 * @return
	 */
	public static int readPictureDegree(String imagePath) {
		int angle = 0;
		try {
			// ExifInterface用于获取图片的各类信息
			ExifInterface exifInterface = new ExifInterface(imagePath);
			// 获取图片方向
			int orienation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orienation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				angle = 90;
				break;

			case ExifInterface.ORIENTATION_ROTATE_180:
				angle = 180;
				break;

			case ExifInterface.ORIENTATION_ROTATE_270:
				angle = 270;
				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return angle;
	}

	/**
	 * 读取本地的图片得到缩略图，如图片需要旋转则旋转
	 * 
	 * @param path
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap getLocalThumbImage(String localImagePath, int width,
			int height) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		// 解析图片,此时bitmap返回为空
		Bitmap bitmap = BitmapFactory.decodeFile(localImagePath, options);

		int originalWidth = options.outWidth;
		int originalHeight = options.outHeight;

		// 现在主流手机比较多是800*480分辨率
		float compressWidth = width;
		float compressHeight = height;

		int ratio = 1;
		if (originalWidth > originalHeight && originalWidth > compressWidth) {
			ratio = (int) (originalWidth / compressWidth);
		} else if (originalHeight > originalWidth
				&& originalHeight > compressHeight) {
			ratio = (int) (originalHeight / compressHeight);
		}

		if (ratio <= 0) {
			ratio = 1;
		}
		options.inSampleSize = ratio;
		options.inJustDecodeBounds = false;
		// 重新载入图片
		bitmap = BitmapFactory.decodeFile(localImagePath, options);
		bitmap = compressImage(bitmap, 100); // 对图片进行大小压缩
		int angle = readPictureDegree(localImagePath);
		bitmap = rotateImageView(angle, bitmap);
		return bitmap;
	}

	/**
	 * 图片去色,返回灰色图片
	 * 
	 * @param bitmapOriginal
	 * @return
	 */
	public static Bitmap toGrayscale(Bitmap bitmapOriginal) {

		int width = bitmapOriginal.getWidth();
		int height = bitmapOriginal.getHeight();
		// 生成一个新图
		Bitmap bmpGrayscale = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bmpGrayscale);
		Paint paint = new Paint();
		ColorMatrix colorMatrix = new ColorMatrix();
		// 设置图片的饱和度,0为灰,1为原图
		colorMatrix.setSaturation(0);
		ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(
				colorMatrix);
		paint.setColorFilter(colorFilter);
		canvas.drawBitmap(bitmapOriginal, 0, 0, paint);
		return bmpGrayscale;
	}

	/**
	 * 图片去色并加上圆角
	 * 
	 * @param bitmapOriginal
	 * @param pixels
	 * @return
	 */
	public static Bitmap toGrayscaleAddRoundCorner(Bitmap bitmapOriginal,
			int pixels) {
		return toRoundCorner(toGrayscale(bitmapOriginal), pixels);
	}

	/**
	 * 把图片变成圆角
	 * 
	 * @param bitmap
	 * @param pixels
	 * @return
	 */
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
		Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(outBitmap);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		// 设定画布的大小
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;
		paint.setAntiAlias(true);
		// 黑色的画布
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		/**
		 * 设置图像的相交模式 这是一个非常强大的转换模式，使用它， 可以使用图像合成的16条Porter-Duff规则的任意
		 * 一条来控制Paint如何与已有的Canvas图像进行交互。 在两者相交的地方绘制源图像，并且绘制的效果会受到目标图像对应地方透明度的影响
		 * (绘制出来的图像只有源图)
		 * */
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return outBitmap;
	}

	/**
	 * 把图片变成圆角，同时支持BitmapDrawable
	 * 
	 * @param bitmapDrawable
	 *            需要修改的图片
	 * @param pixels
	 *            图片的弧度
	 * @return
	 */
	public static BitmapDrawable toRoundCorner(BitmapDrawable bitmapDrawable,
			int pixels) {
		Bitmap bitmap = bitmapDrawable.getBitmap();
		bitmapDrawable = new BitmapDrawable(toRoundCorner(bitmap, pixels));
		return bitmapDrawable;
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
		if (src == null) {
			return null;
		}
		int originalWidth = src.getWidth();
		int originalHeight = src.getHeight();

		int watermarkWidth = watermark.getWidth();
		int watermarkHeight = watermark.getHeight();

		// 创建一个与原来相同的bitmap
		Bitmap bitmap = Bitmap.createBitmap(originalWidth, originalHeight,
				Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);

		// 按照原图绘制
		canvas.drawBitmap(src, 0, 0, null);

		// 在右下角绘制水印图片
		canvas.drawBitmap(watermark, originalWidth - watermarkWidth + 5,
				originalHeight - watermarkHeight + 5, null);
		return bitmap;
	}

	/**
	 * 图片合成
	 * 
	 * @param direction
	 * @param bitmaps
	 *            多张图片()
	 * @return
	 */
	public static Bitmap pictureToMix(int direction, Bitmap... bitmaps) {
		if (bitmaps.length <= 0) {
			return null;
		}
		if (bitmaps.length == 1) {
			return bitmaps[0];
		}
		Bitmap newBitmap = bitmaps[0];
		// 多个图片叠合
		for (int i = 1; i < bitmaps.length; i++) {
			newBitmap = pictureToMix(newBitmap, bitmaps[1], direction);
		}
		return newBitmap;
	}

	/**
	 * 
	 * 根据指定的方向,将两张图片拼接合成
	 * 
	 * @param firstBitmap
	 * @param secondBitmap
	 * @param direction
	 *            (第二张图片在合成图的位置)(0,1,2,3分别表示左、右、上、下)
	 * @return
	 */
	public static Bitmap pictureToMix(Bitmap firstBitmap, Bitmap secondBitmap,
			int direction) {
		if (firstBitmap == null) {
			return null;
		}
		if (secondBitmap == null) {
			return firstBitmap;
		}
		int fh = firstBitmap.getHeight();
		int fw = firstBitmap.getWidth();

		int sh = secondBitmap.getHeight();
		int sw = secondBitmap.getWidth();

		Bitmap newBitmap = null;

		if (direction == LEFT) {

			newBitmap = Bitmap.createBitmap(fw + sw, fh > sh ? fh : sh,
					Config.ARGB_8888);
			Canvas canvas = new Canvas(newBitmap);
			canvas.drawBitmap(firstBitmap, sw, 0, null);
			canvas.drawBitmap(secondBitmap, 0, 0, null);
		} else if (direction == RIGHT) {

			newBitmap = Bitmap.createBitmap(fw + sw, fh > sh ? fh : sh,
					Config.ARGB_8888);
			Canvas canvas = new Canvas(newBitmap);
			canvas.drawBitmap(firstBitmap, 0, 0, null);
			canvas.drawBitmap(secondBitmap, fw, 0, null);

		} else if (direction == TOP) {
			newBitmap = Bitmap.createBitmap(fw + sw, fh > sh ? fh : sh,
					Config.ARGB_8888);
			Canvas canvas = new Canvas(newBitmap);
			canvas.drawBitmap(firstBitmap, 0, sh, null);
			canvas.drawBitmap(secondBitmap, 0, 0, null);
		} else if (direction == BOTTOM) {
			newBitmap = Bitmap.createBitmap(fw + sw, fh > sh ? fh : sh,
					Config.ARGB_8888);
			Canvas canvas = new Canvas(newBitmap);
			canvas.drawBitmap(firstBitmap, 0, 0, null);
			canvas.drawBitmap(secondBitmap, 0, fh, null);
		}

		return newBitmap;
	}

	/**
	 * 指定两张图片合成,指定小图的宽和高在大图的正中央
	 * 
	 * @param bigBitmap背景图片
	 * @param smallBitmap内容图片
	 * @param width内容图片的宽
	 * @param height内容图片的高
	 * @param pixels内容图片圆角的度数
	 * @return
	 */
	public static Bitmap pictureToMix(Bitmap bigBitmap, Bitmap smallBitmap,
			int width, int height, int pixels) {
		bigBitmap = bigBitmap.copy(Bitmap.Config.ARGB_8888, true);
		smallBitmap = smallBitmap.copy(Bitmap.Config.ARGB_8888, true);

		Canvas canvas = new Canvas(bigBitmap);
		// 设定画笔
		Paint paint = new Paint();

		// 限制高宽
		if (width != 0 && height != 0) {
			smallBitmap = HandleImage.createBitmapBySize(smallBitmap, width,
					height);
		}
		// 生成圆角
		if (pixels != 0) {
			smallBitmap = HandleImage.toRoundCorner(smallBitmap, pixels);
		}

		int bh = bigBitmap.getHeight();
		int bw = bigBitmap.getWidth();
		int sh = smallBitmap.getHeight();
		int sw = smallBitmap.getWidth();
		canvas.drawBitmap(smallBitmap, (bw - sw) / 2, (bh - sh) / 2, paint);
		// canvas.save(Canvas.ALL_SAVE_FLAG);
		// canvas.restore();
		return bigBitmap;
	}

	/**
	 * 保存图片为PNG格式
	 * 
	 * @param bitmap
	 * @param path
	 */
	public static void savePNG(Bitmap bitmap, String path) {
		File file = new File(path);
		try {
			FileOutputStream outputStream = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)) {
				outputStream.flush();
				outputStream.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * 保存图片为JPGE格式
	 * 
	 * @param bitmap
	 * @param path
	 */
	public static void saveJPGE(Bitmap bitmap, String path) {
		File file = new File(path);
		try {
			FileOutputStream outputStream = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)) {
				outputStream.flush();
				outputStream.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * 将bitmap转换为指定大小的图片
	 * 
	 * @param bitmap
	 * @param width
	 * @param heihgt
	 * @return
	 */
	public static Bitmap createBitmapBySize(Bitmap bitmap, int width, int height) {
		return Bitmap.createScaledBitmap(bitmap, width, height, true);
	}

	/**
	 * 将Bitmap 装换为 Drawable
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Drawable bitmapToDrawable(Bitmap bitmap) {
		Drawable drawable = new BitmapDrawable(bitmap);
		return drawable;
	}

	/**
	 * 将Drawable 装换为 Bitmap
	 * 
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
		return bitmapDrawable.getBitmap();
	}

	/**
	 * 将byte[] 装换为 Bitmap
	 * 
	 * @param b
	 * @return
	 */
	public static Bitmap bytesToBitmap(byte[] b) {

		if (b.length > 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		}
		return null;
	}

	/**
	 * 将Bitmap 转换为 byte[]
	 * 
	 * @return
	 */
	public static byte[] bitmapToBytes(Bitmap bitmap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 把图片资源放入到baos当中去
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

}
