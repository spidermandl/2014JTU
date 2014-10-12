package com.minor.water;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.minor.util.HttpUtils;

public class RoomSliderActivity extends Activity
{
	private List<JSONObject> lists;
	private ProgressDialog pd;
	private String city_str = null;
	private ImageView[] imageViews = null;
	private TextView[] days = null;
	private TextView[] weathers = null;
	private TextView[] types = null;
	private TextView cityName;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.room_mainface_child);
		WindowManager m = getWindowManager();
		Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
		LayoutParams p = getWindow().getAttributes(); // 获取对话框当前的参数值
		p.windowAnimations = android.R.style.Animation_Toast;
		p.height = (int) (d.getHeight() * 0.74); // 高度设置为屏幕的1.0
		p.width = (int) (d.getWidth() * 1.0); // 宽度设置为屏幕的0.8
		getWindow().setAttributes(p); // 设置生效
		getWindow().setGravity(Gravity.TOP);
		imageViews = new ImageView[3];
		days = new TextView[3];
		weathers = new TextView[3];
		types = new TextView[3];
		cityName = (TextView) findViewById(R.id.textView1);
		imageViews[0] = (ImageView) findViewById(R.id.imageView4);
		imageViews[1] = (ImageView) findViewById(R.id.imageView5);
		imageViews[2] = (ImageView) findViewById(R.id.imageView6);
		days[0] = (TextView) findViewById(R.id.textView9);
		days[1] = (TextView) findViewById(R.id.textView12);
		days[2] = (TextView) findViewById(R.id.textView15);
		weathers[0] = (TextView) findViewById(R.id.textView10);
		weathers[1] = (TextView) findViewById(R.id.textView13);
		weathers[2] = (TextView) findViewById(R.id.textView16);
		types[0] = (TextView) findViewById(R.id.textView11);
		types[1] = (TextView) findViewById(R.id.textView14);
		types[2] = (TextView) findViewById(R.id.textView17);
		city_str = getIntent().getStringExtra("city");
		if (city_str != null)
		{
			try
			{
				city_str = java.net.URLEncoder.encode(city_str,
						"UTF-8");
			} catch (UnsupportedEncodingException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(city_str);
		}
		init();

	}

	public void choice(View v)
	{
		Intent intent = new Intent(RoomSliderActivity.this,
				ProvinceActivity.class);
		startActivity(intent);
	}

	private void init()
	{
		lists = new ArrayList<JSONObject>();
		pd = ProgressDialog.show(RoomSliderActivity.this, null,
				"加载数据中，请稍等");
		pd.setCancelable(true);
		getData();
	}

	private void getData()
	{
		new MyAsyncTask().execute();
	}

	class MyAsyncTask extends AsyncTask<String, Integer, String>
	{

		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			// super.onPreExecute();

			if (lists.size() == 0)
			{
				if (pd != null)
					pd.dismiss();
				pd = ProgressDialog.show(RoomSliderActivity.this,
						null, "加载数据中，请稍等...");
				pd.setCancelable(true);
			}
		}

		@Override
		protected String doInBackground(String... params)
		{
			// TODO Auto-generated method stub
			String path = "";
			path = "http://api.map.baidu.com/telematics/v3/weather?location="
					+ city_str
					+ "&output=json&ak=GuZriL3rkm1MUnyTyfsNGvTC";
			// http://api.map.baidu.com/telematics/v3/weather?location=北京&output=json&ak=GuZriL3rkm1MUnyTyfsNGvTC
			String jsonString = HttpUtils.getJsonContent(path);
			return jsonString;// 作为result传递到onPostExecute
		}

		@Override
		protected void onPostExecute(String result)
		{
			// TODO Auto-generated method stub
			// super.onPostExecute(result);
			doingData(result);// 处理得到的数据
		}
	}

	private int parseIcon(int time, String strIcon)
	{
		if (strIcon == null)
			return -1;
		if (time == 0)
		{
			if ("晴".equals(strIcon))
				return R.drawable.wether_ico00;
			if ("多云".equals(strIcon))
				return R.drawable.wether_ico01;
			if ("阴".equals(strIcon))
				return R.drawable.wether_ico02;
			if ("阵雨".equals(strIcon))
				return R.drawable.wether_ico03;
			if ("雷阵雨".equals(strIcon))
				return R.drawable.wether_ico04;
			if ("雷阵雨伴有冰雹".equals(strIcon))
				return R.drawable.wether_ico05;
			if ("雨夹雪".equals(strIcon))
				return R.drawable.wether_ico06;
			if ("小雨".equals(strIcon))
				return R.drawable.wether_ico07;
			if ("中雨".equals(strIcon))
				return R.drawable.wether_ico08;
			if ("大雨".equals(strIcon))
				return R.drawable.wether_ico09;
			if ("暴雨".equals(strIcon))
				return R.drawable.wether_ico10;
			if ("大暴雨".equals(strIcon))
				return R.drawable.wether_ico11;
			if ("特大暴雨".equals(strIcon))
				return R.drawable.wether_ico12;
			if ("阵雪".equals(strIcon))
				return R.drawable.wether_ico13;
			if ("小雪".equals(strIcon))
				return R.drawable.wether_ico14;
			if ("中雪".equals(strIcon))
				return R.drawable.wether_ico15;
			if ("大雪".equals(strIcon))
				return R.drawable.wether_ico16;
			if ("暴雪".equals(strIcon))
				return R.drawable.wether_ico17;
			if ("雾".equals(strIcon))
				return R.drawable.wether_ico18;
			if ("冻雨".equals(strIcon))
				return R.drawable.wether_ico19;
			if ("沙尘暴".equals(strIcon))
				return R.drawable.wether_ico20;
			if ("小雨转中雨".equals(strIcon))
				return R.drawable.wether_ico21;
			if ("中雨转大雨".equals(strIcon))
				return R.drawable.wether_ico22;
			if ("大雨转暴雨".equals(strIcon))
				return R.drawable.wether_ico23;
			if ("暴雨转大暴雨".equals(strIcon))
				return R.drawable.wether_ico24;
			if ("大暴雨转特大暴雨".equals(strIcon))
				return R.drawable.wether_ico25;
			if ("小雪转中雪".equals(strIcon))
				return R.drawable.wether_ico26;
			if ("中雪转大雪".equals(strIcon))
				return R.drawable.wether_ico27;
			if ("大雪转暴雪".equals(strIcon))
				return R.drawable.wether_ico28;
			if ("浮尘".equals(strIcon))
				return R.drawable.wether_ico29;
			if ("扬沙".equals(strIcon) || "霾".equals(strIcon))
				return R.drawable.wether_ico30;
			if ("强沙尘暴".equals(strIcon))
				return R.drawable.wether_ico31;
			return R.drawable.wether_ico00;
		} else
		{
			if (strIcon.indexOf("晴") > 0)
				return R.drawable.wether_ico32;
			if (strIcon.indexOf("云") > 0)
				return R.drawable.wether_ico33;
			if (strIcon.indexOf("雨") > 0)
				return R.drawable.wether_ico34;
			if (strIcon.indexOf("雪") > 0)
				return R.drawable.wether_ico35;
			if (strIcon.indexOf("雷") > 0)
				return R.drawable.wether_ico36;
			if (strIcon.indexOf("冰") > 0)
				return R.drawable.wether_ico37;
			return R.drawable.wether_ico32;
		}
		// return 0;
	}

	public void doingData(String data)
	{
		System.out.println("百度天气接口数据在这" + data);
		JSONObject O;
		try
		{

			O = new JSONObject(data);
			int returnResult = O.getInt("error");
			if (returnResult == 0)
			{
				System.out.println(".............");
				lists.clear();
				String date = O.getString("date");
				JSONArray posts = O.getJSONArray(("results"));// 只有一个result
				JSONObject tempObj = (JSONObject) posts.get(0);// 第0个对象
				String cityname = tempObj.getString("currentCity");
				JSONArray tempArr = tempObj
						.getJSONArray("weather_data");
				cityName.setText(cityname);
				JSONObject temp = new JSONObject();
				for (int i = 0; i < 3; i++)
				{
					temp = (JSONObject) tempArr.get(i);
					String weekday = temp.getString("date")
							.substring(0, 2);
					String temperature = temp
							.getString("temperature");
					weathers[i].setText(temperature);
					String tempWeatherType = temp
							.getString("weather");
					if (i == 0)
					{
						days[i].setText("今天");
					} else
					{
						days[i].setText(weekday);
					}
					int changeType = tempWeatherType.indexOf("转");
					String dayWeatherType = null;
					if (changeType > 0)
					{
						dayWeatherType = tempWeatherType.substring(0,
								changeType);

					} else
					{
						dayWeatherType = tempWeatherType;
					}
					imageViews[i].setImageResource(parseIcon(0,
							dayWeatherType));
					types[i].setText(dayWeatherType);
				}

			}
		} catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "网络问题！",
					Toast.LENGTH_SHORT).show();
		}
		pd.dismiss();
	}
}
