package com.example.onlineclass.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.onlineclass.R;
import com.example.onlineclass.adapter.SyncTechAdapter;
import com.example.onlineclass.model.BookEntity;
import com.example.onlineclass.utils.AppConstant;

/**
 * @author anumbrella
 * 
 * @date 2015-9-21 下午2:31:30
 * 
 *       模拟考场和答案解析Fragment界面
 */
public class ExamFragment extends BaseFragment {

	private GridView gridView;

	private SyncTechAdapter adapter;

	private List<BookEntity> bookList = new ArrayList<BookEntity>();;

	private int position = 0;

	public static ExamFragment newInstance(int position) {
		ExamFragment examFragment = new ExamFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("position", position);
		examFragment.setArguments(bundle);
		return examFragment;

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
			initExamData();
			Log.i("anumbrella33", position + "ExamData");
		} else {
			initQuestionData();
			Log.i("anumbrella33", position + "QuestionData");
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
		return contentView;
	}

	/**
	 * 模拟在线获取的数据
	 */
	private void initExamData() {
		if (bookList.size() == 0) {
			for (int i = 0; i < 6; i++) {
				BookEntity book = new BookEntity();
				book.setType(AppConstant.EXAM);
				bookList.add(book);
			}
		}
	}

	/**
	 * 加载本地数据
	 */
	private void initQuestionData() {
		for (int i = 0; i < 2; i++) {
			BookEntity book = new BookEntity();
			book.setType(AppConstant.EXAM);
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
