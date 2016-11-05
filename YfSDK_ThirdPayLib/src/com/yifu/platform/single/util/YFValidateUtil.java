package com.yifu.platform.single.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @Description: 
 */
public class YFValidateUtil {
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean validateIsNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Validate str is number
	 * @param str The string of validating
	 * @return
	 */
	public static boolean isNumeric(String str){
		if(null == str) return false;
		if("".equals(str)) return false;
		
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches()) return false;
		return true;
    }
	
	/**
	 * Validate str is decimal numberic
	 * @param str
	 * @return
	 */
	public static boolean isDecimalNumberic(String str) {
		return str.matches("[\\d.]+");
	}
	
	/**
	 * 
	 * @param 	strMobileNumber
	 * @return 	ture or false
	 * @param 	Date 2013-11-22
	 */
	public static boolean validateIsMobileNumber(String strMobileNumber) {
		Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[^4,\\D]))\\d{8}$");
        Matcher isNum = pattern.matcher(strMobileNumber);
        if(!isNum.matches()) return true;
        return false;
	}
	
	/**
	 * Remove the dot and zero
	 * @param s
	 * @return
	 */
	public static String subZeroAndDot(String s){
		if(s.indexOf(".") > 0){
			s = s.replaceAll("0+?$", "");
			s = s.replaceAll("[.]$", "");
		}
		
		return s;
	}
}
