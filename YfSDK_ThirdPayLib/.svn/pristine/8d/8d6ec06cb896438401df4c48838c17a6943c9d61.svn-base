package com.yifu.platform.single;

import android.app.Activity;
import android.content.Context;

import com.yifu.platform.single.callback.IYFSDKCallBack;
import com.yifu.platform.single.internal.YFPlatformInternal;
import com.yifu.platform.single.item.GamePropsInfo;
import com.yifu.platform.single.util.YFUtil;


public class YFPlatform {

	private YFPlatformInternal mPlatformInternal;
	public static Activity mActivity = null;

	public static YFPlatform getInstance() {
		return Inner.platform;
	}

	private YFPlatform() {
		mPlatformInternal = YFPlatformInternal.getInstance();
		YFUtil.isBugMode();
	}

	private static class Inner {
		static YFPlatform platform = new YFPlatform();
	}

	/**
	 * SDK初始化
	 * 
	 * @param context
	 */
	public void init(Activity activity) {
		mActivity = activity;
		
		mPlatformInternal.initSDK(activity);

	}

	/**
	 * 支付接口
	 * @return
	 */
	public void invokePay(Context context,
			GamePropsInfo goodsInfo,PayType payType, IYFSDKCallBack callBack) {

		mPlatformInternal.invokePay(context, goodsInfo, payType,
				callBack);
	}


	
	
}
