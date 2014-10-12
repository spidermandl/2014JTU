package com.minor.water;

import java.util.ArrayList;
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
 * ��ʾ����ʡ�ݵ�Activity
 * 
 * @see http://blog.csdn.net/xiaanming
 * 
 * @author xiaanming
 * 
 */
public class ProvinceActivity extends Activity
{
	private List<String> provinceList = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.province);
		init();
	}

	private void init()
	{
		final ListView mProvinceList = (ListView) findViewById(R.id.province_list);
		ProgressDialogUtils.showProgressDialog(this, "���ݼ�����...");

		// ͨ�����������WebService�ӿ�
		WebServiceUtils.callWebService(
				WebServiceUtils.WEB_SERVER_URL, "getSupportProvince",
				null, new WebServiceCallBack()
				{

					// WebService�ӿڷ��ص����ݻص������������
					@Override
					public void callBack(SoapObject result)
					{
						ProgressDialogUtils.dismissProgressDialog();
						if (result != null)
						{
							provinceList = parseSoapObject(result);
							mProvinceList
									.setAdapter(new ArrayAdapter<String>(
											ProvinceActivity.this,
											android.R.layout.simple_list_item_1,
											provinceList));
						} else
						{
							Toast.makeText(ProvinceActivity.this,
									"��ȡWebService���ݴ���",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		mProvinceList
				.setOnItemClickListener(new OnItemClickListener()
				{

					@Override
					public void onItemClick(AdapterView<?> parent,
							View view, int position, long id)
					{
						Intent intent = new Intent(ProvinceActivity.this,
								CityActivity.class);
						intent.putExtra("province",
								provinceList.get(position));
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
				.getProperty("getSupportProvinceResult");
		if (provinceSoapObject == null)
		{
			return null;
		}
		for (int i = 0; i < provinceSoapObject.getPropertyCount(); i++)
		{
			list.add(provinceSoapObject.getProperty(i).toString());
		}

		return list;
	}

}
