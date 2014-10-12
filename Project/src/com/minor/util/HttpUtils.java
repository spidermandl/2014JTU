package com.minor.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;

public class HttpUtils {

	public static String HOME = "http://www.cxtec.com.cn/MeetingCloud/wp-content/plugins/MobileInterface/";

	public HttpUtils() {
		// TODO Auto-generated constructor stub
	}

	public static String getJsonContent(String url_path) {
		try {
			URL url = new URL(url_path);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(3000);
			connection.setRequestMethod("GET");
			connection.setDoInput(true);

			int code = connection.getResponseCode();
			if (code == 200) {
				return changeInputStream(connection.getInputStream());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	public static Bitmap getPic(String url_path) {
		Bitmap bitmap = null;
		try {
			URL url = new URL(url_path);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(3000);
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			int code = connection.getResponseCode();
			if (code == 200) {
				InputStream inputStream = connection.getInputStream();
				bitmap = BitmapFactory.decodeStream(inputStream);
				// System.out.println("=============="+bitmap.getWidth()+","+bitmap.getHeight());
				return bitmap;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bitmap;
	}

	private static String changeInputStream(InputStream inputStream) {
		// TODO Auto-generated method stub
		String jsonString = "";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		int len = 0;
		byte[] data = new byte[1024];
		try {
			while ((len = inputStream.read(data)) != -1) {
				outputStream.write(data, 0, len);
			}
			jsonString = new String(outputStream.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}

	public static String visitHtml(String url_path) {
		try {
			URL url = new URL(url_path);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(3000);
			connection.setRequestMethod("GET");
			connection.setDoInput(true);

			int code = connection.getResponseCode();
			// if (code == 200) {
			// return changeInputStream(connection.getInputStream());
			// }
			return "1";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "/n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * Post 方式
	 * 
	 * @param url
	 *            网址
	 * @param paramsArray
	 *            参数
	 * @param values
	 *            �?
	 * @param charset
	 *            编码，例如：UTF-8、GBK
	 * @return
	 */
	public static String postUrl(String url, String[] paramsArray,
			String[] values, String charset) {
		String returnConnection = null;
		// 封装数据
		Map<String, String> parmas = new HashMap<String, String>();
		int paramsArrayLength = paramsArray.length;
		int valuesLength = values.length;
		if (paramsArrayLength == valuesLength) {
			for (int i = 0; i < paramsArrayLength; i++) {
				 System.out.println("paramsArray = " + paramsArray[i] +
				 ", value = " + values[i]);
				parmas.put(paramsArray[i], values[i]);
			}

			DefaultHttpClient client = new DefaultHttpClient();// http客户�?
			HttpPost httpPost = new HttpPost(url);

			List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
			if (parmas != null) {
				Set<String> keys = parmas.keySet();
				for (Iterator<String> i = keys.iterator(); i.hasNext();) {
					String key = (String) i.next();
					pairs.add(new BasicNameValuePair(key, parmas.get(key)));
					System.out.println("key = " + key + ", value = "
							+ parmas.get(key));
				}
				try {
					StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
							.permitAll().build();
					StrictMode.setThreadPolicy(policy);
					UrlEncodedFormEntity p_entity = new UrlEncodedFormEntity(
							pairs, charset);
					// 将POST数据放入HTTP请求
					httpPost.setEntity(p_entity);
					// 发出实际的HTTP POST请求
					HttpResponse response = client.execute(httpPost);
					int statusCode = response.getStatusLine().getStatusCode();

					if (statusCode != HttpStatus.SC_OK) {
						System.out.println("错误代码�?  " + statusCode);
					} else {
						HttpEntity entity = response.getEntity();
						InputStream content = entity.getContent();
						returnConnection = changeInputStream(content);
						// returnConnection = content.toString();//
						// changeInputStream(content);
						System.out.println("结果2�?" + returnConnection);
					}
				} catch (IllegalStateException e) {
					e.printStackTrace();
					System.out.println("出错1�?" + e.getStackTrace());
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("出错2�?" + e.getStackTrace());
				}
			}
		} else {
			System.out.println("出错3：参数不�?");
		}
		return returnConnection;
	}

}
