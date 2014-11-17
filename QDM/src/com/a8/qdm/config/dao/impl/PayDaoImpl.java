package com.a8.qdm.config.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.PayDao;
import com.a8.qdm.config.dao.bean.Pay;

/**
 * 支付方式Dao实现
 * 
 * @author lund
 *
 */
public class PayDaoImpl extends SqlSessionDaoSupport implements PayDao {

	/**
	 * 查询所有支付方式
	 * 
	 * @return 支付方式集合
	 * @throws Exception
	 */
	public List<Pay> queryAllPay() throws Exception {
		return this.getSqlSession().selectList("queryAllPay");
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
		return this.getSqlSession().selectOne("queryPayById", payId);
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
		return this.getSqlSession().selectOne("queryPayCount", search);
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("payId", search.getPayId());
		map.put("payName", search.getPayName());
		map.put("startIndex", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		return this.getSqlSession().selectList("queryPayList", map);
	}

	/**
	 * 添加支付方式
	 * 
	 * @param pay
	 *            支付方式类
	 * @throws Exception
	 */
	public void addPay(Pay pay) throws Exception {
		this.getSqlSession().insert("addPay", pay);
	}

	/**
	 * 修改支付方式
	 * 
	 * @param pay
	 *            支付方式类
	 * @throws Exception
	 */
	public void updatePay(Pay pay) throws Exception {
		this.getSqlSession().update("updatePay", pay);
	}

	/**
	 * 删除支付方式
	 * 
	 * @param payId
	 *            支付ID数组
	 * @throws Exception
	 */
	public void deletePay(String[] payId) throws Exception {
		this.getSqlSession().delete("deletePay", payId);
	}
}
