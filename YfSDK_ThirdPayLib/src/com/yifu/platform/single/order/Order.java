package com.yifu.platform.single.order;

import java.io.Serializable;

import android.content.Context;




public class Order implements Serializable {

	
	public String order_id;

	public String desc;
	
	public String order_time;
	
	public String status;

	public String channel;

	public String price;
	
	public String item_id;
	
	public String userdata = "";
	
	public String item_name;
	
	public String merchant_id;
	
	public long create_time;
	
	public long modify_time;

	public long query_latest;

	

	public boolean timeout = false;
	
	public boolean lastQuery = false;
	
	
	public boolean isSend = false;
	
	public boolean isBlocked = false;

	private Context mContext = null;
	
	private int tPeriod = 15;
	
	public String mchNo;

	

	public Order(String jsonString) {
	}

	public Order(Context context) {
		mContext = context;
	}

	public Context getContext() {
		return mContext;
	}

	

	public String getMchNo() {
		return mchNo;
	}

	public void setMchNo(String mchNo) {
		this.mchNo = mchNo;
	}


	public long getQuery_latest() {
		return query_latest;
	}

	public void setQuery_latest(long l) {
		this.query_latest = l;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getOrder_time() {
		return order_time;
	}

	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", desc=" + desc
				+ ", order_time=" + order_time + ", status=" + status
				+ ", channel=" + channel + ", price=" + price + ", item_id="
				+ item_id + ", userdata=" + userdata + ", item_name="
				+ item_name + ", merchant_id=" + merchant_id + ", create_time="
				+ create_time + ", modify_time=" + modify_time
				+ ", query_latest=" + query_latest + ", timeout=" + timeout
				+ ", lastQuery=" + lastQuery + ", isSend=" + isSend
				+ ", isBlocked=" + isBlocked + ", mContext=" + mContext
				+ ", tPeriod=" + tPeriod + ", mchNo=" + mchNo + "]";
	}

	

	


}
