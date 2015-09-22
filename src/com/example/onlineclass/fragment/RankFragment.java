package com.example.onlineclass.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.onlineclass.R;
import com.example.onlineclass.adapter.GameRankAdapter;
import com.example.onlineclass.model.BookEntity;

/**
 * @author anumbrella
 * 
 * @date 2015-9-22 下午7:12:10
 * 
 *       最强排名fragment界面
 */
public class RankFragment extends BaseFragment {
	private List<BookEntity> listBook = new ArrayList<BookEntity>();
	private GameRankAdapter adapter;
	private ListView mListView;

	public static RankFragment newInstance(int position) {
		RankFragment fragment = new RankFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("position", position);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.fragment_view_list,
				container, false);
		mListView = (ListView) contentView.findViewById(R.id.list);
		initData();
		adapter = new GameRankAdapter(getActivity(), listBook);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getActivity(),
						"这个是第" + position + "个排名,可以在这添加处理逻辑",
						Toast.LENGTH_SHORT).show();

			}
		});
		mListView.setAdapter(adapter);
		return contentView;
	}

	void initData() {
		if (listBook.size() == 0) {
			for (int i = 0; i < 4; i++) {
				BookEntity book = new BookEntity();
				listBook.add(book);
			}
		}
	}

	private void parseArgument() {
		Bundle bundle = getArguments();
		int position = bundle.getInt("position");
	}

}
