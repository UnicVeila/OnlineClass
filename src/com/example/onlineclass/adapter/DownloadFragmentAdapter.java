package com.example.onlineclass.adapter;

import com.example.onlineclass.R;
import com.example.onlineclass.fragment.DownedFragment;
import com.example.onlineclass.fragment.DownloadingFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author anumbrella
 * 
 * @date 2015-9-24 上午8:15:26
 * 
 *       下载管理适配器
 */
public class DownloadFragmentAdapter extends FragmentPagerAdapter {

	private String[] titleStr = null;

	public DownloadFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	public DownloadFragmentAdapter(FragmentManager fm, Context context) {
		super(fm);
		titleStr = context.getResources()
				.getStringArray(R.array.download_array);

	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			return new DownloadingFragment().newInstance(index);
		case 1:
			return new DownedFragment().newInstance(index);
		}

		return null;
	}

	@Override
	public int getCount() {
		return titleStr.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titleStr[position];
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

}
