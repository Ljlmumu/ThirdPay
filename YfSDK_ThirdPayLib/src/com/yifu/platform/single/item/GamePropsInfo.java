package com.yifu.platform.single.item;

import java.io.Serializable;
import java.math.BigDecimal;

public class GamePropsInfo implements Serializable{

	private String propsId; //商品道具id
	private String price; //价钱
	private String title; //商品名字
	private String userdata;//用户透传字段
	public GamePropsInfo(){
		
	}
	
	public GamePropsInfo( String mPrice, String mTitle,String mUserdata){
		propsId = "8";
		price = String.valueOf(new BigDecimal(mPrice).multiply(new BigDecimal(100)).intValue());
		title = mTitle;
		userdata = mUserdata;
	}

	

	public String getPropsId() {
		return propsId;
	}

	public String getPrice() {
		return price;
	}

	public String getTitle() {
		return title;
	}

	public String getUserdata() {
		return userdata;
	}
	
	
}
