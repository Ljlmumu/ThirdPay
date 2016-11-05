package com.yifu.platform.single.util;

import java.util.HashMap;


public class Constants {
	//public static final String URL_SITE_BASE = "http://123.57.237.83/sdkapi";// 外网测试服务器
		public static final String URL_SITE_BASE = "http://123.57.237.83";// 外网正式服务器

	/** 调试总开关，其他类 debug 开关采用 &方式，方便总控制。 */
	public static boolean DEBUG = false;
	public static boolean SMS_DEBUG = false;
	/** 悬浮窗控制抽奖按钮开关 */
	public static boolean YF_SUSPENSION_LOTTERY_SUPPORT = true;

	// debug switch for cp only.
	public static boolean CP_DEBUG = false;

	public static MNCType PHONE_MNC;

	public static int refreshInterval = 10;
	public static double smsTimeOut = 1.5;

	public static final String SDK_VERSION = "2.0.2";
	/** 支付版本号信息 **/
	public static final String SDK_PAY_VERSION = "1.1.0";
	public static final String SDK_VERSION_DISPLAY = SDK_VERSION + "_20160527";

	public static final String SDK_PX_VERSION = "1.0.3";

	// 客服电话号码
	public static final String TEL_PHONE = "4000825642";

	// 防止多次更新短信发送状态
	public static HashMap<String, Boolean> mapSMSIsSend = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> mapSMSIsBlocked = new HashMap<String, Boolean>();

	// Response error codes.
	public static final int DC_OK = 0;
	// 中间层接口错误
	public static final int DC_NETWORK_ERROR = 3000;
	// 话付宝的商品编号error
	public static int ERROR_CODE_CP_MERCHANTID_DIFF = 3001;
	// 话付宝不支持此额度error
	public static int ERROR_CODE_CP_AMOUNT_DIFF = 3002;

	// CP传递物品数据到多酷支付平台的参数名称定义
	public static final String PARAM_BUY_GOODS = "cp_to_yifu_data";
	// public static final String PARAM_CM_MDO_DATA = "cm_mdo_data";
	// public static final String PARAM_CM_MM_DATA = "cm_mm_data";
	// public static final String PARAM_CM_GB_DATA = "cm_gb_data";
	// public static final String PARAM_CT_EGAME_DATA = "ct_egmae_data";
	public static final String PARAM_ALL_GAME_DATA = "all_egmae_data";

	public static final String PARAM_ORDER = "order";
	public static final String PARAM_CHANNEL_VIEWVALUE = "channel_view_value";
	// CP传递给多酷支付平台的手机SIM卡类型的参数名称定义
	public static final String PARAM_SIM_OPERATOR = "cp_to_yifu_sim_operator";
	// 通用传递数据key字段
	public static final String PARAM_INDEX_1 = "PARAM_INDEX_1";
	public static final String PARAM_INDEX_2 = "PARAM_INDEX_2";
	// 点击计费点时判断是否联网
	public static final String PARAM_FLAG_CONNECT_NET = "param_flag_connect_net";

	// Channel id
	public static final String FLAG_YFSDK_CHANNEL = "yfsdk_channel";


	
	public static final String URL_SITE_USER_BASE = "http://yfsdk.yf-game.cn";// 获取手机号服务器地址
	



	public static final String URL_UPLOAD_ORDER_INFO = URL_SITE_BASE + "/makeOrder";// 协议3
																					// 上传订单信息
	public static final String URL_QUERY_ORDER_STATUS = URL_SITE_BASE + "/queryOrderById";// 协议4
																							// 订单状态查询
	public static final String URL_QUERY_PAYMENT_INFO = URL_SITE_BASE + "/queryNewPayChannel";
	public static final String URL_QUERY_NON_CONSUMER_LIST = URL_SITE_BASE + "/queryNonconsumerEquipment";
	public static final String URL_COUNT_DEVICE_ACTIVE = URL_SITE_BASE + "/clientActive";
	public static final String URL_DO_YEEPAY_PAYMENT = URL_SITE_BASE + "/cardRecharge";
	// 统计游戏的使用时长
	public static final String URL_COUNT_GAMEONLINE_TIME = URL_SITE_BASE + "/recordGameTimes";
	// 查询手机归属地
	public static final String URL_QUERY_MOBILE_OWNERSHIP = URL_SITE_BASE + "/queryAvailablePayChannel";

	// 查询可用的支付方式（联通、电信）
	public static final String URL_QUERY_CHANNEL_MOBILE_UDID = URL_SITE_BASE + "/queryNewAvailablePayChannel";

	// 45初始化交叉推荐的数据
	// public static final String URL_INIT_CROSS_RECOMMEND_GAME = URL_SITE_BASE
	// + "/getGameRecommendV140";
	public static final String URL_INIT_CROSS_RECOMMEND_GAME = URL_SITE_BASE + "/getGameRecommendV140";
	/**
	 * tag 46 获取交叉推荐列表
	 */
	// public static final String URL_CROSS_RECOMMEND_GAME_LIST =
	// URL_SITE_BASE+"/getGameRecommendHotorBest";
	public static final String URL_CROSS_RECOMMEND_GAME_LIST = URL_SITE_BASE + "/getGameRecommendHotorBest";
	// tag 101 统计交叉推荐数据的接口
	// public static final String URL_STATISTICS_CROSS_RECOMMEND = URL_SITE_BASE
	// + "/GameRecommendStatistics";
	public static final String URL_STATISTICS_CROSS_RECOMMEND = URL_SITE_BASE + "/gameRecommendStatistics";
	// 2014-05-23 add start
	// 查询指定游戏的所有计费点
	public static final String URL_QUERY_PROPS_BY_GAMEID = URL_SITE_BASE + "/queryAllSMSPayChannel";
	/**
	 * tag 141
	 */
	public static final String URL_QUERY_SMS_PAYCHANNEL_FLAG = URL_SITE_BASE + "/queryAllSMSPayChannel";
	/**
	 * tag 144
	 */
	 public static final String URL_QUERY_INIT_PAYCHANNEL_FLAG = URL_SITE_BASE+ "/queryGbAndMmSupport";
//	public static final String URL_QUERY_INIT_PAYCHANNEL_FLAG = URL_SITE_BASE + "/pay";
	// 2014-05-23 add end

