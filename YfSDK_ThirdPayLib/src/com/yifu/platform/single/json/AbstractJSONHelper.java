package com.yifu.platform.single.json;

import org.json.JSONException;

import com.yifu.platform.single.net.response.BaseResult;

public abstract class AbstractJSONHelper {
	
	public abstract BaseResult doParserWithTag(int requestTag, String resData) throws JSONException;
	
}
