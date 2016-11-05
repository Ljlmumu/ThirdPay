package com.yifu.platform.single.util.simoperator;

import com.yifu.platform.single.setting.YFSingleSDKSettings;
import com.yifu.platform.single.util.MNCType;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class SIMOpertorByMnc {
	
	
	public static int getOpertor(Context context){
		return getOperatorByMnc(getOperator(context));
	}
	
	/**
	 * 获取sim卡的状态
	 * 
	 * @param ctx
	 * @return true，SIM卡良好可以正常methodName使用；false 其它状态
	 */
	public static boolean getSimState(Context ctx) {
		if(ctx==null){
			return false;
		}
		TelephonyManager telephonyManager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
		int simState = telephonyManager.getSimState();
		return (simState == TelephonyManager.SIM_STATE_READY) ? true : false;
	}
	
	
	public static String getOperator(Context ctx) {
		String type = "";
		if (getSimState(ctx)) {
			TelephonyManager telephonyManager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
			String operator = telephonyManager.getSimOperator();
			if (operator != null) {
				type = operator;
			}
		}

		return type;
	}
	
	
	public static int getOperatorByMnc(String mccmnc) {
		if (TextUtils.isEmpty(mccmnc))
			return 4;
		int code = 0;
		try {
			code = Integer.parseInt(mccmnc);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		int ret = 0;
		switch (code) {
		case 46000:
		case 46002:
		case 46007:
		case 46020:
			YFSingleSDKSettings.MNC = MNCType.CHINA_MOBILE;
			ret = 0;//移动卡
			break;

		case 46001://联通卡
		case 46006:
		case 46009:
			YFSingleSDKSettings.MNC = MNCType.CHINA_UNICOM;
			ret = 1;
			break;

		case 46003:
		case 46005:
		case 46011:
			YFSingleSDKSettings.MNC = MNCType.CHINA_TELCOM;
			ret = 2;//电信卡
			break;
		default:
			break;
		}
		return ret;
	}
}
