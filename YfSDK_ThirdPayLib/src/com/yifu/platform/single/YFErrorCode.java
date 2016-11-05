package com.yifu.platform.single;

public final class YFErrorCode {

	public static final int DC_Error = -1; // 错误
	public static final int DC_OK = 0; // 正常

	
	// 网络错误码定义
	public static final int YF_NET_TIME_OUT = 4504; // 网络超时
	public static final int YF_NET_DATA_ERROR = 41000; // 网络传输数据错误
	public static final int YF_NET_GENER_ERROR = 1001; // 网络通用错误标识
	public static final int YF_SESSION_INVALID = 1004;	// session失效
	public static final int YF_NETWORK_USELESS = 42003; //网络不可用
	
	// 逻辑错误码定义
	public static final int YF_JSON_PARSER_ERROR = 41017; // 解析网络传输的json数据出错
	
	
	// 2014-05-21 add start
	
	// reorder校验
	public static final int BDG_REORDER_CHECK = 2010;
	public static final int BDG_REORDER_NON_CONSUME = 2011;//暂时不用
	
	// 支付结果回调状态码
	public static final int BDG_RECHARGE_SUCCESS = 3010;			// 支付成功
	public static final int BDG_RECHARGE_FAIL = 3011;				// 支付失败
	public static final int BDG_RECHARGE_CANCEL = 3012;				// 支付取消
	public static final int BDG_RECHARGE_EXCEPTION = 3013;			// 支付异常
	public static final int BDG_RECHARGE_ACTIVITY_CLOSED = 3014;	// 关闭SDK支付中心
	public static final int BDG_RECHARGE_USRERDATA_ERROR = 3015; 	// CP传USRERDATA有特殊字符或超出11位
	public static final int BDG_RECHARGE_SDKMODE_EXCEPTION = 3016; //CP传入的SDKMode为基本半，不支持支付功能
	
	// 查询订单状态回调状态码
	public static final int BDG_QUERY_ORDER_STATUS_SUCCESS = 4000;	// 成功返回订单支付结果
	public static final int BDG_QUERY_ORDER_STATUS_FAIL = 4001;		// 没有查到订单对应的支付结果
	
	//品轩回调状态码
	public static final int BDG_CROSSRECOMMEND_INIT_FINSIH = 5001;	//交叉推荐初始化完成
	
	public static final int BDG_NO_SIM = 7001;//无SIM卡
	public static final int BDG_NO_WAY = 7002;//无支付通道
	
	// 2014-05-21 add end
}
