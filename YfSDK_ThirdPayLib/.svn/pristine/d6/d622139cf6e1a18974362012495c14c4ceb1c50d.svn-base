package com.yifu.platform.single.util.simoperator;

import com.yifu.platform.single.YFPlatform;
import com.yifu.platform.single.setting.YFSingleSDKSettings;
import com.yifu.platform.single.util.MNCType;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.location.LocationManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class PhoneUtil {

	private static TelephonyManager mTelephonyManager;

//	public enum MNCType {
//		ChinaMobile, ChinaUnicom, ChinaTelcom, UNKNOWN, OTHER
//	}

	public static String getScreenWH(Context context) {
		WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
//		WindowManager wm = (WindowManager) YFPlatform.getInstance().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		String screenwh = dm.widthPixels + "_" + dm.heightPixels;

		return screenwh;

	}

	public static boolean isSimInserted(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
		int status = tm.getSimState();
		if ((status == TelephonyManager.SIM_STATE_UNKNOWN) || (status == TelephonyManager.SIM_STATE_ABSENT)) {
			return false;
		}
		return true;
	}

	public static String getIMEI(Context appcontext) {

		if (mTelephonyManager == null) {
			mTelephonyManager = (TelephonyManager) appcontext.getSystemService(Context.TELEPHONY_SERVICE);
		}
		String res = mTelephonyManager.getDeviceId();
		if (res != null && res.length() > 0) {
			return res;
		}
		return "";
	}
	
	// 2014-02-25 add start (wounipay)
	public static String getIMSI(Context context){
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String imsi = tm.getSubscriberId();
		return imsi == null ? "" : imsi;
	}
	// 2014-02-25 add end 
	
	public static String getAppVersionName() {
		return "1.0.0";
	}

	public static String getGameVersion(Context context) {
		String version = "";
		PackageManager manager = context.getPackageManager();
		PackageInfo packInfo = null;
		try {
			packInfo = manager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		if (packInfo != null)
			version = packInfo.versionName;
		if(null == version || "".equals(version))
			version = packInfo.versionCode + "";
		return version;
	}
	
	public static String getGameVersionCode(Context context) {
		String version = "";
		PackageManager manager = context.getPackageManager();
		PackageInfo packInfo = null;
		try {
			packInfo = manager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		if (packInfo != null)
			version = packInfo.versionCode + "";
		if(null == version || "".equals(version))
			version = packInfo.versionName;
		return version;
	}

	/**
	 * Get the MNC of current phone.
	 * 
	 * @param pContext
	 * @return
	 */
	public static synchronized MNCType getMNCType(Context pContext) {
		TelephonyManager tempTelephonyManager = (TelephonyManager) pContext.getSystemService(Context.TELEPHONY_SERVICE);
		String strOperator = tempTelephonyManager.getSimOperator().trim();
		if (!isSimInserted(pContext)) {
//			YFSingleSDKSettings.PHONE_MNC = "";
			YFSingleSDKSettings.MNC=MNCType.UNKNOWN;
			return MNCType.UNKNOWN;
		}
		if (strOperator.endsWith("00") || strOperator.endsWith("02") || strOperator.endsWith("07")) {
//			YFSingleSDKSettings.PHONE_MNC = MNCType.CHINA_MOBILE.name;
			YFSingleSDKSettings.MNC=MNCType.CHINA_MOBILE;
			return MNCType.CHINA_MOBILE;
		} else if (strOperator.endsWith("01")) {
			YFSingleSDKSettings.MNC=MNCType.CHINA_UNICOM;
//			YFSingleSDKSettings.PHONE_MNC = MNCType.CHINA_UNICOM.name;
			return MNCType.CHINA_UNICOM;
		} else if (strOperator.endsWith("03") || strOperator.endsWith("99") || strOperator.endsWith("20404")) {
			YFSingleSDKSettings.MNC=MNCType.CHINA_TELCOM;
//			YFSingleSDKSettings.PHONE_MNC = MNCType.CHINA_TELCOM.name;
			return MNCType.CHINA_TELCOM;
		} else {
			return MNCType.UNKNOWN;
		}
	}

	/**
	 * 根据手机的SimOperator来判断手机SIM卡的类型
	 * */
	public static synchronized MNCType getMNCType(int simOperator) {
		String strOperator = String.valueOf(simOperator);

		if (strOperator.endsWith("00") || strOperator.endsWith("02") || strOperator.endsWith("07")) {
			return MNCType.CHINA_MOBILE;
		} else if (strOperator.endsWith("01")) {
			return MNCType.CHINA_UNICOM;
		} else if (strOperator.endsWith("03")) {
			return MNCType.CHINA_TELCOM;
		} else {
			return MNCType.UNKNOWN;
		}
	}

	public static synchronized String getYFSIMOperator(Context context){
		String strOperator = "";
		MNCType type = getMNCType(context);
		if(MNCType.CHINA_MOBILE == type){
			strOperator = MNCType.CHINA_MOBILE.name;
			YFSingleSDKSettings.MNC = MNCType.CHINA_MOBILE;
		} else if(MNCType.CHINA_UNICOM == type){
			strOperator = MNCType.CHINA_UNICOM.name;
			YFSingleSDKSettings.MNC = MNCType.CHINA_UNICOM;
		} else if(MNCType.CHINA_TELCOM == type){
			strOperator = MNCType.CHINA_TELCOM.name;
			YFSingleSDKSettings.MNC = MNCType.CHINA_TELCOM;
		}
		return strOperator;
	}
	
	/**
	 * 获取手机号
	 * */
	public static String getPhoneNumber(Context context){
		TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String phoneNum = telManager.getLine1Number();
		return phoneNum;
	}
	
	/**
	 * Get the phone's identity. Format:IMEI+MAC
	 * 
	 * @param pContext
	 * @return
	 */
	public static String getPhoneIdentity(Context pContext) {

		TelephonyManager tManager = (TelephonyManager) pContext.getSystemService(Context.TELEPHONY_SERVICE);

		StringBuffer sbIdentity = new StringBuffer();

		sbIdentity.append(tManager.getDeviceId());

		return "";
	}
	
	public static int globlePx(Context context,float pxValue){
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int)(pxValue*fontScale*2/3);
	}
	
	/* px to sp */
	public static int px2sp(Context context,float pxValue){
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}
	
	/* sp to px */
	public static int sp2px(Context context,float spValue){
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int)(spValue * fontScale + 0.5f);
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
	/**
	 * 获取经纬度信息 
	 * @param context 
	 * @return “纬度_经度”
	 */
	public static String getLBSInfo(Context context){
		String res = "";
		LocationManager locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		if(locationManager!=null){
			try{
				Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				if(location != null){
					res = Double.toString(location.getLatitude())+"_"+Double.toString(location.getLongitude());
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if("".equals(res)){
			res = getCourseLBSInfo(context);
		}

		return res;
		
	}
	/**
	 * 获取经纬度信息 
	 * @param context 
	 * @return “纬度_经度”
	 */
	public static String getCourseLBSInfo(Context context){
		String res = "";
		LocationManager location_manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		if(location_manager!=null){
			try{
				Location location = location_manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
				if(location != null){
					res = Double.toString(location.getLatitude())+"_"+Double.toString(location.getLongitude());
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return res;
	}
}
