package com.yifu.platform.wxapi.xingluo;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.conn.util.InetAddressUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import com.ipaynow.wechatpay.plugin.api.WechatPayPlugin;
import com.ipaynow.wechatpay.plugin.manager.route.dto.ResponseParams;
import com.ipaynow.wechatpay.plugin.manager.route.impl.ReceivePayResult;
import com.ipaynow.wechatpay.plugin.utils.PreSignMessageUtil;
import com.yifu.platform.single.YFErrorCode;
import com.yifu.platform.single.callback.IYFSDKCallBack;
import com.yifu.platform.single.control.PayChannel;
import com.yifu.platform.single.control.pay.YFPayController;
import com.yifu.platform.single.item.YFOrderInfoData;
import com.yifu.platform.single.item.YFOrderStatus;
import com.yifu.platform.single.json.JSONManager;
import com.yifu.platform.single.net.ConnectManagerHelper;
import com.yifu.platform.single.net.INetListener;
import com.yifu.platform.single.net.NetManager;
import com.yifu.platform.single.net.response.BaseResult;
import com.yifu.platform.single.net.response.UploadOrderInfoResult;
import com.yifu.platform.single.order.Order;
import com.yifu.platform.single.util.Constants;
import com.yifu.platform.single.util.PayOrderChannel;
import com.yifu.platform.single.util.SMSOrderIDGenerator;
import com.yifu.platform.single.util.SharePreferenceUtil;
import com.yifu.platform.single.util.YFUtil;
import com.yifu.platform.wxapi.Md5Util;
import com.yifu.platform.wxapi.WeChatConstans;

/**
 * 聚合微信支付
 * 
 * @author tf
 *
 */
