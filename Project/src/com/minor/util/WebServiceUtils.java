package com.minor.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.client.HttpResponseException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.os.Handler;
import android.os.Message;

/**
 * ����WebService�Ĺ�����,
 * 
 * @see http://blog.csdn.net/xiaanming
 * 
 * @author xiaanming
 * 
 */
public class WebServiceUtils
{
	public static final String WEB_SERVER_URL = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";

	// ����3���̵߳��̳߳�
	private static final ExecutorService executorService = Executors
			.newFixedThreadPool(3);

	// �����ռ�
	private static final String NAMESPACE = "http://WebXml.com.cn/";

	/**
	 * 
	 * @param url
	 *            WebService��������ַ
	 * @param methodName
	 *            WebService�ĵ��÷�����
	 * @param properties
	 *            WebService�Ĳ���
	 * @param webServiceCallBack
	 *            �ص��ӿ�
	 */
	public static void callWebService(String url,
			final String methodName,
			HashMap<String, String> properties,
			final WebServiceCallBack webServiceCallBack)
	{
		// ����HttpTransportSE���󣬴���WebService��������ַ
		final HttpTransportSE httpTransportSE = new HttpTransportSE(
				url);
		// ����SoapObject����
		SoapObject soapObject = new SoapObject(NAMESPACE, methodName);

		// SoapObject��Ӳ���
		if (properties != null)
		{
			for (Iterator<Map.Entry<String, String>> it = properties
					.entrySet().iterator(); it.hasNext();)
			{
				Map.Entry<String, String> entry = it.next();
				soapObject.addProperty(entry.getKey(),
						entry.getValue());
			}
		}

		// ʵ����SoapSerializationEnvelope������WebService��SOAPЭ��İ汾��
		final SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		// �����Ƿ���õ���.Net������WebService
		soapEnvelope.setOutputSoapObject(soapObject);
		soapEnvelope.dotNet = true;
		httpTransportSE.debug = true;

		// �������߳������߳�ͨ�ŵ�Handler
		final Handler mHandler = new Handler()
		{

			@Override
			public void handleMessage(Message msg)
			{
				super.handleMessage(msg);
				// ������ֵ�ص���callBack�Ĳ�����
				webServiceCallBack.callBack((SoapObject) msg.obj);
			}

		};

		// �����߳�ȥ����WebService
		executorService.submit(new Runnable()
		{

			@Override
			public void run()
			{
				SoapObject resultSoapObject = null;
				try
				{
					httpTransportSE.call(NAMESPACE + methodName,
							soapEnvelope);
					if (soapEnvelope.getResponse() != null)
					{
						// ��ȡ��������Ӧ���ص�SoapObject
						resultSoapObject = (SoapObject) soapEnvelope.bodyIn;
					}
				} catch (HttpResponseException e)
				{
					e.printStackTrace();
				} catch (IOException e)
				{
					e.printStackTrace();
				} catch (XmlPullParserException e)
				{
					e.printStackTrace();
				} finally
				{
					// ����ȡ����Ϣ����Handler���͵����߳�
					mHandler.sendMessage(mHandler.obtainMessage(0,
							resultSoapObject));
				}
			}
		});
	}

	/**
	 * 
	 * 
	 * @author xiaanming
	 * 
	 */
	public interface WebServiceCallBack
	{
		public void callBack(SoapObject result);
	}

}