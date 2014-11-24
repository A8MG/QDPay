package com.a8.qdm.query.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.ActionDao;
import com.a8.qdm.config.dao.bean.Cp;
import com.a8.qdm.query.action.bean.OrderWebBean;
import com.a8.qdm.query.dao.OrderDao;
import com.a8.qdm.query.dao.PayOrderDao;
import com.a8.qdm.query.dao.bean.Crop;
import com.a8.qdm.query.dao.bean.Order;
import com.a8.qdm.query.dao.bean.PayOrder;
import com.a8.qdm.query.service.OrderService;

/**
 * 订单Service实现
 * 
 * @author Lund
 * 
 */
public class OrderServiceImpl implements OrderService {

	/**
	 * orderDao注入
	 */
	private OrderDao orderDao;

	/**
	 * payOrderDao注入
	 */
	private PayOrderDao payOrderDao;

	/**
	 * actionDao接口注入
	 */
	private ActionDao actionDao;

	/**
	 * 根据usernumber和linkid查询订单
	 * 
	 * @param order
	 *            查询条件
	 * @return 订单信息
	 * @throws Exception
	 */
	public Order queryOrderBySim(Order order) throws Exception {
		return orderDao.queryOrderBySim(order);
	}

	/**
	 * 根据订单号查询订单
	 * 
	 * @param orderNo
	 *            查询条件
	 * @return 订单信息
	 * @throws Exception
	 */
	public Order queryOrderByNo(String orderNo) throws Exception {
		return orderDao.queryOrderByNo(orderNo);
	}

	/**
	 * 查询游戏Url
	 * 
	 * @param orderNo
	 *            订单号
	 * @return Cp
	 * @throws Exception
	 */
	public Cp queryCpByNo(String orderNo) throws Exception {
		return orderDao.queryCpByNo(orderNo);
	}

	/**
	 * 查询合作方前一个月交易额
	 * 
	 * @return 各合作方交易额的集合
	 * @throws Exception
	 */
	public List<Crop> queryCpCrop() throws Exception {
		return orderDao.queryCpCrop();
	}

	/**
	 * 查询产品前一个月交易额
	 * 
	 * @param cpId
	 *            合作方ID
	 * @return 各产品交易额的集合
	 * @throws Exception
	 */
	public List<Crop> queryGameCrop(String cpId) throws Exception {
		return orderDao.queryGameCrop(cpId);
	}

	/**
	 * 查询订单数量
	 * 
	 * @param cpId
	 *            合作方
	 * @param search
	 *            订单查询条件
	 * @return 数量
	 * @throws Exception
	 */
	public int queryOrderCount(String cpId, Order search) throws Exception {
		return orderDao.queryOrderCount(cpId, search);
	}

	/**
	 * 查询订单列表
	 * 
	 * @param cpId
	 *            合作方
	 * @param search
	 *            订单查询条件
	 * @param page
	 *            分页对象
	 * @return 列表集合
	 * @throws Exception
	 */
	public List<OrderWebBean> queryOrderList(String cpId, Order search,
			Page page) throws Exception {
		return orderDao.queryOrderList(cpId, search, page);
	}

	/**
	 * 添加订单及关系、收入记录
	 * 
	 * @param order
	 *            订单类
	 * @param payId
	 *            支付类型ID
	 * @throws Exception
	 */
	@Transactional
	public void addOrder(Order order, String payId) throws Exception {
		PayOrder payOrder = new PayOrder();

		// 支付状态默认为0：失败
		order.setState("0");

		// CP回复状态默认为0：失败
		order.setReply("0");

		payOrder.setOrderNo(order.getOrderNo());
		payOrder.setPayId(payId);

		// 添加订单及支付与订单关联关系
		orderDao.addOrder(order);
		payOrderDao.addPayOrder(payOrder);

		// 修改打开SDK次数
		actionDao.updateOpenTimes(order.getGameId());
	}

	/**
	 * 修改短信发送状态
	 * 
	 * @param orderNo
	 *            订单号
	 * @throws Exception
	 */
	public void updateSms(String orderNo) throws Exception {
		orderDao.updateSms(orderNo);
	}

	/**
	 * 修改付费状态
	 * 
	 * @param order
	 *            订单
	 * @throws Exception
	 */
	@Transactional
	public void updateState(Order order) throws Exception {

		// 修改订单状态
		orderDao.updateState(order);

		// 修改确认支付次数
		actionDao.updateConfigTimes(order.getGameId());
	}

	/**
	 * 修改CP回复状态
	 * 
	 * @param order
	 *            订单
	 * @throws Exception
	 */
	public void updateReply(Order order) throws Exception {
		orderDao.updateReply(order);
	}

	/**
	 * 删除订单
	 * 
	 * @param orderNo
	 *            订单数组
	 * @throws Exception
	 */
	public void deleteOrder(String[] orderNo) throws Exception {
		orderDao.deleteOrder(orderNo);
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public PayOrderDao getPayOrderDao() {
		return payOrderDao;
	}

	public void setPayOrderDao(PayOrderDao payOrderDao) {
		this.payOrderDao = payOrderDao;
	}

	public ActionDao getActionDao() {
		return actionDao;
	}

	public void setActionDao(ActionDao actionDao) {
		this.actionDao = actionDao;
	}
}
