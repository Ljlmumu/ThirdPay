package com.yifu.platform.wxapi.yibao;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import org.json.JSONObject;

public class EncryUtil {

	/**
	 * 生成RSA签名
	 */
	public static String handleRSA(TreeMap<String, Object> map,
			String privateKey) {
		StringBuffer sbuffer = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sbuffer.append(entry.getValue());
		}
		String signTemp = sbuffer.toString();

		String sign = "";
		if (privateKey!=null||!privateKey.isEmpty()) {
			sign = RSA.sign(signTemp, privateKey);
		}
		return sign;
	}

	/**
	 * 对易宝支付返回的结果进行验签
	 * 
	 * @param data
	 *            易宝支付返回的业务数据密文
	 * @param encrypt_key
	 *            易宝支付返回的对ybAesKey加密后的密文
	 * @param yibaoPublickKey
	 *            易宝支付提供的公钥
	 * @param merchantPrivateKey
	 *            商户自己的私钥
	 * @return 验签是否通过
	 * @throws Exception
	 */

	public static boolean checkDecryptAndSign(String data, String encrypt_key,
			String yibaoPublickKey, String merchantPrivateKey) throws Exception {

		/** 1.使用YBprivatekey解开aesEncrypt。 */
		String AESKey = "";
		try {
			AESKey = RSA.decrypt(encrypt_key, merchantPrivateKey);
		} catch (Exception e) {
			/** AES密钥解密失败 */
			e.printStackTrace();
			return false;
		}

		/** 2.用aeskey解开data。取得data明文 */
		String realData = AES.decryptFromBase64(data, AESKey);
		
		TreeMap<String, String> map = (TreeMap<String, String>) getMapForJson(realData);
		/** 3.取得data明文sign。 */
		String sign = map.get("sign");

		/** 4.对map中的值进行验证 */
		StringBuffer signData = new StringBuffer();
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();

			/** 把sign参数隔过去 */
			if ("sign".equals(entry.getKey())) {
				continue;
			}
			signData.append(entry.getValue() == null ? "" : entry.getValue());
		}
		
		/** 5. result为true时表明验签通过 */
		boolean result = RSA.checkSign(signData.toString(), sign,
				yibaoPublickKey);

		return result;
	}

//	/**
//	 * 生成hmac
//	 */
//	public static String handleHmac(TreeMap<String, String> map, String hmacKey) {
//		StringBuffer sbuffer = new StringBuffer();
//		for (Map.Entry<String, String> entry : map.entrySet()) {
//			sbuffer.append(entry.getValue());
//		}
//		String hmacTemp = sbuffer.toString();
//
//		String hmac = "";
//		if (StringUtils.isNotEmpty(hmacKey)) {
//			hmac = Digest.hmacSHASign(hmacTemp, hmacKey, Digest.ENCODE);
//		}
//		return hmac;
//	}
	
	/** 
     * Json 转成 Map<> 
     * @param jsonStr 
     * @return 
     */  
    public static Map<String, String> getMapForJson(String jsonStr){  
        JSONObject jsonObject ;  
        try {  
            jsonObject = new JSONObject(jsonStr);  
              
            Iterator<String> keyIter= jsonObject.keys();  
            String key;  
            String value ;  
            Map<String, String> valueMap = new TreeMap<String, String>();  
            while (keyIter.hasNext()) {  
                key = keyIter.next();  
                value = (String) jsonObject.get(key);  
                valueMap.put(key, value);  
            }  
            return valueMap;  
        } catch (Exception e) {  
            // TODO: handle exception  
            e.printStackTrace();  
           
        }  
        return null;  
    }
    
	public static Random random = new Random();

	public static String getRandom(int length) {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < length; i++) {
			boolean isChar = (random.nextInt(2) % 2 == 0);// 输出字母还是数字
			if (isChar) { // 字符串
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
				ret.append((char) (choice + random.nextInt(26)));
			} else { // 数字
				ret.append(Integer.toString(random.nextInt(10)));
			}
		}
		return ret.toString();
	}
}