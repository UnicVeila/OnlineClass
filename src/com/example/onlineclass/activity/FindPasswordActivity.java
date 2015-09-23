package com.example.onlineclass.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.onlineclass.R;

/**
 * @author anumbrella
 * 
 * @date 2015-9-23 上午10:56:00
 * 
 *       找寻密码界面activity
 */
public class FindPasswordActivity extends BaseActivity implements
		OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_password);
		initView();
		iv_title.setImageResource(R.drawable.password_gray);
		tv_title.setText(R.string.password_find);
	}

	/**
	 * 初始化一下视图资源
	 */
	private void initView() {

		btn_back = (Button) findViewById(R.id.btn_back);
		iv_title = (ImageView) findViewById(R.id.iv_title);
		tv_title = (TextView) findViewById(R.id.tv_title);
		btn_back.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		}
	}

}
