package com.yifu.platform.single.setting;

import java.util.List;

import com.yifu.platform.single.util.MNCType;

public class YFSingleSDKSettings {

	// public enum CUSMSAmount {
	// CUAmountFour(4), CUAmountSix(6);
	// private final int value;
	// public int getValue() {
	// return value;
	// }
	//
	// CUSMSAmount(int value) {
	// this.value = value;
	// }
	// }

	// App ID
	public static String SDK_APPID = "";
	// app_key
	public static String SDK_APPKEY = "";
	// app secret
	public static String SDK_APPSECRET = "";
	// Channel id
	public static String SDK_CHANNELID = "";
	// Pay Channel
	public static String SDK_PAY_CHANNEL = "";
	// Business Channel
	public static String SDK_BUSINESS_CHANNEL = "";
	// Ghost
	public static String SDK_GHOST = "";

	// The MNC of this phone.
	public static MNCType MNC;
	// The MNC string.
	// public static String PHONE_MNC = "";
	public static String PHONE_UA = "";
	public static String strPhoneIdentity = "";
	// The amount info.
	// Screen Orient.
	public static int SCREEN_ORIENT = 999;

	public static boolean MOBILE_MM_INIT_IS_OK = false;

	public static boolean GAMEBASE_SDK_INIT_IS_OK = false;

	public static boolean WOSTORE_SDK_INIT_IS_OK = false;

	public static boolean CROSSRECOMMEND_INIT_FINSIH = false;

	public static boolean CHINATELE_IS_SUPPORT = false;
	public static boolean CHINAMOBILE_IS_SUPPORT = false;
	public static boolean CHINAUNICOM_IS_SUPPORT = false;
	public static boolean OTHERPAY_IS_SUPPORT = false;
	public static boolean GHOST_IS_SUPPORT = false;

	public void init(String id, String key, String secret, String channelid) {

		if (id == null || id.equals("") || key == null || key.equals("") || secret == null || secret.equals("")
				|| channelid == null || channelid.equals("")) {
			throw new NullPointerException("Appid,Appkey,Appsecret can not be null or blank.");
		}

		SDK_APPID = id;
		SDK_APPKEY = key;
		SDK_APPSECRET = secret;
		SDK_CHANNELID = channelid;

	}

}
