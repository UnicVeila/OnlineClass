package com.example.onlineclass.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineclass.R;

public class MainColorFragment extends BaseFragment {

	private int position = 0;

	public static MainColorFragment newInstance(int position) {
		MainColorFragment fragment = new MainColorFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("position", position);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.fragment_theme_color,
				container, false);

		return contentView;
	}

}
