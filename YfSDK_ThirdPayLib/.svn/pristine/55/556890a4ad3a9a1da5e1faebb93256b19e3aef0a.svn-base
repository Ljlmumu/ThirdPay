package com.yifu.platform.single.control.pay;

import android.app.Dialog;

import com.yifu.platform.single.callback.IYFSDKCallBack;
import com.yifu.platform.single.item.YFOrderInfoData;
import com.yifu.platform.single.json.JSONManager;



public class YFPayController {

	private IYFSDKCallBack mSDKCallBack = null;

	public IYFSDKCallBack getmSDKCallBack() {
		return mSDKCallBack;
	}

	public void setmSDKCallBack(IYFSDKCallBack mSDKCallBack) {
		this.mSDKCallBack = mSDKCallBack;
	}

	public static YFPayController getInstance() {
		return Inner.insatance;
	}

	private static class Inner {
		static YFPayController insatance = new YFPayController();
	}

	public void onPlatformRechargeResult(final int code,IYFSDKCallBack callBack,
			final YFOrderInfoData orderInfoData) {
		mSDKCallBack = callBack;
		if (mSDKCallBack == null)
			return;
		if (orderInfoData != null) {
			String result = JSONManager.getJsonBuilder()
					.buildPayCenterResponse(code, orderInfoData);
			mSDKCallBack.onResponse(result);
		} 
	}
}
