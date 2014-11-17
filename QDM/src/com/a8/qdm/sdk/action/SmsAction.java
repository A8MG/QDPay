package com.a8.qdm.sdk.action;

import java.io.IOException;
import java.net.URLDecoder;
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
import com.a8.qdm.config.dao.bean.Sim;
import com.a8.qdm.config.service.GameService;
import com.a8.qdm.config.service.SimService;
import com.a8.qdm.query.dao.bean.Order;
import com.a8.qdm.query.service.OrderService;
import com.a8.qdm.sdk.util.SdkUtil;
import com.a8.qdm.sdk.util.task.SendToCpTask;
import com.a8.qdm.sdk.util.task.SendToCpTaskEnd;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 短信入口
 * 
 * @author Lund
 * 
 */
public class SmsAction extends ActionSupport {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -9041197083786810988L;

	/**
	 * 短信订单号标识
	 */
	private static final String SMS_ORDER = "S";

	/**
	 * 短信默认发送状态0：未发送
	 */
	private static final String SMS_FAIL = "0";

	/**
	 * 短信发送状态1：已发送
	 */
	private static final String SMS_SUCCUSS = "1";

	/**
	 * 短信支付ID
	 */
	private static final String SMS_PAYID = "0";

	/**
	 * 运营商扣费成功
	 */
	private static final String CHARGE_SUCCESS = "0";

	/**
	 * 发送给SDK结果1：成功
	 */
	private static final String SDK_SUCCESS = "1";

	/**
	 * 发送给SDK结果0：失败
	 */
	private static final String SDK_FAIL = "0";

	/**
	 * 支付结果0：失败
	 */
	private static final String PAY_FAIL = "0";

	/**
	 * 移动对用户的校验码000：成功
	 */
	private static final String CHECK_SUCCESS = "000";

	/**
	 * 网游：给移动返回结果1：成功
	 */
	private static final String NET_SUCCESS = "1";

	/**
	 * 网游：给移动返回结果0：失败
	 */
	private static final String NET_FAIL = "0";

	/**
	 * 单机：给移动返回结果1：成功
	 */
	private static final String SINGLE_SUCCESS = "0";

	/**
	 * 设置合作方回复状态2：不发送
	 */
	private static final String DB_REPLY_NO = "2";

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(SmsAction.class);

	/**
	 * simService接口注入
	 */
	private SimService simService;

	/**
	 * orderService接口注入
	 */
	private OrderService orderService;

	/**
	 * gameService接口注入
	 */
	private GameService gameService;

