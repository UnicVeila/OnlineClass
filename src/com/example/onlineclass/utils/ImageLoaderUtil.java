package com.example.onlineclass.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
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

	private static final String TAG = "ImageLoaderUtil";

	/**
	 * 图片缓存存储HashMap键值对当中(保存对象的软引用)
	 */

	private static HashMap<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();

	/**
	 * 
	 * 异步加载url图片
	 * 
	 * @param imageView
	 *            存放图片的imageView
	 * @param url
	 *            imageView的http地址
	 * @param cacheLocalDir
	 *            imageView的缓存图片地址
	 * @param defaultImage
	 *            imageView图片的默认资源
	 * @param compressWidth
	 *            图片压缩的宽度
	 */
	public static void loadUrlImageAsync(ImageView imageView, String url,
			String cacheLocalDir, Drawable defaultImage, int compressWidth) {
		// 判断（优先进行异步网络加载）
		if (imageView != null && url != null && url.trim().length() > 0
				&& !("null".equals(url))) {
			new ImageUrlAsyncTask().execute(imageView, url, cacheLocalDir,
					defaultImage, compressWidth);
		} else if (defaultImage != null && imageView != null) {
			// 如果不是网络图片,就直接加载本地的图片资源
			imageView.setImageDrawable(defaultImage);
		}
	}

	/**
	 * 异步加载本地图片
	 * 
	 * @param imageView
	 *            保存单个图片的view
	 * @param path
	 *            保存图片的本地路径
	 * @param defaultImage
	 *            默认的本地资源图片id
	 * @param compressWidth
	 *            图片压缩的宽度
	 */
	public static void loadLocalImageAsync(ImageView imageView, String path,
			int defaultImage, int compressWidth) {
		// 判断(优先进行本地的异步图片加载)
		if (imageView != null && path != null && path.trim().length() > 0
				&& !"null".equals(path)) {
			new ImageLocalAsyncTask().execute(imageView, path, defaultImage,
					compressWidth);
		} else if (imageView != null && defaultImage != 0) {
			// 如果不是本地异步图片,加载本地的资源id
			imageView.setImageResource(defaultImage);
		}

	}

	/**
	 * 
	 * 异步加载成圆角图片的形式
	 * 
	 * @param context
	 *            上下文环境变量
	 * @param imageView
	 *            图片资源View
	 * @param url
	 *            图片url资源处
	 * @param cacheLocalDir
	 *            图片缓存地址
	 * @param defaultImage
	 *            默认图片的位置
	 * @param compressWidth
	 *            图片压缩的宽度
	 */
	public static void loadRoundedImageAsync(Context context,
			ImageView imageView, String url, String cacheLocalDir,
			int defaultImage, int compressWidth) {
		Bitmap defaultBitmap = null;
		if (defaultImage > 0) {
			// 解析图片的资源
			defaultBitmap = BitmapFactory.decodeResource(
					context.getResources(), defaultImage);
		}
		// 首先进行网络异步图片获取
		if (imageView != null && url != null && url.trim().length() > 0
				&& !"nul".equals(url)) {
			new ImageRoundAsyncTask().execute(imageView, url, cacheLocalDir,
					defaultBitmap, compressWidth);
		} else if (imageView != null && defaultBitmap != null) {
			imageView.setImageBitmap(defaultBitmap);
		}

	}

	/**
	 * url图片异步加载类
	 * 
	 */
	public static class ImageUrlAsyncTask extends
			AsyncTask<Object, Object, Bitmap> {

		/**
		 * 单个图片保存View
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
					// 判断程序采用本地缓存还是软连接缓存
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
								/ ((float) bitmap.getWidth() / (float) compressWidth);
						// 生成新的缩放的图片
						bitmap = Bitmap
								.createScaledBitmap(bitmap,
										(int) compressWidth,
										(int) compressHeight, true);

						// 根据要求对生成的图进行大小压缩
						bitmap = HandleImage.compressImage(bitmap, 100);

						String imageType = url.substring(
								url.lastIndexOf('.') + 1, url.length());
						HandleImage.savebitmap(bitmap,
								cacheLocalPathDir + MD5.md5(url), imageType);
					} else {
						// 根据url键值对保存到缓存当中
						imageCache.put(url, new SoftReference<Bitmap>(bitmap));
					}
				}

			}

			return bitmap;
		}

		/*
		 * 在doInBackground()结束后返回的结果
		 */
		@Override
		protected void onPostExecute(Bitmap result) {
			// 优先网络图片
			if (result != null) {
				imageView.setImageBitmap(result);
			} else if (defaultImage != null) {
				// 设置图片资源为本地图片
				imageView.setImageDrawable(defaultImage);
			}
		}
	}

	/**
	 * 本地图片异步加载类
	 * 
	 */
	public static class ImageLocalAsyncTask extends
			AsyncTask<Object, Object, Bitmap> {

		/**
		 * 单个图片保存View
		 */
		private ImageView imageView;

		/**
		 * 图片保存路径
		 */
		private String path;

		private int defaultImage = 0;

		/**
		 * 图片最小缩放比例
		 */
		private int minCompressWidth = 50;

		/**
		 * 图片的缩放比例
		 */
		private int compressWidth = 50;

		@Override
		protected Bitmap doInBackground(Object... params) {
			// TODO Auto-generated method stub
			imageView = (ImageView) params[0];
			if (params.length > 1) {
				path = (String) params[1];
			}
			if (params.length > 2) {
				defaultImage = (Integer) params[2];
			}
			if (params.length > 3) {
				compressWidth = (Integer) params[3];
			}

			Bitmap bitmap = null;
			// 判断本地的文件是否存在
			File file = new File(path);
			// 如果图片资源存在,就解析为bitmap
			if (file.exists()) {
				try {
					// 获得图片文件的输入流
					FileInputStream inStream = new FileInputStream(file);
					// 将文件解析为Bitmap文件
					bitmap = BitmapFactory.decodeStream(inStream);
				} catch (FileNotFoundException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			// 从路径当中解析到了Bitmap文件
			if (bitmap != null) {

				if (compressWidth < minCompressWidth) {
					compressWidth = minCompressWidth;
				}
				// 定义图片的缩放高度
				float compressHeight = 0;
				// 如果缩放的宽度比原图还要大,则缩放的比例就为原图的宽度
				if (compressWidth > bitmap.getWidth()) {
					compressWidth = bitmap.getWidth();
				}

				// 根据定义比例，获得缩放的高度
				compressHeight = (float) bitmap.getHeight()
						/ ((float) bitmap.getWidth() / (float) compressWidth);

				// 重新生成Bitmap文件
				bitmap = Bitmap.createScaledBitmap(bitmap, (int) compressWidth,
						(int) compressHeight, true);
			}

			return bitmap;
		}

		/*
		 * 在doInBackground()结束后返回的结果
		 */
		@Override
		protected void onPostExecute(Bitmap result) {
			// 判断按要求缩放生成的Bitmap是否存在
			if (result != null) {
				imageView.setImageBitmap(result);
			} else if (defaultImage != 0) {
				imageView.setImageResource(defaultImage);
			}
		}
	}

	/**
	 * 异步加载圆角图片
	 */
	public static class ImageRoundAsyncTask extends
			AsyncTask<Object, Object, Bitmap> {

		/**
		 * 单个图片存储View
		 */
		private ImageView imageView;

		private String url;

		/**
		 * 本地缓存目录
		 */
		private String cacheLocalPathDir;

		private Bitmap defaultBitmap = null;

		/**
		 * 最小缩放宽度
		 */
		private int minCompressWidth = 50;

		/**
		 * 缩放宽度
		 */
		private int compressWidth = 50;

		@Override
		protected Bitmap doInBackground(Object... params) {
			// TODO Auto-generated method stub
			imageView = (ImageView) params[0];
			if (params.length > 1) {
				url = (String) params[1];
			}
			if (params.length > 2) {
				cacheLocalPathDir = (String) params[2];
			}
			if (params.length > 3) {
				defaultBitmap = (Bitmap) params[3];
			}
			if (params.length > 4) {
				compressWidth = (Integer) params[4];
			}

			Bitmap bitmap = null;

			if (cacheLocalPathDir != null && url != null) {
				File file = new File(cacheLocalPathDir + MD5.md5(url));
				if (file.exists()) {
					try {
						FileInputStream inputStream = new FileInputStream(file);
						bitmap = BitmapFactory.decodeStream(inputStream);
					} catch (FileNotFoundException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			} else if (url != null) {
				SoftReference<Bitmap> reference = imageCache.get(url);
				if (reference != null) {
					bitmap = reference.get();
				}
			}
			if (bitmap == null && url != null) {
				try {
					bitmap = BitmapFactory.decodeStream(new URL(url)
							.openStream());
				} catch (MalformedURLException e) {
					// TODO: handle exception
					e.printStackTrace();
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				if (bitmap != null && cacheLocalPathDir != null) {
					if (compressWidth < minCompressWidth) {
						compressWidth = minCompressWidth;
					}

					int compressHeight = 0;

					if (compressWidth > bitmap.getWidth()) {
						compressWidth = bitmap.getWidth();
					}

					compressHeight = bitmap.getHeight()
							/ (bitmap.getWidth() / compressWidth);

					// 创建压缩后的图片
					bitmap = Bitmap.createScaledBitmap(bitmap, compressWidth,
							compressHeight, true);
					saveBitmpa(cacheLocalPathDir + MD5.md5(url), bitmap);
				} else if (bitmap != null && url != null) {
					imageCache.put(url, new SoftReference<Bitmap>(bitmap));
				}

			}

			return bitmap;
		}

		/*
		 * 在doInBackground()结束后返回的结果
		 */
		@Override
		protected void onPostExecute(Bitmap result) {
			if (result != null) {
				result = HandleImage.getRoundedCornerbitmap(result, 10);
				imageView.setImageBitmap(result);
			} else if (defaultBitmap != null) {
				imageView.setImageBitmap(defaultBitmap);
			}
		}
	}

	/**
	 * 根据路径保存并压缩图片
	 * 
	 * @param path
	 * @param bitmap
	 */
	public static void saveBitmpa(String path, Bitmap bitmap) {
		File file = new File(path);
		if (!file.exists()) {
			createDipPath(path);
		}
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
		try {
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * 根据路径创建文件夹
	 * 
	 * @param path
	 */
	private static void createDipPath(String path) {
		// TODO Auto-generated method stub

		String parentPath = path.substring(0, path.lastIndexOf('/'));
		File file = new File(path);
		File parentFile = new File(parentPath);
		if (!file.exists()) {
			parentFile.mkdirs();
			try {
				file.createNewFile();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

}
