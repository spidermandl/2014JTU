package com.minor.water;



import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;

public class FinishRegistActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.finishregist);
		findViewById(R.id.finished).setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.finished:
			Intent intent = new Intent(FinishRegistActivity.this,LoginLoadingActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		
	}

}
