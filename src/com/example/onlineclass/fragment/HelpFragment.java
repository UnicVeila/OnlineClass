package com.example.onlineclass.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineclass.R;

/**
 * @author anumbrella
 * 
 * @date 2015-9-26 下午4:59:49
 * 
 *       用户帮助界面
 */
public class HelpFragment extends BaseFragment {
	private int position = 0;

	public static HelpFragment newInstance(int position) {
		HelpFragment fragment = new HelpFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("position", position);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.fragment_help, container,
				false);

		return contentView;
	}

}
