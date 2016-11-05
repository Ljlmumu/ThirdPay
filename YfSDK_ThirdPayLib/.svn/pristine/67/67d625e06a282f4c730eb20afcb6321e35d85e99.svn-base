package com.yifu.platform.single;

import com.yifu.platform.alipay.YFAlipayPay;
import com.yifu.platform.alipay.YFPayAlipayWorker;
import com.yifu.platform.single.control.IPayFactory;
import com.yifu.platform.single.control.PayChannel;
import com.yifu.platform.wxapi.huifubao.YFPayHFB;
import com.yifu.platform.wxapi.shenzhoufu.YFPayweichat;
import com.yifu.platform.wxapi.xingluo.YFPayWeiXinWorker;
import com.yifu.platform.wxapi.yibao.YFPayYiBao;
import com.yifu.platform.wxapi.zhongxin.ZhongXinWechatPay;

public enum PayType {

	/* 微信 */
	TENCENTMM("tencentmm", new IPayFactory() {
		public PayChannel payChannel;

		// 星罗
		public PayChannel getPayChannel() {
			payChannel = new YFPayWeiXinWorker();
			return payChannel;
		}

		// 神州付
		public PayChannel getSparePayChannel() {
			// if (null == payChannel) {
			payChannel = new YFPayweichat();
			// }
			return payChannel;
		}

		// 汇付宝
		public PayChannel getThirdPayChannel() {
			// if (null == payChannel) {
			payChannel = new YFPayHFB();
			// }
			return payChannel;
		}

		// 中信威富通
		public PayChannel getFourPayChannel() {
			// if (null == payChannel) {
			payChannel = new ZhongXinWechatPay();
			// }
			return payChannel;
		}

		// 易宝
		public PayChannel getFivePayChannel() {
			// if (null == payChannel) {
			payChannel = new YFPayYiBao();
			// }
			return payChannel;
		}
	}),
	/* 支付宝 */
	ALIPAY("alipay", new IPayFactory() {
		public PayChannel payChannel;

		public PayChannel getPayChannel() {
			// if (null == payChannel) {
			payChannel = new YFPayAlipayWorker();
			// }
			return payChannel;
		}

		/*
		 * 神州付付支付宝
		 * 
		 * @return
		 */
		public PayChannel getSparePayChannel() {
			payChannel = new YFAlipayPay();
			return payChannel;
		}

		// 汇付宝
		public PayChannel getThirdPayChannel() {
			payChannel = new YFPayHFB();
			// }
			return payChannel;
		}

		/**
		 * 易宝支付
		 * 
		 * @return
		 */
		@Override
		public PayChannel getFourPayChannel() {
			payChannel = new YFPayYiBao();
			return payChannel;
		}

		@Override
		public PayChannel getFivePayChannel() {
			// TODO Auto-generated method stub
			return null;
		}
	});
	private IPayFactory factory;
	private final String channel;

	private PayType(String channel, IPayFactory factory) {
		this.channel = channel;
		this.factory = factory;
	}

	public IPayFactory getFactory() {
		return factory;
	}

	public String getChannel() {
		return channel;
	}

	public void setFactory(IPayFactory factory) {
		this.factory = factory;
	}
}
