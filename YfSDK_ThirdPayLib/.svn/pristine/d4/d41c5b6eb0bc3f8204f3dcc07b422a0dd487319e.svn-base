package com.yifu.platform.wxapi.huifubao;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.yifu.platform.single.YFErrorCode;
import com.yifu.platform.single.callback.IYFSDKCallBack;
import com.yifu.platform.single.control.PayChannel;
import com.yifu.platform.single.control.pay.YFPayController;
import com.yifu.platform.single.control.pay.YFProgressDialog;
import com.yifu.platform.single.item.YFOrderInfoData;
import com.yifu.platform.single.item.YFOrderStatus;
import com.yifu.platform.single.json.JSONManager;
import com.yifu.platform.single.net.ConnectManagerHelper;
import com.yifu.platform.single.net.INetListener;
import com.yifu.platform.single.net.NetManager;
import com.yifu.platform.single.net.response.BaseResult;
import com.yifu.platform.single.net.response.QueryOrderStatusResult;
import com.yifu.platform.single.net.response.UploadOrderInfoResult;
import com.yifu.platform.single.order.Order;
import com.yifu.platform.single.util.Constants;
import com.yifu.platform.single.util.PayOrderChannel;
import com.yifu.platform.single.util.SMSOrderIDGenerator;
import com.yifu.platform.single.util.SharePreferenceUtil;
import com.yifu.platform.single.util.YFUtil;
import com.yifu.platform.wxapi.WeChatConstans;
import com.yifu.platform.wxapi.YFH5PayActivity;
import com.yifu.platform.wxapi.zhongxin.ZhongXinConfig;

/**
 * 汇付宝
 * 
 * @author lijilei
 *
 */