	// tag 145 更新计费配置文件 (SDK1.41以后替代tag8) 2015-3-16
	 public static final String URL_QUERY_PAYMENT_INFO_145 =URL_SITE_BASE+"/queryNewPayChannelV200";
//	public static final String URL_QUERY_PAYMENT_INFO_145 = URL_SITE_BASE + "/pay";
	/**
	 * TAG = 146 获取游戏充值卡列表
	 */
	public static final String URL_QUERY_RECHARGECARDLIST = URL_SITE_BASE + "/queryRechargeCardList";

	/**
	 * TAG = 147 获取支持的第三方支付列表
	 */
	//public static final String URL_THIRDPAY_SUPPORT_LIST = URL_SITE_BASE + "/pay";
	public static final String URL_THIRDPAY_SUPPORT_LIST = URL_SITE_BASE + "/thirdpaySupportList";
	/**
	 * TAG = 148 获取微信支付所需参数
	 */
	public static final String URL_QUERY_TENCENTMM_PARAMS = URL_SITE_BASE + "/queryTencentMMParams";
	/**
	 * TAG = 149 获取聚合微信支付信息加密结果
	 */
	// public static final String URL_GET_WX_PAYINFO_ENCRYPT_149 = URL_SITE_BASE
	// + "/getWxipayEncrypt";
	public static final String URL_GET_WX_PAYINFO_ENCRYPT_149 = URL_SITE_BASE + "/pay";

	/**
	 * tag 150 统一支付订单创建
	 */
	 public static final String URL_PAY_CREATEORDER = URL_SITE_BASE+ "/createorder";
	 
	/**
	 * tag 151统一支付验证码提交
	 */
	public static final String URL_PAY_COMMITVERIFY = URL_SITE_BASE+ "/commitverify";
	 
	/**
	 * TAG = 220 SDK加载开关
	 */
	public static final String URL_INIT_LOCATION = URL_SITE_BASE + "/sdkconfig";

	// 2014-09-15 add end

	// 查询琅琅支付是否交易成功
	public static final String URL_CHECK_LLPAY_151 = URL_SITE_BASE + "/llorder";


	public static final String URL_QUICKPAY_AGAIN_GET_VERRIFYCODE = URL_SITE_BASE + "/againGetVerifycode";// TAG
																											// =
	// 单次提交的使用时长记录条数的最大值
	public static final int MAX_COUNT_ONLINE_TIME_NUMBER = 300;
	// 记录游戏初始化的时间
	public static final String SP_COUNT_GAMEONLINE_START_TIME = "online_start_time";
	//
	public static final String SP_DEBUG_STATE = "cp_debug_state";
	//
	public static final String SP_DEVIDE_UDID = "cp_debug_udid";
	// 记录上一次记录的日期
	public static final String SP_COUNT_GAMEONLINE_LAST_DATE = "online_last_date";

	public static final String CROSS_RECOMMEND_DATA = "corss_recomend_data";

	public static final String JSON_OR_CHANNEL = "or_channel";

	// JSON常量定义
	public static final String JSON_TAG = "tag";
	public static final String JSON_VERSION = "version";
	public static final String JSON_PX_VERSION = "px_version";
	public static final String JSON_GAME_VERSION = "gameversion";
	public static final String JSON_UA = "ua";
	public static final String JSON_OS = "os";
	public static final String JSON_CONNECT_TYPE = "connecttype";
	public static final String JSON_SCREENWH = "screenwh";
	public static final String JSON_CHANNEL = "channel";
	public static final String JSON_IMEI = "imei";
	public static final String JSON_IMSI = "imsi";
	public static final String JSON_ICCID = "iccid";
	public static final String JSON_MAC = "mac";
	public static final String JSON_UDID = "udid";
	public static final String JSON_APPID = "appid";
	public static final String JSON_APPKEY = "appkey";
	public static final String JSON_APP_SECRET = "app_secret";
	public static final String JSON_BDCUID = "bdcuid";
	public static final String JSON_BDPUSHID = "bdpushid";
	public static final String JSON_LOCATION = "location";
	public static final String JSON_PAYVERSION = "pay_version";
	public static final String JSON_STATIONINFO = "station_info";
	public static final String JSON_LBS = "LBS";
	// 2014-01-16 add
	public static final String JSON_GAME_VERSIONCODE = "gameversioncode";
	public static final String JSON_GAME_PACKAGENAME = "gamepackagename";

	public static final String JSON_ORDER_CHANNEL = "paychannel";
	public static final String JSON_MERCHANT_ID = "merchantid";
	public static final String JSON_ORDER_ORDERID = "orderid";
	public static final String JSON_ORDER_ORDERID_UPPER = "orderId";
	public static final String JSON_ORDER_ITEMID = "itemid";
	public static final String JSON_ORDER_ORDER_TIME = "ordertime";
	public static final String JSON_ORDER_ITEMNAME = "itemname";
	public static final String JSON_ORDER_PRICE = "price";
	public static final String JSON_ORDER_DESC = "desc";
	public static final String JSON_ORDER_STATUS = "orderstatus";
	public static final String JSON_PHONENUMBER = "phonenumber";
	public static final String JSON_PHONE = "phone";
	public static final String JSON_PAY_MONEY = "paymoney";
	public static final String JSON_PAY_MAX = "paymax";
	public static final String JSON_PAID_UDID = "paid_udid";
	public static final String JSON_PAID_MAX_UDID = "paidm_udid";