public class YFPayWeiXinWorker extends PayChannel implements INetListener,
		ReceivePayResult {
	private Order order;
	private String orderId;
	private Activity context;
	private static ProgressDialog progressDialog = null;
	// private IWXAPI api;
	// private PayResult mPayResult;
	private static String preSignStr = null;// 带签名字符
	private PreSignMessageUtil preSign = new PreSignMessageUtil();
	IYFSDKCallBack mSDKCallBack = null;
	@Override
	public void doPay(Context context,IYFSDKCallBack mSDKCallBack,Order order) {

		if (!YFUtil.isAppAvilible(context, "com.tencent.mm")) {

			Toast.makeText(context, "请确认您的手机安装微信客户端后重试！", Toast.LENGTH_LONG)
					.show();

			return;

		}
		WechatPayPlugin.getInstance().init(context);
		// 获取订单
		this.context = (Activity) context;
		this.mSDKCallBack = mSDKCallBack;
		// 获取订单
		this.order = order;
		if (order == null) {
			throw new NullPointerException("orderValue can not be null.");
		}

		// 更新第三方支付方式sp
		SharePreferenceUtil.getInstance(context).saveString(
				Constants.SHARE_LAST_THIRDPAY, Constants.CHANNEL_WEIXIN);
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
	 * 
	 */
	private void startPay() {

		// 调用微信支付
		String no = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA)
				.format(new Date());
		prePayMessage(no);
		goPay();
	}

	/**
	 * 调起支付接口
	 */
	private void goPay() {

		if (ConnectManagerHelper.isNetConnect()) {
			progressDialog = new ProgressDialog(context);
			progressDialog.setTitle("进度提示");
			progressDialog.setMessage("支付安全环境扫描");
			progressDialog.setCancelable(false);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			preSignStr = preSign.generatePreSignMessage();// 待签名字符串
			String sign = sign(preSignStr);// 签名
			String requestStr = preSignStr + "&" + "mhtSignature=" + sign + "&"
					+ "mhtSignType=" + "MD5";

			WechatPayPlugin.getInstance().setPayLoading(progressDialog)// 自定义进度条
					.setCallResultActivity(context)// 传入回调用的Activity对象
					.setCallResultReceiver(this).pay(requestStr);// 传入请求数据

		} else {
			Builder builder = new AlertDialog.Builder(context);
			// builder.setIcon(R.drawable.ic_launcher);
			builder.setTitle("网络状态");
			builder.setMessage("没有可用网络,是否进入设置面板");
			builder.setPositiveButton("是",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							context.startActivity(new Intent(
									android.provider.Settings.ACTION_WIRELESS_SETTINGS));
						}
					});
			builder.setNegativeButton("否",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(context, "联网失败", Toast.LENGTH_SHORT)
									.show();
						}
					});
			builder.create().show();
		}
	}

	/**
	 * 签名
	 * 
	 * @param preSignStr2
	 */
	private String sign(String preSignStr2) {
		String sign = Md5Util.md5(preSignStr2 + "&"
				+ Md5Util.md5(WeChatConstans.PARTNER_KEY));
		// String sign = Md5Util.md5(preSignStr2 + "&" +
		// WeChatConstans.PARTNER_KEY);
		return sign;
	}

	/**
	 * 准备订单参数
	 */
	public void prePayMessage(String mhtOrderStartTime) {
		preSign.appId = WeChatConstans.APP_ID;// appid
		preSign.mhtOrderNo = getOrderNo();// 订单号
		preSign.mhtOrderName = order.desc;// 商品名字
		preSign.mhtOrderType = "01";// 消费种类
		preSign.mhtCurrencyType = "156";// 币种
		preSign.mhtOrderAmt = order.price;// 价格
		preSign.mhtOrderDetail = order.desc;// 商品信息
		preSign.mhtOrderTimeOut = "3600";
		preSign.mhtOrderStartTime = mhtOrderStartTime;
		preSign.notifyUrl = WeChatConstans.NOTIFY_URL;// 后台通知地址
		preSign.mhtCharset = "UTF-8";
		preSign.payChannelType = "13";
		// ///
		// preSign.consumerId = "456123";
		// preSign.consumerName = "yuyang";
	}

	/**
	 * 微信格式的订单号
	 * 
	 * @return
	 */
	private String getOrderNo() {
		StringBuilder orderid = new StringBuilder("");
		// orderid.append(order.mchNo).append(SMSOrderIDGenerator.getOrderID(8));//
		// 商户号+随机串
		orderid.append("4414").append(order.order_id).append(order.mchNo);// 我方商户号+随机串+cp商户号
		// 商户号+随机串
		System.out.println(orderid.toString());
		return orderid.toString();
	}

	/**
	 * 上传订单信息
	 * 
	 * @param orderid
	 */
	private void uploadOrderInfo(StringBuilder orderid) {
		String content = order.merchant_id;
		// tag3,上传订单信息
		String request = JSONManager.getJsonBuilder()
				.buildSmsOrderInfoUploadString(order.channel,
						orderid.toString(), order.price, order.item_id,
						content, order.desc, order.userdata);
		NetManager.getHttpConnect().sendRequest(
				Constants.URL_UPLOAD_ORDER_INFO,
				Constants.TAGS_UPLOAD_ORDER_INFO, request, this);
		//System.out.println("星罗微信的订单号==" + orderid.toString());
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
					Toast.makeText(context, "订单创建失败", 1000).show();
				}
			}
		}
		// else if (Constants.TAGS_QUERY_ORDER_STATUS == requestTag) {// 查询订单状态
		// if (requestTag == Constants.TAGS_QUERY_ORDER_STATUS) {
		// QueryOrderStatusResult qosr = (QueryOrderStatusResult) responseData;
		//
		// // MyLogger.getLogger(Order.class.getSimpleName())
		// // .d("gamecard_test:onNetResponse price " + price + "&" +
		// // qosr.getOrderPrice());
		//
		// String status = qosr.getOrderstatus() + "";
		// YFOrderInfoData orderInfo = new YFOrderInfoData();
		// orderInfo.setyfOrderPayChannel(PayOrderChannel.CHANNEL_WEIXIN);
		// orderInfo.setyfOrderPrice(order.price);
		// orderInfo.setyfOrderProductId(order.item_id);
		// orderInfo.setyfOrderId(order.order_id);
		// if (Constants.ORDER_STATUS_SUCCESS == status) {// 成功
		// orderInfo.setyfOrderStatus(YFOrderStatus.YF_ORDER_STATUS_SUCCESS);
		// SharePreferenceUtil.getInstance(context).saveString(Constants.SHARE_LAST_THIRDPAY_SUCCESS,
		// Constants.CHANNEL_WEIXIN);
		// YFPlatformInternal.getInstance().getControllerManager().getPayController()
		// .onPlatformRechargeResult(YFErrorCode.BDG_RECHARGE_SUCCESS,
		// orderInfo);
		// context.finish();
		// } else if (Constants.ORDER_STATUS_DEALING == status) {// 处理中
		//
		// /**********************暂时按失败处理 ***************************/
		//
		// orderInfo.setyfOrderStatus(YFOrderStatus.YF_ORDER_STATUS_FAIL);
		// defaultWeChatExit(context);
		// YFPlatformInternal.getInstance().getControllerManager().getPayController()
		// .onPlatformRechargeResult(YFErrorCode.BDG_RECHARGE_FAIL, orderInfo);
		//
		// } else {// 失败
		// orderInfo.setyfOrderStatus(YFOrderStatus.YF_ORDER_STATUS_FAIL);
		// defaultWeChatExit(context);
		// YFPlatformInternal.getInstance().getControllerManager().getPayController()
		// .onPlatformRechargeResult(YFErrorCode.BDG_RECHARGE_FAIL, orderInfo);
		// }
		//
		// OrderInfoDao.getInstance(context).updateOrderStatus(order.getOrder_id(),
		// status);
		// }
		// }

	}

	@Override
	public void onDownLoadStatus(DownLoadStatus status, int requestId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDownLoadProgressCurSize(long curSize, long totalSize,
			int requestId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNetResponseErr(int requestTag, int requestId, int errorCode,
			String msg) {
		// TODO Auto-generated method stub

	}

	/**
	 * 查询订单状态
	 */
	// public void queryOrderState() {
	// String data =
	// JSONManager.getJsonBuilder().buildQueryStatusOfOrderString(order.order_id);
	// NetManager.getHttpConnect().sendRequest(Constants.URL_QUERY_ORDER_STATUS,
	// Constants.TAGS_QUERY_ORDER_STATUS,
	// data, this);
	// }
	/**
	 * 支付失败的处理
	 * 
	 * @param context
	 */
	private void defaultWeChatExit(Context context) {
		String lastSuccess = SharePreferenceUtil.getInstance(context)
				.getString(Constants.SHARE_LAST_THIRDPAY_SUCCESS);
		// 上一次支付成功微信
		if (lastSuccess.equals(Constants.CHANNEL_WEIXIN)) {
			SharePreferenceUtil.getInstance(context).saveString(
					Constants.SHARE_LAST_THIRDPAY_SUCCESS, "");
	
		}
	}

	@Override
	public void onIpaynowTransResult(ResponseParams resp) {
		progressDialog.dismiss();
		String status = Constants.ORDER_STATUS_DEALING;
		YFOrderInfoData orderInfo = new YFOrderInfoData();
		orderInfo.setyfOrderPayChannel(PayOrderChannel.CHANNEL_WEIXIN);
		orderInfo.setyfOrderPrice(order.price);
		orderInfo.setyfOrderProductId(order.item_id);
		orderInfo.setyfOrderId(order.order_id);
		String respCode = resp.respCode;
		String errorCode = resp.errorCode;
		
		String respMsg = resp.respMsg;
		StringBuilder temp = new StringBuilder();
		if (respCode.equals("00")) {
			status = Constants.ORDER_STATUS_SUCCESS;
			SharePreferenceUtil.getInstance(context).saveString(
					Constants.SHARE_LAST_THIRDPAY_SUCCESS,
					Constants.CHANNEL_WEIXIN);
			orderInfo.setyfOrderStatus(YFOrderStatus.YF_ORDER_STATUS_SUCCESS);
			YFPayController.getInstance()
					.onPlatformRechargeResult(YFErrorCode.BDG_RECHARGE_SUCCESS,mSDKCallBack,
							orderInfo);
			
		} else {

			if (respCode.equals("02")) {
				temp.append("交易状态:取消");

			} else if (respCode.equals("01")) {
				temp.append("交易状态:失败").append("\n").append("错误码:")
						.append(errorCode).append("原因:" + respMsg);
			} else if (respCode.equals("03")) {
				temp.append("交易状态:未知").append("\n").append("错误码:")
						.append(errorCode).append("原因:" + respMsg);
			}
			status = Constants.ORDER_STATUS_FAIL;
			orderInfo.setyfOrderStatus(YFOrderStatus.YF_ORDER_STATUS_FAIL);
			YFPayController.getInstance()
					.onPlatformRechargeResult(YFErrorCode.BDG_RECHARGE_FAIL,mSDKCallBack,
							orderInfo);
			defaultWeChatExit(context);
		}
		
	}
}
