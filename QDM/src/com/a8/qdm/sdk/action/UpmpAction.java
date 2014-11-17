package com.a8.qdm.sdk.action;

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

import com.a8.qdm.config.action.bean.GameWebBean;
import com.a8.qdm.config.dao.bean.Cp;
import com.a8.qdm.config.service.GameService;
import com.a8.qdm.query.dao.bean.Order;
import com.a8.qdm.query.service.OrderService;
import com.a8.qdm.sdk.util.SdkUtil;
import com.a8.qdm.sdk.util.UpmpConfig;
import com.a8.qdm.sdk.util.UpmpService;
import com.a8.qdm.sdk.util.task.QueryUpmpTask;
import com.a8.qdm.sdk.util.task.QueryUpmpTaskEnd;
import com.a8.qdm.sdk.util.task.SendToCpTask;
import com.a8.qdm.sdk.util.task.SendToCpTaskEnd;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 银联入口
 * 
 * @author lund
 *
 */
public class UpmpAction extends ActionSupport {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 6392091214498346756L;

	/**
	 * 银联订单标识
	 */
	private static final String UPMP_ORDER = "U";

	/**
	 * 银联支付ID
	 */
	private static final String UPMP_PAYID = "2";

	/**
	 * 支付结果0：失败
	 */
	private static final String PAY_FAIL = "0";

	/**
	 * 设置合作方回复状态2：不发送
	 */
	private static final String DB_REPLY_NO = "2";

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(UpmpAction.class);

	/**
	 * orderService接口注入
	 */
	private OrderService orderService;

	/**
	 * gameService接口注入
	 */
	private GameService gameService;

