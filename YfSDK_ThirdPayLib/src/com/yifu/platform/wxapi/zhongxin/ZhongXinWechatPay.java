package com.yifu.platform.wxapi.zhongxin;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Xml;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ipaynow.wechatpay.plugin.manager.route.dto.ResponseParams;
import com.yifu.platform.single.YFErrorCode;
import com.yifu.platform.single.YFPlatform;
import com.yifu.platform.single.callback.IYFSDKCallBack;
import com.yifu.platform.single.control.PayChannel;
import com.yifu.platform.single.control.pay.YFPayController;
import com.yifu.platform.single.control.pay.YFProgressDialog;
import com.yifu.platform.single.internal.YFPlatformInternal;
import com.yifu.platform.single.item.YFOrderInfoData;
import com.yifu.platform.single.item.YFOrderStatus;
import com.yifu.platform.single.json.JSONManager;
import com.yifu.platform.single.net.ConnectManagerHelper;
import com.yifu.platform.single.net.INetListener;
import com.yifu.platform.single.net.NetManager;
import com.yifu.platform.single.net.INetListener.DownLoadStatus;
import com.yifu.platform.single.net.response.BaseResult;
import com.yifu.platform.single.net.response.QueryOrderStatusResult;
import com.yifu.platform.single.net.response.UploadOrderInfoResult;
import com.yifu.platform.single.order.Order;
import com.yifu.platform.single.util.Constants;
import com.yifu.platform.single.util.PayOrderChannel;
import com.yifu.platform.single.util.SMSOrderIDGenerator;
import com.yifu.platform.single.util.SharePreferenceUtil;
import com.yifu.platform.single.util.YFUtil;
import com.yifu.platform.wxapi.Md5Util;
import com.yifu.platform.wxapi.WeChatConstans;
import com.yifu.platform.wxapi.YFH5PayActivity;

