package com.a8.qdm.sdk.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import com.a8.qdm.config.action.bean.GameWebBean;
import com.a8.qdm.config.dao.bean.Cp;
import com.a8.qdm.config.service.GameService;
import com.a8.qdm.query.dao.bean.Order;
import com.a8.qdm.query.service.OrderService;
import com.a8.qdm.sdk.util.AlipayConfig;
import com.a8.qdm.sdk.util.AlipayNotify;
import com.a8.qdm.sdk.util.AlipaySubmit;
import com.a8.qdm.sdk.util.SdkUtil;
import com.a8.qdm.sdk.util.task.SendToCpTask;
import com.a8.qdm.sdk.util.task.SendToCpTaskEnd;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 支付宝入口
 * 
 * @author Lund
 * 
 */
public class AliAction extends ActionSupport {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 2877320406945911487L;

	/**
	 * 支付宝订单标识
	 */
	private static final String ALI_ORDER = "A";

	/**
	 * 支付宝支付ID
	 */
	private static final String ALI_PAYID = "1";

	/**
	 * 支付结果0：失败
	 */
	private static final String PAY_FAIL = "0";

	/**
	 * 发送给SDK结果1：成功
	 */
	private static final String SDK_SUCCESS = "1";

	/**
	 * 发送给SDK结果0：失败
	 */
	private static final String SDK_FAIL = "0";

	/**
	 * 设置合作方回复状态2：不发送
	 */
	private static final String DB_REPLY_NO = "2";

	/**
	 * 加、解密方式
	 */
	private static final String RSA = "0001";

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(AliAction.class);

	/**
	 * orderService接口注入
	 */
	private OrderService orderService;

	/**
	 * gameService接口注入
	 */
	private GameService gameService;