	/**
	 * 银联SDK入口
	 * 
	 * @return 无需跳转页面
	 */
	public String upmpSdk() {

		// 入口日志
		log.info("---------------upmpSdk start---------------");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> result = new HashMap<String, String>();
		JSONObject jsonObject = new JSONObject();

		// 获取支付信息
		String gameId = request.getParameter("gameId");
		String price = request.getParameter("price");
		String prop = request.getParameter("prop");
		log.info("产品ID：" + gameId + "|" + "金额：" + price);

		// 生成订单号
		String orderNo = SdkUtil.getOrderNo(UPMP_ORDER);
		jsonObject.put("orderNo", orderNo);

		try {
			// 创建订单
			Order order = new Order();
			order.setOrderNo(orderNo);
			order.setGameId(gameId);
			order.setPrice(price);
			order.setProp(SdkUtil.nullToString(prop));

			// 支付宝：添加订单及收入信息
			orderService.addOrder(order, UPMP_PAYID);

			// 添加参数
			params.put("version", UpmpConfig.VERSION);
			params.put("charset", UpmpConfig.CHARSET);
			params.put("transType", UpmpConfig.TRANS_TYPE);
			params.put("merId", UpmpConfig.MER_ID);
			params.put("backEndUrl", UpmpConfig.MER_BACK_END_URL);
			params.put("orderTime", UpmpConfig.ORDER_TIME);
			params.put("orderNumber", orderNo);
			params.put("orderAmount", price);
			log.info("银联交易请求参数：" + params);

			// 请求返回流水号
			if (UpmpService.trade(params, result)) {
				jsonObject.put("tn", result.get("tn"));
				jsonObject.put("state", "1");

				// 查询交易结果
				queryUpmp(order, params.get("orderTime"));
			} else {
				jsonObject.put("state", "0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			jsonObject.put("state", "0");
		} finally {
			try {
				// 发送给SDK
				response.getWriter().print(jsonObject);
				log.info("发送给轻点支付SDK内容：" + jsonObject);
			} catch (Exception ex) {
				ex.printStackTrace();
				log.error(ex);
			}
		}

		// 出口日志
		log.info("---------------upmpSdk end---------------");
		return null;
	}

	/**
	 * 银联SDK异步通知
	 * 
	 * @return 无需跳转页面
	 */
	public String upmpSdkNotify() {

		// 入口日志
		log.info("---------------upmpSdkNotify start---------------");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, String> params = new HashMap<String, String>();

		try {
			// 根据订单号查询订单
			String orderNo = request.getParameter("orderNumber");
			Order order = orderService.queryOrderByNo(orderNo);

			// 过滤重复的请求
			if (order != null && PAY_FAIL.equals(order.getState())) {

				// 获取银联POST过来异步通知信息
				Map<String, String[]> requestParams = request.getParameterMap();
				for (Iterator<String> iter = requestParams.keySet().iterator(); iter
						.hasNext();) {
					String name = (String) iter.next();
					String[] values = (String[]) requestParams.get(name);
					String valueStr = "";
					for (int i = 0; i < values.length; i++) {
						valueStr = (i == values.length - 1) ? valueStr
								+ values[i] : valueStr + values[i] + ",";
					}
					params.put(name, valueStr);
				}
				log.info("银联通知参数：" + params);

				// 服务器签名验证成功
				if (UpmpService.verifySignature(params)) {
					response.getWriter().print("success");

					// 交易状态
					String transStatus = params.get("transStatus");

					// 交易处理成功
					if (UpmpConfig.RESPONSE_CODE_SUCCESS.equals(transStatus)) {
						doResult(order);
					} else {
						log.info("订单：" + order.getOrderNo() + "交易失败");
					}
				}
			} else {// 服务器签名验证失败
				response.getWriter().print("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------upmpSdkNotify end---------------");

		return null;
	}

	/**
	 * 查询银联交易结果
	 * 
	 * @param order
	 *            订单
	 * @param orderTime
	 *            订单交易时间
	 */
	public void queryUpmp(Order order, String orderTime) {

		// 入口日志
		log.info("---------------queryUpmp start---------------");
		Map<String, String> req = new HashMap<String, String>();

		try {
			// 添加参数
			req.put("version", UpmpConfig.VERSION);
			req.put("charset", UpmpConfig.CHARSET);
			req.put("transType", UpmpConfig.TRANS_TYPE);
			req.put("merId", UpmpConfig.MER_ID);
			req.put("orderTime", orderTime);
			req.put("orderNumber", order.getOrderNo());
			log.info("银联查询请求参数：" + req);

			// 创建定时器
			Timer timer = new Timer();

			// 执行定时器
			timer.schedule(new QueryUpmpTask(timer, req, order, this),
					10 * 60 * 1000, 10 * 60 * 1000);

			// 30分钟后结束定时器，可执行3次任务
			timer.schedule(new QueryUpmpTaskEnd(timer), 30 * 60 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------queryUpmp end---------------");
	}

	/**
	 * 执行银联查询交易结果任务
	 * 
	 * @param timer
	 *            定时器
	 * @param req
	 *            请求参数
	 * @param order
	 *            订单
	 */
	public void runTask(Timer timer, Map<String, String> req, Order order) {
		Map<String, String> resp = new HashMap<String, String>();

		// 查询交易结果
		boolean validResp = UpmpService.query(req, resp);

		try {
			// 服务器应答签名验证成功
			if (validResp) {
				String transStatus = resp.get("transStatus");

				// 交易成功
				if (UpmpConfig.RESPONSE_CODE_SUCCESS.equals(transStatus)) {

					// 结束任务
					timer.cancel();

					// 处理结果
					doResult(order);
				} else {
					log.info("订单：" + order.getOrderNo() + "交易失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}

	/**
	 * 处理结果
	 * 
	 * @param order
	 *            订单类
	 */
	private void doResult(Order order) throws Exception {

		// 修改支付状态及收入记录
		order.setModifyTime(SdkUtil.currentTime());
		orderService.updateState(order);
		log.info("订单：" + order.getOrderNo() + "交易成功");

		// 根据订单号查询CP
		Cp cp = orderService.queryCpByNo(order.getOrderNo());

		// 根据产品ID查询通知概率
		GameWebBean gameWebBean = gameService.queryGameById(order.getGameId());
		boolean isSend = SdkUtil.isSend(gameWebBean.getChance());

		if (isSend) {
			if (!"".equals(cp.getHttpUrl())) {
				log.info("向合作方发送通知");

				// 定义定时器
				Timer timer = new Timer();

				// 执行任务
				timer.schedule(
						new SendToCpTask(timer, order, cp, orderService), 0,
						5 * 60 * 1000);

				// 10分钟后结束任务，可执行3次任务
				timer.schedule(new SendToCpTaskEnd(timer), 10 * 60 * 1000);
			}
		} else {

			// 不发送给合作方
			order.setReply(DB_REPLY_NO);
			orderService.updateReply(order);
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