	public static final String JSON_LOCAL_PACKAGE_LIST = "packageName_list";
	public static final String JSON_SCREEN_ORIENTATION = "screen_orientation";

	public static final String JSON_ORDERPRICE = "orderprice";

	public static final String JSON_TAG_QUERY_ITEM_LIST = "1";
	// 1.2系列之后，查询接口tag2变为tag8.
	public static final String JSON_TAG_QUERY_PAYCHANNEL = "8";
	public static final String JSON_TAG_UPLOAD_ORDER = "3";
	public static final String JSON_TAG_QUERY_ORDER_STATUS = "4";
	public static final String JSON_TAG_QUERY_MOBILE_OWNERSHIP = "7";
	public static final String JSON_TAG_QUERY_CHANNEL_MOBILE = "43";
	public static final String JSON_TAG_INIT_CROSS_RECOMMEND_GAME = "45";

	public static final String JSON_TAG_LOTTERY_INIT = "47";
	public static final String JSON_TAG_LOTTERY_CONFIRM = "48";
	public static final String JSON_TAG_LOTTERY_VERIFYCODE = "50";
	public static final String JSON_TAG_LOTTERY_INIT_CHOSENITEMS = "51";
	public static final String JSON_TAG_LOTTERY_DRAW_ONCE = "52";
	public static final String JSON_TAG_LOTTERY_HISTORY = "53";
	public static final String JSON_TAG_LOTTERY_RECOMMEND = "54";
	/**
	 * 获取交叉推荐列表 2014/08/22
	 */
	public static final String JSON_TAG_INIT_CROSS_RECOMMEND_GAME_LIST = "46";
	/**
	 * 悬浮窗方案二———一页请求的数量； 2014/08/22
	 */
	public static final int JSON_TAG_SUSPENSION_REQUEST_NUM = 10;
	/**
	 * 本地包名的集合 2014/08/28
	 */

	public static final String JSON_TAG_STATISTICS_CROSS_RECOMMEND_GAME = "101";
	public static final String JSON_TAG_QUERY_PROPS_BY_GAMEID = "141";

	public static final String JSON_NONCONSUMER_LIST_ITEMS = "items";
	public static final String JSON_NONCONSUMRE_LIST_COUNT = "itemcount";

	// Count online time
	public static final String JSON_COUNT_DATA = "data";
	public static final String JSON_COUNT_ONLINE_DATE = "date";
	public static final String JSON_COUNT_ONLINE_TIME = "duration";

	// tag 46上行参数 2014/08/22
	public static final String JSON_MORETYPE = "moretype";
	public static final String JSON_PAGE = "page";
	public static final String JSON_DATACOUNT = "datacount";

	
	// Tags.
	public static final String JSON_AMOUNT_CHANNELS = "paychannels";
	public static final String JSON_AMOUNT_VERIFY_INFO = "sms_verify_info";
	public static final String JSON_AMOUNT_REPLY_SWITCH = "sms_delete";
	public static final String JSON_AMOUNT_REPLY_INFO = "sms_delete_info";
	public static final String JSON_AMOUNT_REPLY_PHONE = "need_phone";
	public static final String JSON_AMOUNT_OPERATOR = "operator"; 
	public static final String JSON_AMOUNT_UTDID = "utdid";
	public static final String JSON_AMOUNT_OS = "os_version";
	public static final String JSON_AMOUNT_CHECKID = "checkId";
	public static final String JSON_AMOUNT_CHANNEL = "channel_name";
	public static final String JSON_AMOUNT_ISVALID = "isvalid";
	public static final String JSON_AMOUNT_VALIDSTART = "valid_start";
	public static final String JSON_AMOUNT_VALIDEND = "valid_end";
	public static final String JSON_AMOUNT_DAY_MAX = "day_max";

	public static final String JSON_AMOUNT_AMOUNTS = "amounts";
	public static final String JSON_AMOUNT_AMOUNT = "amount";
	public static final String JSON_AMOUNT_CODE = "code";
	public static final String JSON_AMOUNT_DEST = "dest";
	public static final String JSON_AMOUNT_GROUP = "group";
	public static final String JSON_AMOUNT_SORT = "sort";
	public static final String JSON_AMOUNT_LIMIT = "limit";
	public static final String JSON_AMOUNT_LIMITS_CUHJ = "cu_hj_limits";
	public static final String JSON_AMOUNT_LIMITS_SFDX = "cu_sfdx_limits";

	public static final String JSON_WOSTORE_PREFIX = "prefix";// 沃商店专有字段
																// orderid前缀
	public static final String JSON_SUBJECT = "subject";
	public static final String JSON_VERCODE = "vercode";
	public static final String JSON_APK_NAME = "apk_name";
	public static final String JSON_APK_SIZE = "apk_size";
	/**
	 * 2015/03/27
	 */
	public static final String JSON_WXPAYINFO = "wxPayInfo";
	public static final String JSON_LLPAYINFO = "orderid";

	/**
	 * 悬浮窗推广 ———已下载次数；
	 */
	public static final String JSON_DOWNLAND_NUM = "downloaded";
	/**
	 * 是否支持高速下载（0不支持 ，1支持）
	 */
	public static final String JSON_IS_SUPPORT_HDOWN = "hdown_flag";
	/**
	 * 高速下载apk名称
	 */
	public static final String JSON_HDOWN_APKNAME = "hdown_apkname";
	/**
	 * 游戏下载界面描述 
	 */
	public static final String JSON_HDOWN_DESC = "dldesc";

