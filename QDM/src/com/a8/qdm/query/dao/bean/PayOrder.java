package com.a8.qdm.query.dao.bean;

/**
 * 支付与订单关联类
 * 
 * @author Lund
 * 
 */
public class PayOrder {

	/**
	 * 支付ID
	 */
	private String payId;

	/**
	 * 订单号
	 */
	private String orderNo;

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String toString() {
		return payId + "|" + orderNo;
	}
}
