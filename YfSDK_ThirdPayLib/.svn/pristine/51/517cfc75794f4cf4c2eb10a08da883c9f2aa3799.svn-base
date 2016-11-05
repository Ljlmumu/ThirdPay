/**
 * 
 */

package com.yifu.platform.single.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.yifu.platform.single.YFProtocolKeys;
import com.yifu.platform.single.internal.YFPlatformInternal;
import com.yifu.platform.single.item.YFOrderInfoData;
import com.yifu.platform.single.net.ConnectManagerHelper;
import com.yifu.platform.single.setting.YFSingleSDKSettings;
import com.yifu.platform.single.util.Constants;
import com.yifu.platform.single.util.DeviceId;
import com.yifu.platform.single.util.MNCType;
import com.yifu.platform.single.util.MyLogger;
import com.yifu.platform.single.util.PhoneUtil;
import com.yifu.platform.single.util.SharePreferenceUtil;
import com.yifu.platform.single.util.simoperator.SIMOpertorByMnc;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public final class JSONBuilder {
	private MyLogger mLogger = MyLogger.getLogger(JSONBuilder.class.getName());

	private static String mVersionName = "";
	private static String mVersionCode = "";
	private static String mPackagename = "";
	private static String screenwh = "";
	private static String mac = "";
	private static String operator;
	private static String utdid;
	private static Context context;
	private static int osVersion;
	private static JSONBuilder instance;

	/** 20160728 获取安装包信息集合 **/
	private List<PackageInfo> infos;

	public static JSONBuilder getInstance() {
		if (instance == null) {
			instance = new JSONBuilder();
		}
		return instance;
	}

	public JSONBuilder() {

	}

	/**
	 * 初始化Game version
	 * */
	public static void initGameVersionValue(Context context) {
		JSONBuilder.context = context;
		mVersionName = PhoneUtil.getGameVersion(context);
		mVersionCode = PhoneUtil.getGameVersionCode(context);
		mPackagename = context.getPackageName();
		operator = MNCType.getOpeater(SIMOpertorByMnc.getOpertor(context));
		utdid = PhoneUtil.getUTDID(context);
		mac = PhoneUtil.getLocalMacAddress(context);
		screenwh = PhoneUtil.getScreenWH(context);
		osVersion = PhoneUtil.getAndroidOSVersion();
	}

	public JSONObject createJsonObject() throws JSONException {

		String connectString = ConnectManagerHelper.getCurrentConnectString();
		String imeiString = PhoneUtil.getIMEI(YFPlatformInternal.getInstance().getmContext());
		String udid = DeviceId.getDeviceID(YFPlatformInternal.getInstance().getmContext());
		String appId = YFSingleSDKSettings.SDK_APPID;
		String appkey = YFSingleSDKSettings.SDK_APPKEY;
		String appSecret = YFSingleSDKSettings.SDK_APPSECRET;

		JSONObject jsonObject = new JSONObject();
		jsonObject.put(Constants.JSON_GAME_VERSION, mVersionName);
		jsonObject.put(Constants.JSON_PX_VERSION, Constants.SDK_PX_VERSION);
		jsonObject.put(Constants.JSON_GAME_VERSIONCODE, mVersionCode);
		jsonObject.put(Constants.JSON_GAME_PACKAGENAME, mPackagename);
		jsonObject.put(Constants.JSON_VERSION, Constants.SDK_VERSION);
		jsonObject.put(Constants.JSON_UA, YFSingleSDKSettings.PHONE_UA);
		jsonObject.put(Constants.JSON_OS, "android");
		jsonObject.put(Constants.JSON_CONNECT_TYPE, connectString);
		jsonObject.put(Constants.JSON_SCREENWH, screenwh);
		jsonObject.put(Constants.JSON_CHANNEL, YFSingleSDKSettings.SDK_CHANNELID + ",");
		jsonObject.put(Constants.JSON_IMEI, imeiString);
		jsonObject.put(Constants.JSON_IMSI, PhoneUtil.getIMSINew(context));
		jsonObject.put(Constants.JSON_ICCID, PhoneUtil.getSIMID(context));
		jsonObject.put(Constants.JSON_PHONE, "");
		jsonObject.put(Constants.JSON_MAC, mac);
		jsonObject.put(Constants.JSON_UDID, udid);
		jsonObject.put(Constants.JSON_APPID, appId);
		jsonObject.put(Constants.JSON_APPKEY, appkey);
		jsonObject.put(Constants.JSON_APP_SECRET, appSecret);
		jsonObject.put(Constants.JSON_AMOUNT_OPERATOR, operator);
		jsonObject.put(Constants.JSON_AMOUNT_UTDID, utdid);
		jsonObject.put(Constants.JSON_AMOUNT_OS, osVersion);
		jsonObject.put(Constants.JSON_LOCATION, Constants.LocalProvince);// 添加地理信息
		jsonObject.put(Constants.JSON_PAYVERSION, Constants.SDK_PAY_VERSION);// 添加支付版本号
		jsonObject.put(Constants.JSON_BDCUID,"");
		jsonObject.put(Constants.JSON_AMOUNT_UTDID, utdid);
		jsonObject.put(Constants.JSON_AMOUNT_OS, osVersion);
		jsonObject.put(Constants.JSON_BDPUSHID,
				SharePreferenceUtil.getInstance(context).getString(Constants.PARAM_PUSH_USER_ID));
		if (!PhoneUtil.getLBSInfo(context).equals("")) {
			jsonObject.put(Constants.JSON_LBS, PhoneUtil.getLBSInfo(context));// 添加地理信息
		}
		// else{
		// Map map = PhoneUtil.getLacAndCid(context);
		// String lac = "";
		// String cid = "";
		// if (map != null) {
		// lac = map.get("bsc_lac") + "";
		// cid = map.get("bsc_cid") + "";
		// }
		// jsonObject.put(Constants.JSON_LBS, lac+","+cid);
		// }
		return jsonObject;
	}

	

	

	/**
	 * Tag 3 Upload the info of order to server.
	 * 
	 * @return the content of order in format of json.
	 */
	public String buildSmsOrderInfoUploadString(String channel, String orderid, String price, String itemid,
			String itemname, String desc, String userdata) {
		String res = null;

		try {
			JSONObject json = createJsonObject();
			json.put(Constants.JSON_TAG, Constants.JSON_TAG_UPLOAD_ORDER);
			json.put(Constants.JSON_ORDER_CHANNEL, channel);
			json.put(Constants.JSON_MERCHANT_ID, itemname);
			json.put("mchNo", itemname);
			json.put(Constants.JSON_ORDER_ORDERID, orderid);
			json.put(Constants.JSON_ORDER_PRICE, price);
			
			json.put(Constants.JSON_ORDER_ITEMID, itemid);
			json.put(Constants.JSON_ORDER_STATUS, Constants.ORDER_STATUS_UNKNOWN);
			json.put(Constants.JSON_PHONE, "");// 手机号码
			json.put(Constants.JSON_AMOUNT_OPERATOR,operator);// 运营商
			if (userdata != null && !"".equals(userdata)) {
				json.put(Constants.JSON_CHANNEL, YFSingleSDKSettings.SDK_CHANNELID + "," + userdata);
			}
			res = json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	

	
	/**
	 * Tag 4 Query the status of order.
	 */
	public String buildQueryStatusOfOrderString(String orderid) {
		String res = null;
		String channelString = "";
		try {
			JSONObject json = createJsonObject();
			json.put(Constants.JSON_TAG, Constants.JSON_TAG_QUERY_ORDER_STATUS);
			json.put(Constants.JSON_ORDER_ORDERID, orderid);
			json.put(Constants.JSON_OR_CHANNEL, channelString);
			json.put(Constants.JSON_PHONE, "");// 手机号码
			json.put(Constants.JSON_AMOUNT_OPERATOR, operator);// 运营商
			res = json.toString();
		} catch (Exception e) {
			mLogger.v(e.getMessage());
			e.printStackTrace();
		}

		return res;
	}

	

	


	/*
	 * TAG 220 /* TAG 220 获取用户城市信息
	 */

	public String getLoc() {
		String res = null;
		try {
			JSONObject json = createJsonObject();
			json.put(Constants.JSON_TAG, 220);
			res = json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("220-->"+res);
		return res;
	}

	

	

	

	

	/**
	 * 支付中心返回支付结果的通知
	 * */
	public String buildPayCenterResponse(int stateCode, YFOrderInfoData orderInfoData) {
		String res = null;
		try {
			JSONObject json = new JSONObject();
			json.put(YFProtocolKeys.FUNCTION_STATUS_CODE, stateCode);
			if (orderInfoData != null) {
				json.put(YFProtocolKeys.BD_ORDER_ID, orderInfoData.getyfOrderId());
				json.put(YFProtocolKeys.BD_ORDER_PRODUCT_ID, orderInfoData.getyfOrderProductId());
				json.put(YFProtocolKeys.BD_ORDER_PRICE, orderInfoData.getyfOrderPrice());
				json.put(YFProtocolKeys.BD_ORDER_STATUS, orderInfoData.getyfOrderStatus());
				json.put(YFProtocolKeys.BD_ORDER_PAY_CHANNEL, orderInfoData.getyfOrderPayChannel());
			}
			res = json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 查询订单状态返回的通知
	 * */
	public String buildQueryOrderStatusReponse(int stateCode, String orderStatus) {
		String res = null;
		try {
			JSONObject json = new JSONObject();
			json.put(YFProtocolKeys.FUNCTION_CODE, stateCode);
			json.put(YFProtocolKeys.BD_ORDER_STATUS, orderStatus);

			res = json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 獲取本地安裝包名稱
	 * 
	 * @return
	 */
	public List<String> getPackageNameList() {
		PackageManager pm = context.getPackageManager();
		// List<PackageInfo> infos =
		// pm.getInstalledPackages(PackageManager.GET_PERMISSIONS);
		// 如果集合中存在数据，则不再获取，防止getInstalledPackages信息量过大引起BUG
		if (infos == null) {
			infos = pm.getInstalledPackages(PackageManager.GET_PERMISSIONS);
		}
		List<String> list = new ArrayList<String>();
		for (PackageInfo info : infos)
			if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
				list.add(info.packageName);
		return list;
	}

	/**
	 * TAG=147 第三方支付支持接口
	 * 
	 * @return
	 */
	public String buildGetThirdPaySupportListString(int checkId) {
		String res = null;
		try {
			JSONObject jsonObj = createJsonObject();
			jsonObj.put(Constants.JSON_TAG, Constants.TAGS_THIRDPAY_SUPPORTLIST);
			jsonObj.put("checkId", checkId);

			res = jsonObj.toString();
		} catch (JSONException e) {
			e.printStackTrace();
			mLogger.e(e.toString());
		}
		return res;
	}

	



}