	public static final String JSON_DOWNLOAD_NUM = "downloadNum";

	public static final String DB_AMOUNT_OPERATOR = "operator";
	public static final String DB_AMOUNT_CHANNEL = "channel";
	public static final String DB_AMOUNT_ISVALID = "isvalid";
	public static final String DB_AMOUNT_VALIDSTART = "valid_start";
	public static final String DB_AMOUNT_VALIDEND = "valid_end";
	public static final String DB_AMOUNT_AMOUNT = "amount";
	public static final String DB_AMOUNT_CREATE_TIME = "create_time";
	public static final String DB_AMOUNT_MODIFY_TIME = "modify_time";
	public static final String DB_AMOUNT_HEAD_DEST = "head_dest";
	public static final String DB_AMOUNT_SORT = "sort";
	public static final String DB_AMOUNT_DAYMAX = "dayMax";

	public static final String JSON_MONEY = "money";

	// Columns of order_info
	public static final String DB_ORDER_INFO_ORDERID = "orderid";
	public static final String DB_ORDER_INFO_DESC = "desc";
	public static final String DB_ORDER_INFO_CHANNEL = "channel";
	public static final String DB_ORDER_INFO_ITEMID = "itemid";
	public static final String DB_ORDER_INFO_ITEMNAME = "itemname";
	public static final String DB_ORDER_INFO_STATUS = "status";
	public static final String DB_ORDER_INFO_PRICE = "price";
	public static final String DB_ORDER_INFO_ORDER_TIME = "order_time";
	public static final String DB_ORDER_INFO_CREATE_TIME = "create_time";
	public static final String DB_ORDER_INFO_MODIFY_TIME = "modify_time";

	// Status of order.
	public static final String ORDER_STATUS_UNKNOWN = "0";
	public static final String ORDER_STATUS_ORDER_DUPLICATE = "4";
	public static final String ORDER_STATUS_DEALING = "1";
	public static final String ORDER_STATUS_SUCCESS = "3";
	public static final String ORDER_STATUS_FAIL = "2";
	public static final String ORDER_STATUS_DELIVERY = "5";
	// 用户取消订单（暂时只针对支付宝使用此状态值）
	public static final String ORDER_STATUS_CANCEL = "6";

	public static final int ORDER_STATUS_FAIL_INT = 2;

	// 每分钟内仅限于有1笔支付宝未支付订单(版本1.2内加的限制)
	public static final int LIMIT_TIME_ALIPAY = 60 * 1000;

	// Intent filter of SMS.
	public static final String INTENT_FILTER_SMS_SEND = "com.yifu.sms.send";
	public static final String INTENT_FILTER_SMS_MULTI_SEND = "com.yifu.sms.multi.send";
	public static final String INTENT_FILTER_SMS_DELIVERY = "com.yifu.sms.delivery";

	// MNC String
	// public static final String CHINA_MOBILE = "cm";
	// public static final String CHINA_UNICOM = "cu";
	// public static final String CHINA_TELCOM = "ct";
	// public static final String CHINA_OTHER = "other";

	// 在能识别出手机号的情况下的（联通）联通华建的次数日限
	public static final int MAX_DAY_LIMITS_CU_CUHJ = 5;
	// 在能识别出手机号的情况下的（联通）盛峰短信的次数日限
	public static final int MAX_DAY_LIMITS_CU_SFDX = 15;

	// tags
	public static final int TAGS_UPLOAD_ORDER_INFO = 3;
	public static final int TAGS_QUERY_ORDER_STATUS = 4;
	// SDK1.2系列之后，查询支付渠道tag2变为tag8
	public static final int TAGS_QUERY_AMOUNT = 8;
	// public static final int TAGS_QUERY_ITEM_LIST = 1;
	public static final int TAGS_YEEPAY_PAYMENT = 5;
	// public static final int TAGS_COUNT_ONLINE_TIME = 6;
	// public static final int TAGS_REGISTER_DEVICE = 100;
	public static final int TAGS_QUERY_MOBILE_OWNERSHIP = 7;
	public static final int TAGS_QUERY_CHANNEL_MOBILE = 43;
	public static final int TAGS_INIT_CROSS_RECOMMEND_GAME = 45;

	/**
	 * 2015/10/21 获取地理位置
	 */
	public static final int TAGS_INIT_LOCATION = 220;

	/**
	 * 悬浮窗推荐游戏列表
	 */
	public static final int TAGS_INIT_CROSS_RECOMMEND_GAME_LIST = 46;
	public static final int TAGS_STATISTICS_CROSS_RECOMMEND_GAME = 101;

	public static final int TAGS_QUERY_PROPS_BY_GAMEID = 141;

	public static final int TAGS_QUERY_INIT_PAYCHANNEL_FLAG = 144;
	public static final int TAGS_QUERY_AMOUNT_145 = 145;
	public static final int TAGS_QUERY_RECHARGECARDLIST = 146;
	public static final int TAGS_THIRDPAY_SUPPORTLIST = 147;
	public static final int TAGS_QUERY_TENCENTMM_PARAMS = 148;
	public static final int TAGS_GET_WX_PAYINFO_ENCRYPT_149 = 149;
	public static final int TAGS_GET_PAY_CREATEORDER = 150;
	public static final int TAGS_COMMIT_VERIFY = 151;
	
//	public static final int TAGS_CHECK_LLPAY_151 = 151;

	// 2014-09-15 begin
	public static final int TAGS_THIRDPAY_BENEFIT = 160;

