package com.yifu.platform.alipay;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.yifu.platform.single.util.Constants;

import android.util.Log;


/**
 * 对签名进行验签
 * 
 */
public class ResultChecker {

//	public static final int RESULT_INVALID_PARAM = 0;
//	public static final int RESULT_CHECK_SIGN_FAILED = 1;
//	public static final int RESULT_CHECK_SIGN_SUCCEED = 2;

	public static final int RESULT_KEYCODE_BACK = 3;
	
	private static final Map<String, String> sResultStatus;
	static {
		sResultStatus = new HashMap<String, String>();
		sResultStatus.put("9000", "支付成功");
		sResultStatus.put("8000", "支付结果确认中");
		sResultStatus.put("4000", "系统异常");
		sResultStatus.put("4001", "数据格式不正确");
		sResultStatus.put("6001", "用户中途取消支付操作");
	}

	private String mResult;
	
	public String resultStatus = null;
	public String memo = null;
	//String result = null;
	public boolean retVal = false;
	
	public ResultChecker(String content) {
		this.mResult = content;
	}
	
	public  void parseResult() {
		
		try {
			String src = mResult.replace("{", "");
			src = src.replace("}", "");
			resultStatus = getContent(src, "resultStatus=", ";memo");
			memo = getContent(src, "memo=", ";result");
			String result = getContent(src, "result=", null);
			retVal = checkSign(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private  String getContent(String src, String startTag, String endTag) {
		String content = src;
		int start = src.indexOf(startTag);
		start += startTag.length();

		try {
			if (endTag != null) {
				int end = src.indexOf(endTag);
				content = src.substring(start, end);
			} else {
				content = src.substring(start);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return content;
	}
	
	/**
	 * 对签名进行验签
	 * 
	 * @return
	 */
	public boolean checkSign(String result) {

		boolean retVal = false;
		try {
			JSONObject json = string2JSON(result, "&");

			int pos = result.indexOf("&sign_type=");
			String signContent = result.substring(0, pos);

			String signType = json.getString("sign_type");
			signType = signType.replace("\"", "");

			String sign = json.getString("sign");
			sign = sign.replace("\"", "");

			if (signType.equalsIgnoreCase("RSA")) {
				//sign ="jrkZHIv/ihM1uHxctZ7LVYAjh0/29isgXw43qRrpRyUnrEqtqcxgzEbsd4VIngySis7ApGatHSpK/GWIsHoa8mNApGTwcGnDhXBCvjQruMpznTxGr4IIehgXgIbdIM+IE1HzVr3eoF0pKQCJ8WpDHI1I8uaWcbIPqaRhWeov+B8=";
				retVal = Rsa.doCheck(signContent, sign, PartnerConfig.RSA_ALIPAY_PUBLIC);
				//Log.e("lijilei")
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.i("Result", "Exception =" + e);
		}
		Log.i("Result", "checkSign =" + retVal);
		return retVal;
	}
	
	public  JSONObject string2JSON(String src, String split) {
		JSONObject json = new JSONObject();

		try {
			String[] arr = src.split(split);
			for (int i = 0; i < arr.length; i++) {
				String[] arrKey = arr[i].split("=");
				json.put(arrKey[0], arr[i].substring(arrKey[0].length() + 1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}
}