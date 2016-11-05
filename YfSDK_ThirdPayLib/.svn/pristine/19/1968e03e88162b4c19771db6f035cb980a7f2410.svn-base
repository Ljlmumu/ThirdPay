package com.yifu.platform.single.net;

import com.yifu.platform.single.json.AbstractJSONHelper;
import com.yifu.platform.single.json.AbstractJsonBuilder;

public interface IHttpInterface {

public int sendRequest(String url, int requestTag, String content, INetListener listener, AbstractJSONHelper jsonHelper);
	
	public int sendRequest(AbstractJsonBuilder jsonBuilder, INetListener listener, AbstractJSONHelper jsonHelper);
	
	public int sendRequest(String url, int requestTag, String bodydata, INetListener listener);
	

	public int sendDownLoadRequest(String url, String filepath, INetListener listener);

	public void cancelRequestById(int requestId);

	public void cancelAllRequest();
}
