package com.a8.qdm.query.dao;

import com.a8.qdm.query.dao.bean.PayOrder;

/**
 * 支付与订单关联Dao
 * 
 * @author Lund
 * 
 */
public interface PayOrderDao {

	/**
	 * 添加支付与订单关联关系
	 * 
	 * @param payOrder
	 *            支付与订单关联类
	 * @throws Exception
	 */
	void addPayOrder(PayOrder payOrder) throws Exception;
}
