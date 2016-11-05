package com.yifu.platform.single.item;

import java.io.Serializable;

import com.yifu.platform.single.util.PayOrderChannel;


/**
 * 返回给CP的订单数据
 * create time : 2014-02-24
 * */
public class YFOrderInfoData implements Serializable{

	/** 订单ID **/
	private String yfOrderId;
	/** 道具ID **/
	private String yfOrderProductId;
	/** 道具价格 **/
	private String yfOrderPrice;
	/** 支付状态 **/
	private YFOrderStatus yfOrderStatus;
	/** 支付渠道 **/
	private PayOrderChannel yfOrderPayChannel;
	public YFOrderInfoData(){
		
	}
	
	public YFOrderInfoData(String myfOrderId, String myfOrderProductId, String myfOrderPrice, 
			YFOrderStatus myfOrderStatus, PayOrderChannel myfOrderPayChannel){
		yfOrderId = myfOrderId;
		yfOrderProductId = myfOrderProductId;
		yfOrderPrice = myfOrderPrice;
		yfOrderStatus = myfOrderStatus;
		yfOrderPayChannel = myfOrderPayChannel;
	}
	
	public String getyfOrderId() {
		return yfOrderId;
	}
	public void setyfOrderId(String yfOrderId) {
		this.yfOrderId = yfOrderId;
	}
	public String getyfOrderProductId() {
		return yfOrderProductId;
	}
	public void setyfOrderProductId(String yfOrderProductId) {
		this.yfOrderProductId = yfOrderProductId;
	}
	public String getyfOrderPrice() {
		return yfOrderPrice;
	}
	public void setyfOrderPrice(String yfOrderPrice) {
		this.yfOrderPrice = yfOrderPrice;
	}
	public YFOrderStatus getyfOrderStatus() {
		return yfOrderStatus;
	}
	public void setyfOrderStatus(YFOrderStatus yfOrderStatus) {
		this.yfOrderStatus = yfOrderStatus;
	}
//	public YFOrderPayChannelData getyfOrderPayChannel() {
//		return yfOrderPayChannel;
//	}
//	public void setyfOrderPayChannel(YFOrderPayChannelData yfOrderPayChannel) {
//		this.yfOrderPayChannel = yfOrderPayChannel;
//	}

	public PayOrderChannel getyfOrderPayChannel() {
		return yfOrderPayChannel;
	}

	public void setyfOrderPayChannel(PayOrderChannel yfOrderPayChannel) {
		this.yfOrderPayChannel = yfOrderPayChannel;
	}
	
}
