/**
 */

package com.yifu.platform.single.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.yifu.platform.single.YFErrorCode;
import com.yifu.platform.single.net.response.BaseResult;
import com.yifu.platform.single.net.response.LocationResult;
import com.yifu.platform.single.net.response.QueryOrderStatusResult;
import com.yifu.platform.single.net.response.ThirdPayResult;
import com.yifu.platform.single.net.response.UploadOrderInfoResult;
import com.yifu.platform.single.util.Constants;
import com.yifu.platform.single.util.MyLogger;

public final class JSONParser {
	public static final String TAG = "JSONParser";
	private MyLogger mLogger = MyLogger.getLogger(JSONParser.class.getSimpleName());

	/**
	 * 解析tag220 获取用户所在城市
	 */
	public BaseResult parseInitLocationEncrypt(String str) throws JSONException {

		JSONObject json = new JSONObject(str);
		LocationResult info = new LocationResult();
		info.setErrorCode(json.optInt("errorcode"));
		info.setLocation(json.optString("location"));
		String ip = json.optString("ip");

		info.setIp(ip);

		Constants.LocalProvince = info.getLocation();
		Constants.ipAdress = info.getIp();
	
		return info;
	}

	

	

	
	/**
	 * TAG 3
	 * 
	 * @param str
	 * @return
	 * @throws JSONException
	 */
	public BaseResult parseUploadOrderInfo(String str) throws JSONException {
		JSONObject jsonObj = new JSONObject(str);

		UploadOrderInfoResult result = new UploadOrderInfoResult();
		result.setRequestTag(Integer.parseInt(jsonObj.getString(Constants.JSON_TAG)));
		try {
			int errorcode = jsonObj.getInt(Constants.JSON_ERROR_CODE);
			String errorStr = jsonObj.getString(Constants.JSON_ERROR_MESSAGE);

			if (errorcode == Constants.DC_OK) {
				String accepTime = jsonObj.getString(Constants.JSON_ACCEPT_TIME);
				String orderId = jsonObj.optString("orderid");
				int orderstatus = jsonObj.getInt(Constants.JSON_ORDER_STATUS);
				result.setAccepTime(accepTime);
				result.setOrder_status(orderstatus);
				result.setOrderid(orderId);
			}

			result.setErrorCode(errorcode);
			result.setErrorString(errorStr);
		} catch (Exception e) {

			result.setErrorCode(Constants.DC_NETWORK_ERROR);
			result.setOrder_status(Constants.ORDER_STATUS_FAIL_INT);

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * TAG 4
	 * 
	 * @param str
	 * @return
	 * @throws JSONException
	 */
	public BaseResult parseQueryOrderStatus(String str) throws JSONException {

		JSONObject jsonObj = new JSONObject(str);

		QueryOrderStatusResult result = new QueryOrderStatusResult();
		result.setRequestTag(Integer.parseInt(jsonObj.getString(Constants.JSON_TAG)));
		do {
			try {
				int errorcode = jsonObj.getInt(Constants.JSON_ERROR_CODE);
				String errorStr = jsonObj.getString(Constants.JSON_ERROR_MESSAGE);

				if (YFErrorCode.DC_OK != errorcode)
					break;

				String accepTime = jsonObj.getString(Constants.JSON_ACCEPT_TIME);
				int orderstatus = jsonObj.getInt(Constants.JSON_ORDER_STATUS);
				String orderid = jsonObj.getString(Constants.JSON_ORDER_ORDERID);

				result.setErrorCode(errorcode);
				result.setErrorString(errorStr);
				result.setAccepTime(accepTime);
				result.setOrderid(orderid);
				result.setOrderstatus(orderstatus);
				if (jsonObj.has(Constants.JSON_ORDERPRICE))
					result.setOrderPrice(jsonObj.getInt(Constants.JSON_ORDERPRICE));

			} catch (Exception e) {

				result.setErrorCode(Constants.DC_NETWORK_ERROR);
				result.setOrderstatus(Constants.ORDER_STATUS_FAIL_INT);

				e.printStackTrace();
			}
		} while (false);

		return result;

	}

	


	public BaseResult parseBaseResult(String str) throws JSONException {

		JSONObject json = new JSONObject(str);
		BaseResult result = new BaseResult();

		int errorcode = json.getInt(Constants.JSON_ERROR_CODE);
		String errorStr = json.getString(Constants.JSON_ERROR_MESSAGE);

		result.setErrorCode(errorcode);
		result.setErrorString(errorStr);
		if (json.has(Constants.JSON_ACCEPT_TIME)) {
			result.setAccepTime(json.getString(Constants.JSON_ACCEPT_TIME));
		}
		result.setRequestTag(json.getInt(Constants.JSON_TAG));

		return result;
	}

	public BaseResult parseThirdPayList(String resData) throws JSONException {
		JSONObject json = new JSONObject(resData);
		ThirdPayResult result = new ThirdPayResult();
		try {
			int errorcode = json.getInt(Constants.JSON_ERROR_CODE);
			String errorStr = json.getString(Constants.JSON_ERROR_MESSAGE);
			int alipay = json.getInt("alipay");
			int quickpay = json.getInt("quickpay");
			int yeepay = json.getInt("yeepay");
			int gamecard = json.getInt("gamecard");
			int tencentmm = json.getInt("tencentmm");
			int alipaytype = json.getInt("alipayType");
			int tencentmmtype = json.getInt("tencentmmType");
			int checkId = json.getInt("checkId");
			result.setAlipay(alipay);
			result.setQuickpay(quickpay);
			result.setYeepay(yeepay);
			result.setGamecard(gamecard);
			result.setTencentmm(tencentmm);
			result.setTencentmmtype(tencentmmtype);
			result.setAlipaytype(alipaytype);
			result.setErrorCode(errorcode);
			result.setErrorString(errorStr);
			result.setCheckId(checkId);

			//JSONArray arr = json.getJSONArray("yeepayllst");
			//result.setYeepayllst(arr.toString());

		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorCode(Constants.DC_NETWORK_ERROR);
		}
		return result;
	}

}
