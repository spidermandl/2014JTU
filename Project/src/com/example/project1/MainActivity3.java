package com.example.project1;

import com.example.project.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity3 extends Activity{
	private Button btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main3);
		
		btn = (Button) findViewById(R.id.ok);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				/*Intent intent=new Intent(getApplicationContext(), MainActivity3.class);
				startActivity(intent);*/
				
			}
		});
		
	}
}
