package com.yifu.platform.single.location;

import com.yifu.platform.single.json.JSONManager;
import com.yifu.platform.single.net.INetListener;
import com.yifu.platform.single.net.NetManager;
import com.yifu.platform.single.net.response.BaseResult;
import com.yifu.platform.single.net.response.LocationResult;
import com.yifu.platform.single.util.Constants;

import android.content.Context;
import android.location.LocationManager;
import android.util.Log;

public class MyLocationManager {
	private static MyLocationManager location;
	private static Context context;
	private LocationManager locationManager;

	private MyLocationManager(Context context) {
		this.context = context;
	}

	public static MyLocationManager getInstance(Context context) {
		if (null == location) {
			location = new MyLocationManager(context);
		}
		return location;
	}
	
	public void location(final LocationListener listener){
		String data = JSONManager.getJsonBuilder().getLoc();
		NetManager.getHttpConnect().sendRequest(Constants.URL_INIT_LOCATION, Constants.TAGS_INIT_LOCATION, data, new INetListener() {
			
			@Override
			public void onNetResponseErr(int requestTag, int requestId, int errorCode, String msg) {
				listener.onFinish();
			}
			
			@Override
			public void onNetResponse(int requestTag, BaseResult responseData, int requestId) {
				LocationResult result = (LocationResult) responseData;
				Constants.LocalProvince = result.getLocation();			
				listener.onFinish();
			}
			@Override
			public void onDownLoadStatus(DownLoadStatus status, int requestId) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onDownLoadProgressCurSize(long curSize, long totalSize, int requestId) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public interface LocationListener{
		public abstract void onFinish();
	}
	
	
}