	public static final String JSON_ERROR_CODE = "errorcode";
	public static final String JSON_ERROR_MESSAGE = "errormsg";
	public static final String JSON_ACCEPT_TIME = "accept_time";
	public static final String JSON_SDK_LAYOUT = "sdk_layout";

	public static final String SP_NONCONSUMERLISTQUERY = "query_nonconsumer";
	public static final String SP_LATEST_REFRESH_CHANNEL_AMOUNT = "refresh_paychannel_amount";

	// JSON参数
	public static final String JSON_PARAM_URLLIST = "pushList";
	public static final String JSON_PARAM_URLID = "pushId";
	public static final String JSON_PARAM_ICON = "pushIcon";
	public static final String JSON_PARAM_TITLE = "pushTitle";
	public static final String JSON_PARAM_CONTENT = "pushContent";
	public static final String JSON_PARAM_URL = "pushUrl";
	public static final String JSON_PARAM_TYPE = "pushType";
	public static final String JSON_PARAM_ACTION = "pushAction";

	public static final String PUSH_INDEX = "pushIndex";
	// 此版本使用的url标识
	public static final String JSON_DATA_URLID = "yifusdk_100";

	// Push生成的channel id
	public static final String PARAM_PUSH_CHANNEL_ID = "push_channel_id";
	// Push生成的user id
	public static final String PARAM_PUSH_USER_ID = "push_user_id";

	public static final String PARAM_ON_CREATE = "onCreate";
	public static final String PARAM_ON_START = "onStart";
	public static final String PARAM_ON_RESUME = "onResume";
	public static final String PARAM_ON_PAUSE = "onPause";
	public static final String PARAM_ON_STOP = "onStop";

	// 交叉推荐退出页面（样式1：图片放在文字的左边）
	public static final int RECOMMEND_EXIT_STYLE_HORIZONTAL = 0;
	// 交叉推荐退出页面（样式2：图片放在文字的上方）
	public static final int RECOMMEND_EXIT_STYLE_VERTICAL = 1;

	// 标识是否有重点推荐的游戏（0表示没有重点推荐的游戏，1表示有重点推荐的游戏）
	public static final int YF_GAME_COMMON_RECOMMEND = 0;
	public static final int YF_GAME_POINTER_RECOMMEND = 1;

	// CP退出游戏时，是否显示游戏资源广告位
	public static final int YF_FLAG_SHOW_EXIT_DATA_NO = 0;
	public static final int YF_FLAG_SHOW_EXIT_DATA_YES = 1;

	// 交叉推荐用到的参数名称
	public static final String JSON_MAIN_PAGE_TYPE = "main_page_type";// 悬浮窗显示类型
																		// 2014/08/22
	public static final String JSON_MAIN_PAGE_DATA = "main_page_data";
	public static final String JSON_EXIT_PAGE_DATA = "exit_page_data";
	public static final String JSON_FLAG_SHOW_EXIT_DATA = "flag_show_exit_data";

	public static final String JSON_FLAG_LOTTERY_DRAW = "luck_flag";// 是否能抽奖
																	// 2014/08/27
	public static final String JSON_FLAG_DOWNSHOW_FLAG = "downshowflag";// 是否显示下载页面
																		// 2015/01/26
	public static final String JSON_FLAG_AN_LOTTERY = "an_lottery";// 是否是 抽奖公告
																	// 2015/03/06
	public static final String JSON_FLAG_BAN_LOTTERY = "ban_lottery";// 是否是
																		// 固定或飘窗抽奖广告
																		// 2015/03/06
	public static final String JSON_FLAG_ADS_LOTTERY = "adslottery";// 是否是
																	// 插播抽奖广告
																	// 2015/03/06

	public static final String JSON_GAME_ID = "game_id";
	public static final String JSON_GAME_NAME = "game_name";
	public static final String JSON_GAME_ICON_URL = "game_icon_url";
	public static final String JSON_GAME_DOWNLOAD_URL = "game_download_url";
	public static final String JSON_GAME_DOWN_OPEN_TYPE = "down_open_type";
	public static final String JSON_FLAG_POINT_RECOMMEND = "flag_point_recommend";
	public static final String JSON_GAMES_POINT_RECOMMEND = "games_point_recommend_list";
	public static final String JSON_MORE_GAME_URL = "more_game_url";

	public static final String JSON_STYLE_INDEX = "style_index";
	public static final String JSON_GAME_IMG_URL = "game_img_url";
	public static final String JSON_GAME_TITLE = "game_title";
	public static final String JSON_GAME_CONTENT = "game_content";

	public static final String JSON_HDOWN_URL = "hdown_url";
	public static final String JSON_HDOWN_GAMEID = "hdown_gameid";
	public static final String JSON_HDOWN_PKNAME = "hdown_pkname";
	public static final String JSON_LOGO_ID = "logo_id";
	public static final String JSON_LOGO_URL = "logo_url";

