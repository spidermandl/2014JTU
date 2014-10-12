package com.minor.water;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.minor.util.ProgressDialogUtils;
import com.minor.util.WebServiceUtils;
import com.minor.util.WebServiceUtils.WebServiceCallBack;

/**
 * ��ʾ���е�Activity
 * 
 * @see http://blog.csdn.net/xiaanming
 * 
 * @author xiaanming
 * 
 */
public class CityActivity extends Activity
{
	private List<String> cityStringList;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.province);
		init();
	}

	private void init()
	{
		final ListView mCityList = (ListView) findViewById(R.id.province_list);
		// ��ʾ������
		ProgressDialogUtils.showProgressDialog(this, "���ݼ�����...");
		// ��Ӳ���
		HashMap<String, String> properties = new HashMap<String, String>();
		properties.put("byProvinceName",
				getIntent().getStringExtra("province"));

		WebServiceUtils.callWebService(
				WebServiceUtils.WEB_SERVER_URL, "getSupportCity",
				properties, new WebServiceCallBack()
				{

					@Override
					public void callBack(SoapObject result)
					{
						ProgressDialogUtils.dismissProgressDialog();
						if (result != null)
						{
							cityStringList = parseSoapObject(result);
							mCityList
									.setAdapter(new ArrayAdapter<String>(
											CityActivity.this,
											android.R.layout.simple_list_item_1,
											cityStringList));
						} else
						{
							Toast.makeText(CityActivity.this,
									"��ȡWebService���ݴ���",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		mCityList.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				Intent intent = new Intent(CityActivity.this,
						RoomSliderActivity.class);
				intent.putExtra("city", cityStringList.get(position));
				startActivity(intent);
			}
		});
	}

	/**
	 * ����SoapObject����
	 * 
	 * @param result
	 * @return
	 */
	private List<String> parseSoapObject(SoapObject result)
	{
		List<String> list = new ArrayList<String>();
		SoapObject provinceSoapObject = (SoapObject) result
				.getProperty("getSupportCityResult");
		for (int i = 0; i < provinceSoapObject.getPropertyCount(); i++)
		{
			String cityString = provinceSoapObject.getProperty(i)
					.toString();
			list.add(cityString.substring(0, cityString.indexOf("("))
					.trim());
		}

		return list;
	}
}