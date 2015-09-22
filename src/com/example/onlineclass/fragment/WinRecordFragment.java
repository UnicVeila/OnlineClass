package com.example.onlineclass.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.onlineclass.R;
import com.example.onlineclass.adapter.ExamScoreAdapter;
import com.example.onlineclass.adapter.GameScoreAdapter;
import com.example.onlineclass.model.BookEntity;

/**
 * @author anumbrella
 * 
 * @date 2015-9-22 下午7:09:29
 * 
 *       奖励记录fragment界面
 */
public class WinRecordFragment extends BaseFragment {
	private List<BookEntity> bookList = new ArrayList<BookEntity>();

	private GameScoreAdapter adapter;

	private ListView mListView;

	private int position = 0;

	public static WinRecordFragment newInstance(int index) {
		WinRecordFragment winRecordFragment = new WinRecordFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("position", index);
		winRecordFragment.setArguments(bundle);
		return winRecordFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.fragment_view_list,
				container, false);
		mListView = (ListView) contentView.findViewById(R.id.list);
		initData();
		adapter = new GameScoreAdapter(getActivity(), bookList);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getActivity(),
						"这个是第" + position + "个奖励记录,可以在这添加处理逻辑",
						Toast.LENGTH_SHORT).show();

			}
		});
		mListView.setAdapter(adapter);
		return contentView;
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		for (int i = 0; i < 4; i++) {
			BookEntity book = new BookEntity();
			bookList.add(book);
		}
	}

	private void parseArgument() {
		Bundle bundle = getArguments();
		position = bundle.getInt("position");
	}

}
