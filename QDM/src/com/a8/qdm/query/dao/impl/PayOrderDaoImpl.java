package com.a8.qdm.query.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.a8.qdm.query.dao.PayOrderDao;
import com.a8.qdm.query.dao.bean.PayOrder;

/**
 * 支付与订单关联Dao实现
 * 
 * @author Lund
 * 
 */
public class PayOrderDaoImpl extends SqlSessionDaoSupport implements
		PayOrderDao {

	/**
	 * 添加支付与订单关联关系
	 * 
	 * @param payOrder
	 *            支付与订单关联类
	 * @throws Exception
	 */
	public void addPayOrder(PayOrder payOrder) throws Exception {
		this.getSqlSession().insert("addPayOrder", payOrder);
	}

}
