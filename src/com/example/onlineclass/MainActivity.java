package com.example.onlineclass;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.onlineclass.activity.BaseActivity;
import com.example.onlineclass.utils.AppConstant;
import com.example.onlineclass.view.SlideImageViewLayout;

/**
 * @author anumbrella
 * 
 * @date 2015-8-10 下午4:52:51
 * 
 *       MainActivity类
 */
public class MainActivity extends BaseActivity implements OnClickListener {

	/**
	 * 设置自动更新轮播广告
	 */
	private static final int H_UPDATE_AUTO_AD = 1;

	/**
	 * 设置同步教材的三个按钮
	 */
	private ImageView btn_english, btn_chinese, btn_number;

	/**
	 * 设置最强大脑的三个按钮
	 */
	private ImageView iv_game, iv_record, iv_rank;

	/**
	 * 设置关于我们的三个按钮
	 */
	private ImageView iv_contactUs, iv_about, iv_aboutMsg, iv_aboutUs;

	/**
	 * 设置文学名著、用户管理、模拟考场、下载管理、用户充值、系统设置按钮
	 */
	private LinearLayout layout_famous, layout_user, layout_exam,
			layout_download, layout_pay, layout_setting;

	private boolean adAuto = true;

	/**
	 * 事件处理Handler
	 */
	private Handler handler;

	/**
	 * 图片轮播图片序列数组
	 */
	private ArrayList<View> imagePagerViewList;

	private ViewPager viewPager = null;

	/**
	 * 设置图片选着标点的存储数组
	 */
	private ImageView[] imageCircleViews;

	/**
	 * 图片选着标点的布局存储视图
	 */
	private ViewGroup imageCircleGroupView;

	/**
	 * 轮播图片类
	 * 
	 * @param savedInstanceState
	 */
	private SlideImageViewLayout slideImageViewLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		// 初始化handler
		initHandler();
		// 初始化轮播选项
		initHeadAd();
		// 初始化视图选项
		initView();
	}

	// 初始化视图资源获取
	private void initView() {
		// TODO Auto-generated method stub
		layout_famous = (LinearLayout) findViewById(R.id.layout_famous);
		layout_download = (LinearLayout) findViewById(R.id.layout_download);
		layout_setting = (LinearLayout) findViewById(R.id.layout_setting);
		layout_pay = (LinearLayout) findViewById(R.id.layout_pay);
		layout_user = (LinearLayout) findViewById(R.id.layout_user);
		layout_exam = (LinearLayout) findViewById(R.id.layout_exam);

		btn_english = (ImageView) findViewById(R.id.btn_english);
		btn_chinese = (ImageView) findViewById(R.id.btn_chinese);
		btn_number = (ImageView) findViewById(R.id.btn_number);

		iv_game = (ImageView) findViewById(R.id.iv_game);
		iv_record = (ImageView) findViewById(R.id.iv_record);
		iv_rank = (ImageView) findViewById(R.id.iv_rank);

		iv_contactUs = (ImageView) findViewById(R.id.iv_contact_us);
		iv_aboutMsg = (ImageView) findViewById(R.id.iv_about_msg);
		iv_aboutUs = (ImageView) findViewById(R.id.iv_about_us);

		// 给所有的图标加上点击事件
		layout_famous.setOnClickListener(this);
		layout_exam.setOnClickListener(this);
		layout_download.setOnClickListener(this);
		layout_pay.setOnClickListener(this);
		layout_user.setOnClickListener(this);
		layout_setting.setOnClickListener(this);

		btn_chinese.setOnClickListener(this);
		btn_english.setOnClickListener(this);
		btn_number.setOnClickListener(this);

		iv_game.setOnClickListener(this);
		iv_rank.setOnClickListener(this);
		iv_record.setOnClickListener(this);

		iv_contactUs.setOnClickListener(this);
		iv_aboutMsg.setOnClickListener(this);
		iv_aboutUs.setOnClickListener(this);

	}

	// 初始化头部的轮播
	private void initHeadAd() {
		// TODO Auto-generated method stub
		imagePagerViewList = new ArrayList<View>();
		viewPager = (ViewPager) findViewById(R.id.imgae_slide_pager);
		int count = AppConstant.imageUrl.length;
		imageCircleViews = new ImageView[count];
		// 获取图标选着点显示的位置布局
		imageCircleGroupView = (ViewGroup) findViewById(R.id.layout_circle_images);
		
	
	}

	// 初始化一些处理操作
	private void initHandler() {
		// TODO Auto-generated method stub
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case H_UPDATE_AUTO_AD:
					break;

				default:
					break;
				}

			}

		};

	}

	/**
	 * @param v
	 * 
	 *            所有图标的点击事件
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