public class YFPayHFB extends PayChannel implements INetListener {
	private Order order;
	private Activity context;
	IYFSDKCallBack mSDKCallBack = null;
	private String parterNo = "1664502 ";
	private String notify_url = "http://123.57.237.83/hfbweixincborder";
	private String return_url = "http://123.57.237.83/hfbweixincborder";
	private String key = "33FC0684BF854538BF016F6E";
	private int times;
	private BroadcastReceiver brocast;
	private YFProgressDialog mYFProgressDialog;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			String orderid = (String) msg.obj;
			queryOrderInfo(orderid);

		};
	};

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public void doPay(Context context, IYFSDKCallBack mSDKCallBack, Order order) {

		this.context = (Activity) context;
		mYFProgressDialog = new YFProgressDialog(context);
		this.mSDKCallBack = mSDKCallBack;
		// 获取订单
		this.order = order;
		if (order == null) {
			throw new NullPointerException("orderValue can not be null.");
		}

		// 更新第三方支付方式sp
		SharePreferenceUtil.getInstance(context).saveString(
				Constants.SHARE_LAST_THIRDPAY, order.channel);
		
		// 生成订单信息
		StringBuilder orderid = new StringBuilder("");
		orderid.append("YF_").append(SMSOrderIDGenerator.getOrderID(8));

		order.setOrder_id(orderid.toString());

		if (ConnectManagerHelper.isNetConnect()) {
			uploadOrderInfo(orderid);
		} else {
			Toast.makeText(context, "网络连接错误，请检查网络", Toast.LENGTH_SHORT).show();
			return;
		}
	}

	/**
	 * 上传订单信息
	 * 
	 * @param orderid
	 */
	private void uploadOrderInfo(StringBuilder orderid) {
		mYFProgressDialog.show();
		System.out.println("上传订单号" + orderid);
		String content = order.merchant_id;
		// tag3,上传订单信息
		String request = JSONManager.getJsonBuilder()
				.buildSmsOrderInfoUploadString(order.channel,
						orderid.toString(), order.price, order.item_id,
						content, order.desc, order.userdata);
		NetManager.getHttpConnect().sendRequest(
				Constants.URL_UPLOAD_ORDER_INFO,
				Constants.TAGS_UPLOAD_ORDER_INFO, request, this);

	}

	@Override
	public void onNetResponse(int requestTag, BaseResult responseData,
			int requestId) {

		if (Constants.TAGS_UPLOAD_ORDER_INFO == requestTag) {
			UploadOrderInfoResult result = (UploadOrderInfoResult) responseData;
			if (null != result) {
				if (Constants.DC_OK == result.getErrorCode()) {
					if (result.getOrder_status() == 4) {// 订单号重复
						StringBuilder orderid = new StringBuilder("");
						orderid.append("YF_").append(
								SMSOrderIDGenerator.getOrderID(8));

						order.setOrder_id(orderid.toString());

						uploadOrderInfo(orderid);
						return;
					}
					if (result.getOrder_status() == 3) {// 交易成功
						return;
					}
					// Save the info of order.
					startPay();
				} else {
					Toast.makeText(context, "订单创建失败", Toast.LENGTH_SHORT)
							.show();
				}
			}
			mYFProgressDialog.dismiss();
		} else if (Constants.TAGS_QUERY_ORDER_STATUS == requestTag) {// 查询订单状态
			if (requestTag == Constants.TAGS_QUERY_ORDER_STATUS) {
				final QueryOrderStatusResult qosr = (QueryOrderStatusResult) responseData;

				int status = qosr.getOrderstatus();
				YFOrderInfoData orderInfo = new YFOrderInfoData();
				orderInfo.setyfOrderPayChannel(PayOrderChannel.CHANNEL_ALIPAY);
				if (order.channel.equalsIgnoreCase(Constants.CHANNEL_WEIXIN))
					orderInfo
							.setyfOrderPayChannel(PayOrderChannel.CHANNEL_WEIXIN);
				orderInfo.setyfOrderPrice(order.price);
				orderInfo.setyfOrderProductId(order.item_id);
				orderInfo.setyfOrderId(order.order_id);

				System.out.println("订单状态====" + status);
				if (YFOrderStatus.YF_ORDER_STATUS_SUCCESS.getValue() == status) {// 成功
					orderInfo
							.setyfOrderStatus(YFOrderStatus.YF_ORDER_STATUS_SUCCESS);
					SharePreferenceUtil.getInstance(context).saveString(
							Constants.SHARE_LAST_THIRDPAY_SUCCESS,
							order.channel);
					YFPayController.getInstance().onPlatformRechargeResult(
							YFErrorCode.BDG_RECHARGE_SUCCESS, mSDKCallBack,
							orderInfo);
				} else {
					if (times < 20) {// 失败，轮询20次
						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								queryOrderInfo(qosr.getOrderid());
							}
						}, 50);

					} else {

						if (YFOrderStatus.YF_ORDER_STATUS_DEALING.getValue() == status) {// 处理中
							orderInfo.setyfOrderStatus(YFOrderStatus.YF_ORDER_STATUS_FAIL);
							YFPayController.getInstance().onPlatformRechargeResult(YFErrorCode.BDG_RECHARGE_FAIL,mSDKCallBack, orderInfo);

						} else {// 失败
							orderInfo
									.setyfOrderStatus(YFOrderStatus.YF_ORDER_STATUS_FAIL);
							YFPayController.getInstance()
									.onPlatformRechargeResult(
											YFErrorCode.BDG_RECHARGE_FAIL,
											mSDKCallBack, orderInfo);
						}
					}
					defaultWeChatExit(context);
				}

			}
		}
	}

	/**
	 * 支付失败的处理
	 * 
	 * @param context
	 */
	private void defaultWeChatExit(Context context) {
		String lastSuccess = SharePreferenceUtil.getInstance(context)
				.getString(Constants.SHARE_LAST_THIRDPAY_SUCCESS);
		// 上一次支付成功微信
		if (lastSuccess.equals(order.channel)) {
			SharePreferenceUtil.getInstance(context).saveString(
					Constants.SHARE_LAST_THIRDPAY_SUCCESS, "");

		}
	}

	/**
	 * 查询订单
	 */
	public void queryOrderInfo(String orderId) {
		times++;
		String data = JSONManager.getJsonBuilder()
				.buildQueryStatusOfOrderString(orderId);
		NetManager.getHttpConnect().sendRequest(
				Constants.URL_QUERY_ORDER_STATUS,
				Constants.TAGS_QUERY_ORDER_STATUS, data, this);

	}

	@SuppressLint("SetJavaScriptEnabled")
	private void startPay() {
		final String url = preRequestparam();

		// Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		// context.startActivity(intent);
		Intent intent = new Intent(context, YFH5PayActivity.class);
		intent.putExtra("price", order.price);
		intent.putExtra("itemid", order.item_id);
		intent.putExtra("orderid", order.order_id);
		intent.putExtra("url", url);
		brocast = new Broadcaset();
		IntentFilter filter = new IntentFilter(WeChatConstans.ACTION_THIRDPAY_RECEIVER);
		context.registerReceiver(brocast, filter);
		context.startActivity(intent);

	}

	class Broadcaset extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// Toast.makeText(context, "接收广播", 0).show();
			Message msg = Message.obtain();
			msg.obj = intent.getStringExtra(WeChatConstans.INTENT_ORDERID);
			msg.what = 200;
			handler.sendMessage(msg);
			context.unregisterReceiver(this);

		}

	}

	@SuppressLint({ "DefaultLocale", "SimpleDateFormat" })
	private String preRequestparam() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(new Date());

		String ipAdress = Constants.ipAdress.replace(".", "_");

		String payType = "";
		if (Constants.CHANNEL_WEIXIN == order.channel) {
			payType = "30";
		} else if (Constants.CHANNEL_ALIPAY == order.channel) {
			payType = "22";
		}

		WeiXinPayModel model = new WeiXinPayModel(parterNo, order, date,
				notify_url, return_url, ipAdress, payType);
		String sign = WeiXinHelper.signMd5(key, model);
		String url = WeiXinHelper.GatewaySubmitUrl(sign, model);

		return url;
	}

	@Override
	public void onDownLoadStatus(DownLoadStatus status, int requestId) {

	}

	@Override
	public void onDownLoadProgressCurSize(long curSize, long totalSize,
			int requestId) {

	}

	@Override
	public void onNetResponseErr(int requestTag, int requestId, int errorCode,
			String msg) {
		if (Constants.TAGS_UPLOAD_ORDER_INFO == requestTag) {
			Log.e("YFPayHFB", "tag3创建订单失败");
			Toast.makeText(context, "订单创建失败", Toast.LENGTH_SHORT).show();
		} else if (Constants.TAGS_QUERY_ORDER_STATUS == requestTag) {
			Log.e("YFPayHFB", "tag4查询订单失败");
		}
	}
}
