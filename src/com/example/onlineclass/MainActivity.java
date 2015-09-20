package com.example.onlineclass;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.onlineclass.R.id;
import com.example.onlineclass.activity.AboutMsgActivity;
import com.example.onlineclass.activity.AboutUsActivity;
import com.example.onlineclass.activity.BaseActivity;
import com.example.onlineclass.activity.ContactUsActivity;
import com.example.onlineclass.activity.ExamActivity;
import com.example.onlineclass.activity.FamousBookActivity;
import com.example.onlineclass.activity.GameListActivity;
import com.example.onlineclass.activity.GameRankActivity;
import com.example.onlineclass.activity.GameRecordActivity;
import com.example.onlineclass.activity.LoginActivity;
import com.example.onlineclass.activity.MyDownloadActivity;
import com.example.onlineclass.activity.SettingActivity;
import com.example.onlineclass.activity.SyncTechActivity;
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
	private ImageView iv_contactUs, iv_aboutMsg, iv_aboutUs;

	/**
	 * 设置文学名著、用户管理、模拟考场、下载管理、用户充值、系统设置按钮
	 */
	private LinearLayout layout_famous, layout_user, layout_exam,
			layout_download, layout_pay, layout_setting;

	/**
	 * 是否自动开启轮播模式
	 */
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

	/**
	 * 当前ViewPager的索引
	 */
	private int pageIndex = 0;

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
		adAuto = true;
		adThread.start();
	}

	Thread adThread = new Thread() {
		public void run() {
			while (true) {
				try {
					sleep(6000);
					if (adAuto) {
						pageIndex++;
						if (pageIndex > imagePagerViewList.size() - 1) {
							pageIndex = 0;
						}
						// 在次线程中更新ui
						handler.sendEmptyMessage(H_UPDATE_AUTO_AD);
					}

				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	};

	// 初始化一些处理操作
	@SuppressLint("HandlerLeak")
	private void initHandler() {
		// TODO Auto-generated method stub
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case H_UPDATE_AUTO_AD:
					upadteSelectedStatus();
					viewPager.setCurrentItem(pageIndex);
					break;
				default:
					break;
				}
			}
		};
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
	@SuppressWarnings("deprecation")
	private void initHeadAd() {
		// TODO Auto-generated method stub
		imagePagerViewList = new ArrayList<View>();
		viewPager = (ViewPager) findViewById(R.id.imgae_slide_pager);
		int count = AppConstant.imageUrl.length;
		imageCircleViews = new ImageView[count];
		// 获取图标选着点显示的位置布局
		imageCircleGroupView = (ViewGroup) findViewById(R.id.layout_circle_images);
		// 加载图片轮播处理类
		slideImageViewLayout = new SlideImageViewLayout(this);
		// 设置指示小圆点个数
		slideImageViewLayout.setImageCircleViews(count);

		// 设置轮播滑动图片
		for (int i = 0; i < count; i++) {
			// 设置轮播开始的默认图片
			int defId = R.drawable.a01;
			switch (i) {
			case 1:
				defId = R.drawable.a02;
				break;
			case 2:
				defId = R.drawable.a03;
				break;
			case 3:
				defId = R.drawable.a04;
				break;
			}
			// 设置轮播滑动图片
			View imageView = slideImageViewLayout.getSlideImageLayout(
					AppConstant.imageUrl[i], i, defId);
			// 将轮播图片添加轮播列表当中
			imagePagerViewList.add(imageView);
			// 设置指示图片指示圆点
			imageCircleViews[i] = slideImageViewLayout.getCircleImageLayout(i);
			// 添加图片到GroupView中
			imageCircleGroupView.addView(slideImageViewLayout.getLineraLayout(
					imageCircleViews[i], 10, 10));
		}
		// 设置图片轮播适配器
		viewPager.setAdapter(new SlideImageAdapter());

		// 给pagerView添加监听事件
		viewPager.setOnPageChangeListener(new ImagePageChangeListener());

	}

	/**
	 * @param v
	 * 
	 *            所有图标的点击事件
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int resId = R.drawable.number_pressed;
		switch (resId) {
		case R.id.btn_chinese:
		case R.id.btn_number:
		case R.id.btn_english:
			if (v.getId() == R.id.btn_number) {
				resId = R.drawable.number_pressed;
			} else if (v.getId() == R.id.btn_english) {
				resId = R.drawable.english_pressed;
			} else if (v.getId() == R.id.btn_chinese) {
				resId = R.drawable.chinese_pressed;
			}
			Intent intent = new Intent(MainActivity.this, SyncTechActivity.class);
			intent.putExtra("resId", resId);
			startActivity(intent);
			break;
		case R.id.layout_famous:
			intent = new Intent(MainActivity.this, FamousBookActivity.class);
			startActivity(intent);
		case R.id.layout_download:
			intent = new Intent(MainActivity.this, MyDownloadActivity.class);
			startActivity(intent);
		case R.id.layout_exam:
			intent = new Intent(MainActivity.this, ExamActivity.class);
			startActivity(intent);
		case R.id.layout_setting:
			intent = new Intent(MainActivity.this, SettingActivity.class);
			startActivity(intent);
		case R.id.layout_user:
			intent = new Intent(MainActivity.this, LoginActivity.class);
			startActivity(intent);
			// 关于我们
		case R.id.iv_about_us:
			intent = new Intent(MainActivity.this, AboutUsActivity.class);
			startActivity(intent);
			// 关于应用信息
		case R.id.iv_about_msg:
			intent = new Intent(MainActivity.this, AboutMsgActivity.class);
			startActivity(intent);
			// 联系我们
		case R.id.iv_contact_us:
			intent = new Intent(MainActivity.this, ContactUsActivity.class);
			startActivity(intent);
			// 游戏菜单
		case R.id.iv_game:
			intent = new Intent(MainActivity.this, GameListActivity.class);
			startActivity(intent);
			// 游戏记录
		case R.id.iv_record:
			intent = new Intent(MainActivity.this, GameRecordActivity.class);
			startActivity(intent);
			// 游戏排名
		case R.id.iv_rank:
			intent = new Intent(MainActivity.this, GameRankActivity.class);
			startActivity(intent);
		default:
			break;
		}
	}

	/**
	 * 滑动图片适配器
	 */
	private class SlideImageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imagePagerViewList.size();
		}

		// 判断pager的一个view是否和instantiateItem方法返回的object是否有关联
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public int getItemPosition(Object object) {
			return super.getItemPosition(object);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(imagePagerViewList.get(arg1));
		}

		/**
		 * 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化 页面的初始化
		 */
		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(imagePagerViewList.get(arg1));
			return imagePagerViewList.get(arg1);
		}

	}

	/**
	 * 给轮播滑动图片添加监听事件
	 */
	private class ImagePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int index) {
			// TODO Auto-generated method stub
			pageIndex = index;
			// 更新指示小圆点的状态
			upadteSelectedStatus();
		}
	}

	/**
	 * 更新当选中轮播图片时指示小圆点的状态
	 */
	private void upadteSelectedStatus() {
		// TODO Auto-generated method stub
		// 设定图片轮播类里图片索引指针
		slideImageViewLayout.setPageIndex(pageIndex);
		for (int i = 0; i < imageCircleViews.length; i++) {
			if (i == pageIndex) {
				imageCircleViews[i]
						.setImageResource(R.drawable.page_indicator_focused);
			} else {
				imageCircleViews[i]
						.setImageResource(R.drawable.page_indicator_unfocused);
			}
		}

	}
}
