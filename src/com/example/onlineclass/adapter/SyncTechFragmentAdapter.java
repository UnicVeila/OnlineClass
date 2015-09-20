package com.example.onlineclass.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.onlineclass.R;
import com.example.onlineclass.fragment.SyncTechFragment;

/**
 * @author anumbrella
 * 
 * @date 2015-9-20 下午2:58:32
 * 
 *       异步教学Fragment适配器
 */
public class SyncTechFragmentAdapter extends FragmentPagerAdapter {

	/**
	 * 选项卡标题数组
	 */
	private String[] titleStr;

	public SyncTechFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	public SyncTechFragmentAdapter(FragmentManager fm, Context context) {
		super(fm);
		// 从xml中获取字符串资源
		titleStr = context.getResources().getStringArray(
				R.array.sync_tech_array);

	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titleStr[position];
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	@Override
	public Fragment getItem(int position) {

		switch (position) {
		case 0:
		case 1:
			return SyncTechFragment.newInstance(position);
		}

		return null;
	}

	@Override
	public int getCount() {
		return titleStr.length;
	}

}
