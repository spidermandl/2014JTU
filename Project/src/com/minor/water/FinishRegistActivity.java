package com.minor.water;

/**
 * 完成注册界面
 */


import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class FinishRegistActivity extends Activity implements OnClickListener {
EditText username,password,password2;
private String FILE = "saveUserNamePwd";//用于保存SharedPreferences的文件
private SharedPreferences sp = null;//声明一个SharedPreferences 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.finishregist);
		username =(EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		password2 = (EditText) findViewById(R.id.password2);
		findViewById(R.id.finished).setOnClickListener(this);
		findViewById(R.id.back).setOnClickListener(this);
		sp = getSharedPreferences(FILE, MODE_PRIVATE);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//完成按钮单击事件
		case R.id.finished:
			
			if(TextUtils.isEmpty(username.getText().toString())){
				Toast.makeText(FinishRegistActivity.this, "用户名不能为空", 0).show();
			}else if(TextUtils.isEmpty(password.getText().toString())){
				Toast.makeText(FinishRegistActivity.this, "密码不能为空", 0).show();
			}else if(TextUtils.isEmpty(password2.getText().toString())){
				Toast.makeText(FinishRegistActivity.this, "确认密码不能为空", 0).show();
			}else if(!password.getText().toString().equals(password2.getText().toString()))
			{
				Toast.makeText(FinishRegistActivity.this, "两次密码不一致", 0).show();

			}else{
				//弹出进度条
			
				LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
				View view = inflater.inflate(R.layout.loading, null);
				AlertDialog.Builder builder2 = new AlertDialog.Builder(FinishRegistActivity.this);
				builder2.setView(view);
				
				builder2.show();
				   Timer timer = new Timer();
				   TimerTask task = new TimerTask(){

					@Override
					public void run() {
						///builder2.
						//将用户名和密码存到SharedPreference中
						Editor editor = sp.edit();
						editor.putString("username", username.getText().toString());
						editor.putString("password", password.getText().toString());
						editor.commit();
						//dismissDialog(R.layout.loading);
                          Intent intent = new Intent(FinishRegistActivity.this,SuccessActivity.class);						
				          startActivity(intent);			          
					}					   
				   };
				  
				   timer.schedule(task, 1000*3);
			}
			
			break;
			//返回
		case R.id.back:
			finish();
			break;
		default:
			break;
		}
		
	}

}