	/**
	 * 支付宝网页支付
	 * 
	 * @return 无需要跳转页面
	 */
	public String aliWap() {

		// 入口日志
		log.info("---------------aliWap start---------------");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, String> sParaTempToken = new HashMap<String, String>();
		Map<String, String> sParaTemp = new HashMap<String, String>();
		JSONObject jsonObject = new JSONObject();

		// 获取支付信息
		String gameId = request.getParameter("gameId");
		String price = request.getParameter("price");
		String prop = request.getParameter("prop");
		log.info("产品ID：" + gameId + "|" + "金额：" + price);

		try {
			// 生成订单号
			String orderNo = SdkUtil.getOrderNo(ALI_ORDER);
			jsonObject.put("orderNo", orderNo);

			// 创建订单
			Order order = new Order();
			order.setOrderNo(orderNo);
			order.setGameId(gameId);
			order.setPrice(price);
			order.setProp(SdkUtil.nullToString(prop));

			// 支付宝：添加订单及收入信息
			orderService.addOrder(order, ALI_PAYID);

			// 请求业务参数详细
			String req_dataToken = "<direct_trade_create_req><notify_url>"
					+ AlipayConfig.wap_notify_url
					+ "</notify_url><call_back_url>"
					+ AlipayConfig.call_back_url
					+ "</call_back_url><seller_account_name>"
					+ AlipayConfig.seller_account_name
					+ "</seller_account_name><out_trade_no>"
					+ orderNo
					+ "</out_trade_no><subject>"
					+ AlipayConfig.subject
					+ "</subject><total_fee>"
					+ Integer.parseInt(price)
					* 0.01
					+ "</total_fee><pay_expire>0</pay_expire></direct_trade_create_req>";
			sParaTempToken.put("service", "alipay.wap.trade.create.direct");
			sParaTempToken.put("partner", AlipayConfig.partner);
			sParaTempToken.put("_input_charset", AlipayConfig.input_charset);
			sParaTempToken.put("sec_id", AlipayConfig.wap_sign_type);
			sParaTempToken.put("format", AlipayConfig.format);
			sParaTempToken.put("v", AlipayConfig.v);
			sParaTempToken.put("req_id", orderNo);
			sParaTempToken.put("req_data", req_dataToken);
			log.info("支付宝网页支付请求参数：" + sParaTempToken);

			// 建立请求
			String sHtmlTextToken = AlipaySubmit
					.buildRequestToken(sParaTempToken);

			// URLDECODE返回的信息
			sHtmlTextToken = URLDecoder.decode(sHtmlTextToken,
					AlipayConfig.input_charset);

			// 获取token
			String request_token = AlipaySubmit.getRequestToken(sHtmlTextToken);
			log.info("支付宝Token：" + request_token);

			// 业务详细
			String req_data = "<auth_and_execute_req><request_token>"
					+ request_token + "</request_token></auth_and_execute_req>";
			sParaTemp.put("service", "alipay.wap.auth.authAndExecute");
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("sec_id", AlipayConfig.wap_sign_type);
			sParaTemp.put("format", AlipayConfig.format);
			sParaTemp.put("v", AlipayConfig.v);
			sParaTemp.put("req_data", req_data);
			log.info("支付宝网页支付交易参数：" + sParaTemp);

			// 签名并拼接URL
			String aliWapUrl = AlipaySubmit.buildTradeUrl(sParaTemp, true);
			jsonObject.put("aliWapUrl", aliWapUrl);
			jsonObject.put("state", SDK_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			jsonObject.put("state", SDK_FAIL);
		} finally {
			try {
				// 发送给SDK
				response.getWriter().print(jsonObject);
				log.info("发送给轻点支付SDK内容：" + jsonObject);
			} catch (IOException io) {
				io.printStackTrace();
				log.error(io);
			}
		}

		// 出口日志
		log.info("---------------aliWap end---------------");
		return null;
	}

	/**
	 * 网页：获取支付宝异步通知结果
	 * 
	 * @return 无需跳转页面
	 */
	public String aliWapNotify() {

		// 入口日志
		log.info("---------------aliWapNotify start---------------");

		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = getParams();
			log.info("支付宝网页支付异步通知内容：" + params);

			// RSA签名解密
			if (AlipayConfig.wap_sign_type.equals(RSA)) {
				params = AlipayNotify.decrypt(params);
			}

			// XML解析notify_data数据
			Document doc_notify_data = DocumentHelper.parseText(params
					.get("notify_data"));

			// 商户订单号
			String out_trade_no = doc_notify_data.selectSingleNode(
					"//notify/out_trade_no").getText();

			// 交易状态
			String trade_status = doc_notify_data.selectSingleNode(
					"//notify/trade_status").getText();
			log.info("订单号：" + out_trade_no + "|" + "交易状态：" + trade_status);

			// 处理结果
			doResult(params, out_trade_no, trade_status, true);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------aliWapNotify end---------------");
		return null;
	}

	/**
	 * 获取支付宝同步通知结果
	 * 
	 * @return 无需跳转页面
	 */
	public String aliCallback() {

		// 入口日志
		log.info("---------------aliCallback start---------------");
		String redirectPage = "";

		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = getParams();
			log.info("支付宝网页支付同步通知内容：" + params);

			// 计算得出通知验证结果
			boolean verify_result = AlipayNotify.verifyReturn(params);
			if (verify_result) {// 验证成功
				redirectPage = "success";
			} else {// 验证失败
				redirectPage = "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------aliCallback end---------------");
		return redirectPage;
	}

	/**
	 * 支付宝SDK支付
	 * 
	 * @return 无需跳转页面
	 */
	public String aliSdk() {

		// 入口日志
		log.info("---------------aliSdk start---------------");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, String> params = new HashMap<String, String>();
		JSONObject jsonObject = new JSONObject();

		// 获取支付信息
		String gameId = request.getParameter("gameId");
		String price = request.getParameter("price");
		String prop = request.getParameter("prop");
		log.info("产品ID：" + gameId + "|" + "金额：" + price);

		try {
			// 生成订单号
			String orderNo = SdkUtil.getOrderNo(ALI_ORDER);
			jsonObject.put("orderNo", orderNo);

			// 创建订单
			Order order = new Order();
			order.setOrderNo(orderNo);
			order.setGameId(gameId);
			order.setPrice(price);
			order.setProp(SdkUtil.nullToString(prop));

			// 支付宝：添加订单及收入信息
			orderService.addOrder(order, ALI_PAYID);

			// 添加参数
			params.put("service", "\"mobile.securitypay.pay\"");
			params.put("partner", "\"" + AlipayConfig.partner + "\"");
			params.put("_input_charset", "\"" + AlipayConfig.input_charset
					+ "\"");
			params.put(
					"notify_url",
					"\""
							+ URLEncoder.encode(AlipayConfig.sdk_notify_url,
									AlipayConfig.input_charset) + "\"");
			params.put("out_trade_no", "\"" + orderNo + "\"");
			params.put("subject", "\"" + AlipayConfig.subject + "\"");
			params.put("payment_type", "\"1\"");
			params.put("seller_id", "\"" + AlipayConfig.seller_account_name
					+ "\"");
			params.put("total_fee",
					"\"" + String.valueOf(Integer.parseInt(price) * 0.01)
							+ "\"");
			params.put("body", "\"" + AlipayConfig.subject + "\"");

			// 签名并拼接URL
			String aliSdkUrl = AlipaySubmit.buildTradeUrl(params, false);
			log.info("支付宝SDK支付URL：" + aliSdkUrl);
			jsonObject.put("aliSdkUrl", aliSdkUrl);
			jsonObject.put("state", SDK_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			jsonObject.put("state", SDK_FAIL);
		} finally {
			try {
				// 发送给SDK
				response.getWriter().print(jsonObject);
				log.info("发送给轻点支付SDK内容：" + jsonObject);
			} catch (IOException io) {
				io.printStackTrace();
				log.error(io);
			}
		}

		// 出口日志
		log.info("---------------aliSdk end---------------");
		return null;
	}

	/**
	 * SDK：获取支付宝异步通知结果
	 * 
	 * @return 无需跳转页面
	 */
	public String aliSdkNotify() {

		// 入口日志
		log.info("---------------aliSdkNotify start---------------");

		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = getParams();
			log.info("支付宝SDK支付异步通知内容：" + params);

			// 商户订单号
			String out_trade_no = params.get("out_trade_no");

			// 交易状态
			String trade_status = params.get("trade_status");
			log.info("订单号：" + out_trade_no + "|" + "交易状态：" + trade_status);

			// 处理结果
			doResult(params, out_trade_no, trade_status, false);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------aliSdkNotify end---------------");
		return null;
	}

	/**
	 * 获取支付宝返回参数
	 * 
	 * @return 参数集合
	 */
	private Map<String, String> getParams() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();

		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();

		for (Iterator<String> iter = requestParams.keySet().iterator(); iter
				.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}

		return params;
	}

	/**
	 * 处理结果
	 * 
	 * @param params
	 *            支付宝返回参数
	 * @param out_trade_no
	 *            订单号
	 * @param trade_status
	 *            交易状态
	 * @param isWap
	 *            支付类型true：网页|false：SDK
	 */
	private void doResult(Map<String, String> params, String out_trade_no,
			String trade_status, boolean isWap) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();

		// 验证成功
		if (AlipayNotify.verifyNotify(params, isWap)) {
			response.getWriter().print("success");
			if (trade_status.equals("TRADE_FINISHED")) {

				// 根据订单号查询订单
				Order order = orderService.queryOrderByNo(out_trade_no);

				// 过滤重复的请求
				if (order != null && PAY_FAIL.equals(order.getState())) {

					// 修改支付状态及收入记录
					order.setModifyTime(SdkUtil.currentTime());
					orderService.updateState(order);
					log.info("订单" + out_trade_no + "交易成功");

					// 根据订单号查询CP
					Cp cp = orderService.queryCpByNo(out_trade_no);

					// 根据产品ID查询通知概率
					GameWebBean gameWebBean = gameService.queryGameById(order
							.getGameId());
					boolean isSend = SdkUtil.isSend(gameWebBean.getChance());

					if (isSend) {
						if (!"".equals(cp.getHttpUrl())) {
							log.info("向合作方发送通知");

							// 定义定时器
							Timer timer = new Timer();

							// 执行任务
							timer.schedule(new SendToCpTask(timer, order, cp,
									orderService), 0, 5 * 60 * 1000);

							// 10分钟后结束任务，可执行3次任务
							timer.schedule(new SendToCpTaskEnd(timer),
									10 * 60 * 1000);
						}
					} else {

						// 不发送给合作方
						order.setReply(DB_REPLY_NO);
						orderService.updateReply(order);
					}
				}
			}
		} else {// 验证失败
			log.info("订单" + out_trade_no + "交易失败");
			response.getWriter().print("fail");
		}
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}
}
