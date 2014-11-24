package com.a8.qdm.config.dao;

import java.util.List;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.bean.Pay;

/**
 * 支付方式Dao
 * 
 * @author Lund
 * 
 */
public interface PayDao {

	/**
	 * 查询所有支付方式
	 * 
	 * @return 支付方式集合
	 * @throws Exception
	 */
	List<Pay> queryAllPay() throws Exception;

	/**
	 * 根据ID查询支付方式
	 * 
	 * @param payId
	 *            ID
	 * @return 支付方式
	 * @throws Exception
	 */
	Pay queryPayById(String payId) throws Exception;

	/**
	 * 查询支付方式数量
	 * 
	 * @param search
	 *            查询条件
	 * @return 数量
	 * @throws Exception
	 */
	int queryPayCount(Pay search) throws Exception;

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
	List<Pay> queryPayList(Pay search, Page page) throws Exception;

	/**
	 * 添加支付方式
	 * 
	 * @param pay
	 *            支付方式类
	 * @throws Exception
	 */
	void addPay(Pay pay) throws Exception;

	/**
	 * 修改支付方式
	 * 
	 * @param pay
	 *            支付方式类
	 * @throws Exception
	 */
	void updatePay(Pay pay) throws Exception;

	/**
	 * 删除支付方式
	 * 
	 * @param payId
	 *            支付ID数组
	 * @throws Exception
	 */
	void deletePay(String[] payId) throws Exception;
}
