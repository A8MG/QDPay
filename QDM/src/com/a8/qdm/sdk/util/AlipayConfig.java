package com.a8.qdm.sdk.util;

/**
 * 支付宝配置
 * 
 * @author Lund
 * 
 */
public class AlipayConfig {

	/**
	 * 卖家支付宝帐户
	 */
	public static String seller_account_name = "pay@a8.com";

	/**
	 * 合作身份者ID，以2088开头由16位纯数字组成的字符串
	 */
	public static String partner = "2088011129356338";

	/**
	 * 交易安全检验码，由数字和字母组成的32位字符串
	 */
	public static String key = "zauzlpl9c8u3pu0mbeouldar28e58560";

	/**
	 * 支付宝网关地址
	 */
	public static String alipay_gateway_new = "http://wappaygw.alipay.com/service/rest.htm?";

	/**
	 * 支付宝消息验证地址
	 */
	public static String https_verify_url = "https://mapi.alipay.com/gateway.do?service=notify_verify&";

	/**
	 * 服务器异步通知页面路径
	 */
	public static String wap_notify_url = "http://220.231.188.100:82/QDM/aliWapNotify";

	/**
	 * 服务器异步通知页面路径
	 */
	public static String sdk_notify_url = "http://220.231.188.100:82/QDM/aliSdkNotify";

	/**
	 * 页面跳转同步通知页面路径
	 */
	public static String call_back_url = "http://220.231.188.100:82/QDM/aliCallback";

	/**
	 * 字符编码格式 目前支持 utf-8
	 */
	public static String input_charset = "utf-8";

	/**
	 * WAP签名方式，选择项：0001(RSA)、MD5
	 */
	public static String wap_sign_type = "MD5";

	/**
	 * SDK签名方式，选择项：RSA
	 */
	public static String sdk_sign_type = "RSA";

	/**
	 * 返回格式
	 */
	public static String format = "xml";

	/**
	 * 版本号
	 */
	public static String v = "2.0";

	/**
	 * 订单名称
	 */
	public static String subject = "轻点支付";

	/**
	 * 支付宝的私钥
	 */
	public static String private_key = "MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBAK91HdJY6m+rk7WuP1eqXmU9Ebqnqc8l+3qj3Pq+aP52iVmA8Lku3ZBE8qFHKdmOalgaRi7DRHt8Fj1dpUCXmUDseCmCNli0A7agH/Q+9v8JVktJ3eonlpFggp6JP+b8BFY75dKW4QZnFNetQEP88VT3jjCpjlmbE6rPWxt5CzSnAgMBAAECgYEAhc4f5vlOF8lWRRhUUAuqHzS6X4lbc5E4vidW75XkRiKpzisJ6X93En2yNxIpNTNpoDSxTxo1EKLp5vSSgLbb9Ka8rkV5WSTbgH6RSCgkveK0XXPidBmuXCxWoierY7RpeSnbUeHyXbof/6zZgeO1HmJlnmC6xqKUxBbcD38/gqECQQDaQeo3GaQgnKCoQaXPkJcag2TXXQZGtxYIIMmBjvKmB6IOLmWgZk6QUavqdaaGYuY6miQt3+UCBtH+Lyrj1mT3AkEAzcx62l2DRib3BRRd8q5obW6PwjrxL2C5hPtR7AIiLM8qOiPvQ4vFfKen4GQ1kcU0CR1UTyyfVyLjpbBdtWyx0QJBAMVDaA4O9cGn7qjEVLnKZFfJQ/5GjKKjXZ1RDO81JOic44JPuviDHyTfPDN3zF+PyahTorl3sALp6SgEt44fOzcCQQCG8yJQVOfDNcPfudZNT968e7abOAl19uhQcnjq2Q9N8N8Q1wWLdnke39WuyCd0misgGvJxJKKsAf3jZX5dfJQxAkEAk6PzoCJ/0T314mKPEABcENwrok/OK1OZqjPJaAFPHax0ThE10r4FNgaXDl11Gn0BJqe2jf3R3UD8a1JtYbdWtA==";

	/**
	 * 支付宝的公钥
	 */
	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
}
