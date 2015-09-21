package com.example.onlineclass.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.onlineclass.R;
import com.example.onlineclass.adapter.ExamScoreAdapter;
import com.example.onlineclass.model.BookEntity;

/**
 * @author anumbrella
 * 
 * @date 2015-9-21 下午2:24:59
 * 
 *       考试成绩分数fragment界面
 */
public class ExamScoreFragment extends BaseFragment {

	private List<BookEntity> bookList = new ArrayList<BookEntity>();

	private ExamScoreAdapter adapter;

	private ListView mListView;

	private int position = 0;

	public static ExamScoreFragment newInstance(int index) {
		ExamScoreFragment examScoreFragment = new ExamScoreFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("position", index);
		examScoreFragment.setArguments(bundle);
		return examScoreFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.fragment_view_list,
				container, false);
		mListView = (ListView) contentView.findViewById(R.id.list);
		initData();
		adapter = new ExamScoreAdapter(getActivity(), bookList);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getActivity(),
						"这是第" + position + "成绩,可以在这里添加处理逻辑", Toast.LENGTH_SHORT)
						.show();
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
