package com.example.onlineclass.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineclass.R;
import com.example.onlineclass.model.BookEntity;
import com.example.onlineclass.utils.AppConstant;

/**
 * @author anumbrella
 * 
 * @date 2015-9-20 下午3:17:47
 * 
 *       同步数据适配器
 */
public class SyncTechAdapter extends BaseAdapter {

	private List<BookEntity> listBook = null;

	private Context context;

	private boolean isFamousBook = false;

	public SyncTechAdapter(Context context, List<BookEntity> list,
			boolean isFamousBook) {
		this.context = context;
		this.listBook = list;
		this.isFamousBook = isFamousBook;
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
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			if (isFamousBook) {
				convertView = LinearLayout.inflate(context,
						R.layout.view_famous_book, null);
			} else {
				convertView = LinearLayout.inflate(context,
						R.layout.sync_tech_item, null);
			}
			holder.iv_book = (ImageView) convertView.findViewById(R.id.iv_book);
			holder.layout_bg = (RelativeLayout) convertView
					.findViewById(R.id.layout_book);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		BookEntity book = listBook.get(position);

		if (book.getType() == AppConstant.FAMOUS_BOOK) {
			holder.iv_book.setImageResource(R.drawable.b_default);
			holder.tv_name.setText("三个火枪手" + position);
		} else if (book.getType() == AppConstant.EXAM) {
			holder.layout_bg.setBackgroundResource(R.drawable.exam_book_bg);
			holder.tv_name.setText("新课标" + position + "\n语文\n三年级\n下");
		} else {
			holder.iv_book.setImageResource(R.drawable.english_default);
			holder.tv_name.setText("新课标" + position + "\n语文\n三年级\n下");
		}
		return convertView;
	}

	private class ViewHolder {
		private ImageView iv_book;
		private TextView tv_name;
		private RelativeLayout layout_bg;

	}

}
