package com.yifu.platform.single.util;

/**
 * 字符串判断和格式化类
 * @author yanjiaqi
 *
 */
public class StringUtils {
	private static String[] smsSigns = {
			Constants.YF_PARAM__MDO_MID,
			Constants.YF_PARAM_ORDER_MID,
			Constants.YF_PARAM_PAID_MID,
			Constants.YF_PARAM_TCDC_MID,
			Constants.YF_PARAM_TCLT_MID};
	
	public final static String SMSSigns = Constants.YF_PARAM__MDO_MID+" "+
			Constants.YF_PARAM_ORDER_MID+" "+
			Constants.YF_PARAM_PAID_MID+" "+
			Constants.YF_PARAM_TCDC_MID+" "+
			Constants.YF_PARAM_TCLT_MID;
			
	/**
	 * 
	 * @param str
	 * @return
	 * @throws NumberFormatException
	 */
	public static String getInputPriceFormattedString(String str)throws NumberFormatException{
		String handledStr = null;
		float f_price = Float.valueOf(isValid(str));
		if(f_price>=1){//数字大于1强转成int型的字符串
			handledStr = Integer.toString((int)f_price);
		}else
		if(f_price>0){//强转成Float格式的String
			handledStr = Float.toString(f_price);
		}
		return isValid(handledStr);
	}
	/**
	 * USERDATA是否合法
	 * @param str
	 * @return
	 */
	public static boolean checkUserData(String str,boolean onlyThirdPay){
		if(onlyThirdPay){
			if(str.length() > 24){
				return true;
			}
		}else{
			if(str.length() > 11){
				return true;
			}
			for(int i =0;i<smsSigns.length;i++){
				if(str.contains(smsSigns[i])){
					return true;
				}
			}
		}
		
		return false;
	}
//	/**
//	 * 是否包含中文字符
//	 * @return
//	 */
//	public static boolean isChineseIncluded(String str){
//		String regEx = "[u4e00-u9fa5]";
//		Pattern pat = Pattern.compile(regEx);
//		Matcher mat = pat.matcher(str);
//		return mat.find();
//	}
	/**
	 * 右对齐空位补零
	 * @param content
	 * @param length
	 * @return
	 */
	public static String format(String content,int length){
		if(content == null){
			content = "";
		}
		if(content.length()>length){
			//截取后几位
			return content.substring(content.length()-length);
//			throw new NumberFormatException("parameter:"+content+"\nlength not valid,length should <="+length);
		}else
		if(content.length() == length){
			return content;
		}
		else{
			StringBuilder zeroPrefix = new StringBuilder();
			for(int i =0;i<length-content.length();i++){
				zeroPrefix.append("0");
			}
			return zeroPrefix.append(content).toString();
		}
	}
	/**
	 * 判断是否为空和空串,以NumberFormatException的方式集中处理
	 * @param str
	 * @return
	 * @throws NumberFormatException
	 */
	public static String isValid(String str) throws NumberFormatException{
		if(str == null || "".equals(str))
			throw new NumberFormatException();
		return str;
	}
	
	public static boolean haveContent(String str){
		if(str == null || "".equals(str)){
			return false;
		}
		return true;
	}
	/**
	 * 为字符串找到一个没有包含关系的分隔符 避免解析出错
	 */
	public static String getNoConflictConnector(String rawString){
		for(int i=0;i<=smsSigns.length;i++){
			if(!rawString.contains(smsSigns[i])){
				return smsSigns[i];
			}
		}
		return null;
	}
}
