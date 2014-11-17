package com.a8.qdm.sdk.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import com.a8.qdm.sdk.util.sign.MD5;
import com.a8.qdm.sdk.util.sign.RSA;

/**
 * AlipayNotify
 * 
 * @author Lund
 * 
 */
public class AlipayNotify {

	/**
	 * 验证消息是否是支付宝发出的合法消息，验证callback
	 * 
	 * @param params
	 *            通知返回来的参数数组
	 * @return 验证结果
	 */
	public static boolean verifyReturn(Map<String, String> params) {
		String sign = "";

		// 获取返回时的签名验证结果
		if (params.get("sign") != null) {
			sign = params.get("sign");
		}

		// 验证签名
		boolean isSign = getSignVeryfy(params, sign, true, true);

		// 判断isSign是否为true
		if (isSign) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 验证消息是否是支付宝发出的合法消息，验证服务器异步通知
	 * 
	 * @param params
	 *            通知返回来的参数数组
	 * @param isWap
	 *            支付类型true：网页|false：SDK
	 * @return 验证结果
	 */
	public static boolean verifyNotify(Map<String, String> params, boolean isWap)
			throws Exception {

		// 获取是否是支付宝服务器发来的请求的验证结果
		String responseTxt = "true";
		String notify_id = "";
		boolean isSign = false;
		try {
			if (isWap) {
				// 网页支付，XML解析notify_data数据，获取notify_id
				Document document = DocumentHelper.parseText(params
						.get("notify_data"));
				notify_id = document.selectSingleNode("//notify/notify_id")
						.getText();
			} else {
				// SDK支付方式
				notify_id = params.get("notify_id");
			}
			responseTxt = verifyResponse(notify_id);
		} catch (Exception e) {
			responseTxt = e.toString();
		}

		// 获取返回时的签名验证结果
		String sign = "";
		if (params.get("sign") != null) {
			sign = params.get("sign");
		}

		// 判断支付宝支付方式true：网页|false：SDK
		if (isWap) {
			isSign = getSignVeryfy(params, sign, false, true);
		} else {
			isSign = getSignVeryfy(params, sign, true, false);
		}

		// 判断responsetTxt是否为true，isSign是否为true
		if (isSign && responseTxt.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 解密
	 * 
	 * @param inputPara
	 *            要解密数据
	 * @return 解密后结果
	 */
	public static Map<String, String> decrypt(Map<String, String> inputPara)
			throws Exception {
		inputPara.put("notify_data", RSA.decrypt(inputPara.get("notify_data"),
				AlipayConfig.private_key, AlipayConfig.input_charset));

		return inputPara;
	}

	/**
	 * 根据反馈回来的信息，生成签名结果
	 * 
	 * @param Params
	 *            通知返回来的参数数组
	 * @param sign
	 *            比对的签名结果
	 * @param isSort
	 *            是否排序
	 * @param isWap
	 *            支付类型true：网页|false：SDK
	 * @return 生成的签名结果
	 */
	private static boolean getSignVeryfy(Map<String, String> params,
			String sign, boolean isSort, boolean isWap) {

		// 过滤空值、sign与sign_type参数
		Map<String, String> sParaNew = AlipayCore.paraFilter(params);

		// 获取待签名字符串
		String preSignStr = "";
		if (isSort) {
			preSignStr = AlipayCore.createLinkString(sParaNew);
		} else {
			preSignStr = AlipayCore.createLinkStringNoSort(sParaNew);
		}

		// 获得签名验证结果
		boolean isSign = false;

		// 判断支付宝支付方式true：网页|false：SDK
		if (isWap) {
			if (AlipayConfig.wap_sign_type.equals("MD5")) {
				isSign = MD5.verify(preSignStr, sign, AlipayConfig.key,
						AlipayConfig.input_charset);
			} else if (AlipayConfig.wap_sign_type.equals("0001")) {
				isSign = RSA
						.verify(preSignStr, sign, AlipayConfig.ali_public_key,
								AlipayConfig.input_charset);
			}
		} else {
			isSign = RSA.verify(preSignStr, sign, AlipayConfig.ali_public_key,
					AlipayConfig.input_charset);
		}

		return isSign;
	}

	/**
	 * 获取远程服务器ATN结果,验证返回URL
	 * 
	 * @param notify_id
	 *            通知校验ID
	 * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
	 *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	 */
	private static String verifyResponse(String notify_id) {

		// 获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求
		String partner = AlipayConfig.partner;
		String veryfy_url = AlipayConfig.https_verify_url + "partner="
				+ partner + "&notify_id=" + notify_id;

		return checkUrl(veryfy_url);
	}

	/**
	 * 获取远程服务器ATN结果
	 * 
	 * @param urlvalue
	 *            指定URL路径地址
	 * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
	 *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	 */
	private static String checkUrl(String urlvalue) {
		String inputLine = "";

		try {
			URL url = new URL(urlvalue);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));
			inputLine = in.readLine().toString();
		} catch (Exception e) {
			e.printStackTrace();
			inputLine = "";
		}

		return inputLine;
	}
}
