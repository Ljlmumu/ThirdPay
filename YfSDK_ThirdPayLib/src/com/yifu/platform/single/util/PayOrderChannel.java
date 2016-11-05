package com.yifu.platform.single.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

import com.yifu.platform.single.setting.YFSingleSDKSettings;

public enum PayOrderChannel {
	CHANNEL_UNKNOWN("unknow", 0, MNCType.UNKNOWN, NOCheckFinish.getInstance(), 0),
	CHANNEL_CT_SFYZ("ct_sfyz", 0, MNCType.CHINA_TELCOM, NOCheckFinish.getInstance(), -1),// 电信盛峰最新代码
	CHANNEL_CT_SHENGFENG("ct_sfdx", 3, MNCType.CHINA_TELCOM, NOCheckFinish.getInstance(), -1), // 电信盛峰
	// 获取可用的支付方式（根据手机号和地区来获取）
	CHANNEL_CM_MDO("mdo", 14, MNCType.CHINA_MOBILE, new ICheckInitFinish() {
		@Override
		public boolean isInitFinish(Collection<String> prameType) {
			if (prameType.contains(CHANNEL_CM_MDO.channelName)) {
				SharePreferenceUtil shareUtil = SharePreferenceUtil.getInstance();
				int intMdoSupport = shareUtil.getInt(PayOrderChannel.CHANNEL_CM_MDO.channelName + "_support", -1);
				if (intMdoSupport != 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}, 3),
	CHANNEL_CM_YFLD("cm_yfld", 15, MNCType.CHINA_MOBILE, NOCheckFinish.getInstance(), -1),
	CHANNEL_CM_LDYS("hfb", 1, MNCType.CHINA_MOBILE, NOCheckFinish.getInstance(), -1), // 联动优势

	/** 移动基地支付方式 */
	CHANNEL_CM_GB_SDK_OTHER("gb_sdk_other", 18, MNCType.CHINA_MOBILE, NOCheckFinish.getInstance(), -1),
	CHANNEL_CU_WOSTORE("cu_wostore", 13, MNCType.CHINA_UNICOM, NOCheckFinish.getInstance(), -1), // 联通沃商店渠道版
	CHANNEL_CM_GAMEBASE("game_base", 11, MNCType.CHINA_MOBILE, NOCheckFinish.getInstance(), -1), //
	/** 支付宝 **/
	CHANNEL_ALIPAY("alipay",7,MNCType.CHINA_OTHER,NOCheckFinish.getInstance(), -1),
	/** 微信 **/
	CHANNEL_WEIXIN("tencentmm",8,MNCType.CHINA_OTHER,NOCheckFinish.getInstance(), -1);
	public final String channelName;

	public final int channelvalue;

	public final MNCType mncType;

	public final ICheckInitFinish finish;

	public final int defaultPayOrder;

	public static EnumSet<PayOrderChannel> enumSet = EnumSet.allOf(PayOrderChannel.class);

	public static List<PayOrderChannel> getDefaultPayChannels(MNCType prameType){
		List<PayOrderChannel> channels=new ArrayList<PayOrderChannel>();
		for (PayOrderChannel payOrderChannel : PayOrderChannel.enumSet) {
			if (payOrderChannel.mncType.equals(prameType)&&payOrderChannel.defaultPayOrder!=-1) {
				channels.add(payOrderChannel);
			}
		}
		Collections.sort(channels,new Comparator<PayOrderChannel>() {
			@Override
			public int compare(PayOrderChannel lhs, PayOrderChannel rhs) {
				return lhs.defaultPayOrder-rhs.defaultPayOrder;
			}
		});
		return channels;
	}

	public static PayOrderChannel valueOf(int value) {
		switch (value) {
		default:
			return CHANNEL_UNKNOWN;
		}
	}

	public static boolean channelCanUse(String channelName, Collection<String> prameType) {
		PayOrderChannel payOrderChannelByString = PayOrderChannel.getPayOrderChannelByString(channelName);
		if (YFSingleSDKSettings.MNC.equals(payOrderChannelByString.mncType)) {
			boolean initFinish = payOrderChannelByString.finish.isInitFinish(prameType);
			return initFinish;
		}
		return false;
	}

	public static PayOrderChannel getPayOrderChannelByString(String channelName) {
		for (PayOrderChannel channel : PayOrderChannel.enumSet) {
			if (channel.channelName.equals(channelName)) {
				return channel;
			}
		}
		MyLogger.getLogger(PayOrderChannel.class.getSimpleName()).d("gamecard_test:" + channelName);
		return CHANNEL_UNKNOWN;
	}

	private PayOrderChannel(String channelName, int channelValue, MNCType mncType, ICheckInitFinish checkInitFinish,
			int payOrder) {
		this.channelName = channelName;
		this.channelvalue = channelValue;
		this.mncType = mncType;
		this.finish = checkInitFinish;
		this.defaultPayOrder = payOrder;
	}

}
