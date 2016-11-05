package com.yifu.platform.single.item;

public enum YFOrderStatus {

	/** 订单状态未知（订单在服务器上没有记录）**/
	YF_ORDER_STATUS_UNKNOWN(0),
	/** 订单正在处理中  **/
	YF_ORDER_STATUS_DEALING(1),
	/** 订单交易成功  **/
	YF_ORDER_STATUS_SUCCESS(3),
	/** 订单交易失败  **/
	YF_ORDER_STATUS_FAIL(2),
	/** 支付短信发送成功  **/
	YF_ORDER_STATUS_SMS_SEND(5);
	
	private final int value;
    
    private YFOrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
