package com.example.onlineclass.activity;

import com.example.onlineclass.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author anumbrella
 * 
 * @date 2015-9-19 上午9:17:36
 * 
 *       登录界面
 */
public class LoginActivity extends BaseActivity implements OnClickListener {

	private TextView tv_register, find_passowrd;

	private Button btn_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		tv_register = (TextView) findViewById(R.id.tv_register);
		find_passowrd = (TextView) findViewById(R.id.find_password);
		btn_back = (Button) findViewById(R.id.btn_back);
		tv_register.setOnClickListener(this);
		find_passowrd.setOnClickListener(this);
		btn_back.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_register:
			Intent intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
			break;
		case R.id.find_password:
			intent = new Intent(this, FindPasswordActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_back:
			finish();
			break;
		}
	}

}