	public static final String JSON_ADS_DATA = "ads_data";
	public static final String JSON_BANNERADS = "bannerads";
	public static final String JSON_INSERTADS = "insertads";
	public static final String JSON_AN_DATA = "an_data";
	public static final String JSON_ADSTIME = "adstime";
	public static final String JSON_BAN_STYLE = "ban_style";
	public static final String JSON_BAN_TIME = "ban_time";
	public static final String JSON_BAN_ICON = "ban_icon";
	public static final String JSON_BAN_GID = "ban_gid";
	public static final String JSON_BAN_GNAME = "ban_gname";
	public static final String JSON_BAN_APKNAME = "ban_apkname";
	public static final String JSON_BAN_DL = "ban_dl";
	public static final String JSON_BAN_APKSIZE = "ban_apksize";
	public static final String JSON_HDOWN_FLAG = "hdown_flag";
	public static final String JSON_ADSID = "adsid";
	public static final String JSON_ADSICON = "adsicon";
	public static final String JSON_ADS_GID = "adsgid";
	public static final String JSON_ADS_GNAME = "adsgname";
	public static final String JSON_ADS_APKNAME = "adsapkname";
	public static final String JSON_ADS_DL = "adsdl";
	public static final String JSON_ADS_APKSIZE = "adsapksize";
	public static final String JSON_ADS_AN_TYPE = "an_type";
	public static final String JSON_ADS_AN_TEXT = "an_text";
	public static final String JSON_ADS_AN_IMG = "an_img";
	public static final String JSON_AN_GID = "an_gid";
	public static final String JSON_AN_GNAME = "an_gname";
	public static final String JSON_AN_APKNAME = "an_apkname";
	public static final String JSON_AN_GDL = "an_gdl";
	public static final String JSON_AN_APKSIZE = "an_apksize";

	// 交叉推荐统计用到的参数名称
	public static final String JSON_PARAM_STATISTICS_ID = "statistics_id";
	public static final String JSON_PARAM_STATISTICS_DESC = "statistics_desc";
	public static final String JSON_PARAM_STATISTICS_VALUE = "statistics_value";
	public static final String JSON_PARAM_STAT_TYPE = "stat_type";
	// 交叉推广游戏显示
	public static final String STATISTICS_RECOMMEND_GAME_SHOW = "statistics_recommend_game_show";
	// 重点游戏显示
	public static final String STATISTICS_SINGLE_POINTER_GAME_SHOW = "statistics_single_pointer_game_show";
	public static final String STATISTICS_SINGLE_GAME_DOWNLOAD = "statistics_single_game_download";
	// public static final String STATISTICS_POINTER_RECOMMEND_GAME =
	// "statistics_pointer_recommend_game";
	public static final int YF_GAME_FROM_WINDOW = 1;
	public static final int YF_GAME_FROM_DIALOG = 2;
	// 悬浮窗2展示/点击/下载
	public static final String STATISTICS_SUSPEND2_SHOW = "statistics_suspend2_show";
	public static final String STATISTICS_SUSPEND2_PV = "statistics_suspend2_pv";
	public static final String STATISTICS_SUSPEND2_DOWNLOAD = "statistics_suspend2_download";
	public static final int SUSPEND2_TYPE = 1;
	// 热门推荐
	public static final String STATISTICS_BOUTIQUE_SHOW = "statistics_boutique_show";
	public static final String STATISTICS_BOUTIQUE_PV = "statistics_boutique_pv";
	public static final String STATISTICS_BOUTIQUE_DOWNLOAD = "statistics_boutique_download";
	public static final int BOUTIQUE_TYPE = 1;
	// 广告展示/点击/下载
	public static final String STATISTICS_ADS_SHOW = "ads_show";
	public static final String STATISTICS_ADS_PV = "ads_pv";
	public static final String STATISTICS_ADS_DOWNLOAD = "ads_download";
	public static final int ADS_SUSPEND_TYPE = 1;
	public static final int ADS_FIXED_TYPE = 2;
	// 公告展示/点击/下载
	public static final String STATISTICS_NOTICE_SHOW = "notice_show";
	public static final String STATISTICS_NOTICE_PV = "notice_pv";
	public static final String STATISTICS_NOTICE_DOWNLOAD = "notice_download";
	public static final int NOTICE_TYPE = 1;
	// 插屏展示/点击/下载
	public static final String STATISTICS_INADS_SHOW = "inads_show";
	public static final String STATISTICS_INADS_PV = "inads_pv";
	public static final String STATISTICS_INADS_DOWNLOAD = "inads_download";
	public static final int INADS_TYPE = 1;
	// 高速下载
	public static final String STATISTICS_HIGHT_DOWNLOAD = "hdown_pv";
	public static final int HDOWN_TYPE = 0;
	// 抽奖展示/点击
	public static final String STATISTICS_LOTTERY_SHOW = "lottery_show";
	public static final String STATISTICS_LOTTERY_PV = "lottery_pv";
	public static final int LOTTERY_TYPE = 1;
	// 统计奖项
	public static final String STATISTICS_LOTTERY_ID = "lottery_id";
	// 下载完成次数
	public static final String STATISTICS_DOWNLOAD_NUM = "download_num";
	// 安装次数
	public static final String STATISTICS_INSTALL_NUM = "install_num";
	// 游戏点击
	public static final String STATISTICS_AGAME_PV = "agame_pv";
	// 退出
	public static final String STATISTICS_EXIT_PV = "exit_pv";
	// 悬浮按钮点击
	// public static final String STATISTICS_SUSPEN_PV = "suspen_pv";

	// 统计类型
	public static int DL_FIXED_ADS_TYPE = 0;// 固定banner广告
	public static int DL_SUSPEND_ADS_TYPE = 1;// 飘窗广告
	public static int DL_INADS_TYPE = 2;// 插播广告
	public static int DL_NOTICE_TYPE = 3;// 公告
	public static int DL_SUSPENSION_WIN_TYPE = 4;// 悬浮窗WINDOWS
	public static int DL_SUSPENSION_DIALOG_TYPE = 5;
	public static int DL_SUSPENSION2_WIN_TYPE = 6;// 悬浮窗2WINDOWS
	public static int DL_BOUTIQUE_WIN_TYPE = 7;// 悬浮窗2WINDOWS

	public static final String PARAM_YF_SINGLE_GV = "gv";
	public static final String PARAM_YF_SINGLE_SV = "sv";
	public static final String PARAM_YF_SINGLE_FR = "fr";
	public static final String YF_SINGLE_FR = "game_singlesdk";

