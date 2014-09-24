package com.example.project1;

import com.example.project.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity{
	
	private ImageView image;
	private EditText edit;
	private Button btn;
	public static final String CL="TEXT";
	private String str2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
image = (ImageView) findViewById(R.id.iv);
		
		edit = (EditText) findViewById(R.id.et);
		
		btn = (Button) findViewById(R.id.register);
		/**
		 * ���ص���һ�����
		 */
		image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		/**
		 * ע�ᰴť����
		 */
        btn.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		str2 = edit.getText().toString();
		if(TextUtils.isEmpty(str2)){
			Toast.makeText(getApplicationContext(), "�������ֻ���", 0).show();
		}else if(str2.length()<11){
			Toast.makeText(getApplicationContext(), "��������ȷ���ֻ���", 0).show();
			   return;
		}else{
		/**
		 * 
		 * ���Ͷ���
		 */
		Intent intent=new Intent(getApplicationContext(), MainActivity2.class);
		intent.putExtra("TEXT", str2);
		startActivity(intent);
		}
	}
});		
	}

}
