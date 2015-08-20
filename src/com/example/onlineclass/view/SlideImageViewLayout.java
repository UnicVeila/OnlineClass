package com.example.onlineclass.view;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

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
	public View getSlideImageLayout(int position) {
		LinearLayout linearLayout = new LinearLayout(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		// 设定单个的图片
		ImageView iView = new ImageView(context);
		// 设置图片缩放的类型
		iView.setScaleType(ScaleType.FIT_XY);
		iView.setTag(position);
		// 异步类(AsyncTask)获取图片

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
		// linearLayout.setPadding(10, 0, 10, 0);
		linearLayout.addView(view);
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

}