	public static final String YF_SINGLE_IMAGE_CACHE = "yifu/single/cache";
	public static final String YF_SINGLE_DOWNLOAD_PATH = "/single/";

	public static final String YF_APPLICATION_ARCHIVE = "application/vnd.android.package-archive";

	public static final String YF_FILE_SUFFIX = ".apk";
	public static final String YF_PARAM_PAID_MID = ".";
	public static final String YF_PARAM_ORDER_MID = "#";
	public static final String YF_PARAM_TCDC_MID = "_";
	public static final String YF_PARAM__MDO_MID = ",";
	public static final String YF_PARAM_TCLT_MID = "-";

	// 测试
	public static final int YF_CM_PAID_MAX_DAY_UDID = 10000;//100
	// public static final int YF_CM_PAID_MAX_DAY_UDID = 10;
	public static final int YF_CU_PAID_MAX_DAY_UDID = 10000;//100
	// public static final int YF_CU_PAID_MAX_DAY_UDID = 10;
	public static final int YF_CT_PAID_MAX_DAY_UDID = 10000;//100
	// public static final int YF_CT_PAID_MAX_DAY_UDID = 10;
	// 移动MM最大日限
	public static final int YF_CM_MM_MAX_DAY = 20000;//200
	// 移动基地最大日限
	public static final int YF_CM_GBSDK_MAX_DAY = 20000;//200
	// 移动MDO最大日限
	public static final int YF_CM_MDO_MAX_DAY = 20000;//200
	// 联通沃商店sdk最大日限
	public static final int YF_CU_WOSTORE_MAX_DAY = 10000;//100
	// 电信爱游戏sdk最大日限
	public static final int YF_CT_EGAME_MAX_DAY = 100000;//1000

	public static final String YF_CU_WO_READER_CP = "14011002";
	public static final String YF_CU_WO_KEY = "d13dc99cc1eca5cbb1fddfbc9e81d01e";
	public static final String YF_CU_SF_TEN = "yf_cu_sf_ten";
	// 盛峰联通10元支付有2个小时的限制
	public static final long YF_CU_TEN_TIME_LIMIT = 7200000;

	// 品宣模块的热门推荐的默认地址
	public static final String YF_MORE_GAME_URL = "http://m.yifu.com/agame";

	// 默认的交叉推荐保存地址
	public static final String YF_SHARED_PINXUAN_DATA = "yf_shared_pinxuan_data";


	// 棋牌类改变领奖状态的记录索引
	public static final String YF_COMPETITION_CHESS_INDEX_AWARD = "yf_competition_chess_index_award";


	public static final String JSON_SESSIONID = "sessionid";
	public static final String JSON_USERID = "userid";
	public static final String JSON_USERIDS = "userids";
	public static final String JSON_NEWPWD = "newpwd";
	public static final String JSON_OLDPWD = "oldpwd";
	public static final String JSON_VERIFYCODE = "verifycode";
	public static final String JSON_LAST_ACCEPT_TIME = "last_accept_time"; // 上次验证码下行的时间
	public static final String JSON_NEWPHONENUMBER = "newphonenumber";
	public static final String JSON_RANK_NEW = "rank_new";
	public static final String JSON_COMPETITION_ID = "competition_id"; // 争霸赛活动的ID
	public static final String JSON_COMPETITION_EVENT_ID = "competition_event_id"; // 争霸赛活动的场次ID
	public static final String JSON_RECORD_VAL = "record_val"; // 最好成绩
	public static final String JSON_RECORD_SHOW = "record_show"; // 此字段针对那些排名比较模糊的规则，默认的
																	// record_val=record_show
																	// 一致（此字段使用率不会很高）
	public static final String JSON_DATE = "date";
	public static final String JSON_RANDOMCODE = "randomcode";
	public static final String JSON_NICKNAME = "nickname";
	public static final String JSON_NICKNAMES = "nicknames";
	public static final String JSON_FLAG = "flag";
	public static final String JSON_USERNAME = "username";
	public static final String JSON_PASSWORD = "password";
	// end

	// dk competition
	public static final String YF_COMPETITION_NONCHESS_RANK_BEST_NUMBER = "yf_competition_nonchess_rank_best_number"; // 存数上次排名字段
	public static final String YF_COMPETITION_NONCHESS_BESTS_VALUE = "yf_competition_nonchess_bests_value"; // 存储最好成绩字段
	public static final String YF_COMPETION_RANK_RULE = "yf_competion_rank_rule"; // 成绩排名规则
																					// 1表示正序，2表示倒序
	public static final String YF_COMPETITION_NONCHESS_BESTS_DESC = "yf_competition_nonchess_bests_desc";
	public static final String YF_COMPETITION_RECENT_LOGIN_USER = "yf_competition_recent_login_user";
	public static final String YF_COMPETITION_DATA = "yf_competition_data";
	public static final String YF_COMPETITION_HOME_TIP_FLAG_SHOW = "yf_competition_home_tip_flag_show"; // 是否显示首页弹窗

	public static final int NICKNAME_LENGTH_MAX = 12;

	public static final String INTENT_FILTER_SMS_SEND_FOR_COMPLETE_INFO = "com.yifu.sms.send.for.complete.info";
	public static final String INTENT_FILTER_SMS_STATE_UNKNOWN = "com.yifu.sms.state.unknown";

	// Intent filter of chess competition award state
	public static final String INTENT_FILTER_CHANGE_CHESS_AWARD_STATE = "com.yifu.competition.chess.award.change";
	// Intent filter of nonchess update nickname
	public static final String INTENT_FILTER_NONCHESS_UPDATE_NICKNAME = "com.yifu.competition.nonchess.update.nickname";

