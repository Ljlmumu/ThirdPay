package com.yifu.platform.single.net.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第三方支付的配置开关返回
 * 
 * @author tf
 *
 */
public class ThirdPayResult extends BaseResult {
	public ThirdPayResult() {
	}

	private int checkId;
	private int alipay;
	private int quickpay;
	private int yeepay;
	private int gamecard;
	private int tencentmm;
	//private String yeepayllst;
	private int alipaytype;// 1官方2神州付
	private int tencentmmtype;// 1星罗微信2神州付
	public int getCheckId() {
		return checkId;
	}
	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}
	public int getAlipay() {
		return alipay;
	}
	public void setAlipay(int alipay) {
		this.alipay = alipay;
	}
	public int getQuickpay() {
		return quickpay;
	}
	public void setQuickpay(int quickpay) {
		this.quickpay = quickpay;
	}
	public int getYeepay() {
		return yeepay;
	}
	public void setYeepay(int yeepay) {
		this.yeepay = yeepay;
	}
	public int getGamecard() {
		return gamecard;
	}
	public void setGamecard(int gamecard) {
		this.gamecard = gamecard;
	}
	public int getTencentmm() {
		return tencentmm;
	}
	public void setTencentmm(int tencentmm) {
		this.tencentmm = tencentmm;
	}
//	public String getYeepayllst() {
//		return yeepayllst;
//	}
//	public void setYeepayllst(String yeepayllst) {
//		this.yeepayllst = yeepayllst;
//	}
	public int getAlipaytype() {
		return alipaytype;
	}
	public void setAlipaytype(int alipaytype) {
		this.alipaytype = alipaytype;
	}
	public int getTencentmmtype() {
		return tencentmmtype;
	}
	public void setTencentmmtype(int tencentmmtype) {
		this.tencentmmtype = tencentmmtype;
	}
	

}
