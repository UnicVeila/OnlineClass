package com.example.onlineclass.view;

import java.util.ArrayList;

import com.example.onlineclass.R;
import com.example.onlineclass.utils.ImageLoaderUtil;
import com.example.onlineclass.utils.ImageLoaderUtil.ImageUrlAsyncTask;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

/**
 * @author anumbrella
 * 
 * @date 2015-8-20 下午1:21:39
 * 
 *       图片轮播类
 */
public class SlideImageViewLayout {

	/**
	 * 显示图片轮播类的上下的环境变量
	 */
	private Context context;

	/**
	 * 图片轮播中图片的高宽
	 */
	private int imageWidth, imageHeight;

	/**
	 * 图片滑动区域image存储的数组list
	 */
	private ArrayList<ImageView> imageList;

	/**
	 * 设置轮播圆点图片存储数组
	 */
	private ImageView[] imageViews;

	/**
	 * 圆点图片存储View
	 */
	private ImageView imageView;

	/**
	 * 当前图片滑动的索引
	 */
	private int pageIndex = 0;

	public SlideImageViewLayout(Context context) {
		this.context = context;
		// 获取手机屏幕的宽度
		imageWidth = context.getResources().getDisplayMetrics().widthPixels;

		imageHeight = imageWidth / 2;

		imageList = new ArrayList<ImageView>();

	}

	/**
	 * 
	 * 生成轮播图片滑动区域
	 * 
	 * @return
	 */
	public View getSlideImageLayout(String imageUrl, int position, int defaultId) {
		LinearLayout linearLayout = new LinearLayout(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		// 设定单个的图片
		ImageView iView = new ImageView(context);
		// 设置图片缩放的类型
		iView.setScaleType(ScaleType.FIT_XY);
		iView.setTag(position);
		// 异步类(AsyncTask)获取图片
		ImageLoaderUtil.loadUrlImageAsync(iView, imageUrl, null, context
				.getResources().getDrawable(defaultId), imageWidth);
		// 给轮播的图片设置点击事件
		iView.setOnClickListener(new ImageOnClickListener());
		linearLayout.addView(iView, params);
		// 将轮播中的每个图片添加到轮播list当中去
		imageList.add(iView);
		return linearLayout;
	}

	/**
	 * 生成LinearLayout布局
	 * 
	 * @return
	 */
	public View getLineraLayout(View view, int width, int height) {
		LinearLayout linearLayout = new LinearLayout(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,
				height, 1);
		linearLayout.setPadding(10, 0, 10, 0);
		linearLayout.addView(view, params);
		return linearLayout;
	}

	/**
	 * 设置图片圆点的个数
	 * 
	 * @param size
	 */
	public void setImageCircleViews(int size) {
		imageViews = new ImageView[size];
	}

	/**
	 * 
	 * 生成圆点指示图片的区域
	 * 
	 * @param index
	 *            圆点指针索引
	 * @return
	 */
	public ImageView getCircleImageLayout(int index) {
		imageView = new ImageView(context);
		// 设置图片的宽和高
		imageView.setLayoutParams(new LayoutParams(10, 10));
		// 设置图片缩放的模式
		imageView.setScaleType(ScaleType.FIT_XY);
		// 将指示圆点图片存储到数组当中去(地址)
		imageViews[index] = imageView;

		if (index == 0) {
			imageViews[index]
					.setBackgroundResource(R.drawable.page_indicator_focused);
		} else {
			imageViews[index]
					.setBackgroundResource(R.drawable.page_indicator_unfocused);
		}
		return imageViews[index];
	}

	/**
	 * 设置当前滑动图片的指针索引
	 */
	public void setPageIndex(int index) {
		pageIndex = index;
	}

	/**
	 * 轮播图片的点击事件
	 */
	private class ImageOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(context, "你当前点击的是第" + (pageIndex+1) + "张图片",
					Toast.LENGTH_SHORT).show();
		}
	}

}
