package com.example.onlineclass.adapter;

import com.example.onlineclass.R;
import com.example.onlineclass.fragment.FamousBookFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author anumbrella
 * 
 * @date 2015-9-21 下午1:11:53
 * 
 *       文学名著activity的fragment的适配器
 */
public class FamousBookFragmentAdapter extends FragmentPagerAdapter {

	/**
	 * 选项卡标题数组
	 */
	private String[] titleStr = null;

	public FamousBookFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	public FamousBookFragmentAdapter(FragmentManager fm, Context context) {
		super(fm);
		titleStr = context.getResources().getStringArray(
				R.array.famous_book_array);

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
			return new FamousBookFragment().newInstance(position);
		}
		return null;
	}

	@Override
	public int getCount() {
		return titleStr.length;
	}

}
