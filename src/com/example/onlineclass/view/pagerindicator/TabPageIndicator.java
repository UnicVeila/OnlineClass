/*
 * Copyright (C) 2011 The Android Open Source Project
 * Copyright (C) 2011 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.onlineclass.view.pagerindicator;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * @author anumbrella
 * 
 * @date 2015-9-19 下午1:16:05
 * 
 *       来源于Jake Wharton,查看源码,覆写并添加中文注释
 * 
 *       TabPageIndicator可以根据配置让widget实现bar上的tab动态改变。
 */
public class TabPageIndicator extends HorizontalScrollView implements
		PagerIndicator {

	/**
	 * 当adapter没有提供主题时,默认使用该主题
	 */
	private static final CharSequence EMPTY_TITLE = "";

	/**
	 * 定义一个监听接口,用于当tab被重新选择时调用
	 */
	public interface OnTabReselectedListener {
		/**
		 * 回调函数,当tab被重新选择时调用
		 * 
		 * @param position
		 *            当前tab的位置
		 */
		void OnTabReselected(int position);
	}

	private Runnable mTabSelector;

	private final LinearLayout mTabLayout;

	private ViewPager mViewPager;

	/**
	 * ViewPager选择改变监听器
	 */
	private ViewPager.OnPageChangeListener pageChangeListener;

	/**
	 * 定义单个tab最大的宽度
	 */
	private int mMaxTabWidth;

	/**
	 * 选中的tab项的指针
	 */
	private int mSelectedTabIndex;

	/**
	 * 回调函数接口监听
	 */
	private OnTabReselectedListener mTabReselectedListener;

	public TabPageIndicator(Context context) {
		this(context, null);
	}

	public TabPageIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 设置HorizontalScrollBar是否可以拖动
		setHorizontalScrollBarEnabled(false);
		mTabLayout = new LinearLayout(getContext());
		// 添加视图Tab到ViewGroup
		addView(mTabLayout, new ViewGroup.LayoutParams(WRAP_CONTENT,
				MATCH_PARENT));
	}

	/**
	 * 设置tab选择回调函数监听
	 * 
	 * @param listener
	 */
	public void setOnTabReselectedListener(OnTabReselectedListener listener) {
		mTabReselectedListener = listener;
	}

	/*
	 * 测量视图大小
	 */
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasurSpec) {

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setViewPager(ViewPager viewPager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setViewPager(ViewPager viewPager, int initialPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCurrentItem(int item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOnPageChangeListener(OnPageChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub

	}

}
