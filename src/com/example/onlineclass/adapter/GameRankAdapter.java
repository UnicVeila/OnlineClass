package com.example.onlineclass.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlineclass.R;
import com.example.onlineclass.model.BookEntity;

public class GameRankAdapter extends BaseAdapter {

	private List<BookEntity> bookList;

	private Context context;

	public GameRankAdapter(Context context, List<BookEntity> bookList) {
		this.context = context;
		this.bookList = bookList;
	}

	@Override
	public int getCount() {
		return bookList.size();
	}

	@Override
	public Object getItem(int position) {
		return bookList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.game_rank_item, null);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		BookEntity book = bookList.get(position);
		return convertView;
	}

	class ViewHolder {
		private ImageView iv_tag;
		private TextView tv_name;
		private TextView tv_score;
	}

}
