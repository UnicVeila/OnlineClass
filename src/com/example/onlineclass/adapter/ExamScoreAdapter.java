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

/**
 * @author anumbrella
 * 
 * @date 2015-9-21 下午2:44:12
 * 
 *       考试成绩显示适配器
 */
public class ExamScoreAdapter extends BaseAdapter {

	private Context context;

	private List<BookEntity> listBook = null;

	public ExamScoreAdapter(Context context, List<BookEntity> list) {
		this.context = context;
		this.listBook = list;
	}

	@Override
	public int getCount() {
		return listBook.size();
	}

	@Override
	public Object getItem(int position) {
		return listBook.get(position);
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
					R.layout.record_list_item, null);
			holder.iv_tag = (ImageView) convertView.findViewById(R.id.iv_tag);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_score = (TextView) convertView
					.findViewById(R.id.tv_score);
			holder.tv_class = (TextView) convertView
					.findViewById(R.id.tv_class);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		BookEntity book = listBook.get(position);
		holder.iv_tag.setVisibility(View.GONE);
		holder.tv_class.setVisibility(View.VISIBLE);
		holder.tv_class.setText("四年级英语");
		holder.tv_name.setText("2014/6/12(14:30:12)");
		holder.tv_score.setText("95分");
		return convertView;
	}

	private class ViewHolder {
		ImageView iv_tag;
		TextView tv_class;
		TextView tv_name;
		TextView tv_score;
	}

}