	public static final String INTENT_DATA_YF_DOWN_LOAD_GAME_DATA = "intent_data_yf_down_load_game_data";
	public static final String INTENT_DATA_YF_DOWN_LOAD_GAME_TYPE = "intent_data_yf_down_load_game_type";
	public static final String INTENT_DATA_YF_DOWN_LOAD_GAME_FLAG = "intent_data_yf_down_load_game_flag";

	public static final String JSON_PACKAGENAME = "packagename";

	// 调试模式
	// public static final String YFSDK_DEBUG = "yfSDK_debug";
	public static final String YFSDK_APPID = "yfsdk_appid";
	public static final String YFSDK_APPKEY = "yfsdk_appkey";
	public static final String YFSDK_APPSECRET = "yfsdk_appsecret";
	public static final String YFSDK_CHANNEL = "yfsdk_rec_channel";

	public static final String YFSDK_BUSINESS_CHANNEL = "yfsdk_business_channel";
	public static final String APP_ID_BAIDU = "a305af1f56";
	// Baidu 设置Session超时的秒数，单位为秒
	// public static final int TIMEOUT_SESSION_BAIDU = 100;

	// 品宣初始化统计项
	public static final String YF_BD_STAT_INIT = "10001";
	// 品宣悬浮窗按钮的点击统计项
	public static final String YF_BD_STAT_SUSPEN_BTN_CLICK = "10002";
	// 品宣推荐的游戏的点击统计项
	public static final String YF_BD_STAT_AGAME_CLICK = "10003";
	// 品宣推荐游戏的下载统计项
	public static final String YF_BD_STAT_AGAME_DOWNLOAD = "10004";
	// 热门推荐按钮的点击统计项
	public static final String YF_BD_STAT_POINTER_BTN_CLICK = "10005";
	// 离开游戏按钮的点击统计项
	public static final String YF_BD_STAT_EXIT_BTN_CLICK = "10006";
	// banner广告点击统计项
	public static final String YF_BD_STAT_BANNER_CLICK = "10007";
	// 插屏广告点击统计项
	public static final String YF_BD_STAT_INTADS_CLICK = "10008";
	// 公告点击统计项
	public static final String YF_BD_STAT_NOTICE_CLICK = "10009";
	// 高速下载统计项
	public static final String YF_BD_GAME_HIGH_DOWNLOAD = "10010";


	// 1.4.0新版渠道配置文件key、设置
	public static final String BDG_CM_MAX = "cm_max";
	public static final String BDG_CU_MAX = "cu_max";
	public static final String BDG_CT_MAX = "ct_max";

	public static final String JSON_PROPS = "props";
	public static final String JSON_MM = "mm";
	public static final String JSON_PROPSID = "propsid";
	public static final String GAME_SUPPORT_MM = "game_support_mm";

	public static final String SDK_VIEWID = "sdk_view_id";


	public static final int YF_SUPPORT_GBSDK_YES = 1;
	public static final int YF_SUPPORT_GBSDK_NO = 0;
	public static final int YF_SUPPORT_MDO_YES = 1;
	public static final int YF_SUPPORT_MDO_NO = 0;
	public static final int YF_SUPPORT_MM_YES = 1;
	public static final int YF_SUPPORT_MM_NO = 0;

	public static final String YF_SEQUENCE_OF_MOBILE_BILLING = "yf_sequence_of_mobile_billing";

	public static final String JSON_DMAX = "dmax";
	public static final String JSON_MM_DMAX = "mm_dmax";

	public static final String JSON_PAYCHANNEL_GB_SDK = "gb_sdk";
	public static final String JSON_PAYCHANNEL_GB_SDK_DAYMAX = "gb_sdk_dmax";
	public static final String JSON_PAY_ORDER_BY_MOBILE = "payorder";

	// 品宣广告、公告
	public static final String SHARE_ADS_IS_SHOW = "isShow";
	public static final String SHARE_ADS_IS_VIEW = "isView";
	public static final String SHARE_ADS_IS_INSERT_VIEW = "isInsertView";
	public static final String SHARE_ADS_IS_BANSHOW_FINISH = "isBanShowFinish";
	public static final String SHARE_ADS_IS_BANVIEW_SHOW = "isBannerViewShow";
//	public static final String SHARE_ADS_IS_PAY_SHOW = "isPayViewShow";
	
	// 上一次使用的第三方支付方式
	public static final String SHARE_LAST_THIRDPAY = "share_last_thirdpay";
	public static final String SHARE_LAST_THIRDPAY_SUCCESS = "share_last_thirdpay_success";
	public static final String SHARE_THIRDPAY_TIMES = "share_thirdpay_itmes";

	//支付宝
		public static final String CHANNEL_ALIPAY = "alipay";
		public static final String CHANNEL_WEIXIN = "tencentmm";//微信/
		
	// 用游戏大厅下载广播
	public static final String YF_GAMESEARCH_SPEEDDOWNLOAD_BROADCAST = "com.yifu.gamesearch.speeddownload.REQUESTRECEIVER";

	public static final String YF_RECOMMENDGAMEDATA_LIMIT = "yf_recommendgamedata_limit";
	/**
	 * 支付渠道列表的版本 
	 */
	public static final String SP_PAYCHANNEL_LIST_VERSION = "paychannel_configuration_version";
	/**
	 * 第三方支付列表开关的版本
	 */
	public static final String SP_THIRDPAY_LIST_VERSION = "thirdpay_configuration_checkid";
	
	public static String LocalProvince = "yf";
	public static String ipAdress = "0.0.0.0";


}
