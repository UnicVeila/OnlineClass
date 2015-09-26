package com.example.onlineclass.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineclass.R;

/**
 * @author anumbrella
 * 
 * @date 2015-9-26 下午4:59:24
 * 
 *       字体设置界面
 */
public class FontFragment extends BaseFragment {
	private int position = 0;

	public static FontFragment newInstance(int position) {
		FontFragment fragment = new FontFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("position", position);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.fragment_font, container,
				false);

		return contentView;
	}

}
