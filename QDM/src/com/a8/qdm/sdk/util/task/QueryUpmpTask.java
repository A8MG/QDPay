package com.a8.qdm.sdk.util.task;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.a8.qdm.query.dao.bean.Order;
import com.a8.qdm.sdk.action.UpmpAction;

/**
 * 银联查询交易结果定时任务
 * 
 * @author lund
 *
 */
public class QueryUpmpTask extends TimerTask {

	/**
	 * 定时器
	 */
	private Timer timer;

	/**
	 * 参数
	 */
	private Map<String, String> req;

	/**
	 * 订单
	 */
	private Order order;

	/**
	 * 银联Action
	 */
	private UpmpAction upmpAction;

	/**
	 * 构造函数
	 * 
	 * @param timer
	 *            定时器
	 * @param orderNo
	 *            订单号
	 * @param orderTime
	 *            交易时间
	 * @param upmpAction
	 *            银联Action
	 */
	public QueryUpmpTask(Timer timer, Map<String, String> req, Order order,
			UpmpAction upmpAction) {
		this.timer = timer;
		this.req = req;
		this.order = order;
		this.upmpAction = upmpAction;
	}

	/**
	 * 开启任务
	 */
	@Override
	public void run() {
		upmpAction.runTask(timer, req, order);
	}
}