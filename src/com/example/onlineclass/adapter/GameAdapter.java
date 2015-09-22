package com.example.onlineclass.adapter;

import java.util.List;

import com.example.onlineclass.R;
import com.example.onlineclass.model.BookEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GameAdapter extends BaseAdapter {

	private List<BookEntity> bookList;

	private Context context;

	public GameAdapter(Context context, List<BookEntity> bookList) {
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
					R.layout.view_game_item, null);
			holder.iv_book = (ImageView) convertView.findViewById(R.id.iv_book);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (position % 2 == 0) {
			holder.iv_book.setImageResource(R.drawable.game_default);
			holder.tv_name.setText("斗地主");

		} else {
			holder.iv_book.setImageResource(R.drawable.g_default);
			holder.tv_name.setText("俄罗斯方块");
		}
		return convertView;
	}

	class ViewHolder {
		ImageView iv_book;
		TextView tv_name;
	}

}
