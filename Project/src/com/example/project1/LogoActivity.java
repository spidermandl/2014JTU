package com.example.project1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.example.project.R;

public class LogoActivity extends Activity
{

	private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	setContentView(R.layout.logo);
    	iv=(ImageView) findViewById(R.id.iv);
    	AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);
    	aa.setDuration(2200);
    	// 开始播放动画
    	iv.startAnimation(aa);
    	aa.setAnimationListener(new AnimationListener()
		{
			
			@Override
			public void onAnimationStart(Animation animation)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent(LogoActivity.this, GuideActivity.class);
				startActivity(intent);
			}
		});
    	
    }
}
