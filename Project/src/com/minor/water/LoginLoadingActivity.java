package com.minor.water;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

public class LoginLoadingActivity extends Activity {
	private ProgressBar progressBar; 
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				if(msg.arg1==5){
				Intent intent = new Intent(LoginLoadingActivity.this,SuccessActivity.class);
				startActivity(intent);
				}
					
				
				break;

			default:
				break;
			}
			
			
		}
		
		
		
	
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		
		new Thread(new Runnable() {
			int i=0;
			@Override
			public void run() {
				for(;i<6;i++){
				
				
				Message msg = new Message();
				msg.what=0;
				msg.arg1=i;
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(msg.arg1);
				handler.sendMessage(msg);
			}
				
			}
		}).start();
		}
	
}
