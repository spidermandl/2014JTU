package com.example.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.project.R;



public class LoginActivity extends Activity
{
	private ImageView[] imageViews = null;
	private ImageView imageView = null;
	private ViewPager advPager = null;
	private AtomicInteger what = new AtomicInteger(0);
	private boolean isContinue = true;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.login);
		initViewPager();
	}
	
	
	
	public void register(View v){
		Intent intent=new Intent(getApplicationContext(), MainActivity.class);
		startActivity(intent);
	}

	@SuppressLint("NewApi")
	private void initViewPager()
	{
		advPager = (ViewPager) findViewById(R.id.adv_pager);
		ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);

		// 这里存放的是四张广告背景
		List<View> advPics = new ArrayList<View>();

		ImageView img1 = new ImageView(this);
		img1.setBackgroundResource(R.drawable.icon1);
		advPics.add(img1);

		ImageView img2 = new ImageView(this);
		img2.setBackgroundResource(R.drawable.icon2);
		advPics.add(img2);

		ImageView img3 = new ImageView(this);
		img3.setBackgroundResource(R.drawable.icon3);
		advPics.add(img3);
		// 对imageviews进行填充
		imageViews = new ImageView[advPics.size()];
		// 小图标
		for (int i = 0; i < advPics.size(); i++)
		{
			imageView = new ImageView(this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					50, 6);
			//params.setMarginEnd(20);
			imageView.setLayoutParams(params);
			// imageView.setPadding(20, 5, 20, 5);
			imageViews[i] = imageView;
			if (i == 0)
			{
				imageViews[i].setBackgroundResource(R.drawable.focus);
			} else
			{
				imageViews[i]
						.setBackgroundResource(R.drawable.nofocus);
			}
			group.addView(imageViews[i]);
		}

		advPager.setAdapter(new AdvAdapter(advPics));
		advPager.setOnPageChangeListener(new GuidePageChangeListener());
		advPager.setOnTouchListener(new OnTouchListener()
		{

			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				switch (event.getAction())
				{
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_MOVE:
					isContinue = false;
					break;
				case MotionEvent.ACTION_UP:
					isContinue = true;
					break;
				default:
					isContinue = true;
					break;
				}
				return false;
			}
		});
		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				while (true)
				{
					if (isContinue)
					{
						viewHandler.sendEmptyMessage(what.get());
						whatOption();
					}
				}
			}

		}).start();
	}

	private void whatOption()
	{
		what.incrementAndGet();
		if (what.get() > imageViews.length - 1)
		{
			
			
		}
		try
		{
			Thread.sleep(5000);
		} catch (InterruptedException e)
		{

		}
	}

	private final Handler viewHandler = new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{
			advPager.setCurrentItem(msg.what);
			super.handleMessage(msg);
		}

	};

	private final class GuidePageChangeListener implements
			OnPageChangeListener
	{

		@Override
		public void onPageScrollStateChanged(int arg0)
		{

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2)
		{

		}

		@Override
		public void onPageSelected(int arg0)
		{
			what.getAndSet(arg0);
			for (int i = 0; i < imageViews.length; i++)
			{
				imageViews[arg0]
						.setBackgroundResource(R.drawable.focus);
				if (arg0 != i)
				{
					imageViews[i]
							.setBackgroundResource(R.drawable.nofocus);
				}
			}

		}

	}

	private final class AdvAdapter extends PagerAdapter
	{
		private List<View> views = null;

		public AdvAdapter(List<View> views)
		{
			this.views = views;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2)
		{
			((ViewPager) arg0).removeView(views.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0)
		{

		}

		@Override
		public int getCount()
		{
			return views.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1)
		{
			((ViewPager) arg0).addView(views.get(arg1), 0);
			return views.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1)
		{
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1)
		{

		}

		@Override
		public Parcelable saveState()
		{
			return null;
		}

		@Override
		public void startUpdate(View arg0)
		{

		}

	}
}
