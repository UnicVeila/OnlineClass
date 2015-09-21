package com.example.onlineclass.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.onlineclass.R;
import com.example.onlineclass.fragment.ExamFragment;
import com.example.onlineclass.fragment.ExamScoreFragment;

/**
 * @author anumbrella
 * 
 * @date 2015-9-21 下午2:18:01
 * 
 *       模拟考场fragment适配器
 */
public class ExamFragmentAdapter extends FragmentPagerAdapter {

	private String[] titleStr = null;

	public ExamFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	public ExamFragmentAdapter(FragmentManager fm, Context context) {
		super(fm);
		titleStr = context.getResources().getStringArray(R.array.exam_array);
	}

	@Override
	public Fragment getItem(int position) {

		switch (position) {
		case 0:
			return ExamFragment.newInstance(position);
		case 1:
			return ExamScoreFragment.newInstance(position);
		case 2:
			return ExamFragment.newInstance(position);

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
