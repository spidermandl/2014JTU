package com.minor.water;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class Login2Activity extends Activity implements OnClickListener {
EditText login_username,login_password;
private String FILE = "saveUserNamePwd";//���ڱ���SharedPreferences���ļ�
private SharedPreferences sp = null;//����һ��SharedPreferences 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.login2);
		sp = getSharedPreferences(FILE, MODE_PRIVATE);
		login_username =(EditText) findViewById(R.id.login_username);
		login_password =(EditText) findViewById(R.id.login_password);
		findViewById(R.id.login).setOnClickListener(this);
		findViewById(R.id.back).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.login:
			if(TextUtils.isEmpty(login_username.getText().toString())){
				Toast.makeText(Login2Activity.this, "�������û���", 0).show();
			}else if(TextUtils.isEmpty(login_password.getText().toString())){
				Toast.makeText(Login2Activity.this, "����������", 0).show();

			}else if(!login_username.getText().toString().equals(sp.getString("username", ""))){
				Toast.makeText(Login2Activity.this, "�û���������", 0).show();
			}else if(login_username.getText().toString().equals(sp.getString("username", ""))){
					if(!login_password.getText().toString().equals(sp.getString("password", ""))){
						Toast.makeText(Login2Activity.this, "���벻��ȷ", 0).show();
				}
			}else if(login_username.getText().toString().equals(sp.getString("username", ""))){
				if(login_password.getText().toString().equals(sp.getString("password", ""))){
			
					
				Toast.makeText(Login2Activity.this, "��ȷ", 0).show();

				Intent intent111 = new Intent(Login2Activity.this,SuccessActivity.class);
				startActivity(intent111);
			}
			}
			
		default:
			break;
		}
		
	}

}
