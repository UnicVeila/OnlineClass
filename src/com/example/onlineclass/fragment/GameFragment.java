package com.example.onlineclass.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.onlineclass.R;
import com.example.onlineclass.adapter.GameAdapter;
import com.example.onlineclass.model.BookEntity;
import com.example.onlineclass.utils.AppConstant;

/**
 * @author anumbrella
 * 
 * @date 2015-9-22 下午7:08:21
 * 
 *       游戏列表fragment界面
 */
public class GameFragment extends BaseFragment {
	private GridView gridView;
	private GameAdapter adapter;
	private List<BookEntity> listBook = new ArrayList<BookEntity>();

	public static GameFragment newInstance(int position) {
		GameFragment fragment = new GameFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("position", position);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.fragment_sync_tech,
				container, false);
		gridView = (GridView) contentView.findViewById(R.id.gridview);
		initData();
		adapter = new GameAdapter(getActivity(), listBook);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getActivity(),
						"这个是第" + position + "个游戏,可以在这添加处理逻辑",
						Toast.LENGTH_SHORT).show();

			}
		});
		gridView.setAdapter(adapter);
		return contentView;
	}

	void initData() {
		if (listBook.size() == 0) {
			for (int i = 0; i < 9; i++) {
				BookEntity book = new BookEntity();
				book.setType(AppConstant.BEST_HEAD);
				listBook.add(book);
			}
		}
	}

	private void parseArgument() {
		Bundle bundle = getArguments();
		int position = bundle.getInt("position");

	}

}
