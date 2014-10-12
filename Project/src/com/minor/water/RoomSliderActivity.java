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
		Display d = m.getDefaultDisplay(); // Ϊ��ȡ��Ļ����
		LayoutParams p = getWindow().getAttributes(); // ��ȡ�Ի���ǰ�Ĳ���ֵ
		p.windowAnimations = android.R.style.Animation_Toast;
		p.height = (int) (d.getHeight() * 0.74); // �߶�����Ϊ��Ļ��1.0
		p.width = (int) (d.getWidth() * 1.0); // �������Ϊ��Ļ��0.8
		getWindow().setAttributes(p); // ������Ч
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
				"���������У����Ե�");
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
						null, "���������У����Ե�...");
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
			// http://api.map.baidu.com/telematics/v3/weather?location=����&output=json&ak=GuZriL3rkm1MUnyTyfsNGvTC
			String jsonString = HttpUtils.getJsonContent(path);
			return jsonString;// ��Ϊresult���ݵ�onPostExecute
		}

		@Override
		protected void onPostExecute(String result)
		{
			// TODO Auto-generated method stub
			// super.onPostExecute(result);
			doingData(result);// ����õ�������
		}
	}

	private int parseIcon(int time, String strIcon)
	{
		if (strIcon == null)
			return -1;
		if (time == 0)
		{
			if ("��".equals(strIcon))
				return R.drawable.wether_ico00;
			if ("����".equals(strIcon))
				return R.drawable.wether_ico01;
			if ("��".equals(strIcon))
				return R.drawable.wether_ico02;
			if ("����".equals(strIcon))
				return R.drawable.wether_ico03;
			if ("������".equals(strIcon))
				return R.drawable.wether_ico04;
			if ("��������б���".equals(strIcon))
				return R.drawable.wether_ico05;
			if ("���ѩ".equals(strIcon))
				return R.drawable.wether_ico06;
			if ("С��".equals(strIcon))
				return R.drawable.wether_ico07;
			if ("����".equals(strIcon))
				return R.drawable.wether_ico08;
			if ("����".equals(strIcon))
				return R.drawable.wether_ico09;
			if ("����".equals(strIcon))
				return R.drawable.wether_ico10;
			if ("����".equals(strIcon))
				return R.drawable.wether_ico11;
			if ("�ش���".equals(strIcon))
				return R.drawable.wether_ico12;
			if ("��ѩ".equals(strIcon))
				return R.drawable.wether_ico13;
			if ("Сѩ".equals(strIcon))
				return R.drawable.wether_ico14;
			if ("��ѩ".equals(strIcon))
				return R.drawable.wether_ico15;
			if ("��ѩ".equals(strIcon))
				return R.drawable.wether_ico16;
			if ("��ѩ".equals(strIcon))
				return R.drawable.wether_ico17;
			if ("��".equals(strIcon))
				return R.drawable.wether_ico18;
			if ("����".equals(strIcon))
				return R.drawable.wether_ico19;
			if ("ɳ����".equals(strIcon))
				return R.drawable.wether_ico20;
			if ("С��ת����".equals(strIcon))
				return R.drawable.wether_ico21;
			if ("����ת����".equals(strIcon))
				return R.drawable.wether_ico22;
			if ("����ת����".equals(strIcon))
				return R.drawable.wether_ico23;
			if ("����ת����".equals(strIcon))
				return R.drawable.wether_ico24;
			if ("����ת�ش���".equals(strIcon))
				return R.drawable.wether_ico25;
			if ("Сѩת��ѩ".equals(strIcon))
				return R.drawable.wether_ico26;
			if ("��ѩת��ѩ".equals(strIcon))
				return R.drawable.wether_ico27;
			if ("��ѩת��ѩ".equals(strIcon))
				return R.drawable.wether_ico28;
			if ("����".equals(strIcon))
				return R.drawable.wether_ico29;
			if ("��ɳ".equals(strIcon) || "��".equals(strIcon))
				return R.drawable.wether_ico30;
			if ("ǿɳ����".equals(strIcon))
				return R.drawable.wether_ico31;
			return R.drawable.wether_ico00;
		} else
		{
			if (strIcon.indexOf("��") > 0)
				return R.drawable.wether_ico32;
			if (strIcon.indexOf("��") > 0)
				return R.drawable.wether_ico33;
			if (strIcon.indexOf("��") > 0)
				return R.drawable.wether_ico34;
			if (strIcon.indexOf("ѩ") > 0)
				return R.drawable.wether_ico35;
			if (strIcon.indexOf("��") > 0)
				return R.drawable.wether_ico36;
			if (strIcon.indexOf("��") > 0)
				return R.drawable.wether_ico37;
			return R.drawable.wether_ico32;
		}
		// return 0;
	}

	public void doingData(String data)
	{
		System.out.println("�ٶ������ӿ���������" + data);
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
				JSONArray posts = O.getJSONArray(("results"));// ֻ��һ��result
				JSONObject tempObj = (JSONObject) posts.get(0);// ��0������
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
						days[i].setText("����");
					} else
					{
						days[i].setText(weekday);
					}
					int changeType = tempWeatherType.indexOf("ת");
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
			Toast.makeText(getApplicationContext(), "�������⣡",
					Toast.LENGTH_SHORT).show();
		}
		pd.dismiss();
	}
}
