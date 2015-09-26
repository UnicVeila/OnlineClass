package com.example.onlineclass.adapter;

import com.example.onlineclass.R;
import com.example.onlineclass.fragment.FontFragment;
import com.example.onlineclass.fragment.HelpFragment;
import com.example.onlineclass.fragment.MainColorFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SettingFragmentAdapter extends FragmentPagerAdapter {

	private String[] titleStr = null;

	public SettingFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	public SettingFragmentAdapter(FragmentManager fm, Context context) {
		super(fm);
		titleStr = context.getResources().getStringArray(R.array.setting_array);

	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			return MainColorFragment.newInstance(index);
		case 1:
			return FontFragment.newInstance(index);
		case 2:
			return HelpFragment.newInstance(index);
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
