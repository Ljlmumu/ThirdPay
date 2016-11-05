package com.yifu.platform.single.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Md5Tools
{

	private Md5Tools()
	{
	}

	public static String toMd5(byte abyte0[], boolean flag)
	{
		String res = "";
		
		try{
			MessageDigest messagedigest;
			messagedigest = MessageDigest.getInstance("MD5");
			messagedigest.reset();
			messagedigest.update(abyte0);
			res = toHexString(messagedigest.digest(), "", flag);
			
		}catch(NoSuchAlgorithmException e){
			
		}finally{
			
		}
		
		return res;
	}

	public static String toHexString(byte abyte0[], String s, boolean flag)
	{
		StringBuilder stringbuilder = new StringBuilder();
		byte abyte1[] = abyte0;
		int i = abyte1.length;
		for (int j = 0; j < i; j++)
		{
			byte byte0 = abyte1[j];
			String s1 = Integer.toHexString(0xff & byte0);
			if (flag)
				s1 = s1.toUpperCase();
			if (s1.length() == 1)
				stringbuilder.append("0");
			stringbuilder.append(s1).append(s);
		}

		return stringbuilder.toString();
	}
	
	
	public final static String getMessageDigest(byte[] content) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(content);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
}