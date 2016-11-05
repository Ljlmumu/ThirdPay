
package com.yifu.platform.single.util;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;

public final class PhoneHelper {
	public static final String CMCC = "1";
	public static final String CUCC = "2";
	public static final String CTCC = "3";
	public static final String CAN_NOT_FIND = "0";
	
	private static TelephonyManager mTelephonyManager = null;
	
	public static String getPhoneNumber(Context appcontext) {

		if (mTelephonyManager == null) {
			mTelephonyManager = (TelephonyManager) appcontext
					.getSystemService(Context.TELEPHONY_SERVICE);
		}

		String res = mTelephonyManager.getLine1Number();
		
		if (res != null && res.length() > 0) {
			return res;
		}
		return "";
	}
	
    public static String getProvidersName(Context appcontext) {  
        String ProvidersName = null;  
        // ����Ψһ���û�ID;�������ſ��ı�������   
        if (mTelephonyManager == null) {
        	mTelephonyManager = (TelephonyManager) appcontext
					.getSystemService(Context.TELEPHONY_SERVICE);
        }
        
        String IMSI = mTelephonyManager.getSubscriberId();  
        // IMSI��ǰ��3λ460�ǹ�ң������ź���2λ00 02���й��ƶ���01���й���ͨ��03���й���š�     
        if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {  
            ProvidersName = CMCC;  //�ƶ�
        } else if (IMSI.startsWith("46001")) {  
            ProvidersName = CUCC;  //��ͨ
        } else if (IMSI.startsWith("46003")) {  
        	ProvidersName = CTCC;  //����
        } else {
        	ProvidersName = CAN_NOT_FIND;  //�������
        }
        return ProvidersName;  
    }  
	
	public static String getIMEI(Context appcontext) {

		if (mTelephonyManager == null) {
			mTelephonyManager = (TelephonyManager) appcontext
					.getSystemService(Context.TELEPHONY_SERVICE);
		}
		String res = mTelephonyManager.getDeviceId();
		if (res != null && res.length() > 0) {
			return res;
		}
		return "";
	}


	public static String getAppVersionName(Context appcontext) {
		try {
			
			PackageManager packageManager = appcontext.getPackageManager();
			PackageInfo packInfo = packageManager.getPackageInfo(
					appcontext.getPackageName(), 0);
			return packInfo.versionName;
		} catch (Exception e) {
			//
		}
		return "";
	}
	
	public static String getAppName(Context appcontext){
		try {
			PackageManager packageManager = appcontext.getPackageManager();
			PackageInfo packInfo = packageManager.getPackageInfo(
					appcontext.getPackageName(), 0);
			return packInfo.applicationInfo.name;
		} catch (Exception e) {
			//
		}
		return "";
	}
	
	public static int getAndroidSDKVersion() {
		int version = Build.VERSION.SDK_INT;

		return version;
	}
	public static String getUdid(Context appcontext) {
		
		String res = "";
		
		if (null != appcontext){
			res = DeviceId.getDeviceID(appcontext);
		}
		
		return res;
	}

	/* dp to px */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/* px to dp */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static void startCallNumber(Context context, String number){
		Intent intent=new Intent(); 
		intent.setAction(Intent.ACTION_CALL);   
		intent.setData(Uri.parse("tel:" + number));
		context.startActivity(intent);
	}
	
	
	//for baidu push -- to get the 
    public static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String apiKey = null;
        if (context == null || metaKey == null) {
        	return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
            	apiKey = metaData.getString(metaKey);
            }
        } catch (NameNotFoundException e) {

        }
        return apiKey;
    }
    
    public static boolean hasSimCard(Context appcontext) {
    	if (mTelephonyManager == null) {
			mTelephonyManager = (TelephonyManager) appcontext
					.getSystemService(Context.TELEPHONY_SERVICE);
		}
    	
    	if (mTelephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY) {
    		return true;
    	}
    	return false;
    }
}
