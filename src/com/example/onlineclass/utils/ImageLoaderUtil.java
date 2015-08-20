package com.example.onlineclass.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * @author anumbrella
 * 
 * @date 2015-8-20 下午1:33:32
 * 
 *       图片加载类
 */
public class ImageLoaderUtil {

	/**
	 * 图片缓存存储HashMap键值对当中
	 */
	private static HashMap<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();

	/**
	 * 
	 * 异步加载url图片
	 * 
	 * @param mClass
	 *            调用类
	 * @param imageView
	 *            存放图片的imageView
	 * @param url
	 *            imageView的http地址
	 * @param cacheLocalDir
	 *            imageView的缓存图片地址
	 * @param defaultImage
	 *            imageView图片的默认地址
	 * @param compressWidth
	 *            图片压缩的宽度
	 */
	public static void loadUrlImageAsync(String mClass, ImageView imageView,
			String url, String cacheLocalDir, Drawable defaultImage,
			int compressWidth) {
		// 判断
		if (imageView != null && url != null && url.trim().length() > 0
				&& !("null".equals(url))) {
			new ImageUrlAsyncTask().execute(imageView, url, cacheLocalDir,
					defaultImage, compressWidth);

		}

	}

	/**
	 * 异步加载本地图片
	 * 
	 * @param imageView
	 * @param path
	 * @param defaultImage
	 * @param compressWidth
	 */
	public static void loadLocalImageAsync(ImageView imageView, String path,
			int defaultImage, int compressWidth) {

	}

	/**
	 * url图片异步加载类
	 * 
	 */
	public static class ImageUrlAsyncTask extends
			AsyncTask<Object, Object, Bitmap> {

		/**
		 * 暂存的组件
		 */
		private ImageView imageView = null;

		private String url = null;

		// 图片本地缓存地址
		private String cacheLocalPathDir = null;

		private Drawable defaultImage = null;

		// 图片最小缩放比例
		private int minCompressWidth = 50;
		// 图片缩放比例
		private int compressWidth = 50;

		@Override
		protected Bitmap doInBackground(Object... params) {
			// TODO Auto-generated method stub
			// 获取在执行execute方法的参数
			imageView = (ImageView) params[0];
			// 检查传入的参数的个数
			if (params.length > 1) {
				url = (String) params[1];
			}
			if (params.length > 2) {
				cacheLocalPathDir = (String) params[2];
			}
			if (params.length > 3) {
				defaultImage = (Drawable) params[3];
			}
			if (params.length > 4) {
				compressWidth = (Integer) params[4];
			}

			Bitmap bitmap = null;

			if (cacheLocalPathDir != null) {
				// 将缓存的图片存入到SD卡当中
				// 创建一个file对象
				File file = new File(cacheLocalPathDir + MD5.md5(url));
				if (file.exists()) {
					try {
						FileInputStream in = new FileInputStream(file);
						bitmap = BitmapFactory.decodeStream(in);

					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}

			} else {
				// 根据键值找到缓存的图片的存储的地址
				SoftReference<Bitmap> reference = imageCache.get(url);
				if (reference != null) {
					bitmap = reference.get();
				}
			}

			if (bitmap == null) {

				try {
					bitmap = BitmapFactory.decodeStream(new URL(url)
							.openStream());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (bitmap != null) {
					if (cacheLocalPathDir != null) {
						// 根据情况选择缩放的比例
						if (compressWidth < minCompressWidth) {
							compressWidth = minCompressWidth;
						}
						// 定义图片的高的缩放比例
						float compressHeight = 0;
						if (compressWidth > bitmap.getWidth()) {
							// 如果比实际情况还要大，则根据实际的值来取
							compressWidth = bitmap.getWidth();
						}

						/**
						 * commpressWidth这个值代表新图片的宽度是多少，
						 * 新图片的高依照旧图片的宽与新宽的比例来求出新图片的高， 比如：送过来commpressWidth =
						 * 100；旧图片的宽X高=200X300，
						 * 那么新图就是宽：100，比原图缩放了(200/100=2)倍,那么高也：300/(200/100) =
						 * 150; 结论：宽缩放了多少，那么高也缩放多少。
						 */
						compressHeight = (float) bitmap.getHeight()
								/ ((float) bitmap.getWidth() / compressWidth);
						// 生成新的缩放的图片
						bitmap = Bitmap
								.createScaledBitmap(bitmap,
										(int) compressWidth,
										(int) compressHeight, true);
						
						
						

					}
				}

			}

			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {

		}

	}

	/**
	 * 本地图片异步加载类
	 * 
	 */
	public static class ImageLocalAsyncTask extends
			AsyncTask<Object, Object, Bitmap> {

		@Override
		protected Bitmap doInBackground(Object... params) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void onPostExecute(Bitmap result) {

		}

	}

}
