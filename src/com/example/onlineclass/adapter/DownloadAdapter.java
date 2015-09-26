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
import com.example.onlineclass.view.NumberProgressBar;

/**
 * @author anumbrella
 * 
 * @date 2015-9-26 上午8:07:36
 * 
 *       下载界面适配器
 */
public class DownloadAdapter extends BaseAdapter {

	private List<BookEntity> bookList = null;
	private Context context;

	public DownloadAdapter(Context context, List<BookEntity> bookList) {
		this.context = context;
		this.bookList = bookList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return bookList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return bookList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.view_download_item, null);
			holder.iv_pause = (ImageView) convertView
					.findViewById(R.id.iv_pase);
			holder.iv_del = (ImageView) convertView.findViewById(R.id.iv_del);
			holder.tv_title = (TextView) convertView
					.findViewById(R.id.tv_title);
			holder.tv_author = (TextView) convertView
					.findViewById(R.id.tv_author);
			holder.tv_size = (TextView) convertView.findViewById(R.id.tv_size);
			holder.numbar = (NumberProgressBar) convertView
					.findViewById(R.id.progress);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		BookEntity book = bookList.get(position);
		if (book.getType() == 1) {
			holder.numbar.setVisibility(View.INVISIBLE);
		} else {
			holder.numbar.setVisibility(View.VISIBLE);
		}
		return convertView;
	}

	private class ViewHolder {

		ImageView iv_pause;
		ImageView iv_del;
		TextView tv_title;
		TextView tv_author;
		TextView tv_size;
		NumberProgressBar numbar;

	}

}
