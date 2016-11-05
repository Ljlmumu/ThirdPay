package com.yifu.platform.single.json;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 客户端生成提交服务器消息接口
 * @author zuowenbin
 *
 */
public abstract class AbstractJsonBuilder {

	private JSONObject jsonObject;

	private String url;
	private int requestTag;
	
	public AbstractJsonBuilder(String url, int requestTag) {
		try {
			jsonObject = JSONBuilder.getInstance().createJsonObject();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		this.url = url;
		this.requestTag = requestTag;
	}

	/**
	 * 生成提交给服务器的json字符串
	 * 
	 * @param params
	 * @return
	 */
	public String buildJson(){
		try {
			buildJsonString(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
	
	/**
	 * 具体实现方法
	 * 
	 * @param params
	 * @return
	 */
	public abstract void buildJsonString(JSONObject jsonObject) throws Exception;

	public String getUrl() {
		return url;
	}
	
	public int getRequestTag() {
		return requestTag;
	}
	
}
