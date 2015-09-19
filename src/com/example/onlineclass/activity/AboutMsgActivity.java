package com.example.onlineclass.activity;

import com.example.onlineclass.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author anumbrella
 * 
 * @date 2015-9-19 上午9:18:46
 * 
 *       关于应用的信息
 */
public class AboutMsgActivity extends BaseActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);
		// 初始化一下
		initView();
		iv_title.setImageResource(R.drawable.about_msg_pressed);
		tv_title.setText(getResources().getString(R.string.about_us));
	}

	private void initView() {
		// TODO Auto-generated method stub
		iv_title = (ImageView) findViewById(R.id.iv_title);
		tv_title = (TextView) findViewById(R.id.tv_title);
		btn_back = (Button) findViewById(R.id.btn_back);
		btn_home = (Button) findViewById(R.id.btn_home);
		btn_back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		}

	}

}
