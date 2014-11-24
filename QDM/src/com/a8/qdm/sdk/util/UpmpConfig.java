package com.a8.qdm.sdk.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 银联配置类
 * 
 * @author lund
 *
 */
public class UpmpConfig {

	// 版本号
	public static String VERSION = "1.0.0";

	// 编码方式
	public static String CHARSET = "UTF-8";

	// 交易网址
	public static String TRADE_URL = "https://mgate.unionpay.com/gateway/merchant/trade";

	// 查询网址
	public static String QUERY_URL = "https://mgate.unionpay.com/gateway/merchant/query";

	// 商户代码
	public static String MER_ID = "898111148160325";

	// 通知URL
	public static String MER_BACK_END_URL = "http://220.231.188.100:82/QDM/upmpSdkNotify";

	// 前台通知URL
	public static String MER_FRONT_END_URL;

	// 返回URL
	public static String MER_FRONT_RETURN_URL;

	// 加密方式
	public static String SIGN_TYPE = "MD5";

	// 交易类型01：消费|02：预授权
	public static String TRANS_TYPE = "01";

	// 交易开始日期时间
	public static String ORDER_TIME = new SimpleDateFormat("yyyyMMddHHmmss")
			.format(new Date());

	// 商城密匙，需要和银联商户网站上配置的一样
	public static String SECURITY_KEY = "NkRUGoy9yBVIrZsAwJT2TzCgiuNx5mFi";

	// 成功应答码
	public static String RESPONSE_CODE_SUCCESS = "00";

	// 签名
	public static String SIGNATURE = "signature";

	// 签名方法
	public static String SIGN_METHOD = "signMethod";

	// 应答码
	public static String RESPONSE_CODE = "respCode";

	// 应答信息
	public static String RESPONSE_MSG = "respMsg";
}
