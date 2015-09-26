package com.example.onlineclass.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.onlineclass.R;
import com.example.onlineclass.adapter.DownloadAdapter;
import com.example.onlineclass.model.BookEntity;
import com.example.onlineclass.view.NumberProgressBar;

/**
 * @author anumbrella
 * 
 * @date 2015-9-26 上午8:02:54
 * 
 *       正在下载fragment界面
 */
public class DownloadingFragment extends BaseFragment {

	private List<BookEntity> bookList = new ArrayList<BookEntity>();

	private DownloadAdapter adapter;

	private ListView mListView;

	private int position = 0;

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				View view = mListView.getChildAt(1);
				if (view != null) {
					NumberProgressBar numBar = (NumberProgressBar) view
							.findViewById(R.id.progress);
					numBar.incrementProgressBy(5);
					if (numBar.getProgress() == 100) {
						Toast.makeText(getActivity(), "下载完成!",
								Toast.LENGTH_SHORT).show();
						mHandler.removeCallbacks(myRunnable);
					}
				}
				break;
			}
		}

	};

	public static DownloadingFragment newInstance(int position) {
		DownloadingFragment downloadingFragment = new DownloadingFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("position", position);
		downloadingFragment.setArguments(bundle);
		return downloadingFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.fragment_view_list, null);
		parseArgument();
		initDownloadingData();
		mHandler.post(myRunnable);
		mListView = (ListView) contentView.findViewById(R.id.list);
		adapter = new DownloadAdapter(getActivity(), bookList);
		mListView.setAdapter(adapter);
		return contentView;
	}

	Runnable myRunnable = new Runnable() {
		public void run() {
			Message msg = mHandler.obtainMessage();
			msg.what = 0;
			mHandler.sendMessage(msg);
			mHandler.postDelayed(this, 1000);
		}
	};

	// 初始化下载数据
	private void initDownloadingData() {
		for (int i = 0; i < 4; i++) {
			BookEntity book = new BookEntity();
			bookList.add(book);
		}

	}

	private void parseArgument() {
		Bundle bundle = getArguments();
		position = bundle.getInt("position");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (myRunnable != null)
			mHandler.removeCallbacks(myRunnable);
	}
}
