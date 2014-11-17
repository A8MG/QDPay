package com.a8.qdm.query.service.bean;

import com.a8.qdm.query.dao.bean.Order;
import com.a8.qdm.query.dao.bean.PayOrder;

/**
 * 订单业务类
 * 
 * @author Lund
 * 
 */
public class OrderServiceBean {

	/**
	 * 订单
	 */
	private Order order;

	/**
	 * 关联关系
	 */
	private PayOrder payOrder;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public PayOrder getPayOrder() {
		return payOrder;
	}

	public void setPayOrder(PayOrder payOrder) {
		this.payOrder = payOrder;
	}

	@Override
	public String toString() {
		return "OrderServiceBean [order=" + order + ", payOrder=" + payOrder
				+ "]";
	}
}
