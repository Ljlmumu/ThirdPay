package com.yifu.platform.single.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceUtil {
	private static SharePreferenceUtil mInstance;
	private final int MODE = Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE;
	private final SharedPreferences sharedpreferences;
	private static final String FILE_NAME = "com_yf_shared_preferences";

	/** 定义SharedPreference key的常量值 */
	public static final String YF_REMEMBER_LOGIN_STATE = "yf_remember_login_state"; // boolean

	public static final String YF_BIND_PHONE_VERIFYCODE = "yf_bind_phone_verifycode"; // String,用于存储获取的短信验证码
	public static final String YF_BIND_PHONE_VERIFYCODE_LAST_ACCESSTIME = "yf_bind_phone_verifycode_last_accesstime";
	public static final String YF_BIND_PHONE_VERIFYCODE_PHONE_NUMBER = "yf_bind_phone_verifycode_phone_number";

	public static final String YF_MODIFY_PHONE_VERIFYCODE_FOR_PRE_PHONE = "yf_modify_phone_verifycode_for_pre_phone"; // String
	public static final String YF_MODIFY_PHONE_VERIFYCODE_FOR_PRE_PHONE_PHONE_NUMBER = "yf_modify_phone_verifycode_for_pre_phone_phone_number";
	public static final String YF_MODIFY_PHONE_VERIFYCODE_FOR_PRE_PHONE_LAST_ACCESSTIME = "yf_modify_phone_verifycode_for_pre_phone_last_accesstime";
	
	public static final String YF_MODIFY_PHONE_VERIFYCODE_FOR_NEW_PHONE = "yf_modify_phone_verifycode_for_new_phone"; // String
	public static final String YF_MODIFY_PHONE_VERIFYCODE_FOR_NEW_PHONE_LAST_ACCESSTIME = "yf_modify_phone_verifycode_for_new_phone_last_accesstime";
	public static final String YF_MODIFY_PHONE_VERIFYCODE_FOR_NEW_PHONE_PHONE_NUMBER = "yf_modify_phone_verifycode_for_new_phone_phone_number";

	private SharePreferenceUtil(Context context, String fileName) {
		sharedpreferences = context.getSharedPreferences(fileName, MODE);
	}

	public static SharePreferenceUtil getInstance() {
		return mInstance;
	}
	
	public static SharePreferenceUtil getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new SharePreferenceUtil(context, FILE_NAME);
		}

		return mInstance;
	}

	public boolean saveString(YFKeyString key, YFValueString yfValue) {
		SharedPreferences.Editor editor = sharedpreferences.edit();
		String outValue = yfValue.getValue();
		if (yfValue.getIfEncrypt()) {
			outValue = YFUtil.encodeString(outValue);
		}

		editor.putString(key.getKey(), outValue);
		return editor.commit();
	}

	public boolean saveString(String key, String value) {
		SharedPreferences.Editor editor = sharedpreferences.edit();
		editor.putString(key, value);

		return editor.commit();
	}

	public boolean saveLong(String key, Long value){
		SharedPreferences.Editor editor = sharedpreferences.edit();
		editor.putLong(key, value);
		
		return editor.commit();
	}
	
	public boolean saveInt(String key, Integer value){
		SharedPreferences.Editor editor = sharedpreferences.edit();
		editor.putInt(key, value);
		
		return editor.commit();
	}
	
	public String getString(YFKeyString key) {
		String value = null;
		value = sharedpreferences.getString(key.getKey(), "");
		if (key.isValueEncrypted()) {
			value = YFUtil.decodeString(value);
		}
		return value;
	}

	public String getString(String key) {
		return sharedpreferences.getString(key, "");
	}

	public Long getLong(String key){
		return sharedpreferences.getLong(key, 0);
	}
	
	public Integer getInt(String key){
		return sharedpreferences.getInt(key, 0);
	}
	public int getInt(String key, int defaultValue){
		return sharedpreferences.getInt(key, defaultValue);
	}
	public boolean saveInt(String key, int value){
		SharedPreferences.Editor editor = sharedpreferences.edit();
		editor.putInt(key, value);
		
		return editor.commit();
	}
	
	
	public boolean saveBoolean(String key, boolean value) {
		SharedPreferences.Editor editor = sharedpreferences.edit();
		editor.putBoolean(key, value);
		return editor.commit();
	}

	public boolean getBoolean(String key) {
		return sharedpreferences.getBoolean(key, false);
	}

	public boolean removeKey(String key) {
		SharedPreferences.Editor editor = sharedpreferences.edit();
		editor.remove(key);
		return editor.commit();
	}

	public boolean removeAllKey() {
		SharedPreferences.Editor editor = sharedpreferences.edit();
		editor.clear();
		return editor.commit();
	}

	/**
	 * 将key值映射的value置为空串
	 * 
	 * @param key
	 * @return
	 */
	public boolean clearString(String key) {
		SharedPreferences.Editor editor = sharedpreferences.edit();
		editor.putString(key, "");
		return editor.commit();
	}

	/**
	 * 将key值映射的value置为false
	 * 
	 * @param key
	 * @return
	 */
	public boolean clearBoolean(String key) {
		SharedPreferences.Editor editor = sharedpreferences.edit();
		editor.putBoolean(key, false);
		return editor.commit();
	}
}
