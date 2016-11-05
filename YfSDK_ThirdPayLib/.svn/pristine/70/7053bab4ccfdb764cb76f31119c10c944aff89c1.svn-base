/**
 */

package com.yifu.platform.single.net.response;

import com.yifu.platform.single.YFErrorCode;

public class BaseResult {
	public static String JSON_INT_ERRORCODE = "errorcode";
	public static String JSON_STR_TAG = "tag";
	public static String JSON_STR_ERRORMSG = "errormsg";
	public static String JSON_STR_ACCEPT_TIME = "accept_time";
	
	protected int mErrorCode = YFErrorCode.DC_Error;
	protected String mErrorString;
	protected String mAccepTime; // add by wenzutong, 2013-03-05
	protected int mTag;//add by yanjiaqi, 2014-06-03
	
	public int getErrorCode() {
		return mErrorCode;
	}

	public void setErrorCode(int errorCode) {
		this.mErrorCode = errorCode;
	}

	public String getErrorString() {
		return mErrorString;
	}

	public void setErrorString(String errorString) {
		this.mErrorString = errorString;
	}

	public String getAcceptTime() {
		return mAccepTime;
	}

	public void setAccepTime(String mAccepTime) {
		this.mAccepTime = mAccepTime;
	}
	
	public void setRequestTag(int tag){
		this.mTag = tag;
	}
	
	public int getRequestTag(){
		return mTag;
	}

	@Override
	public String toString() {
		return "BaseResult [mErrorCode=" + mErrorCode + ", mErrorString="
				+ mErrorString + ", mAccepTime=" + mAccepTime + ", mTag="
				+ mTag + "]";
	}
	
}
