/**
 */

package com.yifu.platform.single.net;


import com.yifu.platform.single.internal.YFPlatformInternal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class ConnectManagerHelper {
	private static Context mContext; // 这个上下文context需要cp提供

	static {
		mContext = YFPlatformInternal.getInstance().getmContext();
	}

	public static enum ConnectType {
		NONE_Connect, // no available connection
		NET_Connect, WAP_ChinaMobile_Connect, WAP_ChinaUnicom_Connect, WAP_ChinaTelecom_Connect, WIFI_Connect
	}

	/*
	 * Check whether have available net connection
	 * 
	 * @return true or false
	 */
	public static boolean isNetConnect() {

		if (mContext == null) {
			return false;
		}
		final ConnectivityManager conn = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		if ((conn.getActiveNetworkInfo() != null) && conn.getActiveNetworkInfo().isAvailable()) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Get Current connection type
	 * 
	 * @Return ConnectType have three kind of type
	 */
	public static ConnectType getCurrentConnectType() {

		ConnectType res = ConnectType.NONE_Connect;

		if (mContext == null) {
			return res;
		}

		final ConnectivityManager conn = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (conn != null) {

			NetworkInfo info = conn.getActiveNetworkInfo();

			if (info != null) {
				String connStr = info.getTypeName();

				if (connStr.equalsIgnoreCase("WIFI")) {
					res = ConnectType.WIFI_Connect;

				} else if (connStr.equalsIgnoreCase("MOBILE")) {

					String apn = info.getExtraInfo();

					if (apn.indexOf("wap") > -1) {

						if (apn.equals("cmwap")) {

							res = ConnectType.WAP_ChinaMobile_Connect;

						} else if (apn.equals("uniwap") || apn.equals("3gwap")) {

							res = ConnectType.WAP_ChinaUnicom_Connect;

						} else if (apn.equals("ctwap")) {

							res = ConnectType.WAP_ChinaTelecom_Connect;

						} else {
							res = ConnectType.NET_Connect;
						}

					} else {
						res = ConnectType.NET_Connect;
					}
				}
			}
		}
		return res;

	}

	public static String getCurrentConnectString() {

		String res = "";

		if (mContext == null) {
			return res;
		}

		final ConnectivityManager conn = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (conn != null) {

			NetworkInfo info = conn.getActiveNetworkInfo();

			if (info != null) {
				String connStr = info.getTypeName();

				if (connStr.equalsIgnoreCase("WIFI")) {
					res = "wifi";

				} else if (connStr.equalsIgnoreCase("MOBILE")) {

					String apn = info.getExtraInfo();

					if (null != apn && apn.indexOf("wap") > -1) {

						if (apn.equals("cmwap")) {

							res = "cmwap";

						} else if (apn.equals("uniwap") || apn.equals("3gwap")) {

							res = "uniwap";

						} else if (apn.equals("ctwap")) {

							res = "ctwap";

						} else {
							res = "net";
						}

					} else {
						res = "net";
					}
				}
			}
		}
		return res;
	}
}
