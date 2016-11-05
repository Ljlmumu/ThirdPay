package com.yf.thirdPaysdk.demo;

import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yf.thirdPaysdk.R;
import com.yifu.platform.single.PayType;
import com.yifu.platform.single.YFErrorCode;
import com.yifu.platform.single.YFPlatform;
import com.yifu.platform.single.YFProtocolKeys;
import com.yifu.platform.single.callback.IYFSDKCallBack;
import com.yifu.platform.single.item.GamePropsInfo;


public class DemoActivity extends Activity implements OnClickListener {
	Button Alipay,Tencentmm;
	Button query;
	EditText pay_input;
	String orderid = null;
	String channel = null;
	private Activity activity = this;
	private HashMap<String, String> propsIdMap = null;
	private PayType payType;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spay_main);
		
		initSDK();
		pay_input = (EditText) findViewById(R.id.pay_input);
		Alipay = (Button) findViewById(R.id.Alipay);
		Alipay.setOnClickListener(this);
		Tencentmm = (Button) findViewById(R.id.Tencentmm);
		Tencentmm.setOnClickListener(this);
		
		findViewById(R.id.pay1).setOnClickListener(this);
		findViewById(R.id.pay2).setOnClickListener(this);
		findViewById(R.id.pay3).setOnClickListener(this);
		findViewById(R.id.pay4).setOnClickListener(this);
		findViewById(R.id.pay6).setOnClickListener(this);
		findViewById(R.id.pay10).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.pay1:
			invokePay("1");
			break;
		case R.id.pay2:
			invokePay("2");
			break;
		case R.id.pay3:
			invokePay("3");
			break;
		case R.id.pay4:
			invokePay("4");
			break;
		case R.id.pay6:
			invokePay("6");
			break;
		case R.id.pay10:
			invokePay("10");
			break;
		case R.id.Alipay://支付宝
			payType = PayType.ALIPAY;
			pay();
			break;
		case R.id.Tencentmm://微信
			payType = PayType.TENCENTMM;
			pay();
			break;
		}
	}

	/**
	 *
	 * PayType.TENCENTMM 微信支付
	 * PayType.ALIPAY 支付宝支付
     */
	private void pay() {
		String price = pay_input.getText().toString().trim();
		if (price != null && price.length() > 0) {
			String mchorderId = String.valueOf(System.currentTimeMillis());
			GamePropsInfo gamePropsInfo = new GamePropsInfo(price,price + "元测试",mchorderId);
			YFPlatform.getInstance().invokePay(activity,
					gamePropsInfo,payType,RechargeCallback);
		} else {
			Toast.makeText(this, "请输入金额", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * SDK初始化
	 */
	private void initSDK() {

		/**
		 * 初始化函数 
		 */
		YFPlatform.getInstance().init(this);
	}


	/**
	 * 支付接口
	 * PayType.TENCENTMM 微信支付
	 * PayType.ALIPAY 支付宝支付
	 * @param price
	 */
	private void invokePay(String price) {
		String mchorderId = String.valueOf(System.currentTimeMillis());
		GamePropsInfo gamePropsInfo = new GamePropsInfo(
				price, price + "元测试", mchorderId);
		YFPlatform.getInstance().invokePay(activity,
				gamePropsInfo,PayType.TENCENTMM,RechargeCallback);
	}

	/**
	 * 支付处理过程的结果回调函数
	 * */
	IYFSDKCallBack RechargeCallback = new IYFSDKCallBack() {
		@Override
		public void onResponse(String paramString) {
		
			try {
				JSONObject jsonObject = new JSONObject(paramString);
				// 支付状态码
				int mStatusCode = jsonObject
						.getInt(YFProtocolKeys.FUNCTION_STATUS_CODE);

				if (mStatusCode == YFErrorCode.BDG_RECHARGE_SUCCESS) {
					// 返回支付成功的状态码，开发者可以在此处理相应的逻辑

					// 订单ID
					String mOrderId = null;
					// 订单状态
					String mOrderStatus = null;
					
					// 道具价格
					String mOrderPrice = null;
					
					if (jsonObject.has(YFProtocolKeys.BD_ORDER_ID)) {
						mOrderId = jsonObject
								.getString(YFProtocolKeys.BD_ORDER_ID);

					}
					if (jsonObject.has(YFProtocolKeys.BD_ORDER_STATUS)) {
						mOrderStatus = jsonObject
								.getString(YFProtocolKeys.BD_ORDER_STATUS);
					}
					
					if (jsonObject.has(YFProtocolKeys.BD_ORDER_PRICE)) {
						mOrderPrice = jsonObject
								.getString(YFProtocolKeys.BD_ORDER_PRICE);
					}
					
					Toast.makeText(
							activity,
							"道具购买成功!\n金额:" + Float.valueOf(mOrderPrice) / 100
									+ "元", Toast.LENGTH_LONG).show();

				} else if (mStatusCode == YFErrorCode.BDG_RECHARGE_USRERDATA_ERROR) {

					Toast.makeText(activity, "用户透传订单不合法", Toast.LENGTH_LONG)
							.show();

				} else if (mStatusCode == YFErrorCode.BDG_RECHARGE_ACTIVITY_CLOSED) {

					// 返回玩家手动关闭支付中心的状态码，开发者可以在此处理相应的逻辑
					Toast.makeText(activity, "玩家关闭支付中心", Toast.LENGTH_LONG)
							.show();

				} else if (mStatusCode == YFErrorCode.BDG_RECHARGE_FAIL) {

					// 返回支付失败的状态码，开发者可以在此处理相应的逻辑
					Toast.makeText(activity, "购买失败", Toast.LENGTH_LONG).show();

				} else if (mStatusCode == YFErrorCode.BDG_RECHARGE_EXCEPTION) {

					// 返回支付出现异常的状态码，开发者可以在此处理相应的逻辑
					Toast.makeText(activity, "购买出现异常", Toast.LENGTH_LONG)
							.show();

				} else if (mStatusCode == YFErrorCode.BDG_RECHARGE_CANCEL) {
					Toast.makeText(activity, "玩家取消支付", Toast.LENGTH_LONG)
							.show();
				}  else {
					System.out.println("异常code--->" + mStatusCode);
					Toast.makeText(activity, "未知情况", Toast.LENGTH_LONG).show();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
	};


	
}
