package com.example.onlineclass.activity;

import com.example.onlineclass.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author anumbrella
 * 
 * @date 2015-9-23 下午6:34:03
 * 
 *       用户充值界面
 */
public class PayActivity extends BaseActivity implements OnClickListener {

	private Button btn_pay, btn_reset;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay);
		// 初始化一些视图资源
		initView();
		iv_title.setImageResource(R.drawable.money_pressed);
		tv_title.setText(getResources().getString(R.string.add_account));
	}

	private void initView() {
		iv_title = (ImageView) findViewById(R.id.iv_title);
		tv_title = (TextView) findViewById(R.id.tv_title);
		btn_back = (Button) findViewById(R.id.btn_back);
		btn_pay = (Button) findViewById(R.id.btn_pay);
		btn_reset = (Button) findViewById(R.id.btn_reset);
		btn_back.setOnClickListener(this);
		btn_pay.setOnClickListener(this);
		btn_reset.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		case R.id.btn_pay:
			Intent intent = new Intent(PayActivity.this, PayOkActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_reset:
			Toast.makeText(this, "这个是充值按钮", Toast.LENGTH_SHORT).show();
			break;
		}
	}
}
