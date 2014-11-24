package com.a8.qdm.sdk.util;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.a8.qdm.config.dao.bean.Cp;
import com.a8.qdm.query.dao.bean.Order;
import com.a8.qdm.sdk.util.httpClient.HttpProtocolHandler;
import com.a8.qdm.sdk.util.httpClient.HttpRequest;
import com.a8.qdm.sdk.util.httpClient.HttpResponse;
import com.a8.qdm.sdk.util.httpClient.HttpResultType;
import com.a8.qdm.sdk.util.sign.MD5;

/**
 * SDK工具类
 * 
 * @author Lund
 * 
 */
public class SdkUtil {

	/**
	 * 支付结果1：成功
	 */
	private static final String PAY_SUCCESS = "1";

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(SdkUtil.class);

	/**
	 * 生成订单号
	 * 
	 * @param orderStart
	 *            订单号起始标记
	 * @return 订单号
	 */
	public static String getOrderNo(String orderStart) {
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmssSSS")
				.format(new Date());
		int randomNo = (int) (Math.random() * 100);
		String orderNo = "Q000" + orderStart + currentTime + randomNo;
		log.info("订单号：" + orderNo);

		return orderNo;
	}

	/**
	 * 当前系统时间
	 * 
	 * @return
	 */
	public static String currentTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	/**
	 * 时间格式化
	 * 
	 * @param dateStr
	 *            日期
	 * @return
	 * @throws Exception
	 */
	public static String dateFormat(String dateStr) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = sdf.parse(dateStr);
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sdf.format(date);
	}

	/**
	 * 按概率计算是否发送通知
	 * 
	 * @param chance
	 *            概率
	 * @return true：发送|false：不发送
	 */
	public static boolean isSend(String chance) throws Exception {
		boolean isSend = true;
		int randomNo = (int) (Math.random() * 100 + 1);
		if (randomNo > Integer
				.parseInt(chance.substring(0, chance.length() - 1))) {
			isSend = false;
		}

		return isSend;
	}

	/**
	 * 将null转为空字符串
	 * 
	 * @param str
	 *            字符串
	 * @return 返回值
	 */
	public static String nullToString(String str) {
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param order
	 *            订单
	 * @param cp
	 *            合作商
	 */
	public static void sendToCp(Order order, Cp cp) throws Exception {
		HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler
				.getInstance();
		HttpRequest request = new HttpRequest(HttpResultType.BYTES);
		String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		StringBuilder sb = new StringBuilder();
		Map<String, String> param = new HashMap<String, String>();
		String result = "";

		// 参数构建为XML格式
		sb.append("<?xml version='1.0' encoding='UTF-8'?><data><prop>");
		sb.append(order.getProp());
		sb.append("</prop><price>");
		sb.append(order.getPrice());
		sb.append("</price><state>");
		sb.append(PAY_SUCCESS);
		sb.append("</state><orderno>");
		sb.append(order.getOrderNo());
		sb.append("</orderno><paytime>");
		sb.append(currentTime);
		sb.append("</paytime></data>");

		// MD5签名
		String sign = MD5.sign(sb.toString(), cp.getKeyt(),
				AlipayConfig.input_charset);

		// 装载参数
		param.put("data",
				URLEncoder.encode(sb.toString(), AlipayConfig.input_charset));
		param.put("sign", URLEncoder.encode(sign, AlipayConfig.input_charset));
		log.info("通知合作方<" + cp.getCpName() + ">参数" + param);

		// 设置编码集
		request.setCharset(AlipayConfig.input_charset);
		request.setParameters(AlipaySubmit.generatNameValuePair(param));
		request.setUrl(cp.getHttpUrl());
		HttpResponse response = httpProtocolHandler.execute(request);
		if (response != null) {
			result = response.getStringResult();
		}

		// 设置合作方返回结果
		order.setReply(result);
		log.info("合作方<" + cp.getCpName() + ">返回结果：" + result);
	}
}
