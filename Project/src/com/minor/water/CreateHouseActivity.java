package com.minor.water;

import android.app.Activity;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

public class CreateHouseActivity extends Activity {

	private ImageView back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.createhouse);
		init();
	}
	void init(){
		String user1=null;
		try{
		Intent user = getIntent();
		user1=user.getStringExtra("user");
		}catch(Exception e){
			e.printStackTrace();
		}
		//返回
		back = (ImageView) findViewById(R.id.create_back);
		back.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	//根据是否登录来判断是否显示返回
		if(user1!=null){
			back.setVisibility(View.INVISIBLE);
		}
		//下一步
		findViewById(R.id.next).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CreateHouseActivity.this,WiFiActivity.class);
				startActivity(intent);
				
			}
		});
		
	}

}
