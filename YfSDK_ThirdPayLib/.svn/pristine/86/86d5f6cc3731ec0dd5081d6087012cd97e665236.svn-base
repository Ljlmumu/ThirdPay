/**
 */

package com.yifu.platform.single.json;

import org.json.JSONException;

import android.util.Log;

import com.yifu.platform.single.net.response.BaseResult;
import com.yifu.platform.single.util.Constants;
import com.yifu.platform.single.util.MyLogger;

public final class JSONHelper {

	public static BaseResult parserWithTag(int requestTag, String resData) throws JSONException {
		BaseResult res = null;
//System.out.println(requestTag+"----->"+resData);
		switch (requestTag) {
			case Constants.TAGS_UPLOAD_ORDER_INFO:
				res = JSONManager.getJsonParser().parseUploadOrderInfo(resData);
				break;
			case Constants.TAGS_QUERY_ORDER_STATUS:
				res = JSONManager.getJsonParser().parseQueryOrderStatus(resData);
				break;
			
			
			case Constants.TAGS_INIT_LOCATION:
				res = JSONManager.getJsonParser().parseInitLocationEncrypt(resData);
				break;
			
			case Constants.TAGS_THIRDPAY_SUPPORTLIST://147
				//System.out.println("147----------->>>>>" + resData);
				res = JSONManager.getJsonParser().parseThirdPayList(resData);
				break;
			default:
				res = JSONManager.getJsonParser().parseBaseResult(resData);
				break;
		}

		return res;
	}
}
	
//    public static void showLogCompletion(String log,int showCount){  
//        if(log.length() >showCount){  
//            String show = log.substring(0, showCount);  
////          System.out.println(show);  
//            Log.i("TAG", show+"");  
//            if((log.length() - showCount)>showCount){//剩下的文本还是大于规定长度  
//                String partLog = log.substring(showCount,log.length());  
//                showLogCompletion(partLog, showCount);  
//            }else{  
//                String surplusLog = log.substring(showCount, log.length());  
////              System.out.println(surplusLog);  
//                Log.i("TAG", surplusLog+"");  
//            }  
//              
//        }else{  
////          System.out.println(log);  
//            Log.i("TAG", log+"");  
//        }  
//    }
//}
