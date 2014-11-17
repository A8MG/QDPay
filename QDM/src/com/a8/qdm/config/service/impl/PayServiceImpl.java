package com.a8.qdm.config.service.impl;

import java.util.List;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.PayDao;
import com.a8.qdm.config.dao.bean.Pay;
import com.a8.qdm.config.service.PayService;

/**
 * 支付方式Service实现
 * 
 * @author lund
 *
 */
public class PayServiceImpl implements PayService {

	/**
	 * 注入payDao
	 */
	private PayDao payDao;

	/**
	 * 查询所有支付方式
	 * 
	 * @return 支付方式集合
	 * @throws Exception
	 */
	public List<Pay> queryAllPay() throws Exception {
		return payDao.queryAllPay();
	}

	/**
	 * 根据ID查询支付方式
	 * 
	 * @param payId
	 *            ID
	 * @return 支付方式
	 * @throws Exception
	 */
	public Pay queryPayById(String payId) throws Exception {
		return payDao.queryPayById(payId);
	}

	/**
	 * 查询支付方式数量
	 * 
	 * @param search
	 *            查询条件
	 * @return 数量
	 * @throws Exception
	 */
	public int queryPayCount(Pay search) throws Exception {
		return payDao.queryPayCount(search);
	}

	/**
	 * 查询所有支付方式
	 * 
	 * @param search
	 *            查询条件
	 * @param page
	 *            分页
	 * @return 支付方式集合
	 * @throws Exception
	 */
	public List<Pay> queryPayList(Pay search, Page page) throws Exception {
		return payDao.queryPayList(search, page);
	}

	/**
	 * 添加支付方式
	 * 
	 * @param pay
	 *            支付方式类
	 * @throws Exception
	 */
	public void addPay(Pay pay) throws Exception {
		payDao.addPay(pay);
	}

	/**
	 * 修改支付方式
	 * 
	 * @param pay
	 *            支付方式类
	 * @throws Exception
	 */
	public void updatePay(Pay pay) throws Exception {
		payDao.updatePay(pay);
	}

	/**
	 * 删除支付方式
	 * 
	 * @param payId
	 *            支付ID数组
	 * @throws Exception
	 */
	public void deletePay(String[] payId) throws Exception {
		payDao.deletePay(payId);
	}

	public PayDao getPayDao() {
		return payDao;
	}

	public void setPayDao(PayDao payDao) {
		this.payDao = payDao;
	}
}
