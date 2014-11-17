package com.a8.qdm.sdk.util.task;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.a8.qdm.config.dao.bean.Cp;
import com.a8.qdm.query.dao.bean.Order;
import com.a8.qdm.query.service.OrderService;
import com.a8.qdm.sdk.util.SdkUtil;

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
			log.info("向合作方<" + cp.getCpName() + ">发送通知");

			// 通知合作方
			SdkUtil.sendToCp(order, cp);

			// 修改CP回复状态
			if (REPLY_SUCCESS.equals(order.getReply())) {
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