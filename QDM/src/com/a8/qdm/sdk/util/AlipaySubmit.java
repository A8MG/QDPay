package com.a8.qdm.sdk.util;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import com.a8.qdm.sdk.util.httpClient.HttpProtocolHandler;
import com.a8.qdm.sdk.util.httpClient.HttpRequest;
import com.a8.qdm.sdk.util.httpClient.HttpResponse;
import com.a8.qdm.sdk.util.httpClient.HttpResultType;
import com.a8.qdm.sdk.util.sign.MD5;
import com.a8.qdm.sdk.util.sign.RSA;

/**
 * AlipaySubmit
 * 
 * @author Lund
 * 
 */
public class AlipaySubmit {

	/**
	 * 生成签名结果
	 * 
	 * @param sPara
	 *            要签名的数组
	 * @param isWap
	 *            支付类型true：网页|false：SDK
	 * @return 签名结果字符串
	 */
	public static String buildRequestMysign(Map<String, String> sPara,
			boolean isWap) {

		// 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		String prestr = AlipayCore.createLinkString(sPara);
		String mysign = "";

		// 判断支付宝支付方式true：网页|false：SDK
		if (isWap) {
			if (AlipayConfig.wap_sign_type.equals("MD5")) {
				mysign = MD5.sign(prestr, AlipayConfig.key,
						AlipayConfig.input_charset);
			} else if (AlipayConfig.wap_sign_type.equals("0001")) {
				mysign = RSA.sign(prestr, AlipayConfig.private_key,
						AlipayConfig.input_charset);
			}
		} else {
			mysign = RSA.sign(prestr, AlipayConfig.private_key,
					AlipayConfig.input_charset);
		}

		return mysign;
	}

	/**
	 * 生成要请求给支付宝的参数数组
	 * 
	 * @param sParaTemp
	 *            请求前的参数数组
	 * @param isWap
	 *            支付类型true：网页|false：SDK
	 * @return 要请求的参数数组
	 * @throws Exception
	 */
	private static Map<String, String> buildRequestPara(
			Map<String, String> sParaTemp, boolean isWap) throws Exception {

		// 除去数组中的空值和签名参数
		Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);

		// 生成签名结果
		String mysign = buildRequestMysign(sPara, isWap);

		// 判断支付宝支付方式true：网页|false：SDK
		if (isWap) {
			sPara.put("sign", mysign);
		} else {
			sPara.put(
					"sign",
					"\""
							+ URLEncoder.encode(mysign,
									AlipayConfig.input_charset) + "\"");
			sPara.put("sign_type", "\"" + AlipayConfig.sdk_sign_type + "\"");
		}

		return sPara;
	}

	/**
	 * 请求支付宝，获取token
	 * 
	 * @param sParaTemp
	 *            请求参数数组
	 * @return 支付宝处理结果
	 * @throws Exception
	 */
	public static String buildRequestToken(Map<String, String> sParaTemp)
			throws Exception {
		String strResult = null;

		// 待请求参数数组
		Map<String, String> sPara = buildRequestPara(sParaTemp, true);
		HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler
				.getInstance();
		HttpRequest request = new HttpRequest(HttpResultType.BYTES);

		// 设置编码集
		request.setCharset(AlipayConfig.input_charset);
		request.setParameters(generatNameValuePair(sPara));
		request.setUrl(AlipayConfig.alipay_gateway_new + "_input_charset="
				+ AlipayConfig.input_charset);
		HttpResponse response = httpProtocolHandler.execute(request);
		if (response != null) {
			strResult = response.getStringResult();
		}

		return strResult;
	}

	/**
	 * MAP类型数组转换成NameValuePair类型
	 * 
	 * @param properties
	 *            MAP类型数组
	 * @return NameValuePair类型数组
	 */
	public static NameValuePair[] generatNameValuePair(
			Map<String, String> properties) {
		NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
		int i = 0;
		for (Map.Entry<String, String> entry : properties.entrySet()) {
			nameValuePair[i++] = new NameValuePair(entry.getKey(),
					entry.getValue());
		}

		return nameValuePair;
	}

	/**
	 * 解析远程模拟提交后返回的信息，获得token
	 * 
	 * @param text
	 *            要解析的字符串
	 * @return 解析结果
	 * @throws Exception
	 */
	public static String getRequestToken(String text) throws Exception {
		String request_token = "";

		// 以“&”字符切割字符串
		String[] strSplitText = text.split("&");

		// 把切割后的字符串数组变成变量与数值组合的字典数组
		Map<String, String> paraText = new HashMap<String, String>();
		for (int i = 0; i < strSplitText.length; i++) {

			// 获得第一个=字符的位置
			int nPos = strSplitText[i].indexOf("=");

			// 获得字符串长度
			int nLen = strSplitText[i].length();

			// 获得变量名
			String strKey = strSplitText[i].substring(0, nPos);

			// 获得数值
			String strValue = strSplitText[i].substring(nPos + 1, nLen);

			// 放入MAP类中
			paraText.put(strKey, strValue);
		}

		if (paraText.get("res_data") != null) {
			String res_data = paraText.get("res_data");

			// 解析加密部分字符串（RSA与MD5区别仅此一句）
			if (AlipayConfig.wap_sign_type.equals("0001")) {
				res_data = RSA.decrypt(res_data, AlipayConfig.private_key,
						AlipayConfig.input_charset);
			}

			// token从res_data中解析出来（也就是说res_data中已经包含token的内容）
			Document document = DocumentHelper.parseText(res_data);
			request_token = document.selectSingleNode(
					"//direct_trade_create_res/request_token").getText();
		}

		return request_token;
	}

	/**
	 * 创建与支付宝交易URL
	 * 
	 * @param sParaTemp
	 *            请求参数数组
	 * @param isWap
	 *            支付类型true：网页|false：SDK
	 * @return URL
	 * @throws Exception
	 */
	public static String buildTradeUrl(Map<String, String> sParaTemp,
			boolean isWap) throws Exception {

		// 支付宝请求地址
		String aliUrl = "";

		// 待请求参数数组
		Map<String, String> sPara = buildRequestPara(sParaTemp, isWap);
		String strResult = AlipayCore.createLinkString(sPara);

		// 判断支付宝支付方式true：网页|false：SDK
		if (isWap) {
			aliUrl = AlipayConfig.alipay_gateway_new + strResult;
		} else {
			aliUrl = strResult;
		}

		return aliUrl;
	}
}
