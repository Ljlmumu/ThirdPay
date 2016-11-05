package com.yifu.platform.single.util;

import java.util.Random;

import com.yifu.platform.single.setting.YFSingleSDKSettings;

public class MdoOrderIDGenerator {
	
	
	private static char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	private static final int maxNum = 36;

	public static String getOrderID(int length) {

//		String generatedID = getRandomOrder(12);
		String generatedID = getRandomOrder(length);
		while(generatedID.contains("null")){
			generatedID = getRandomOrder(length);
		}
		String app_id = YFSingleSDKSettings.SDK_APPID;
		String promotion_id = YFSingleSDKSettings.SDK_CHANNELID;

		// return app_id + promotion_id + generatedID;
		return generatedID;

	}

	/*
	 * Caller Should Check whether result is null. length: the length of return
	 * String
	 */
	private static String getRandomOrder(int length) {

		int count = 0;
		int i = 0;

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();

		while (count < length) {

			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为62-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();
	}
}
