package com.minor.water;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class VerifyActivity extends Activity {

	private TextView text1;
	private TextView text2;
	private ImageView image;
	private ImageView image2;
	private EditText edit;
	private Button btn;
	
	Handler handler=new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			
			if((Integer)msg.obj!=0){
				text2.setText("接收短信大约需要"+msg.obj+"s");
			}else{
				text2.setText("点击重播");
				text2.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						 new Thread(new Runnable() {
								
								@Override
								public void run() {
					                 int i=30;
									while(true){
										i--;
										if(i>=0){
										Message msg=new Message();
										msg.obj=i;
										handler.sendMessage(msg);}
										SystemClock.sleep(1000);
									}
								}
							}).start();
					}
				});
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main2);
		
		Intent intent = getIntent();
		String extra = intent.getStringExtra(RegisterActivity.CL);
		
		text1 = (TextView) findViewById(R.id.textView1);
		text2 = (TextView) findViewById(R.id.textView2);
		image = (ImageView) findViewById(R.id.iv);
		image2 = (ImageView) findViewById(R.id.iv2);
		/**
		 * 清空按鈕
		 */
		image2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				edit.setText("");
			}
		});
		edit = (EditText) findViewById(R.id.editText1);
		btn = (Button) findViewById(R.id.button1);
		text1.setText("我们已经发送了验证码到你的手机:"+extra);
		image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(), RegisterActivity.class);
				startActivity(intent);				
			}
		});
        btn.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		String text = edit.getText().toString().trim();
		if(TextUtils.isEmpty(text)){
			Toast.makeText(getApplicationContext(), "请输入验证码", 0).show();
			return;
		}
		if(!"1234".equals(text)){
			Toast.makeText(getApplicationContext(), "验证码不正确", 0).show();
		}else{
			Intent intent=new Intent(getApplicationContext(), VerifySuccessActivity.class);
			startActivity(intent);
		}
	}
});	
        new Thread(new Runnable() {
			
			@Override
			public void run() {
                 int i=30;
				while(true){
					i--;
					if(i>=0){
					Message msg=new Message();
					msg.obj=i;
					handler.sendMessage(msg);}
					SystemClock.sleep(1000);
				}
			}
		}).start();
	}
}
