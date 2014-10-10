package com.minor.water;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class WiFiActivity extends Activity{

	private TextView goback,connect,wifiName,wifiPwd;
	private WifiManager mWifi;
	private String mWifiName;
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wifiitem);
		init();
	
		mWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	/*	WifiInfo wifiInfo = mWifi.getConnectionInfo();
		mWifiName = wifiInfo.getSSID();
		wifiName.setText(mWifiName);
		System.out.println(wifiInfo.getSSID());*/
		mWifiName="cmcc";
		String wifiPass="12345678";
		
	    WifiConfiguration config = new WifiConfiguration();
        config.SSID = "\""+mWifiName+"\"";
        config.preSharedKey = "\""+wifiPass+"\"";
        config.hiddenSSID = true;
        config.status = WifiConfiguration.Status.ENABLED;
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP); 
        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);   
        int netId = mWifi.addNetwork(config);
        boolean b = mWifi.enableNetwork(netId, true);
		
		
	}

	private void init() {
		goback = (TextView) findViewById(R.id.previous);
		connect =(TextView) findViewById(R.id.connect);
		wifiName = (TextView) findViewById(R.id.wifiname);
		wifiPwd =(TextView) findViewById(R.id.wifipwd);
		goback.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
           finish();				
			}
		});
		connect.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
		LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
			View view = inflater.inflate(R.layout.loading2,null);
		AlertDialog.Builder builder =new AlertDialog.Builder(WiFiActivity.this);
			builder.setView(view);
			builder.show();
			
			Timer timer = new Timer();
		
			TimerTask task2 = new TimerTask(){

				@Override
				public void run() {
				Intent intent =  new Intent(WiFiActivity.this,MainHouseActivity.class);
				startActivity(intent);
					
				}
				
			};
		    timer.schedule(task2, 00*3);
			Toast.makeText(WiFiActivity.this, "连接成功", 0).show();

			}
		});
		

	}
	
}
