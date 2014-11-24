package com.a8.qdm.query.dao;

import java.util.Map;

import com.a8.qdm.query.action.bean.ActiveWebBean;

/**
 * 用户行为Dao
 * 
 * @author lund
 *
 */
public interface ActiveDao {

	/**
	 * 查询渠道用户数据
	 * 
	 * @param map
	 *            查询条件
	 * @return
	 * @throws Exception
	 */
	ActiveWebBean queryActiveNo(Map<String, String> map) throws Exception;
}
