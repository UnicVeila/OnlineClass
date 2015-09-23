package com.example.onlineclass.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlineclass.R;

public class PayOkActivity extends BaseActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay_ok);
		initView();
		iv_title.setImageResource(R.drawable.money_pressed);
		tv_title.setText(getResources().getString(R.string.add_account));
	}

	private void initView() {
		iv_title = (ImageView) this.findViewById(R.id.iv_title);
		tv_title = (TextView) this.findViewById(R.id.tv_title);
		btn_back = (Button) this.findViewById(R.id.btn_back);
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