	/**
	 * 获取指令列表
	 * 
	 * @return 无需跳转页面
	 */
	public String querySim() {

		// 入口日志
		log.info("---------------querySim start---------------");

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject jsonObject = new JSONObject();
		Sim sim = new Sim();

		// 获取支付信息
		String simType = request.getParameter("simType");
		String applyType = request.getParameter("applyType");
		String gameId = request.getParameter("gameId");
		String price = request.getParameter("price");
		String prop = request.getParameter("prop");
		log.info("运营商类型：" + simType + "适用类型：" + applyType + "产品ID：" + gameId
				+ "金额：" + price);

		try {
			// 通过游戏ID、运营商类型和金额获取电信运营商指令及服务端信息
			sim.setSimType(simType);
			sim.setApplyType(applyType);
			sim.setPrice(price);
			sim = simService.querySim(sim);

			if (sim != null) {

				// 网游
				if (gameId.startsWith("NG")) {

					// 创建订单
					Order order = new Order();

					// 生成订单号
					String orderNo = SdkUtil.getOrderNo(SMS_ORDER);
					jsonObject.put("orderNo", orderNo);

					// 封装订单及关联关系
					order.setOrderNo(orderNo);
					order.setPrice(price);
					order.setProp(SdkUtil.nullToString(prop));
					order.setGameId(gameId);

					// 短信发送状态默认为0：未发送
					order.setSms(SMS_FAIL);

					// 短信：添加订单及关联关系
					orderService.addOrder(order, SMS_PAYID);
				}

				// 返回给SDK数据
				jsonObject.put("command", sim.getCommand());
				jsonObject.put("simServer", sim.getSimServer());

				// 返回给SDK请求状态1：成功
				jsonObject.put("state", SDK_SUCCESS);
			} else {
				log.info("没有符合的指令信息");
				
				// 返回给SDK请求状态0：失败
				jsonObject.put("state", SDK_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);

			// 返回给SDK请求状态0：失败
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
		log.info("---------------querySim end---------------");
		return null;
	}

	/**
	 * 修改短信发送状态
	 * 
	 * @return 无需跳转页面
	 */
	public String updateSms() {

		// 入口日志
		log.info("---------------updateSms start---------------");

		HttpServletRequest request = ServletActionContext.getRequest();

		// 获取订单号
		String orderNo = request.getParameter("orderNo");
		log.info("短信订单：" + orderNo + "短信已发送");

		// 根据订单号修改短信发送状态
		try {
			orderService.updateSms(orderNo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------updateSms end---------------");
		return null;
	}

	/**
	 * 获取运营商发送的信息
	 * 
	 * @return 无需跳转页面
	 */
	public String getSim() {

		// 入口日志
		log.info("---------------getSim start---------------");

		Order order = new Order();
		HttpServletRequest request = ServletActionContext.getRequest();
		String dtype = request.getParameter("dtype");
		String usernumber = request.getParameter("usernumber");
		String linkid = request.getParameter("linkid");

		// 封装订单
		order.setUsernumber(usernumber);
		order.setLinkid(linkid);

		try {
			// dtype为mo
			if ("mo".equals(dtype)) {
				log.info("上行信息：" + dtype + "|" + usernumber + "|" + linkid);
				String msg = request.getParameter("msg");
				log.info("付费相关信息：" + msg);
				String str[] = msg.split("[|]");

				// 包含订单号
				if (str.length == 3) {
					String orderNo = str[2];
					order.setOrderNo(orderNo);
				} else if (str.length == 2) {

					// 单机
					String gameId = str[1];
					String price = request.getParameter("fee");
					String orderNo = SdkUtil.getOrderNo(SMS_ORDER);
					order.setOrderNo(orderNo);
					order.setProp("");
					order.setPrice(price);
					order.setSms(SMS_SUCCUSS);
					order.setGameId(gameId);

					// 短信：添加订单及关联关系
					orderService.addOrder(order, SMS_PAYID);
				} else {
					log.info("付费相关信息有误");
				}
			} else {
				log.info("下行信息：" + dtype + "|" + usernumber + "|" + linkid);
				order = orderService.queryOrderBySim(order);

				// 扣费状态
				String sts = request.getParameter("sts");

				// 扣费成功：0
				if (CHARGE_SUCCESS.equals(sts)) {
					doResult(order);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------getSim end---------------");
		return null;
	}

	/**
	 * MDO对接：移动网游通知
	 * 
	 * @return 无需跳转页面
	 */
	public String getCmccNet() {

		// 入口日志
		log.info("---------------getCmccNet start---------------");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		StringBuilder ServiceGetData2SPRsp = new StringBuilder();
		String params = request.getParameter("ServiceGetData2SPReq");
		log.info("移动有网络通知参数：" + params);

		try {
			// XML解析notify_data数据
			Document doc_notify_data = DocumentHelper.parseText(params);

			// 用户检验状态码
			String checkCode = doc_notify_data.selectSingleNode("//checkCode")
					.getText();

			// 业务处理ID
			String transactionId = doc_notify_data.selectSingleNode(
					"//transactionId").getText();
			ServiceGetData2SPRsp
					.append("<?xml version='1.0' encoding='GBK'?><ServiceGetData2SPRsp><transactionId>")
					.append(transactionId).append("</transactionId><success>");

			if (CHECK_SUCCESS.equals(checkCode)) {

				// 指令、订单号
				String commandOrderNo = doc_notify_data.selectSingleNode(
						"//paramMap/entry[@key='command']/value").getText();

				// 订单号
				String orderNo = commandOrderNo.split("[,]")[6];

				// 查询订单
				Order order = orderService
						.queryOrderByNo(orderNo.toUpperCase());

				// 处理结果
				doResult(order);
				ServiceGetData2SPRsp.append(NET_SUCCESS);
			} else {
				ServiceGetData2SPRsp.append(NET_FAIL);
			}
			ServiceGetData2SPRsp
					.append("</success><results><result type='sms'><title></title></result></results></ServiceGetData2SPRsp>");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		} finally {
			try {
				// 发送给移动
				response.getWriter().print(ServiceGetData2SPRsp);
				log.info("返回给移动有网络的内容：" + ServiceGetData2SPRsp);
			} catch (IOException io) {
				io.printStackTrace();
				log.error(io);
			}
		}

		// 出口日志
		log.info("---------------getCmccNet end---------------");
		return null;
	}

	/**
	 * MDO对接：移动单机通知
	 * 
	 * @return 无需跳转页面
	 */
	public String getCmccSingle() {

		// 入口日志
		log.info("---------------getCmccSingle start---------------");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Order order = new Order();

		// 指令、产品ID
		String commandGameId = request.getParameter("cmd");

		// 交易标识
		String tnsid = request.getParameter("tnsid");
		order.setUsernumber(tnsid);
		order.setLinkid(tnsid);

		// 金额
		String price = request.getParameter("feecode");

		// 交易时间
		String time = request.getParameter("tm");
		log.info("移动无网络通知内容：" + commandGameId + "|" + tnsid + "|" + price + "|"
				+ time);

		try {

			// 根据交易标识查询订单
			Order orderTemp = orderService.queryOrderBySim(order);

			// 过滤重复
			if (orderTemp == null) {

				// 解码
				commandGameId = URLDecoder.decode(commandGameId, "UTF-8");

				// 产品ID
				String gameId = commandGameId.split("[,]")[5];

				// 创建订单号
				String orderNo = SdkUtil.getOrderNo(SMS_ORDER);

				// 组装订单
				order.setOrderNo(orderNo);
				order.setProp("");
				order.setPrice(price);
				order.setSms(SMS_SUCCUSS);
				order.setGameId("SG" + gameId);
				order.setModifyTime(SdkUtil.dateFormat(time));

				// 短信：添加订单及关联关系
				orderService.addOrder(order, SMS_PAYID);

				// 处理结果
				doResult(order);

				// 发送给移动
				response.getWriter().print(SINGLE_SUCCESS);
			} else {
				log.info("订单已存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------getCmccSingle end---------------");
		return null;
	}

	/**
	 * 处理结果
	 * 
	 * @param order
	 *            订单
	 */
	private void doResult(Order order) throws Exception {

		// 过滤多次状态报告请求
		if (order != null && PAY_FAIL.equals(order.getState())) {

			// 修改订单状态为1：成功
			orderService.updateState(order);
			log.info("订单：" + order.getOrderNo() + "交易成功");

			// 获取合作商信息
			Cp cp = orderService.queryCpByNo(order.getOrderNo());

			// 根据产品ID查询通知概率
			GameWebBean gameWebBean = gameService.queryGameById(order
					.getGameId());
			boolean isSend = SdkUtil.isSend(gameWebBean.getChance());

			if (isSend) {
				if (!"".equals(cp.getHttpUrl())) {

					// 定义定时器
					Timer timer = new Timer();

					// 执行任务
					timer.schedule(new SendToCpTask(timer, order, cp,
							orderService), 0, 5 * 60 * 1000);

					// 10分钟后结束任务，可执行3次任务
					timer.schedule(new SendToCpTaskEnd(timer), 10 * 60 * 1000);
				}
			} else {

				// 不发送给合作方
				order.setReply(DB_REPLY_NO);
				orderService.updateReply(order);
			}
		} else {
			log.info("订单：" + order.getOrderNo() + "交易失败");
		}
	}

	public SimService getSimService() {
		return simService;
	}

	public void setSimService(SimService simService) {
		this.simService = simService;
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
