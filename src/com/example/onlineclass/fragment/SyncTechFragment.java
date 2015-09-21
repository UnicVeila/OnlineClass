package com.example.onlineclass.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.example.onlineclass.R;
import com.example.onlineclass.adapter.SyncTechAdapter;
import com.example.onlineclass.model.BookEntity;

/**
 * @author anumbrella
 * 
 * @date 2015-9-20 下午3:09:25
 * 
 *       同步教学Fragment
 */
public class SyncTechFragment extends BaseFragment {

	private GridView gridView;

	private SyncTechAdapter adapter;

	private List<BookEntity> bookList = new ArrayList<BookEntity>();;

	private int position = 0;

	public static SyncTechFragment newInstance(int position) {
		SyncTechFragment syncTechFragment = new SyncTechFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("position", position);
		syncTechFragment.setArguments(bundle);
		return syncTechFragment;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceBundle) {
		View contentView = inflater.inflate(R.layout.fragment_sync_tech,
				container, false);
		gridView = (GridView) contentView.findViewById(R.id.gridview);
		// 获取fragment中的数据
		parseArgument();

		if (position == 0) {
			initLocalData();
		} else {
			initOnlineData();
		}
		adapter = new SyncTechAdapter(getActivity(), bookList, false);
		gridView.setAdapter(adapter);
		// 为每一给Item添加点击事件
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getActivity(),
						"这个是第" + position + "本书,可以在这里添加处理逻辑",
						Toast.LENGTH_SHORT).show();
			}
		});
		return gridView;
	}

	/**
	 * 模拟在线获取的数据
	 */
	private void initOnlineData() {
		for (int i = 0; i < 6; i++) {
			BookEntity book = new BookEntity();
			bookList.add(book);
		}

	}

	/**
	 * 加载本地数据
	 */
	private void initLocalData() {
		for (int i = 0; i < 9; i++) {
			BookEntity book = new BookEntity();
			bookList.add(book);
		}

	}

	/**
	 * 解析fragment中的数据
	 */
	public void parseArgument() {
		Bundle bundle = getArguments();
		position = bundle.getInt("position");
	}

}
