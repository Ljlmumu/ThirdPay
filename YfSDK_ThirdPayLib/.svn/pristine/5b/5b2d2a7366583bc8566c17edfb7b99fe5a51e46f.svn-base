package com.yifu.platform.single.util;

public enum MNCType {
	CHINA_MOBILE("cm"), // 中国移动
	CHINA_UNICOM("cu"), // 中国联通
	CHINA_TELCOM("ct"), // 中国电信
	CHINA_OTHER("other"),// 其他渠道
	UNKNOWN("unknow");

	public final String name;

	private MNCType(String stringName) {
		this.name = stringName;
	}
	
	public static String getOpeater(int i){
		switch(i){
		case 0:
			return CHINA_MOBILE.name;
		case 1:
			return CHINA_UNICOM.name;
		case 2:
			return CHINA_TELCOM.name;
		default:
			return CHINA_OTHER.name;
		}
	}

}