public class ZhongXinWechatPay extends PayChannel implements INetListener {
	private Order order;
	private Activity context;
	private int times = 0;// 查询订单的次数
	IYFSDKCallBack mSDKCallBack = null;
	private YFProgressDialog mYFProgressDialog;
	WFTBroadcaset brocast;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			String orderid = (String) msg.obj;
			queryOrderInfo(orderid);

		};
	};

	@Override
	public void doPay(Context context, IYFSDKCallBack mSDKCallBack, Order order) {

		if (!YFUtil.isAppAvilible(context, "com.tencent.mm")) {
			Toast.makeText(context, "请确认您的手机安装微信客户端后重试！", Toast.LENGTH_LONG)
					.show();
			return;
		}
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

	private void startPay() {
		final String xml = preRequestparam();
		// final String wexinurl = "";

		new AsyncTask<Void, Void, String>() {

			@Override
			protected String doInBackground(Void... params) {
				String pay_info = "";
				try {
					byte[] xmlbyte = xml.toString().getBytes("UTF-8");

					//System.out.println(xml);

					URL url = new URL(ZhongXinConfig.REQUEST_URL);

					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setConnectTimeout(5000);
					conn.setDoOutput(true);// 允许输出
					conn.setDoInput(true);
					conn.setUseCaches(false);// 不使用缓存
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
					conn.setRequestProperty("Charset", "UTF-8");
					conn.setRequestProperty("Content-Length",
							String.valueOf(xmlbyte.length));
					conn.setRequestProperty("Content-Type",
							"text/xml; charset=UTF-8");
					conn.setRequestProperty("X-ClientType", "2");// 发送自定义的头信息

					conn.getOutputStream().write(xmlbyte);
					conn.getOutputStream().flush();
					conn.getOutputStream().close();

					if (conn.getResponseCode() != 200)
						throw new RuntimeException("请求url失败");

					InputStream is = conn.getInputStream();// 获取返回数据

					// 使用输出流来输出字符(可选)
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					int len;
					while ((len = is.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					String string = out.toString("UTF-8");
					//System.out.println(string);
					out.close();

					// xml解析
					String status = null;
					String result_code = null;

					XmlPullParser parser = Xml.newPullParser();
					try {
						parser.setInput(
								new ByteArrayInputStream(string
										.getBytes("UTF-8")), "UTF-8");
						// parser.setInput(is, "UTF-8");
						int eventType = parser.getEventType();
						while (eventType != XmlPullParser.END_DOCUMENT) {
							if (eventType == XmlPullParser.START_TAG) {
								if ("status".equals(parser.getName())) {
									status = parser.getAttributeValue(0);
								} else if ("result_code".equals(parser
										.getName())) {
									result_code = parser.nextText();
								} else if ("pay_info".equals(parser.getName())) {
									pay_info = parser.nextText();
								}
							}
							eventType = parser.next();
						}
					} catch (XmlPullParserException e) {
						e.printStackTrace();

					} catch (IOException e) {
						e.printStackTrace();
						System.out.println(e);
					}

					if (pay_info != null && !pay_info.isEmpty()) {

						return pay_info;
					} else {
						Log.e("zhongxinwechatpay", "payinfo为空");
					}
				} catch (Exception e) {

					System.out.println(e);
				}

				return pay_info;
			}

			@Override
			protected void onPostExecute(String result) {

				super.onPostExecute(result);
				Intent intent = new Intent(context, YFH5PayActivity.class);
				intent.putExtra("price", order.price);
				intent.putExtra("itemid", order.item_id);
				intent.putExtra("orderid", order.order_id);
				if (result != null && !result.isEmpty()) {
					intent.putExtra("url", result);
					brocast = new WFTBroadcaset();
					IntentFilter filter = new IntentFilter(WeChatConstans.ACTION_THIRDPAY_RECEIVER);
					context.registerReceiver(brocast, filter);
					context.startActivity(intent);
				} else {
					Log.e("ZhongXinWechatPay", "获取pay_info失败");
					Toast.makeText(context, "下单失败", Toast.LENGTH_LONG).show();
				}

			}
		}.execute();

		// Intent intent = new Intent(Intent.ACTION_VIEW,
		// Uri.parse(url));
		// context.startActivity(intent);

		
	}

	class WFTBroadcaset extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			//Toast.makeText(context, "接收广播", 0).show();
			Message msg = Message.obtain();
			msg.obj = intent.getStringExtra(WeChatConstans.INTENT_ORDERID);
			msg.what=200;
			handler.sendMessage(msg);
			context.unregisterReceiver(this);
			
		}

	}

	
	/**
	 * 构造请求参数
	 * 
	 * @return
	 */
	private String preRequestparam() {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("service", "pay.weixin.wappay");
		map.put("mch_id", ZhongXinConfig.ch_id);
		map.put("out_trade_no", order.mchNo + order.order_id);
		map.put("body", order.desc);
		map.put("total_fee", order.price);
		map.put("mch_create_ip", Constants.ipAdress);
		map.put("notify_url", ZhongXinConfig.notify_url);
		map.put("nonce_str", SMSOrderIDGenerator.getOrderID(8));
		//map.put("callback_url", pageReturnUrl);
		StringBuilder sb = new StringBuilder();
		SignUtils.buildPayParams(sb, map, false);
		//System.out.println(sb.toString());
		String sign = Md5Util.md5(sb.toString() + "&key=" + ZhongXinConfig.key)
				.toUpperCase();
		map.put("sign", sign);
		String xml = SignUtils.toXml(map);
		//System.out.println(xml);
		return xml;
	}

	/**
	 * 上传订单信息
	 * 
	 * @param orderid
	 */
	private void uploadOrderInfo(StringBuilder orderid) {
		mYFProgressDialog.show();
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
				orderInfo.setyfOrderPayChannel(PayOrderChannel.CHANNEL_WEIXIN);
				orderInfo.setyfOrderPrice(order.price);
				orderInfo.setyfOrderProductId(order.item_id);
				orderInfo.setyfOrderId(order.order_id);

				System.out.println("订单状态====" + status);
				if (YFOrderStatus.YF_ORDER_STATUS_SUCCESS.getValue() == status) {// 成功
					orderInfo
							.setyfOrderStatus(YFOrderStatus.YF_ORDER_STATUS_SUCCESS);
					SharePreferenceUtil.getInstance(context).saveString(
							Constants.SHARE_LAST_THIRDPAY_SUCCESS,
							Constants.CHANNEL_WEIXIN);
					YFPayController.getInstance().onPlatformRechargeResult(
							YFErrorCode.BDG_RECHARGE_SUCCESS, mSDKCallBack,
							orderInfo);
				} else {
					if (times < 20) {
						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								queryOrderInfo(qosr.getOrderid());
							}
						}, 50);

					} else {

						if (YFOrderStatus.YF_ORDER_STATUS_DEALING.getValue() == status) {// 处理中
							orderInfo
									.setyfOrderStatus(YFOrderStatus.YF_ORDER_STATUS_FAIL);
							YFPayController.getInstance()
									.onPlatformRechargeResult(
											YFErrorCode.BDG_RECHARGE_FAIL,
											mSDKCallBack, orderInfo);

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
			Log.e("YFPayHFB","tag3创建订单失败");
			
			Toast.makeText(context, "订单创建失败", Toast.LENGTH_SHORT).show();
		}else if(Constants.TAGS_QUERY_ORDER_STATUS == requestTag){
			Log.e("YFPayHFB","tag4查询订单失败");
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

	public Handler getHanler() {
		return handler;
	}

	public void setHanler(Handler hanler) {
		this.handler = hanler;
	}
}
