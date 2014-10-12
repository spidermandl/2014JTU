package com.minor.water;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

public class RoomMainActivity extends FragmentActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.room_mainface);
		
	}
	public void dropDown(View v){
		Intent intent=new  Intent(RoomMainActivity.this,RoomSliderActivity.class);
		startActivity(intent);
	}

}
