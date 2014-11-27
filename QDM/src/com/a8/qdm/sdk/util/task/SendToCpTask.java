package com.a8.qdm.sdk.util.task;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.a8.qdm.config.dao.bean.Cp;
import com.a8.qdm.query.dao.bean.Order;
import com.a8.qdm.query.service.OrderService;
import com.a8.qdm.sdk.util.AlipayConfig;
import com.a8.qdm.sdk.util.AlipaySubmit;
import com.a8.qdm.sdk.util.SdkUtil;
import com.a8.qdm.sdk.util.httpClient.HttpProtocolHandler;
import com.a8.qdm.sdk.util.httpClient.HttpRequest;
import com.a8.qdm.sdk.util.httpClient.HttpResponse;
import com.a8.qdm.sdk.util.httpClient.HttpResultType;
import com.a8.qdm.sdk.util.sign.MD5;

/**
 * 银联：向Cp发送消息任务
 * 
 * @author lund
 *
 */
public class SendToCpTask extends TimerTask {

	/**
	 * CP回复结果success：成功
	 */
	private static final String REPLY_SUCCESS = "success";

	/**
	 * 数据库修改回复结果1：成功
	 */
	private static final String DB_REPLY_SUCCESS = "1";
	
	/**
	 * 支付结果1：成功
	 */
	private static final String PAY_SUCCESS = "1";

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(SendToCpTask.class);

	/**
	 * 定时器
	 */
	private Timer timer;

	/**
	 * 订单
	 */
	private Order order;

	/**
	 * 合作方
	 */
	private Cp cp;

	/**
	 * 注入orderService
	 */
	private OrderService orderService;

	/**
	 * 构造函数
	 * 
	 * @param timer
	 *            定时器
	 * @param order
	 *            订单
	 * @param cp
	 *            合作方
	 */
	public SendToCpTask(Timer timer, Order order, Cp cp,
			OrderService orderService) {
		this.timer = timer;
		this.order = order;
		this.cp = cp;
		this.orderService = orderService;
	}

	/**
	 * 执行任务
	 */
	@Override
	public void run() {
		try {

			// 入口日志
			log.info("---------------sendToCpTask start---------------");
			HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler
					.getInstance();
			HttpRequest request = new HttpRequest(HttpResultType.BYTES);
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
			sb.append(SdkUtil.currentTime());
			sb.append("</paytime></data>");

			// MD5签名
			String sign = MD5.sign(sb.toString(), cp.getKeyt(),
					AlipayConfig.input_charset);

			// 装载参数
			param.put("data", URLEncoder.encode(sb.toString(),
					AlipayConfig.input_charset));
			param.put("sign",
					URLEncoder.encode(sign, AlipayConfig.input_charset));
			log.info("通知合作方<" + cp.getCpName() + ">参数" + param);

			// 设置编码集
			request.setCharset(AlipayConfig.input_charset);
			request.setParameters(AlipaySubmit.generatNameValuePair(param));
			request.setUrl(cp.getHttpUrl());
			log.info("向合作方<" + cp.getCpName() + ">发送通知");
			HttpResponse response = httpProtocolHandler.execute(request);
			if (response != null) {
				result = response.getStringResult();
			}
			log.info("合作方<" + cp.getCpName() + ">返回结果：" + result);

			// 修改CP回复状态
			if (REPLY_SUCCESS.equals(result)) {
				log.info("合作方<" + cp.getCpName() + ">应答成功");

				// 修改合作方响应状态
				order.setReply(DB_REPLY_SUCCESS);
				orderService.updateReply(order);

				// 结束任务
				timer.cancel();
			} else {
				log.info("合作方<" + cp.getCpName() + ">应答失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------sendToCpTask end---------------");
	}
}