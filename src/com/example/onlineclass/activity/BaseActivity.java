package com.example.onlineclass.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author anumbrella
 * 
 * @date 2015-8-19 下午3:19:34
 * 
 * 
 *       基础的activity可以在这重写一些方法
 */
public class BaseActivity extends FragmentActivity {

	protected ActionBar actionBar;

	// 底部菜单的资源
	public Button btn_back;

	public Button btn_search;

	public Button btn_home;

	public ImageView iv_title;

	public TextView tv_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}
}
