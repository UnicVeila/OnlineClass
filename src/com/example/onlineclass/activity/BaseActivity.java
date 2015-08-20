package com.example.onlineclass.activity;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author anumbrella
 * 
 * @date 2015-8-19 下午3:19:34 
 * 
 * 
 * 基础的activity可以在这重写一些方法
 */
public class BaseActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void onStart(){
		super.onStart();
	}
	
	@Override
	protected void onStop(){
		super.onStop();
	}
	
	

}
