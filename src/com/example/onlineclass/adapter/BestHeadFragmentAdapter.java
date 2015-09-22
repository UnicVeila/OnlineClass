package com.example.onlineclass.adapter;

import com.example.onlineclass.R;
import com.example.onlineclass.fragment.GameFragment;
import com.example.onlineclass.fragment.RankFragment;
import com.example.onlineclass.fragment.WinRecordFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class BestHeadFragmentAdapter extends FragmentPagerAdapter {

	private String[] titleStr = null;

	public BestHeadFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	public BestHeadFragmentAdapter(FragmentManager fm, Context context) {
		super(fm);
		titleStr = context.getResources().getStringArray(
				R.array.best_head_array);
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
			return GameFragment.newInstance(position);
		case 1:
			return WinRecordFragment.newInstance(position);
		case 2:
			return RankFragment.newInstance(position);
		}

		return null;
	}

	@Override
	public int getCount() {
		return titleStr.length;
	}

}
