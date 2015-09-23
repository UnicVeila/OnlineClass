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
 * @date 2015-9-23 上午10:54:18
 * 
 *       用户注册界面activity
 */
public class RegisterActivity extends BaseActivity implements OnClickListener {

	private RadioGroup radioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		initView();

	}

	/**
	 * 初始化一下视图资源
	 */
	private void initView() {
		radioGroup = (RadioGroup) findViewById(R.id.radio_sex);
		radioGroup.check(R.id.radio_girl);
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
